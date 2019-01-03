package com.iboot.core.context.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/12/5 16:06
 */
public class RestAspetUtil {

    /**
     * 获取切点方法
     *
     * @param joinPoint
     * @return
     */
    public static Method getMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return methodSignature.getMethod();
    }

    /**
     * 是否是void
     *
     * @param pjp
     * @return
     */
    public static boolean isReturnVoid(ProceedingJoinPoint pjp) {
        Method method = getMethod(pjp);
        Class<?> returnType = method.getReturnType();
        return "void".equals(returnType.getName());
    }

    public static void write(String result) throws IOException {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();
    }
}
