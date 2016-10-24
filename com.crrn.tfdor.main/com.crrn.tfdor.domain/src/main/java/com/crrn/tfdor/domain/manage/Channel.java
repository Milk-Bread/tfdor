package com.crrn.tfdor.domain.manage;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 渠道
 * @author chepeiqing
 */
public class Channel implements Serializable {
	public String channelId;
	public String channelName;
	public String appId;
	public String wxToken;
	public String appSecret;
	public String state;
	public String createTime;


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

	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
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

	@Override
	public String toString() {
		return "Channel{" +
				"channelId='" + channelId + '\'' +
				", channelName='" + channelName + '\'' +
				", appId='" + appId + '\'' +
				", wxToken='" + wxToken + '\'' +
				", appSecret='" + appSecret + '\'' +
				", state='" + state + '\'' +
				", createTime='" + createTime + '\'' +
				'}';
	}
}
