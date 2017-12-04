package org.yangjie.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yangjie.bean.ArticleBean;
import org.yangjie.bean.ArticleInfoBean;
import org.yangjie.bean.ArticleSendBean;
import org.yangjie.bean.WeixinResultBean;
import org.yangjie.util.HttpUtil;
import org.yangjie.util.JsonUtil;

/**
 * 推送到微信
 * @author YangJie [2017年9月7日 上午9:45:28]
 */
@Service
public class PushService {
	
	private Logger logger = LoggerFactory.getLogger(PushService.class);
	
	/** 添加图文消息接口 */
	private String newsUrl = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=";
	/** 图文消息预览接口 */
	private String previewUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=";
	/** 图文消息发送接口 */
	private String sendUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=";
	
	@Autowired
	private UploadService uploadService;
	@Autowired
	private TokenService tokenService;
	
	
	/**
	 * 发布到微信
	 * @param beanList
	 * @return
	 */
	public boolean publish() {
		String mediaId = addNews();
		if (mediaId==null || mediaId.trim().isEmpty()) {
			logger.error("微信发布异常, mediaId={}", mediaId);
			return false;
		}
		ArticleSendBean sendBean = new ArticleSendBean();
		ArticleSendBean.Filter filter = new ArticleSendBean.Filter();
		filter.setIsToAll(true); // 所有用户
		sendBean.setFilter(filter);
		ArticleSendBean.Mpnews mpnews = new ArticleSendBean.Mpnews();
		mpnews.setMediaId(mediaId); // 图文类型
		sendBean.setMpnews(mpnews);
		sendBean.setMsgtype(ArticleSendBean.MSGTYPE_MPNEWS);
		String result = HttpUtil.postJson(sendUrl + tokenService.getAccessToken(), JsonUtil.toJson(sendBean));
		// {"errcode":0,"errmsg":"send job submission success","msg_id":1000000001,"msg_data_id":2247483653}
		logger.debug("微信消息发送接口返回：{}", result);
		return isSuccess(result);
	}
	
	/**
	 * 预览
	 * 发送到指定的openid
	 */
	public boolean preview() {
		String mediaId = addNews();
		if (mediaId==null || mediaId.trim().isEmpty()) {
			logger.error("微信预览异常, mediaId={}", mediaId);
			return false;
		}
		String body = "{\"touser\":\"oAYXpt_ybmJWIGeD11WB9036pfAI\",\"mpnews\":{\"media_id\":\""+mediaId+"\"},\"msgtype\":\"mpnews\"}";
		String result = HttpUtil.postJson(previewUrl + tokenService.getAccessToken(), body);
		// {"errcode":0,"errmsg":"preview success"}
		logger.info("微信消息预览接口返回：{}", result);
		return isSuccess(result);
	}
	
	/**
	 * 处理群发消息
	 * @return 返回图文消息media_id
	 */
	private String addNews() {
		ArticleBean articleBean = new ArticleBean();
		List<ArticleInfoBean> articleList = new ArrayList<>();
		String title = "微信发布测试";
		logger.debug("开始构建微信图文消息：{}", title);
		// 构建图文消息
		ArticleInfoBean infoBean = new ArticleInfoBean();
		infoBean.setTitle(title);
		infoBean.setThumbMediaId(uploadService.uploadhumb("http://image.tianjimedia.com/uploadImages/2013/255/HP4C7KBZQ0YP.jpg"));
		// 内容图片
		StringBuilder htmlBuilder = new StringBuilder();
		String oldurl = "http://image.tianjimedia.com/uploadImages/2013/255/HP4C7KBZQ0YP.jpg";
		byte[] bytes = HttpUtil.getBytes(oldurl, oldurl);
		String imgurl = uploadService.uploadImg(bytes); // 传到微信
		htmlBuilder.append("<p>") // data-w为图片宽度 data-ratio为图片高宽比
			.append("<img src='").append(imgurl).append("'>")
			.append("</p>");
		infoBean.setContent(htmlBuilder.toString());
		articleList.add(infoBean);
		if (articleList.isEmpty()) {
			return null;
		}
		articleBean.setArticles(articleList);
		String json = JsonUtil.toJson(articleBean);
		logger.debug("开始发布图文消息: {}", json);
		String result = HttpUtil.postJson(newsUrl + tokenService.getAccessToken(), json);
		// {"media_id":"am3s9b6WoAOFIpmthQw-MYnPJI3IxW_6a-lWeamUymE"}
		if (result==null || !result.startsWith("{\"media_id\":")) {
			logger.error("添加图文消息失败：{}", result);
			return null;
		}
		return result.split("\"")[3];
	}


	/**
	 * 判断微信返回结果是否正确
	 * @param result
	 * @return
	 */
	private boolean isSuccess(String result) {
		WeixinResultBean resultBean = JsonUtil.toObject(result, WeixinResultBean.class);
		return resultBean!=null && resultBean.getErrcode()==0;
	}
	
}
