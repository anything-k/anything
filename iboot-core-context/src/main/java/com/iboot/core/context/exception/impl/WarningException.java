package com.iboot.core.context.exception.impl;


import com.iboot.core.context.exception.IWarningException;

/**
 * @author FanMingxin
 * @date 2018-06-21 上午 11:55
 */
public class WarningException extends RuntimeException implements IWarningException {

    private static final long serialVersionUID = 1042448768831772372L;

    private int code = -2;

    public WarningException() {
        super();
    }

    public WarningException(final String message) {
        super(message);
    }

    public WarningException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public WarningException(int code,final String message) {
        super(message);
        this.code = code;
    }

    public WarningException(final Throwable cause) {
        super(cause);
    }

    @Override
    public int getCode(){
        return this.code;
    }
}
