package com.crrn.tfdor.domain.wechat;

import java.io.Serializable;

/**
 * Created by pengyuming on 16/10/12.
 */
public class QrcodeImg implements Serializable {

    private String qrcodeSeq;

    private String appId;

    private String actionName;

    private String sceneId;

    private String ticket;

    private String url;

    private String qrcodeName;

    private String preservation;

    private String state;

    private String createTime;

    private String updateTime;

    public String getQrcodeSeq() {
        return qrcodeSeq;
    }

    public void setQrcodeSeq(String qrcodeSeq) {
        this.qrcodeSeq = qrcodeSeq;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQrcodeName() {
        return qrcodeName;
    }

    public void setQrcodeName(String qrcodeName) {
        this.qrcodeName = qrcodeName;
    }

    public String getPreservation() {
        return preservation;
    }

    public void setPreservation(String preservation) {
        this.preservation = preservation;
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
