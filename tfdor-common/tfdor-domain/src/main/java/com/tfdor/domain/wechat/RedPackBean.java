package com.tfdor.domain.wechat;

/**
 * Created by pengyuming on 16/11/2.
 */
public class RedPackBean {

    private String redPackSeq;
    private String redPackType;
    private String amountType;
    private String totalAmount;
    private String totalNum;
    private String wishing;
    private String actName;
    private String remark;
    private String createTime;
    private String updateTime;
    private String state;

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRedPackSeq() {
        return redPackSeq;
    }

    public void setRedPackSeq(String redPackSeq) {
        this.redPackSeq = redPackSeq;
    }

    public String getRedPackType() {
        return redPackType;
    }

    public void setRedPackType(String redPackType) {
        this.redPackType = redPackType;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getWishing() {
        return wishing;
    }

    public void setWishing(String wishing) {
        this.wishing = wishing;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
