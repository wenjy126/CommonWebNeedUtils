package com.netzoom.common.model;

import java.io.Serializable;

/**
 * @Copyright (C), 2019, 广州力生信息科技有限公司
 * @FileName: HttpClientResult
 * @Author: chenhl
 * @Date: 2019/9/9 0009 16:44
 * @Description: 响应结果封装类
 */
public class HttpClientResult implements Serializable {

    /**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String content;

    public HttpClientResult() {
    }

    public HttpClientResult(int code) {
        this.code = code;
    }

    public HttpClientResult(int code, String content) {
        this.code = code;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
