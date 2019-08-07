package com.netzoom.common.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.netzoom.common.model.PageParam;

/**
 * 分页插件方法封装工具包
 * @author luje
 */
public class PageHelperUtils {

    /**
     *
     * @param pageParam 分页对象
     * @param count 是否开启统计总数、页数功能
     * @param defaultSort 自定义默认排序字段,如果不传该参数，并且分页对象也未指定排序字段，则按数据库的默认排序
     */
    public static Page startPage(PageParam pageParam , boolean count , String defaultSort ){
        if (pageParam.getOrderType() == null || "".equals(pageParam.getOrderType())) {
            PageHelper.orderBy(defaultSort);
        }
        return PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize() , count);
    }

    public static Page startPage(PageParam pageParam , boolean count ){
        return PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize() , count);
    }

}
