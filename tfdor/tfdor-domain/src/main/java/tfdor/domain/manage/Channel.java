package tfdor.domain.manage;

import java.io.Serializable;

/**
 * 渠道
 * @author chepeiqing
 */
public class Channel implements Serializable {
	private String channelId;
	private String channelName;
	private String state;
	private String createTime;


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
				", state='" + state + '\'' +
				", createTime='" + createTime + '\'' +
				'}';
	}
}
