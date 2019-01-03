package com.iboot.core.context.rest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.iboot.core.context.exception.impl.BusinessException;
import com.iboot.core.context.exception.util.ExceptionUtil;
import lombok.extern.log4j.Log4j2;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author:FanMingxin
 * @Date: 2018/7/5 17:04
 * @Description:
 */
@Log4j2
public class RpcResultUtil {

    private static final String REST_CODE_PHP = "status";
    private static final String REST_MESSAGE_PHP = "msg";

    private static final String REST_CODE_JAVA = "code";
    private static final String REST_MESSAGE_JAVA = "message";

    private static final String REST_ERROR = "error";

    private static final String REST_DATA = "data";

    private static final String REST_COUNT_PHP = "count";

    private static final int SUCCESS = 0;

    private static final int SUCCESS_OTHER = 200;

    /**
     * 是否成功
     *
     * @param result
     * @return
     */
    public static boolean success(JSONObject result) throws BusinessException {
        log.info("RPC-Result:"+ result.toJSONString());
        Integer code = getCode(result);

        if(code != null && (code.intValue() == SUCCESS || code.intValue() == SUCCESS_OTHER)){
            return true;
        }else{
            throw ExceptionUtil.ex(getMessage(result));
        }
    }

    public static <T> T getOne(JSONObject result, Class<T> clzz){
        Object data = result.get(REST_DATA);
        if(data == null){
            return null;
        }
        if(data instanceof JSONObject || data instanceof Map){
            JSONObject jdata = result.getJSONObject(REST_DATA);
            if(jdata == null || jdata.keySet().size() == 0){
                return null;
            }

            if(jdata.containsKey(REST_COUNT_PHP)){
                for (String item : jdata.keySet()) {
                    if (REST_COUNT_PHP.equals(item)) {
                        continue;
                    }
                    JSONObject value = jdata.getJSONObject(item);
                    return value.toJavaObject(clzz);
                }
                return null;
            }else{
                return jdata.toJavaObject(clzz);
            }
        }else if(data instanceof JSONArray || data instanceof Collection){
            JSONArray jsonArray = result.getJSONArray(REST_DATA);
            if(jsonArray == null || jsonArray.size() == 0){
                return null;
            }
            JSONObject json = jsonArray.getJSONObject(0);
            if(json == null){
                return null;
            }
            return json.toJavaObject(clzz);
        }else if(data instanceof Integer && clzz.getName().equals(Integer.class.getName())){
            return (T) data;
        }else if(data instanceof Long && clzz.getName().equals(Long.class.getName())){
            return (T) data;
        }else if(data instanceof String && clzz.getName().equals(String.class.getName())){
            return (T) data;
        }else if(data instanceof Double && clzz.getName().equals(Double.class.getName())){
            return (T) data;
        }else{
            log.error("结果解析失败,对象类型不匹配。RPC Result:"+ result.toJSONString()+";类型：" + clzz);
            throw ExceptionUtil.ex("结果解析失败,对象类型不匹配");
        }
    }

    public static String getData4Str(JSONObject result){
        Object data = result.get(REST_DATA);
        if(data instanceof JSONObject || data instanceof Map){
            JSONObject json = result.getJSONObject(REST_DATA);
            if(json == null){
                return "";
            }
            return json.toJSONString();
        }else if(data instanceof JSONArray || data instanceof Collection){
            JSONArray jsonArray = result.getJSONArray(REST_DATA);
            if(jsonArray == null || jsonArray.size() == 0){
                return "";
            }
            return jsonArray.toJSONString();
        }else{
            return String.valueOf(data);
        }
    }

    public static Object getData(JSONObject result){
        return result.get(REST_DATA);
    }

    public static <T> List<T> getList(JSONObject result, Class<T> clzz){
        List<T> list = Lists.newArrayList();
        Object data = result.get(REST_DATA);
        if(data == null){
            return list;
        }
        if(data instanceof JSONArray || data instanceof Collection){
            JSONArray jsonArray = result.getJSONArray(REST_DATA);
            if(jsonArray == null || jsonArray.size() == 0){
                return list;
            }
            return jsonArray.toJavaList(clzz);
        }else if(data instanceof JSONObject || data instanceof Map){
            JSONObject jdata = result.getJSONObject(REST_DATA);
            if(jdata == null || jdata.keySet().size() == 0){
                return list;
            }

            if(jdata.containsKey(REST_DATA)){
                Object dataItem = jdata.get(REST_DATA);
                if(dataItem instanceof JSONArray || dataItem instanceof Collection){
                    JSONArray jsonArray = jdata.getJSONArray(REST_DATA);
                    if(jsonArray == null || jsonArray.size() == 0){
                        return list;
                    }
                    return jsonArray.toJavaList(clzz);
                }
            }

            if(jdata.containsKey(REST_COUNT_PHP)){
                for (String item : jdata.keySet()) {
                    if (REST_COUNT_PHP.equals(item)) {
                        continue;
                    }
                    JSONObject value = jdata.getJSONObject(item);
                    list.add(value.toJavaObject(clzz));
                }
                return list;
            }

            list.add(jdata.toJavaObject(clzz));
            return list;
        }else{
            log.error("结果解析失败,对象类型不匹配。RPC Result:"+ result.toJSONString()+";类型：" + clzz);
            throw ExceptionUtil.ex("结果解析失败,对象类型不匹配");
        }
    }

    public static Integer getCode(JSONObject result){
        if(result == null){
            return 0;
        }

        if(result.containsKey(REST_CODE_JAVA)){
            return result.getInteger(REST_CODE_JAVA);
        }

        if(result.containsKey(REST_CODE_PHP)){
            return result.getInteger(REST_CODE_PHP);
        }

        return 0;
    }

    public static String getMessage(JSONObject result){
        if(result == null){
            return null;
        }

        if(result.containsKey(REST_MESSAGE_JAVA)){
            return result.getString(REST_MESSAGE_JAVA);
        }

        if(result.containsKey(REST_MESSAGE_PHP)){
            return result.getString(REST_MESSAGE_PHP);
        }

        return null;
    }

    public static String getError(JSONObject result){
        if(result == null){
            return "";
        }

        if(result.containsKey(REST_ERROR)){
            return result.getString(REST_ERROR);
        }

        return "";
    }
}
