package com.yhaitao.manager.dao.mapper;

import java.util.List;
import java.util.Map;

import com.yhaitao.manager.dao.pojo.Team;

/**
 * 分组数据接口。
 * @author yanghaitao
 *
 */
public interface TeamMapper {
	/**
	 * 保存一个分组信息。
	 * @param team 分组信息
	 */
	public void insert(Team team);
	
	/**
	 * 删除一个分组信息。
	 * @param team 分组信息
	 */
	public void delete(int id);
	
	/**
	 * 分页查询分组信息。
	 * @param input 分页信息
	 * @return 当前分页的分组信息
	 */
	public List<Team> selectOnPage(Map<String, String> input);
	
	/**
	 * 查询满足条件的分组的个数
	 */
	public int count(Map<String, String> input);
}
