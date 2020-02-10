package com.tz.pojo;

import java.util.Date;

public class ZaLawyerAuthentication {
    private String id;

    private String userId;

    private String realName;

    private String grade;

    private String lawCase;

    private Integer ageLimit;

    private String lawOffice;

    private String lawIntroduce;

    private String licenceUrl;

    private String qualificationUrl;

    private String idCard;

    private String lawLogo;

    private String province;

    private String city;

    private String lawPhone;

    private String lawAddress;

    private Double comLon;

    private Double comLat;

    private String geoCode;

    private Integer lawStatus;

    private Integer state;

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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getLawCase() {
        return lawCase;
    }

    public void setLawCase(String lawCase) {
        this.lawCase = lawCase == null ? null : lawCase.trim();
    }

    public Integer getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(Integer ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getLawOffice() {
        return lawOffice;
    }

    public void setLawOffice(String lawOffice) {
        this.lawOffice = lawOffice == null ? null : lawOffice.trim();
    }

    public String getLawIntroduce() {
        return lawIntroduce;
    }

    public void setLawIntroduce(String lawIntroduce) {
        this.lawIntroduce = lawIntroduce == null ? null : lawIntroduce.trim();
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

    public String getLawLogo() {
        return lawLogo;
    }

    public void setLawLogo(String lawLogo) {
        this.lawLogo = lawLogo == null ? null : lawLogo.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getLawPhone() {
        return lawPhone;
    }

    public void setLawPhone(String lawPhone) {
        this.lawPhone = lawPhone == null ? null : lawPhone.trim();
    }

    public String getLawAddress() {
        return lawAddress;
    }

    public void setLawAddress(String lawAddress) {
        this.lawAddress = lawAddress == null ? null : lawAddress.trim();
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

    public String getGeoCode() {
        return geoCode;
    }

    public void setGeoCode(String geoCode) {
        this.geoCode = geoCode == null ? null : geoCode.trim();
    }

    public Integer getLawStatus() {
        return lawStatus;
    }

    public void setLawStatus(Integer lawStatus) {
        this.lawStatus = lawStatus;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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