package com.iboot.core.context.aop;

import com.alibaba.fastjson.JSON;
import com.iboot.core.context.annotation.RestCode;
import com.iboot.core.context.page.Page;
import com.iboot.core.context.rest.ReturnValue;
import com.iboot.core.context.util.RestAspetUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * rest接口返回值处理切面
 *
 * @author:FanMingxin
 * @Date: 2018-05-09 下午 02:28
 */
@Component
@Aspect
@Order(2)
public class RestCodeAspect {

	@Autowired
	private ReturnValue returnValue;

	@Around(value = "@annotation(com.iboot.core.context.annotation.RestCode)")
	@Order(1)
	public Object warp(final ProceedingJoinPoint pjp) throws Throwable {
		Object obj = pjp.proceed();

		if (RestAspetUtil.isReturnVoid(pjp)) {
			RestAspetUtil.write(JSON.toJSONString(returnValue.success(getValue(pjp))));
			return obj;
		}

		if(obj instanceof Page && isPagination(pjp)){
			Page page = (Page)obj;
			return returnValue.pageResult(page.getTotalRecords(),
					page.getList(),
					page.getCurrentPage(),
					page.getPageSize());
		}

		return returnValue.success(getValue(pjp),obj);
	}

	/**
	 * 是否包含Pagination注解
	 *
	 * @param pjp
	 * @return
	 */
	private boolean isPagination(ProceedingJoinPoint pjp) {
		Method method = RestAspetUtil.getMethod(pjp);
		RestCode rc = method.getAnnotation(RestCode.class);
		return rc.surface();
	}

	/**
	 * 获取value属性值
	 *
	 * @param pjp
	 * @return
	 */
	private String getValue(ProceedingJoinPoint pjp) {
		Method method = RestAspetUtil.getMethod(pjp);
		RestCode rc = method.getAnnotation(RestCode.class);
		return rc.value();
	}
}