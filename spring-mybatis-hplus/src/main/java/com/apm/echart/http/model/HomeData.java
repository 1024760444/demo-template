package com.apm.echart.http.model;

/**
 * 页面需要展示的常规数据。
 * @author yanghaitao
 *
 */
public class HomeData {
	/**
	 * 浏览器头部信息。
	 */
	private String title;
	private String keyword;
	private String desc;
	
	/**
	 * 当前用户信息。
	 */
	private User user;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
