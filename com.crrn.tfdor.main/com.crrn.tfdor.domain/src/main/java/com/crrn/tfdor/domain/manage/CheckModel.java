package com.crrn.tfdor.domain.manage;

import java.io.Serializable;

public class CheckModel extends BaseCodeModel implements Serializable {
    private String signature;
    private String msg_signature;
    private String encrypt_type;
    private Long timestamp;
    private Long nonce;
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
        return "CheckModel{" +
                "signature='" + signature + '\'' +
                ", msg_signature='" + msg_signature + '\'' +
                ", encrypt_type='" + encrypt_type + '\'' +
                ", timestamp=" + timestamp +
                ", nonce=" + nonce +
                ", echostr='" + echostr + '\'' +
                ", appId='" + appId + '\'' +
                '}';
    }
}
