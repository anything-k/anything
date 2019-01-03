package com.iboot.core.context.aop;

import com.iboot.core.context.page.Page;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

/**
 * 分页切面
 *
 * @Author:FanMingxin
 * @Date: 2018-06-04 下午 19:28
 */
@Component
@Aspect
@Order(3)
public class PageAspect {

	@Value("${page.pageSize:10}")
	private String pageSize;

	/**
	 * 填充分页信息
	 *
	 * @param pjp 切点
	 * @return 分页格式的数据
	 * @throws
	*/
	@Around("@annotation(com.iboot.core.context.annotation.Pagination)")
	@Order(2)
	public Object setPageInfo(final ProceedingJoinPoint pjp) throws Throwable {
		Object list = pjp.proceed();
		if(list instanceof Page){
			return list;
		}

		Page page = getPage(pjp.getArgs());
		if (page == null) {
			page = new Page();
		}

		page.setRecords(list);
		return page;
	}

	/**
	 * 设置分页默认值
	 * <p>
	 * 如果分页没有设置值，则默认从系统的配置文件里读取
	 *
	 * @param jp 切点
	*/
	@Before(value = "@annotation(com.iboot.core.context.annotation.Pagination)")
	@Order(1)
	public void setDefaultPageSize(final JoinPoint jp) {
		Page page = getPage(jp.getArgs());
		if (page == null || page.getPageSize() > 0) {
			return;
		}
		page.setPageSize(pageSize==null?0:Integer.parseInt(pageSize));
	}

	/**
	 * 获取分页对象
	 *
	 * @param args 参数
	 * @return 分页对象。如果参数里没有分页对象，则返回null
	*/
	private Page getPage(final Object[] args) {
		Optional<Object> page = Arrays.stream(args).filter(arg -> arg instanceof Page).findFirst();
		if (!page.isPresent()) {
			return null;
		}
		return (Page) page.get();
	}
}
