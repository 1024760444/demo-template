package com.yhaitao.manager.http;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhaitao.manager.dao.mapper.AttrMapper;
import com.yhaitao.manager.dao.pojo.Attr;
import com.yhaitao.manager.util.Cons;

/**
 * 默认首页
 * @author yanghaitao
 *
 */
@Controller
public class HomeController {
	
	/**
	 * 系统属性
	 */
	@Resource
	private AttrMapper attrMapper;
	
	/**
	 * 跳转到角色页面。
	 * @return 角色页面路径
	 */
	@RequestMapping("/home")
	public String toHome(Model model) {
		// 获取系统属性
		Attr select = attrMapper.select("domain");
		Cons.domain = select.getDocValue();
		
		// 设置到页面
		model.addAttribute("domain", Cons.domain);
		return "index";
	}
}
