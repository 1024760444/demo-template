package com.yhaitao.manager.http;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yhaitao.manager.dao.mapper.AttrMapper;
import com.yhaitao.manager.dao.mapper.UserMapper;
import com.yhaitao.manager.dao.pojo.Attr;
import com.yhaitao.manager.dao.pojo.User;
import com.yhaitao.manager.util.Cons;

/**
 * 登录操作
 * @author yanghaitao
 *
 */
@Controller
public class LoginController {
	/**
	 * 系统属性
	 */
	@Resource
	private AttrMapper attrMapper;
	
	/**
	 * 用户信息
	 */
	@Resource
	private UserMapper userMapper;
	
	/**
	 * 跳转到登录页面。
	 * @return 登录页面路径
	 */
	@RequestMapping("/toLogin")
	public String toLogin(Model model) {
		// 获取系统属性
		Attr select = attrMapper.select("domain");
		Cons.domain = select.getDocValue();
		
		// 设置到页面
		model.addAttribute("domain", Cons.domain);
		return "login";
	}
	
	/**
	 * 登录操作。
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, Model model) {
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		if(userName == null || userName.equals("")) {
			return new ModelAndView("redirect:/toLogin");
		}
		
		Map<String, String> input = new HashMap<String, String>();
		input.put("userName", userName.trim());
		input.put("userPasswd", DigestUtils.md5DigestAsHex(passWord.trim().getBytes()));
		List<User> userList = userMapper.select(input);
		if(userList == null || userList.isEmpty()) {
			return new ModelAndView("redirect:/toLogin");
		}
		
		// 当前登录的用户
		User user = userList.get(0);
		if(user != null && user.getUserName().equals(userName)) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("roleName", user.getRoleName());
			session.setAttribute("roleByname", user.getRoleByname());
			session.setAttribute("roleId", user.getRoleId());
			return new ModelAndView("redirect:/home");
		} else {
			return new ModelAndView("redirect:/toLogin");
		}
	}
	
	/**
	 * 用户退出。
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		session.setAttribute("userId", -1);
		session.setAttribute("userName", "");
		session.setAttribute("roleName", "");
		session.setAttribute("roleByname", "");
		session.setAttribute("roleId", -1);
		return new ModelAndView("redirect:/home");
	}
}
