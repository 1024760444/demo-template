package com.yhaitao.manager.dao.mapper;

import java.util.List;

import com.yhaitao.manager.dao.pojo.Role;

/**
 * 角色接口定义。
 * @author yanghaitao
 *
 */
public interface RoleMapper {
	/**
	 * 查询角色表中所有角色信息。
	 * @return 所有角色信息
	 */
	public List<Role> selectAll();
	
	/**
	 * 插入角色信息
	 * @param role 角色信息
	 */
	public void insert(Role role);
	
	/**
	 * 删除角色信息
	 * @param id 角色信息的唯一标识
	 */
	public void delete(int id);
}
