package com.yhaitao.manager.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhaitao.manager.dao.mapper.RoleMapper;
import com.yhaitao.manager.dao.pojo.Role;
import com.yhaitao.manager.util.Cons;

/**
 * 角色页面操作控制
 * @author yanghaitao
 *
 */
@Controller
@RequestMapping("role")
public class RoleController {
	/**
	 * 角色信息接口
	 */
	@Resource
	private RoleMapper roleMapper;
	
	/**
	 * 跳转到角色页面。
	 * @return 角色页面路径
	 */
	@RequestMapping("/toRole")
	public String toRole(HttpServletRequest request, Model model) {
		model.addAttribute("domain", Cons.domain);
		return "system/_role";
	}
	
	/**
	 * 功能描述：查询角色信息。
	 * @author:yanghaitao
	 * @param model
	 * @return String
	 * 2016年8月17日 下午4:30:28
	 */
	@ResponseBody
	@RequestMapping(value = "/scan", method = RequestMethod.GET)
	public Map<String, String> scan(Model model) {
		/** 查询系统的角色信息 **/
		List<Role> selectRoleList = roleMapper.selectAll();
		
		/** 封装角色信息为JSON格式字符串 **/
		List<String> datas = new ArrayList<String>();
		for(Role role : selectRoleList) {
			List<Object> roleData = new ArrayList<Object>();
			roleData.add(role.getId());
			roleData.add(role.getRoleName());
			roleData.add(role.getRoleByname());
			roleData.add(role.getRoleDesc());
			roleData.add(role.getCreateDate());
			datas.add(Cons.gson.toJson(roleData));
		}
		
		/** 数据返回页面 **/
		Map<String, String> roleTemplate = new HashMap<String, String>();
		roleTemplate.put("datas", Cons.gson.toJson(datas));
		return roleTemplate;
	}
}
