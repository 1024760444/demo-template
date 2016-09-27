package com.apm.echart.dao.model;

/**
 * 
 * 功能描述:用户角色信息。
 * @author: yanght
 */
public class Role {
	/**
	 * 字典编号
	 */
	private int id;
	
	/**
	 * 角色字典关键词
	 */
	private String keyword;
	
	/**
	 * 角色编号
	 */
	private int keyId;
	
	/**
	 * 角色描述
	 */
	private String description;
	
	private String creatime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getKeyId() {
		return keyId;
	}
	public void setKeyId(int keyId) {
		this.keyId = keyId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreatime() {
		return creatime;
	}
	public void setCreatime(String creatime) {
		this.creatime = creatime;
	}
}
