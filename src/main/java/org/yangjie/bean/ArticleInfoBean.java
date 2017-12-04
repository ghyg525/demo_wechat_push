package org.yangjie.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信图文消息实体
 * @author YangJie [2017年9月2日 上午2:08:28]
 */
public class ArticleInfoBean {
	
	/********* 必须
	
	/** 图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得 */
	@JsonProperty("thumb_media_id")
	private String thumbMediaId;
	/** 图文消息的标题 */
	private String title;
	/** 图文消息页面的内容，支持HTML标签。具备微信支付权限的公众号，可以使用a标签，其他公众号不能使用，如需插入小程序卡片，可参考下文。 */
	private String content;
	
	/********* 非必须
	
	/** 图文消息的作者 */
	private String author;
	/** 在图文消息页面点击“阅读原文”后的页面，受安全限制，如需跳转Appstore，可以使用itun.es或appsto.re的短链服务，并在短链后增加 #wechat_redirect 后缀。 */
	@JsonProperty("content_source_url")
	private String contentSourceUrl;
	/** 图文消息的描述，如本字段为空，则默认抓取正文前64个字 */
	private String digest;
	/** 封面图片是否显示在正文中，1为显示，0为不显示 */
	@JsonProperty("show_cover_pic")
	private int showCoverPic;
	
	/********* 留言功能
	/** 是否打开评论，0不打开，1打开 */
	@JsonProperty("need_open_comment")
	private int need_open_comment = 1;
	/** 是否粉丝才可评论，0所有人可评论，1粉丝才可评论 */
	@JsonProperty("only_fans_can_comment")
	private int only_fans_can_comment;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	public String getContentSourceUrl() {
		return contentSourceUrl;
	}
	public void setContentSourceUrl(String contentSourceUrl) {
		this.contentSourceUrl = contentSourceUrl;
	}
	public int getShowCoverPic() {
		return showCoverPic;
	}
	public void setShowCoverPic(int showCoverPic) {
		this.showCoverPic = showCoverPic;
	}
	public int getNeed_open_comment() {
		return need_open_comment;
	}
	public void setNeed_open_comment(int need_open_comment) {
		this.need_open_comment = need_open_comment;
	}
	public int getOnly_fans_can_comment() {
		return only_fans_can_comment;
	}
	public void setOnly_fans_can_comment(int only_fans_can_comment) {
		this.only_fans_can_comment = only_fans_can_comment;
	}

}