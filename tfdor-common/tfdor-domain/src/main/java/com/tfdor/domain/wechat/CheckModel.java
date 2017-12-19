package com.tfdor.domain.wechat;


import com.tfdor.domain.BaseModel;

/**
 * @author chepeiqing
 * @date 2017年12月12日 下午7:11:07
 * @description 微信接入数据模型
 * @modifyBy 修改人
 * @modifyDate 修改时间
 * @modifyNote 修改说明
 */
public class CheckModel extends BaseModel {
    private static final long serialVersionUID = 1L;
    /** 微信加密签名 **/
    private String signature;
    /** 微信密文 **/
    private String msg_signature;
    /** 加密模式(aes,明文) **/
    private String encrypt_type;
    /** 时间戳 **/
    private Long timestamp;
    /** 随机数 **/
    private Long nonce;
    /** 随机字符串 **/
    private String echostr;
    private String appId;

    public String getEncrypt_type() {
        return encrypt_type;
    }

    public void setEncrypt_type(String encrypt_type) {
        this.encrypt_type = encrypt_type;
    }

    public String getMsg_signature() {
        return msg_signature;
    }

    public void setMsg_signature(String msg_signature) {
        this.msg_signature = msg_signature;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getNonce() {
        return nonce;
    }

    public void setNonce(Long nonce) {
        this.nonce = nonce;
    }

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }

    @Override
    public String toString() {
        return "CheckModel{" + "signature='" + signature + '\'' + ", msg_signature='" + msg_signature + '\'' + ", encrypt_type='" + encrypt_type + '\'' + ", timestamp=" + timestamp + ", nonce=" + nonce
            + ", echostr='" + echostr + '\'' + ", appId='" + appId + '\'' + '}';
    }
}
