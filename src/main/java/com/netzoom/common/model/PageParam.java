package com.netzoom.common.model;

import com.netzoom.common.annotation.FieldName;

import javax.validation.constraints.Min;

/**
 * 分页传参实体
 * @author luje
 * */
public class PageParam<T> {

    @Min(1)
    @FieldName(value = "当前页码",comment = "当前页码 默认第1页")
    private Integer pageNum = 1;

    @Min(1)
    @FieldName(value = "每页显示条数",comment = "每页显示几条数据 默认10条")
    private Integer pageSize = 10;

    @FieldName(value = "排序类型",comment = "排序的类型，以该字段作为排序依据")
    private String orderType ;

    @FieldName(value = "排序方向",comment = "排序方向，asc为正序、desc为倒序 ，默认正序")
    private String orderMethod = "ASC" ;

    @FieldName(value = "搜索条件",comment = "搜索条件，根据实体类的属性作为搜索条件")
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
