package com.iboot.core.context.exception;

import com.iboot.core.context.exception.impl.BusinessException;
import com.iboot.core.context.exception.impl.RpcException;
import com.iboot.core.context.exception.impl.WarningException;
import com.iboot.core.context.exception.util.ExceptionUtil;
import com.iboot.core.context.rest.ReturnValue;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 异常处理
 *
 * @author FanMingxin
 * @date 2018-06-01 下午 04:19
 */
@Log4j2
@RestControllerAdvice
public class RrcRestAdvice {

    @Autowired
    private ReturnValue returnValue;

    @ExceptionHandler(Exception.class)
    public Object resolveAndWrite(Exception ex){
        String exMsg = getExMessage(ex);
        //
        if(ex instanceof WarningException){
            log.warn(exMsg);

            WarningException warnEx = (WarningException)ex;
            return returnValue.warn(warnEx.getCode(),exMsg);
        }

        if(ex instanceof BusinessException){
            log.error(exMsg,ex);

            BusinessException bEx = (BusinessException)ex;
            return returnValue.error(bEx.getCode(),exMsg);
        }

        //TODO 细化http请求异常
        if(ex instanceof RpcException){
            log.error(exMsg,ex);

            RpcException rpcEx = (RpcException)ex;
            return returnValue.error(rpcEx.getCode(),exMsg);
        }

        log.error(exMsg,ex);
        return returnValue.error(exMsg);
    }

    @ExceptionHandler(BindException.class)
    public Object handleBindException(BindException ex) {
        // ex.getFieldError():随机返回一个对象属性的异常信息。如果要一次性返回所有对象属性异常信息，则调用ex.getAllErrors()
        FieldError fieldError = ex.getFieldError();
        StringBuilder sb = new StringBuilder();
        //sb.append(fieldError.getField()).append("=[").append(fieldError.getRejectedValue()).append("]")
        sb.append(fieldError.getDefaultMessage());

        return returnValue.error(400,sb.toString());
    }

    /**
     * 获取异常描述信息
     *
     * @param ex
     * @return java.lang.String
     * @throws
     */
    private String getExMessage(Exception ex){

        if(ex instanceof HttpRequestMethodNotSupportedException){
            return "不支持请求方法类型";
        }

        if(ex instanceof MethodArgumentTypeMismatchException){
            return "参数类型不匹配";
        }

        if(ex instanceof MissingServletRequestParameterException){
            return "缺少请求参数";
        }

        if(ex instanceof HttpMediaTypeException){
            return "请求体协议不符合";
        }

        return ExceptionUtil.getSimpleMessage(ex);
    }

}

