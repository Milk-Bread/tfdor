package com.crrn.tfdor.domain.wechat;
/**
 * 二维码生成配置表实体
 *@author chepeiqing
 *@Mail chepeiqin@icloud.com
 *@Date 2016/11/7
 *@Time 下午11:05
 *@version V1.0.0
 */
public class CreateQrcodeImg {
    private String createQISeq;
    private String mchId;
    private String bigenDate;
    private String actionName;
    private String endDate;
    private Integer number;
    private String preservation;
    private String expireSeconds;
    private String createTime;
    private String updateTime;
    private String state;

    public String getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(String expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateQISeq() {
        return createQISeq;
    }

    public void setCreateQISeq(String createQISeq) {
        this.createQISeq = createQISeq;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getBigenDate() {
        return bigenDate;
    }

    public void setBigenDate(String bigenDate) {
        this.bigenDate = bigenDate;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPreservation() {
        return preservation;
    }

    public void setPreservation(String preservation) {
        this.preservation = preservation;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CreateQrcodeImg{" +
                "createQISeq='" + createQISeq + '\'' +
                ", mchId='" + mchId + '\'' +
                ", bigenDate='" + bigenDate + '\'' +
                ", actionName='" + actionName + '\'' +
                ", endDate='" + endDate + '\'' +
                ", number='" + number + '\'' +
                ", preservation='" + preservation + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
