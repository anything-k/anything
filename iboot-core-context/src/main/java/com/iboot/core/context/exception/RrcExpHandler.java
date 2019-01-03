package com.iboot.core.context.exception;

import com.iboot.core.context.exception.util.ExceptionUtil;
import com.iboot.core.context.rest.ReturnValue;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常拦截
 *
 * @author FanMingxin
 * @date  2018-05-31 下午 09:20
 */
@Log4j2
@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class RrcExpHandler implements ErrorController {

    private static final int NOT_FIND = 404;

    @Autowired
    private ReturnValue returnValue;

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping
    public Object error(HttpServletResponse response, HttpServletRequest request) {
        Throwable th = getException(request);
        if(th != null){
            return returnValue.error(ExceptionUtil.getSimpleMessage(th));
        }

        HttpStatus status = getStatus(request);
        if(status.value() != NOT_FIND){
            log.error("REQUEST-STATUS:"+status.value() + ";REQUEST-URL:" + getRequestURI(request));
        }
        logExceptionInfo(request);
        return returnValue.error(status.value(),getErrorMessage(request,status));
    }

    /**
     * 获取异常对象
     *
     * @param request
     * @return
     */
    protected Throwable getException(HttpServletRequest request){
        Object exception = request.getAttribute("javax.servlet.error.exception");
        if(exception == null){
            return null;
        }

        if(exception instanceof Throwable){
            Throwable ex = (Throwable)exception;
            log.error(ex.getMessage(),ex);
            return ex;
        }

        return null;
    }

    /**
     * 记录异常信息
     *
     * @param request
     */
    protected void logExceptionInfo(HttpServletRequest request){
        Object exception = request.getAttribute("org.springframework.web.servlet.DispatcherServlet.EXCEPTION");
        if(exception == null){
            return;
        }
        log.error(exception);
    }

    /**
     * 获取请求url
     *
     * @param request
     * @return
     */
    protected String getRequestURI(HttpServletRequest request){
        Object uri = request.getAttribute("javax.servlet.error.request_uri");
        if(uri == null){
            return "";
        }
        return uri.toString();
    }

    /**
     * 获取错误描述信息
     *
     * @param request
     * @param status
     * @return
     */
    protected String getErrorMessage(HttpServletRequest request, HttpStatus status){
        Object message = request.getAttribute("javax.servlet.error.message");
        if(message == null || "".equals(message.toString())){
            return status.getReasonPhrase();
        }
        return message.toString();
    }

    /**
     * 获取状态
     *
     * @param request
     * @return
     */
    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        }catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}