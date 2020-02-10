package com.tz.pojo;

public class ZaChatPricture {
    private Long id;

    private String hxMsgId;

    private String prictureUrl;

    private String extension;

    private Integer width;

    private Integer height;

    private Integer size;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHxMsgId() {
        return hxMsgId;
    }

    public void setHxMsgId(String hxMsgId) {
        this.hxMsgId = hxMsgId == null ? null : hxMsgId.trim();
    }

    public String getPrictureUrl() {
        return prictureUrl;
    }

    public void setPrictureUrl(String prictureUrl) {
        this.prictureUrl = prictureUrl == null ? null : prictureUrl.trim();
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension == null ? null : extension.trim();
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}