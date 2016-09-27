package com.yhaitao.manager.dao.mapper;

import java.util.List;
import java.util.Map;

import com.yhaitao.manager.dao.pojo.FilePojo;

/**
 * 文件接口操作。
 * @author yanghaitao
 *
 */
public interface FileMapper {
	/**
	 * 分页查询。
	 * @param input 查询条件
	 * @return 满足条件的文件信息
	 */
	public List<FilePojo> selectOnPage(Map<String, String> input);
	
	/**
	 * 统计文件信息数量
	 * @param input 查询条件
	 * @return 文件数量
	 */
	public int count(Map<String, String> input);
	
	/**
	 * 保存文件信息
	 */
	public void insert(FilePojo file);
	
	/**
	 * 根据文件唯一标志，更新文件信息
	 */
	public void update(FilePojo file);
	
	/**
	 * 根据文件ID删除文件。
	 */
	public void delete(int id);
}
