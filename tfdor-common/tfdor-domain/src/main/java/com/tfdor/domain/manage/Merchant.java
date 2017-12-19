package com.tfdor.domain.manage;

/**
 * 商户
 *
 * @author chepeiqing
 * @version V1.0.0
 * @Mail chepeiqin@icloud.com
 * @Date 2016/11/8
 * @Time 下午11:11
 */
public class Merchant {

    /**
     * 序号
     */
    private Integer mchSeq;
    /**
     * 商户ID
     **/
    private String mchId;
    /**
     * 渠道ID 外键
     **/
    private String channelId;
    /**
     * 商户名称
     **/
    private String mchName;
    /**
     * 微信APPID
     **/
    private String appId;
    /**
     * 微信Token
     **/
    private String wxToken;
    /**
     * 微信appSecret
     **/
    private String appSecret;
    /**
     * 微信加解密秘钥
     **/
    private String encodingAesKey;
    /**
     * 微信签名秘钥
     **/
    private String signatureKey;
    /**
     * 商户状态  N-正常，C-销户，S-停用
     **/
    private String state;
    /**
     * 创建时间
     **/
    private String createTime;
    /**
     * 修改时间
     **/
    private String updateTime;

    public Integer getMchSeq() {
        return mchSeq;
    }

    public void setMchSeq(Integer mchSeq) {
        this.mchSeq = mchSeq;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getMchName() {
        return mchName;
    }

    public void setMchName(String mchName) {
        this.mchName = mchName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getWxToken() {
        return wxToken;
    }

    public void setWxToken(String wxToken) {
        this.wxToken = wxToken;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getEncodingAesKey() {
        return encodingAesKey;
    }

    public void setEncodingAesKey(String encodingAesKey) {
        this.encodingAesKey = encodingAesKey;
    }

    public String getSignatureKey() {
        return signatureKey;
    }

    public void setSignatureKey(String signatureKey) {
        this.signatureKey = signatureKey;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
