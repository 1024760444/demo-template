package com.apm.echart.http.model;

/**
 * 用户的基本信息。
 * @author yanghaitao
 *
 */
public class User {
	
	/**
	 * 用户名称。
	 */
	private String uname;
	
	/**
	 * 用户密码。MD5加密。
	 */
	private String passwd;
	
	/**
	 * 登录时间。
	 */
	private String loginTimes;
	
	/**
	 * 角色名称。
	 */
	private String roleName;
	
	/**
	 * 角色权限。
	 */
	private String roleNum;
	
	/**
	 * 用户创建，注册时间。
	 */
	private String cTimes;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(String loginTimes) {
		this.loginTimes = loginTimes;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleNum() {
		return roleNum;
	}

	public void setRoleNum(String roleNum) {
		this.roleNum = roleNum;
	}

	public String getcTimes() {
		return cTimes;
	}

	public void setcTimes(String cTimes) {
		this.cTimes = cTimes;
	}
	
	
}
