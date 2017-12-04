package org.yangjie.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信图文消息实体
 * @author YangJie [2017年9月2日 上午2:08:28]
 */
public class ArticleResultBean {
	
	/********* 必须
	
	/** 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），图文消息（news） */
	private String type;
	/** 媒体文件/图文消息上传后获取的唯一标识 */
	@JsonProperty("media_id")
	private String mediId;
	/** 媒体文件上传时间 */
	@JsonProperty("created_at")
	private String createdAt;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMediId() {
		return mediId;
	}
	public void setMediId(String mediId) {
		this.mediId = mediId;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
}