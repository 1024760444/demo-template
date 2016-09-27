package com.yhaitao.manager.dao.mapper;

import java.util.List;
import java.util.Map;

import com.yhaitao.manager.dao.pojo.User;

/**
 * 用户信息表操作定义
 * @author yanghaitao
 *
 */
public interface UserMapper {
	/**
	 * 新增一个用户。
	 * @param user
	 */
	public void insert(User user);
	
	/**
	 * 查询满足条件的用户
	 */
	public List<User> select(Map<String, String> input);
	
	/**
	 * 分页查询
	 */
	public List<User> selectOnPage(Map<String, String> input);
	
	/**
	 * 查询满足指定条件的用户的数量
	 * @param input 查询条件
	 * @return 用户数量
	 */
	public int count(Map<String, String> input);
	
	/**
	 * 删除一个用户
	 * @param id 删除用户的唯一标识
	 */
	public void deleteOneUser(int id);
	
	/**
	 * 根据用户标识更新用户信息。
	 * @param user 用户信息
	 */
	public void updateOneUser(User user);
	
	/**
	 * 根据用户标识查询用户
	 * @param id 用户标识
	 * @return 用户信息
	 */
	public List<User> selectById(int id);
}
