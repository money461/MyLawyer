package com.tianzhi.shop520.entity.personal;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by thinkpad on 2017/11/2.
 * 我的推荐
 */

public class MyRecommend implements Serializable {
    public String loveTotal;
    public String loveSurplus;
    public String gradeOne;
    public String gradeTwo;
    public String status;

    public String getLoveTotal() {
        if(TextUtils.isEmpty(loveTotal)){
            return "0";
        }else
        return loveTotal;
    }

    public void setLoveTotal(String loveTotal) {
        this.loveTotal = loveTotal;
    }

    public String getLoveSurplus() {
        if(TextUtils.isEmpty(loveSurplus)){
            return "0";
        }else
        return loveSurplus;
    }

    public void setLoveSurplus(String loveSurplus) {
        this.loveSurplus = loveSurplus;
    }

    public String getGradeOne() {
        if(TextUtils.isEmpty(gradeOne)){
            return "0";
        }else
        return gradeOne;
    }

    public void setGradeOne(String gradeOne) {
        this.gradeOne = gradeOne;
    }

    public String getGradeTwo() {
        if(TextUtils.isEmpty(gradeTwo)){
            return "0";
        }else
        return gradeTwo;
    }

    public void setGradeTwo(String gradeTwo) {
        this.gradeTwo = gradeTwo;
    }

    public String getStatus() {
            return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MyRecommend{" +
                "loveTotal='" + loveTotal + '\'' +
                ", loveSurplus='" + loveSurplus + '\'' +
                ", gradeOne='" + gradeOne + '\'' +
                ", gradeTwo='" + gradeTwo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
