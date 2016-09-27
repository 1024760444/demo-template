package com.apm.echart.dao.model;
/**
 * 
 * 功能描述:用户信息。
 * @author: yanght
 */
public class User {
	/**
	 * 用户序号
	 */
	private int id;
	
	/**
	 * 登录名称
	 */
	private String userName;
	
	/**
	 * 登录密码
	 */
	private String userPasswd;
	
	/**
	 * 角色信息
	 */
	private int roleId;
	
	/**
	 * 用户邮箱
	 */
	private String userEmail;

	/**
	 * 用户电话
	 */
	private String userPhone;

	/**
	 * 创建时间
	 */
	private String creatime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPasswd() {
		return userPasswd;
	}
	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getCreatime() {
		return creatime;
	}
	public void setCreatime(String creatime) {
		this.creatime = creatime;
	}
	
	
}
