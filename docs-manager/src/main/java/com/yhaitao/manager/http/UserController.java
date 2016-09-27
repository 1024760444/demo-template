package com.yhaitao.manager.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhaitao.manager.dao.mapper.AttrMapper;
import com.yhaitao.manager.dao.mapper.TeamMapper;
import com.yhaitao.manager.dao.mapper.UserMapper;
import com.yhaitao.manager.dao.pojo.Attr;
import com.yhaitao.manager.dao.pojo.Team;
import com.yhaitao.manager.dao.pojo.User;
import com.yhaitao.manager.util.Cons;
import com.yhaitao.manager.util.TimerUtil;

/**
 * 用户列表页面操作。
 * @author yanghaitao
 *
 */
@Controller
@RequestMapping("user")
public class UserController {
	/**
	 * 角色信息接口
	 */
	@Resource
	private UserMapper userMapper;
	
	/**
	 * 系统属性
	 */
	@Resource
	private AttrMapper attrMapper;
	
	/**
	 * 用户分组
	 */
	@Resource
	private TeamMapper teamMapper;
	
	/**
	 * 跳转到用户信息页。
	 * @param model
	 * @return 
	 */
	@RequestMapping("/toUser")
	public String toIndex(Model model) {
		model.addAttribute("domain", Cons.domain);
		return "system/_user";
	}
	
	/**
	 * 跳转到用户信息页。
	 * @param model
	 * @return 
	 */
	@RequestMapping("/toUserDetails")
	public String toUserDetails(HttpServletRequest request, Model model) {
		String uid = request.getParameter("uid");
		model.addAttribute("domain", Cons.domain);
		model.addAttribute("uid", uid);
		return "system/_user_details";
	}
	
	/**
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userDetails", method = RequestMethod.GET)
	public Map<String, String> userDetails(HttpServletRequest request, Model model) {
		String uid = request.getParameter("uid");
		int userId = Cons.strToInt(uid, -1);
		
		Map<String, String> userDetails = new HashMap<String, String>();
		if(userId != -1) {
			List<User> userList = userMapper.selectById(userId);
			User user = userList.get(0);
			if(user != null) {
				userDetails.put("id", String.valueOf(user.getId()));
				userDetails.put("userName", user.getUserName());
				userDetails.put("roleId", String.valueOf(user.getRoleId()));
				userDetails.put("roleName", user.getRoleName());
				userDetails.put("roleByname", user.getRoleByname());
				userDetails.put("teamId", String.valueOf(user.getTeamId()));
				userDetails.put("teamName", String.valueOf(user.getTeamName()));
				userDetails.put("teamByname", String.valueOf(user.getTeamByname()));
				userDetails.put("userEmail", user.getUserEmail());
				userDetails.put("userPhone", user.getUserPhone());
				
				if(user.getRoleId() == 1) {
					Attr select = attrMapper.select("domain");
					userDetails.put("domain", select.getDocValue());
				}
			}
		}
		return userDetails;
	}
	
	/**
	 * 详情页，更新当前用户信息。
	 */
	@ResponseBody
	@RequestMapping(value = "/updateCurrUser", method = RequestMethod.POST)
	public Map<String, String> updateCurrUser(HttpServletRequest request, Model model) {
		// 接受页面推送的数据
		String idStr = request.getParameter("uid");
		String userName = request.getParameter("userName");
		String userPasswd = request.getParameter("userPasswd");
		String roleIdStr = request.getParameter("roleId");
		String teamIdStr = request.getParameter("teamId");
		String userEmail = request.getParameter("userEmail");
		String userPhone = request.getParameter("userPhone");
		int roleId = Cons.strToInt(roleIdStr, -1);
		int id = Cons.strToInt(idStr, -1);
		int teamId = Cons.strToInt(teamIdStr, -1);
		
		if(roleId != -1 && id != -1) {
			// 组合用户对象
			User user = new User();
			user.setId(id);
			user.setUserName(userName);
			String md5pwd = DigestUtils.md5DigestAsHex(userPasswd.getBytes());
			user.setUserPasswd(md5pwd);
			user.setRoleId(roleId);
			user.setTeamId(teamId);
			user.setUserEmail(userEmail);
			user.setUserPhone(userPhone);
			user.setCreateDate(TimerUtil.getSystemDate());
			userMapper.updateOneUser(user);
		}
		return userDetails(request, model);
	}
	
	/**
	 * 功能描述：查询用户信息
	 * @author:yanghaitao
	 * @param model
	 * @return String
	 * 2016年8月17日 下午4:30:28
	 */
	@ResponseBody
	@RequestMapping(value = "/scan", method = RequestMethod.GET)
	public Map<String, String> scan(HttpServletRequest request, Model model) {
		/** 分页：当前页，每页记录数 **/
		String currPageString = request.getParameter("currpage");
		String perpageString = request.getParameter("perpage");
		String search_userName = request.getParameter("search_userName");
		String search_roleId = request.getParameter("search_roleId");
		String search_teamId = request.getParameter("search_teamId");
		int currpage = Cons.strToInt(currPageString, 1);
		int perpage = Cons.strToInt(perpageString, 10);
		int roleId = Cons.strToInt(search_roleId, -1);
		int teamId = Cons.strToInt(search_teamId, -1);
		
		/** 根据分页查询信息，查询用户列表 **/
		List<String> datas = new ArrayList<String>();
		Map<String, String> input = new HashMap<String, String>();
		if(search_userName != null && !"".equals(search_userName)) {
			input.put("userName", search_userName.trim());
		}
		if(roleId != -1) {
			input.put("roleId", String.valueOf(roleId));
		}
		if(teamId != -1) {
			input.put("teamId", String.valueOf(teamId));
		}
		input.put("orderby", "createDate");
		input.put("start", String.valueOf((currpage - 1) * perpage));
		input.put("perpage", String.valueOf(perpage));
		List<User> selectRoleList = userMapper.selectOnPage(input);
		
		/** 解析查询到的用户列表 **/
		for(User user : selectRoleList) {
			List<Object> roleData = new ArrayList<Object>();
			roleData.add(user.getId()); // 用户唯一标识
			roleData.add(user.getUserName()); // 用户名称
			roleData.add(user.getRoleId()); // 用户角色ID
			roleData.add(user.getRoleByname()); // 用户角色别名
			roleData.add(user.getTeamId());
			roleData.add(user.getTeamByname());
			roleData.add(user.getUserEmail()); // 用户邮箱
			roleData.add(user.getUserPhone()); // 用户电话
			roleData.add(user.getCreateDate()); // 创建日期
			datas.add(Cons.gson.toJson(roleData));
		}
		
		/** 获取当前用户总量 **/
		int count = userMapper.count(input);
		int totalpage = (int)Math.ceil((double)count/perpage);
		
		// 所有分组列表
		List<Team> selectOnPage = teamMapper.selectOnPage(new HashMap<String, String>());
		
		/** 查询到的数据和其他相关数据返回到页面 **/
		Map<String, String> roleTemplate = new HashMap<String, String>();
		roleTemplate.put("datas", Cons.gson.toJson(datas));
		roleTemplate.put("count", String.valueOf(count));
		roleTemplate.put("currpage", String.valueOf(currpage));
		roleTemplate.put("totalpage", String.valueOf(totalpage));
		roleTemplate.put("selectOnPage", Cons.gson.toJson(selectOnPage));
		roleTemplate.put("search_userName", search_userName);
		return roleTemplate;
	}
	
	/**
	 * 功能描述：删除一个用户
	 * @author:yanghaitao
	 * @param request
	 * @param model
	 * @return Map<String,String>
	 * 2016年8月18日 下午5:38:35
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Map<String, String> delete(HttpServletRequest request, Model model) {
		// 删除用户
		String id = request.getParameter("id");
		String roleIdStr = request.getParameter("roleId");
		int roleId = -1;
		try {
			roleId = Integer.parseInt(roleIdStr);
		} catch(Exception e) {
			roleId = -1;
		}
		
		// 非不能识别的角色标识，非网站主标识，那么可以删除该用户。
		if(roleId != -1 && id != null && !"".equals(id)) {
			// 解析更新的用户标识
			int intUserId = 0;
			try {
				intUserId = Integer.parseInt(id);
			} catch(Exception e) {
				intUserId = 0;
			}
			
			// 删除
			if(intUserId != 0) {
				userMapper.deleteOneUser(intUserId);
			}
		}
		
		// 重定向到用户展示页面
		return scan(request, model);
	}
	
	/**
	 * 功能描述：添加或者修改用户
	 * @author:yanghaitao
	 * 2016年8月18日 下午2:18:23
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
	public Map<String, String> addOrUpdate(HttpServletRequest request, Model model) {
		// 接受页面推送的数据
		String id = request.getParameter("id");
		String userName = request.getParameter("userName");
		String userPasswd = request.getParameter("userPasswd");
		String roleIdStr = request.getParameter("roleId");
		String teamIdStr = request.getParameter("teamId");
		String userEmail = request.getParameter("userEmail");
		String userPhone = request.getParameter("userPhone");
		int roleId = Cons.strToInt(roleIdStr, -1);
		int teamId = Cons.strToInt(teamIdStr, -1);
		
		if(roleId != -1) {
			// 组合用户对象
			User user = new User();
			user.setUserName(userName);
			String md5pwd = DigestUtils.md5DigestAsHex(userPasswd.getBytes());
			user.setUserPasswd(md5pwd);
			user.setRoleId(roleId);
			user.setTeamId(teamId);
			user.setUserEmail(userEmail);
			user.setUserPhone(userPhone);
			user.setCreateDate(TimerUtil.getSystemDate());
			
			if(userName != null && !"".equals(userName) 
					&& userPasswd != null && !"".equals(userPasswd))
			{
				// 保存
				if(id == null || "".equals(id)) {
					userMapper.insert(user);
				}
				// 更新
				else {
					// 解析更新的用户标识
					int intUserId = 0;
					try {
						intUserId = Integer.parseInt(id);
					} catch(Exception e) {
						intUserId = 0;
					}
					
					// 更新
					if(intUserId != 0) {
						user.setId(intUserId);
						userMapper.updateOneUser(user);
					}
				}
			}
		}
		
		// 重定向到用户展示页面
		return scan(request, model);
	}
}
