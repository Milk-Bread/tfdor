package com.crrn.tfdor.domain.manage;

import java.sql.Timestamp;

/**
 * 渠道
 * @author chepeiqing
 */
public class Channel {
	public String channelId;
	public String channelName;
	public String state;
	public String createTime;
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
}
