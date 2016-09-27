package com.yhaitao.manager.dao.pojo;

/**
 * 系统属性
 * @author yanghaitao
 *
 */
public class Attr {
	/**
	 * 关键字。
	 */
	private String docKey;
	
	/**
	 * 属性值
	 */
	private String docValue;
	
	/**
	 * 属性创建时间
	 */
	private String createDate;

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getDocValue() {
		return docValue;
	}

	public void setDocValue(String docValue) {
		this.docValue = docValue;
	}

	public String getDocKey() {
		return docKey;
	}

	public void setDocKey(String docKey) {
		this.docKey = docKey;
	}
	
}
