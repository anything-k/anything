package com.iboot.core.context.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iboot.core.context.annotation.RestStatus;
import com.iboot.core.context.rest.RestFieldEnum;
import com.iboot.core.context.rest.ReturnValue;
import com.iboot.core.context.rest.StatusCode;
import com.iboot.core.context.util.RestAspetUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 替换code为status规则
 *
 * @author FanMingxin
 * @date 2018-05-09 下午 02:28
 */
@Component
@Aspect
@Order(2)
public class RestStatusAspect {

	@Autowired
	private ReturnValue returnValue;

	@Around(value = "@annotation(com.iboot.core.context.annotation.RestStatus)")
	@Order(1)
	public Object warp(final ProceedingJoinPoint pjp) throws Throwable {
		Object obj = pjp.proceed();

		if (RestAspetUtil.isReturnVoid(pjp)) {
			RestAspetUtil.write(JSON.toJSONString(returnValue.success(getValue(pjp))));
			return obj;
		}

		return parseResult(pjp,obj);
	}

	/**
	 * 结果解析
	 *
	 * @param pjp
	 * @param result
	 * @return
	 */
	private Object parseResult(ProceedingJoinPoint pjp, Object result){
		if(result == null){
			return returnValue.success(getValue(pjp));
		}

		if(result instanceof JSONObject || result instanceof Map){
			JSONObject json = JSON.parseObject(JSON.toJSONString(result));
			return handle(pjp,json);
		}

		if(result instanceof String){
			JSONObject json;
			try {
				json = JSON.parseObject(String.valueOf(result));
			}catch(Exception e){
				return returnValue.success(getValue(pjp),result);
			}

			if(json != null){
				return handle(pjp,json);
			}

			return returnValue.success(getValue(pjp),result);
		}

		JSONObject restJson;
		try {
			restJson = JSON.parseObject(JSON.toJSONString(result));
		}catch(Exception e){
			return returnValue.success(getValue(pjp),result);
		}


		boolean restObj = (restJson.containsKey(RestFieldEnum.CODE.key()) || restJson.containsKey(RestFieldEnum.STATUS.key()))
				&& (restJson.containsKey(RestFieldEnum.MESSAGE.key()) || restJson.containsKey(RestFieldEnum.ERROR_MESSAGE.key()));

		if(restObj){
			Integer code;
			if(restJson.containsKey(RestFieldEnum.CODE.key())){
				code = restJson.getInteger(RestFieldEnum.CODE.key());
			} else {
				code = restJson.getInteger(RestFieldEnum.STATUS.key());
			}

			Object msg;
			if(restJson.containsKey(RestFieldEnum.MESSAGE.key())){
				msg = restJson.get(RestFieldEnum.MESSAGE.key());
			} else {
				msg = restJson.get(RestFieldEnum.ERROR_MESSAGE.key());
			}

			if(code != null){
				if(StatusCode.SUCCESS.key() == code){
					return returnValue.success(getValue(pjp),restJson.get(RestFieldEnum.DATA.key()));
				}else{
					return returnValue.error(Integer.parseInt(String.valueOf(code)),String.valueOf(msg));
				}
			}
		}

		return returnValue.success(getValue(pjp),result);
	}

	private Object handle(ProceedingJoinPoint pjp, JSONObject json){
		if(json.containsKey(RestFieldEnum.CODE.key()) && json.containsKey(RestFieldEnum.MESSAGE.key())){
			Integer code = json.getInteger(RestFieldEnum.CODE.key());
			if(code == StatusCode.SUCCESS.key()){
				return returnValue.success(getValue(pjp),json.get(RestFieldEnum.DATA.key()));
			}else{
				String errorMsg = json.getString(RestFieldEnum.MESSAGE.key());
				return returnValue.error(code,errorMsg);
			}
		}

		if(json.containsKey(RestFieldEnum.STATUS.key()) && json.containsKey(RestFieldEnum.MESSAGE.key())){
			Integer code = json.getInteger(RestFieldEnum.STATUS.key());
			if(code == StatusCode.SUCCESS.key()){
				return returnValue.success(getValue(pjp),json.get(RestFieldEnum.DATA.key()));
			}else{
				String errorMsg = json.getString(RestFieldEnum.MESSAGE.key());
				return returnValue.error(code,errorMsg);
			}
		}

		return returnValue.success(getValue(pjp),json);
	}

	/**
	 * 获取value属性值
	 *
	 * @param pjp
	 * @return
	 */
	private String getValue(ProceedingJoinPoint pjp) {
		Method method = RestAspetUtil.getMethod(pjp);
		RestStatus rc = method.getAnnotation(RestStatus.class);
		return rc.value();
	}
}