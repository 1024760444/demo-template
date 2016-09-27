package com.yhaitao.manager.dao.pojo;

/**
 * 分组信息
 * @author yanghaitao
 *
 */
public class Team {
	/**
	 * 分组编号
	 */
	private int id;
	
	/**
	 * 分组名称
	 */
	private String teamName;
	
	/**
	 * 分组别名
	 */
	private String teamByname;
	
	/**
	 * 分组描述
	 */
	private String teamDesc;
	
	/**
	 * 分组创建时间
	 */
	private String createDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getTeamDesc() {
		return teamDesc;
	}

	public void setTeamDesc(String teamDesc) {
		this.teamDesc = teamDesc;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
