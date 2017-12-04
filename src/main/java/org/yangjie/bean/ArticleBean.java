package org.yangjie.bean;

import java.util.List;

/**
 * 微信图文消息实体
 * @author YangJie [2017年9月2日 上午2:08:28]
 */
public class ArticleBean {
	
	/** 图文消息，一个图文消息支持1到8条图文 */
	List<ArticleInfoBean> articles;

	public List<ArticleInfoBean> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleInfoBean> articles) {
		this.articles = articles;
	}

}