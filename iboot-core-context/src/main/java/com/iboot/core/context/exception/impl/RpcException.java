package com.iboot.core.context.exception.impl;

import com.iboot.core.context.exception.IRpcException;

/**
 * @Author:FanMingxin
 * @Date: 2018-06-21 上午 11:55
 * @Description:
 */
public class RpcException extends RuntimeException implements IRpcException {

    private static final long serialVersionUID = -3821547156716131034L;

    private int code = -1;

    public RpcException() {
        super();
    }

    public RpcException(final String message) {
        super(message);
    }

    public RpcException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RpcException(final int code,final String message, final Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public RpcException(final Throwable cause) {
        super(cause);
    }

    public int getCode(){
        return this.code;
    }
}
