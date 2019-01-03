package com.iboot.core.context.exception.impl;

import com.iboot.core.context.exception.IBusinessException;

/**
 * @author:FanMingxin
 * @Date: 2018-06-01 下午 06:54
 * @Description:
 */
public class BusinessException extends RuntimeException implements IBusinessException {

    private static final long serialVersionUID = 4841002170027340733L;

    private int code = -1;

    public BusinessException() {
        super();
    }

    public BusinessException(final String message) {
        super(message);
    }

    public BusinessException(int code,final String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BusinessException(int code,final String message, final Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(final Throwable cause) {
        super(cause);
    }

    @Override
    public int getCode(){
        return this.code;
    }
}
