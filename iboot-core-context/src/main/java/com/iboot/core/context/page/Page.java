package com.iboot.core.context.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * 分页对象
 *
 * @Author:FanMingxin
 * @Date: 2018-06-04 下午 07:39
 */
@Data
public class Page {
    private int currentPage = 1;
    private long totalRecords;
    private int totalPages;
    private int pageSize;

    private Object list = new Object();

    @JsonIgnore
    private com.baomidou.mybatisplus.plugins.Page pagination;

    public Page() {}

    public Page(Integer currentPage, Integer pageSize) {
        if(currentPage != null){
            this.currentPage = currentPage;
        }

        if(pageSize != null){
            this.pageSize = pageSize;
        }
    }

    public com.baomidou.mybatisplus.plugins.Page toPagination(){
        this.pagination = new com.baomidou.mybatisplus.plugins.Page(this.currentPage,this.pageSize);
        return this.pagination;
    }

    public void setRecords(Object dataList){
        this.list = dataList;
        setPageField();
    }

    private void setPageField(){
        if(this.pagination == null){
            return;
        }
        this.totalRecords = this.pagination.getTotal();
        this.totalPages = this.pagination.getPages();
    }
}

