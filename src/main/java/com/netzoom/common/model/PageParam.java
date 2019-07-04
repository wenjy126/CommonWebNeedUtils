package com.netzoom.common.model;

import javax.validation.constraints.Min;

/**
 * 分页传参实体
 * @author luje
 * */
public class PageParam<T> {

    /** 当前页码 默认第1页 */
    @Min(1)
    private Integer pageNum = 1;

    /** 每页显示几条数据 默认10条*/
    @Min(1)
    private Integer pageSize = 10;

    /** 排序的类型，以该字段作为排序依据*/
    private String orderType ;

    /** 排序方向，asc为正序、desc为倒序 ，默认正序*/
    private String orderMethod = "ASC" ;

    /** 搜索条件，根据实体类的属性作为搜索条件*/
    private T params ;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderMethod() {
        return orderMethod;
    }

    public void setOrderMethod(String orderMethod) {
        this.orderMethod = orderMethod;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }
}
