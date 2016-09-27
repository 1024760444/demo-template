package com.yhaitao.manager.dao.pojo;

/**
 * 登录用户信息
 * @author yanghaitao
 *
 */
public class User {
	/**
	 * 用户标识
	 */
	private int id;
	
	/**
	 * 用户名称
	 */
	private String userName;
	
	/**
	 * 用户密码，md5加密。
	 */
	private String userPasswd;
	
	/**
	 * 用户角色标识
	 */
	private int roleId;
	
	/**
	 * 角色名称
	 */
	private String roleName;
	
	/**
	 * 角色别名
	 */
	private String roleByname;
	
	/**
	 * 分组标识
	 */
	private int teamId;
	
	/**
	 * 分组名称
	 */
	private String teamName;
	
	/**
	 * 分组别名
	 */
	private String teamByname;
	
	/**
	 * 用户邮箱
	 */
	private String userEmail;
	
	/**
	 * 用户手机号码
	 */
	private String userPhone;
	
	/**
	 * 用户创建时间
	 */
	private String createDate;

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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleByname() {
		return roleByname;
	}

	public void setRoleByname(String roleByname) {
		this.roleByname = roleByname;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamByname() {
		return teamByname;
	}

	public void setTeamByname(String teamByname) {
		this.teamByname = teamByname;
	}
	
}
