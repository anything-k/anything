package com.anything.boot.feign.config;

import com.alibaba.fastjson.JSONObject;
import com.iboot.core.context.rest.RpcResultUtil;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * TODO
 *
 * @author FanMingxin
 * @date 2018/12/11 12:02
 */
public class MyDecoder implements Decoder {

    private Decoder decoder;

    public MyDecoder(Decoder decoder){
        this.decoder = decoder;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        JSONObject result =(JSONObject)decoder.decode(response, JSONObject.class);
        if(RpcResultUtil.success(result)){
            return result.getJSONObject("data").toJavaObject(type);
        }

        return result.toJavaObject(type);
    }
}
