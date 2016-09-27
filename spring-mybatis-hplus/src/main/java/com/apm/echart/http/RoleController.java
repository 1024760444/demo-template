package com.apm.echart.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apm.echart.dao.client.RoleMapper;
import com.apm.echart.dao.model.Role;
import com.apm.echart.http.model.HomeData;
import com.apm.echart.util.ComUtil;

/**
 * 功能描述:角色信息控制。
 */
@Controller
@RequestMapping("role")
public class RoleController {
	
	/**
	 * 角色信息表操作
	 */
	@Resource
	private RoleMapper roleMapper;
	
	/**
	 * 功能描述：跳转到角色信息页面。
	 * @author:yanghaitao
	 * @return void
	 * 2016年8月17日 下午3:33:59
	 */
	@RequestMapping("toRole")
	public String toRole(Model model) {
		HomeData homeData = new HomeData();
		homeData.setTitle("角色信息展示");
		homeData.setKeyword("角色， 列表，展示");
		homeData.setDesc("角色信息展示");
		
		model.addAttribute("homeData", homeData);
		model.addAttribute("domain", ComUtil.DOMAIN);
		return "role/role_page";
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
		
		List<String> titles = new ArrayList<String>();
		titles.add("字典序号");
		titles.add("字典名称");
		titles.add("角色编号");
		titles.add("角色描述");
		titles.add("创建时间");
		
		List<String> datas = new ArrayList<String>();
		List<Role> selectRoleList = roleMapper.selectRoleList();
		for(Role role : selectRoleList) {
			List<Object> roleData = new ArrayList<Object>();
			roleData.add(role.getId());
			roleData.add(role.getKeyword());
			roleData.add(role.getKeyId());
			roleData.add(role.getDescription());
			roleData.add(role.getCreatime());
			datas.add(ComUtil.gson.toJson(roleData));
		}
		
		Map<String, String> roleTemplate = new HashMap<String, String>();
		roleTemplate.put("titles", ComUtil.gson.toJson(titles));
		roleTemplate.put("datas", ComUtil.gson.toJson(datas));
		return roleTemplate;
	}
	
}
