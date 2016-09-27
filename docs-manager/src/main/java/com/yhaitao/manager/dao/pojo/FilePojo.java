package com.yhaitao.manager.dao.pojo;

/**
 * 文件对象
 * @author yanghaitao
 *
 */
public class FilePojo {
	/**
	 * 文件序号
	 */
	private int id;
	
	/**
	 * 文件名称
	 */
	private String fileName;
	
	/**
	 * 文件描述
	 */
	private String fileDesc;
	
	/**
	 * 文件分组标志
	 */
	private int teamId;
	
	/**
	 * 分组别名
	 */
	private String teamByname;
	
	/**
	 * 文件用户标志
	 */
	private int userId;
	
	/**
	 * 用户名称
	 */
	private String userName;
	
	/**
	 * 展示权限。 0仅自己可见，1仅本组可见，2所有人可见
	 */
	private int showId;
	
	/**
	 * 下载权限。0仅自己可下载，1仅本组可下载，2所有人可下载
	 */
	private int downId;
	
	/**
	 * 下载码。 可见而不可下载的文档，可通过上传者设置的下载码下载。
	 */
	private String downCode;
	
	/**
	 * 文档上传时间
	 */
	private String createDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public int getDownId() {
		return downId;
	}

	public void setDownId(int downId) {
		this.downId = downId;
	}

	public String getDownCode() {
		return downCode;
	}

	public void setDownCode(String downCode) {
		this.downCode = downCode;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getTeamByname() {
		return teamByname;
	}

	public void setTeamByname(String teamByname) {
		this.teamByname = teamByname;
	}
	
}
