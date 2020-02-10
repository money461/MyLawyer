package com.tianzhi.shop520.entity.shop;

/**
 * Created by thinkpad on 2017/10/24.
 */

public class AddressManageEntity {
    private Class<?> activity;
    private String phone;
    private String name;
    private String address;
    private boolean isdefaul;

    public Class<?> getActivity() {
        return activity;
    }

    public void setActivity(Class<?> activity) {
        this.activity = activity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isdefaul() {
        return isdefaul;
    }

    public void setIsdefaul(boolean isdefaul) {
        this.isdefaul = isdefaul;
    }
}
