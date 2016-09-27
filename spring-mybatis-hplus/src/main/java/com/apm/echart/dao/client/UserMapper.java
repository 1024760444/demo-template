package com.apm.echart.dao.client;

import java.util.List;
import java.util.Map;

import com.apm.echart.dao.model.User;

public interface UserMapper {
	/**
	 * 功能描述：条件查询。
	 * @author:yanghaitao
	 */
	public List<User> selectPageing(Map<String, String> input);
	
	/**
	 * 功能描述：保存一个用户信息。
	 * @author:yanghaitao 
	 * @param user 
	 * @return void 
	 * 2016年8月18日 下午2:27:26 
	 */
	public void saveOneUser(User user);
	
	/**
	 * 功能描述：更新一个用户信息。
	 * @author:yanghaitao 
	 * @param user 
	 * @return void 
	 * 2016年8月18日 下午2:27:26 
	 */
	public void updateOneUser(User user);
	
	/**
	 * 功能描述：根据用户ID，删除指定用户。
	 * @author:yanghaitao
	 * @param user
	 * @return void
	 * 2016年8月18日 下午5:45:56
	 */
	public void deleteOneUser(User user);
	
	/**
	 * 查询记录数据量。
	 * @param input
	 * @return
	 */
	public int count(Map<String, String> input);
}
