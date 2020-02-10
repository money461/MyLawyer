package com.tianzhi.shop520.entity.personal;

import java.io.Serializable;

/**
 * Created by thinkpad on 2017/11/2.
 */

public class ConsumptionList implements Serializable{
    public String id;
    public String userId;
    public String consumeVal;//值
    public String createdTime;//时间
    public String status;//状态 0 减去，1 加
    public String name;//消费名字

    @Override
    public String toString() {
        return "ConsumptionList{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", consumeVal='" + consumeVal + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
