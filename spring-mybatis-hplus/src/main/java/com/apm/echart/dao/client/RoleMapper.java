package com.apm.echart.dao.client;

import java.util.List;

import com.apm.echart.dao.model.Role;

/**
 * 角色信息操作接口
 * @author yanghaitao
 *
 */
public interface RoleMapper {
	
	/**
	 * 功能描述：查询所有角色信息。
	 * @author:yanghaitao
	 * @return List<Role>
	 * 2016年8月17日 下午3:21:38
	 */
	public List<Role> selectRoleList();
	
}