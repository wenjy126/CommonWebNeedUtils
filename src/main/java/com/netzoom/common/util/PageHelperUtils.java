package com.netzoom.common.util;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.netzoom.common.exception.ValidOrderTypesException;
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
     * @param validOrderTypes 有效的分页排序字段，可变参数String
     * @return Page
     * @throws ValidOrderTypesException
     */
    public static Page startPage(PageParam pageParam , boolean count , String defaultSort , String... validOrderTypes) throws ValidOrderTypesException {
        if( !StringUtil.isEmpty( pageParam.getOrderType() ) && validOrderTypes != null  ){
            boolean valid = false;
            for( String validOrderType : validOrderTypes ){
                if(validOrderType.equals(pageParam.getOrderType())){
                    valid = true;
                    break;
                }
            }
            if( !valid ){
                throw new ValidOrderTypesException("分页排序字段无效");
            }
        }
        return startPage(pageParam,count,defaultSort);
    }

    public static Page startPage(PageParam pageParam , boolean count , String defaultSort ) throws ValidOrderTypesException {
        if( !pageParam.getOrderMethod().equalsIgnoreCase("ASC") && !pageParam.getOrderMethod().equalsIgnoreCase("DESC") ){
            throw new ValidOrderTypesException("分页排序顺序无效");
        }
        if ( StringUtil.isEmpty( pageParam.getOrderType() ) ) {
            PageHelper.orderBy(defaultSort);
        }else{
            PageHelper.orderBy(pageParam.getOrderType()+" "+pageParam.getOrderMethod());
        }
        return PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize() , count);
    }

    public static Page startPage(PageParam pageParam , boolean count ){
        return PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize() , count);
    }


}
