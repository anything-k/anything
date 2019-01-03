package com.iboot.core.context.rest;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author FanMingxin
 * @date 2018-12-05 15:19
 */
@Service
public class ReturnValue {

	@Value("${rest.app:false}")
	private Boolean app;

	/**
	 * 成功信息格式化
	 *
	 * @param data
	 * @return
	 */
	public Map<String, Object> data(final Object data) {
		return success("ok",data);
	}

	public Map<String, Object> success(final String message) {
		return success(message,null);
	}

	public Map<String, Object> success(final String message,Object data) {
		return success(StatusCode.SUCCESS.key(),message,data);
	}

	public Map<String, Object> success(int code,String message,final Object data) {
		Map<String, Object> map = Maps.newLinkedHashMap();
		map.put(getStatusField(), code);
		if(message == null){
			message = "ok";
		}
		map.put(getMessage(), message);
		if(data != null){
			map.put(RestFieldEnum.DATA.key(), data);
		}

		return map;
	}

	/**
	 * 错误信息格式化
	 *
	 * @param message
	 * @return
	 */
	public Map<String, Object> error(final String message) {
		return error(StatusCode.FAIL.key(),message);
	}

	public Map<String, Object> error(int code,final String message) {
		return error(code,message,null);
	}

	public Map<String, Object> error(int code,final String message,Object obj) {
		Map<String, Object> map = Maps.newLinkedHashMap();
		map.put(getStatusField(), code);
		map.put(getMessage(), message);
		if(obj != null){
			map.put("error",obj);
		}
		return map;
	}

	/**
	 * 警告提醒信息格式化
	 *
	 * @param message
	 * @return
	 */
	public Map<String, Object> warn(final String message) {
		return warn(StatusCode.VALIDATE_FAIL.key(),message);
	}

	public Map<String, Object> warn(int code,final String message) {
		Map<String, Object> map = Maps.newLinkedHashMap();
		map.put(getStatusField(), code);
		map.put(getMessage(), message);
		return map;
	}

	public Map<String, Object> pageResult(long total,final Object data,int currentPage,int pageSize) {
		Map<String, Object> map = Maps.newLinkedHashMap();
		map.put(getStatusField(), StatusCode.SUCCESS.key());
		map.put(getMessage(), "ok");
		map.put(RestFieldEnum.TOTAL.key(), total);
		map.put(RestFieldEnum.DATA.key(), data);
		map.put("page",currentPage);
		map.put("limit",pageSize);
		return map;
	}

	private String getStatusField(){
		if(app){
			return RestFieldEnum.STATUS.key();
		}

		return RestFieldEnum.CODE.key();
	}

	private String getMessage(){
		if(app){
			return RestFieldEnum.ERROR_MESSAGE.key();
		}

		return RestFieldEnum.MESSAGE.key();
	}
}