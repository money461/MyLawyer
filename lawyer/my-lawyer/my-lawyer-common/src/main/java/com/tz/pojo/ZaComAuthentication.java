package com.tz.pojo;

import java.util.Date;

public class ZaComAuthentication {
    private String id;

    private String userId;

    private String lawId;

    private String comNickname;

    private String comName;

    private String comManager;

    private String comPhone;

    private String comLegalPerson;

    private String legalPersonPhone;

    private String comIntroduce;

    private Integer comStatus;

    private Integer lawEnsure;

    private Integer state;

    private String comAddress;

    private Double comLon;

    private Double comLat;

    private String comLogo;

    private String licenceUrl;

    private String qualificationUrl;

    private String idCard;

    private Date createdTime;

    private Date examineTime;

    private Date updatedTime;

    private String examineId;

    private String operator;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getLawId() {
        return lawId;
    }

    public void setLawId(String lawId) {
        this.lawId = lawId == null ? null : lawId.trim();
    }

    public String getComNickname() {
        return comNickname;
    }

    public void setComNickname(String comNickname) {
        this.comNickname = comNickname == null ? null : comNickname.trim();
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName == null ? null : comName.trim();
    }

    public String getComManager() {
        return comManager;
    }

    public void setComManager(String comManager) {
        this.comManager = comManager == null ? null : comManager.trim();
    }

    public String getComPhone() {
        return comPhone;
    }

    public void setComPhone(String comPhone) {
        this.comPhone = comPhone == null ? null : comPhone.trim();
    }

    public String getComLegalPerson() {
        return comLegalPerson;
    }

    public void setComLegalPerson(String comLegalPerson) {
        this.comLegalPerson = comLegalPerson == null ? null : comLegalPerson.trim();
    }

    public String getLegalPersonPhone() {
        return legalPersonPhone;
    }

    public void setLegalPersonPhone(String legalPersonPhone) {
        this.legalPersonPhone = legalPersonPhone == null ? null : legalPersonPhone.trim();
    }

    public String getComIntroduce() {
        return comIntroduce;
    }

    public void setComIntroduce(String comIntroduce) {
        this.comIntroduce = comIntroduce == null ? null : comIntroduce.trim();
    }

    public Integer getComStatus() {
        return comStatus;
    }

    public void setComStatus(Integer comStatus) {
        this.comStatus = comStatus;
    }

    public Integer getLawEnsure() {
        return lawEnsure;
    }

    public void setLawEnsure(Integer lawEnsure) {
        this.lawEnsure = lawEnsure;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getComAddress() {
        return comAddress;
    }

    public void setComAddress(String comAddress) {
        this.comAddress = comAddress == null ? null : comAddress.trim();
    }

    public Double getComLon() {
        return comLon;
    }

    public void setComLon(Double comLon) {
        this.comLon = comLon;
    }

    public Double getComLat() {
        return comLat;
    }

    public void setComLat(Double comLat) {
        this.comLat = comLat;
    }

    public String getComLogo() {
        return comLogo;
    }

    public void setComLogo(String comLogo) {
        this.comLogo = comLogo == null ? null : comLogo.trim();
    }

    public String getLicenceUrl() {
        return licenceUrl;
    }

    public void setLicenceUrl(String licenceUrl) {
        this.licenceUrl = licenceUrl == null ? null : licenceUrl.trim();
    }

    public String getQualificationUrl() {
        return qualificationUrl;
    }

    public void setQualificationUrl(String qualificationUrl) {
        this.qualificationUrl = qualificationUrl == null ? null : qualificationUrl.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getExamineTime() {
        return examineTime;
    }

    public void setExamineTime(Date examineTime) {
        this.examineTime = examineTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getExamineId() {
        return examineId;
    }

    public void setExamineId(String examineId) {
        this.examineId = examineId == null ? null : examineId.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }
}