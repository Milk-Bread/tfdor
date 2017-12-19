package com.tfdor.domain.wechat;

/**
 * @author chepeiqing
 * @version V1.0.0
 * @Mail chepeiqin@icloud.com
 * @Date 2017/1/2
 * @Time 下午2:10
 */
public class CustomerInfo {
    private String openId;
    private Integer mchSeq;
    private String nickName;
    private String sex;
    private String city;
    private String country;
    private String province;
    private String language;
    private String headimgurl;
    private String subscribeTime;


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getMchSeq() {
        return mchSeq;
    }

    public void setMchSeq(Integer mchSeq) {
        this.mchSeq = mchSeq;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime;
    }
}
