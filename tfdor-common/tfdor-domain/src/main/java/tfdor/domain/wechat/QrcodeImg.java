package tfdor.domain.wechat;

import java.io.Serializable;

/**
 * Created by pengyuming on 16/10/12.
 */
public class QrcodeImg implements Serializable {
    private String qrcodeSeq;
    private String createQISeq;
    private String mchId;
    private String sceneStr;
    private String ticket;
    private String url;
    private String qrcodeName;
    private String state;
    private String createTime;
    private String updateTime;
    private String transjnl;
    private String orderId;
    private String openId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTransjnl() {
        return transjnl;
    }

    public void setTransjnl(String transjnl) {
        this.transjnl = transjnl;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getQrcodeSeq() {
        return qrcodeSeq;
    }

    public void setQrcodeSeq(String qrcodeSeq) {
        this.qrcodeSeq = qrcodeSeq;
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

    public String getSceneStr() {
        return sceneStr;
    }

    public void setSceneStr(String sceneStr) {
        this.sceneStr = sceneStr;
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

    @Override
    public String toString() {
        return "QrcodeImg{" +
                "qrcodeSeq='" + qrcodeSeq + '\'' +
                ", createQISeq='" + createQISeq + '\'' +
                ", mchId='" + mchId + '\'' +
                ", sceneStr='" + sceneStr + '\'' +
                ", ticket='" + ticket + '\'' +
                ", url='" + url + '\'' +
                ", qrcodeName='" + qrcodeName + '\'' +
                ", state='" + state + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
