package org.yangjie.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yangjie.util.HttpUtil;

/**
 * 微信上传图片
 * @author YangJie [2017年8月31日 下午5:00:17]
 */
@Service
public class UploadService {
	
	private Logger logger = LoggerFactory.getLogger(UploadService.class);
	
	/** 添加内容图片接口 */
	private String uploadUrl = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=";
	/** 添加图文消息缩略图接口 */
	private String thumbUrl = "https://api.weixin.qq.com/cgi-bin/material/add_material?type=thumb&access_token=";
	
	@Autowired
	private TokenService tokenService;
	
	
	/**
	 * 上传内容图片
	 * @param imgurl 源地址
	 * @return 返回图片地址（只能在腾讯系域名下访问）
	 */
	public String uploadImg(String imgurl) {
		logger.debug("向微信上传图片： {}", imgurl);
		if (imgurl==null || !imgurl.startsWith("http")) {
			logger.error("图片地址不合法：{}", imgurl);
			return null;
		} // 下载图片并上传
		return uploadImg(HttpUtil.getBytes(imgurl, imgurl));
	}
	
	/**
	 * 上传内容图片
	 * @param imgurl 源地址
	 * @return 返回图片地址（只能在腾讯系域名下访问）
	 */
	public String uploadImg(byte[] bytes) {
		// 上传图片
		String result = HttpUtil.postJpg(uploadUrl + tokenService.getAccessToken(), bytes);
		// {"url":"http:\/\/mmbiz.qpic.cn\/mmbiz_jpg\/IByOwLQTx1Pv9ZiawYAvDsQ4354vwgGyEXibWurbGc2OPBugGbrhuNf3TiadiaktkfQNr2EaYUMia9ohibTyrArfWrJA\/0"}
		if (result==null || !result.startsWith("{\"url\":")) {
			logger.error("上传图片到微信失败：{}", result);
			return null;
		}
		return result.replace("\\", "").split("\"")[3];
	}
	
	/**
	 * 上传缩略图
	 * @param imgurl 源地址
	 * @return 返回media_id
	 */
	public String uploadhumb(String imgurl) {
		if (imgurl==null || !imgurl.startsWith("http")) {
			logger.error("图片地址不合法：{}", imgurl);
			return null;
		}
		// 下载图片并上传
		String result = HttpUtil.postJpg(thumbUrl + tokenService.getAccessToken(), HttpUtil.getBytes(imgurl, imgurl));
		// {"media_id":"am3s9b6WoAOFIpmthQw-MYCpYTrRqcvkk9gVdUteZag","url":"http:\/\/mmbiz.qpic.cn\/mmbiz_jpg\/IByOwLQTx1Pv9ZiawYAvDsQ4354vwgGyEFK31kPxYnzUDddaUx8eEOYDeIdnw0NFNSLEmPey1NNhskTLkiaicfbfQ\/0?wx_fmt=jpeg"}
		if (result==null || !result.startsWith("{\"media_id\":")) {
			logger.error("上传图片到微信失败：{}", result);
			return null;
		}
		return result.split("\"")[3];
	}
	
}
