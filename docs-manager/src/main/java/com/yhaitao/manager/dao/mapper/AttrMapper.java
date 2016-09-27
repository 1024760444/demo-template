package com.yhaitao.manager.dao.mapper;

import com.yhaitao.manager.dao.pojo.Attr;

/**
 * 系统属性接口
 * @author yanghaitao
 *
 */
public interface AttrMapper {
	/**
	 * 查询系统属性。
	 * @return 所有系统属性
	 */
	public Attr select(String key);
}
