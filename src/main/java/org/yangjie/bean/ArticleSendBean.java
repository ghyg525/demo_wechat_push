package org.yangjie.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信图文发送实体
 * @author YangJie [2017年9月2日 上午2:08:28]
 */
public class ArticleSendBean {
	
	/** 图文消息类型 */
	public static final String MSGTYPE_MPNEWS = "mpnews";
	
	
	/** 用于设定图文消息的接收者 */
	private Filter filter;
	/** 用于设定即将发送的图文消息 */
	private Mpnews mpnews;
	/** 群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard */
	private String msgtype;
	/** 图文消息被判定为转载时，是否继续群发。1为继续群发（转载），0为停止群发。该参数默认为0。 */
	@JsonProperty("send_ignore_reprint")
	private int sendIgnoreReprint = 1;

	
	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public Mpnews getMpnews() {
		return mpnews;
	}

	public void setMpnews(Mpnews mpnews) {
		this.mpnews = mpnews;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public int getSendIgnoreReprint() {
		return sendIgnoreReprint;
	}

	public void setSendIgnoreReprint(int sendIgnoreReprint) {
		this.sendIgnoreReprint = sendIgnoreReprint;
	}


	
	public static class Filter {
		/** 用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据tag_id发送给指定群组的用户 */
		@JsonProperty("is_to_all")
		private boolean isToAll;
		/** 群发到的标签的tag_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写tag_id */
		@JsonProperty("tag_id")
		private String tagId;
		public boolean getIsToAll() {
			return isToAll;
		}
		public void setIsToAll(boolean isToAll) {
			this.isToAll = isToAll;
		}
		public String getTagId() {
			return tagId;
		}
		public void setTagId(String tagId) {
			this.tagId = tagId;
		}
		
	}
	
	
	public static class Mpnews {
		/** 用于群发的消息的media_id */
		@JsonProperty("media_id")
		private String mediaId;
		
		public String getMediaId() {
			return mediaId;
		}
		
		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
		}
	}

	
}