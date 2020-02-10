package com.tianzhi.shop520.entity;

import java.io.Serializable;

/**
 * Created by wisdomnt on 2017/3/9.
 */
public class BaseResponse<T> implements Serializable{
    private static final long serialVersionUID = 5213230387175987834L;

    public String flag;
    public String msg;
    public T data;
}
