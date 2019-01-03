package com.iboot.core.context.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DtoUtil {

    public static <D, E> D fromEntity(E entity, Class<D> clazz) {
        if (null == entity || null == clazz) {
            return null;
        }

        D dto = null;
        try {
            dto = clazz.newInstance();
            BeanUtils.copyProperties(entity,dto);
        } catch (Exception e) {
            log.error("bean属性转换异常", e);
        }
        return dto;
    }

    public static <D, E> List<D> fromEntity(List<E> entityList, Class<D> clazz) {
        if (null == entityList || null == clazz || entityList.isEmpty()) {
            return new ArrayList<>(0);
        }

        List<D> dtoList = new ArrayList<>();

        for (E entity : entityList) {
            dtoList.add(fromEntity(entity, clazz));
        }
        return dtoList;
    }

    @Deprecated
    public static <D, E> D fromEntity(E entity, D dto) {
        if (null == entity || null == dto) {
            return null;
        }
        try {
            dto = (D) dto.getClass().newInstance();
            BeanUtils.copyProperties(dto, entity);
        } catch (Exception e) {
            log.error("bean属性转换异常", e);
        }
        return dto;
    }

    @Deprecated
    public static <D, E> List<D> fromEntity(List<E> entityList, D dto) {
        if (null == entityList || null == dto || entityList.isEmpty()) {
            return new ArrayList<>(0);
        }

        List<D> dtoList = new ArrayList<>();

        for (E entity : entityList) {
            dtoList.add(fromEntity(entity, dto));
        }
        return dtoList;
    }

}
