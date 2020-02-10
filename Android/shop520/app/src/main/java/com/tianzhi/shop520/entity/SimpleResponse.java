package com.tianzhi.shop520.entity;

import java.io.Serializable;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：16/9/28
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class SimpleResponse implements Serializable {

    private static final long serialVersionUID = -1477609349345966116L;

    public String flag;
    public String msg;

    public com.tianzhi.shop520.entity.BaseResponse toLzyResponse() {
        com.tianzhi.shop520.entity.BaseResponse baseResponse = new BaseResponse();
        baseResponse.flag = flag;
        baseResponse.msg = msg;
        return baseResponse;
    }

    @Override
    public String toString() {
        return "SimpleResponse{" +
                "flag='" + flag + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}