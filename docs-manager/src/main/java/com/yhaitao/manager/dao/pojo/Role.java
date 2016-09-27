package com.yhaitao.manager.dao.pojo;

/**
 * 角色信息对象。
 * @author yanghaitao
 *
 */
public class Role {
	/**
	 * 角色编号
	 */
	private int id;
	
	/**
	 * 角色名称，英文名称
	 */
	private String roleName;
	
	/**
	 * 角色别名，中文名称
	 */
	private String roleByname;
	
	/**
	 * 角色描述
	 */
	private String roleDesc;
	
	/**
	 * 角色创建日期
	 */
	private String createDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
