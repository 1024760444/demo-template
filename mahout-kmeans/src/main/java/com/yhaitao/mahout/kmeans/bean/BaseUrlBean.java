package com.yhaitao.mahout.kmeans.bean;

/**
 * 基础网页信息。
 * @author yhaitao
 *
 */
public class BaseUrlBean {
	/**
	 * 网页编号
	 */
	private int id;
	
	/**
	 * 网页分类编号
	 */
	private int superId;
	
	/**
	 * 网页名称
	 */
	private String sname;
	
	/**
	 * 网页链接
	 */
	private String url;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSuperId() {
		return superId;
	}

	public void setSuperId(int superId) {
		this.superId = superId;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
