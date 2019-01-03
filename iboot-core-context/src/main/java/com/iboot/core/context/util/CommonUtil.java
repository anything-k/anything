package com.iboot.core.context.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * @Author:FanMingxin
 * @Date: 2018-06-23 下午 05:49
 * @Description:通用工具类
 */
public class CommonUtil {

    /**
     * 判空
     *
     * @param obj
     * @return
     */
    public final static boolean isEmpty(final Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            return "".equals(String.valueOf(obj).trim());
        }
        if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        }
        if (obj instanceof Collection<?>) {
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Map<?, ?>) {
            return ((Map<?, ?>) obj).isEmpty();
        }
        if (obj instanceof Optional<?>) {
            Optional<?> result = (Optional<?>) obj;
            return !result.isPresent();
        }

        return false;
    }

    /**
     *
     * @param num
     * @return
     */
    public final static boolean isEmpty(final Integer num) {
        if(num == null || num <= 0){
            return true;
        }

        return false;
    }

    /**
     *
     * @param num
     * @return
     */
    public final static boolean isEmpty(final Long num) {
        if(num == null || num <= 0){
            return true;
        }

        return false;
    }
}
