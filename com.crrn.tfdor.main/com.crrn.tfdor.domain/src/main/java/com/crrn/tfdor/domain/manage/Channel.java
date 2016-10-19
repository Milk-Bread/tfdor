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
	public String state;
	public Timestamp createTime;
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
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}
