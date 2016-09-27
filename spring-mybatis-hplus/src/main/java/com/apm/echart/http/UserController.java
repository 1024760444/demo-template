package com.apm.echart.http;

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

import com.apm.echart.dao.client.UserMapper;
import com.apm.echart.dao.model.User;
import com.apm.echart.http.model.HomeData;
import com.apm.echart.util.ComUtil;
import com.apm.echart.util.StringUtil;
import com.apm.echart.util.TimerUtil;

/**
 * 功能描述:用户信息控制。
 */
@Controller
@RequestMapping("user")
public class UserController {
	
	/**
	 * 用户信息表接口。
	 */
	@Resource
	private UserMapper userMapper;
	
	/**
	 * 跳转到用户信息页。
	 * @param model
	 * @return 
	 */
	@RequestMapping("toUser")
	public String toIndex(Model model) {
		HomeData homeData = new HomeData();
		homeData.setTitle("用户信息展示");
		homeData.setKeyword("用户， 列表，展示");
		homeData.setDesc("用户信息展示");
		
		model.addAttribute("homeData", homeData);
		model.addAttribute("domain", ComUtil.DOMAIN);
		return "user/user_page";
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
		// 分页：当前页，每页记录数
		String currPageString = request.getParameter("currpage");
		String perpageString = request.getParameter("perpage");
		String search_userName = request.getParameter("search_userName");
		int currpage = StringUtil.strToInt(currPageString, 1);
		int perpage = StringUtil.strToInt(perpageString, 10);
		
		List<String> titles = new ArrayList<String>();
		titles.add("用户序号");
		titles.add("登录名称");
		titles.add("登录密码");
		titles.add("角色信息");
		titles.add("用户邮箱");
		titles.add("用户电话");
		titles.add("创建时间");
		
		List<String> datas = new ArrayList<String>();
		Map<String, String> input = new HashMap<String, String>();
		if(search_userName != null && !"".equals(search_userName)) {
			input.put("userName", search_userName.trim());
		}
		input.put("orderby", "creatime");
		input.put("start", String.valueOf((currpage - 1) * perpage));
		input.put("end", String.valueOf(currpage * perpage));
		List<User> selectRoleList = userMapper.selectPageing(input);
		for(User user : selectRoleList) {
			List<Object> roleData = new ArrayList<Object>();
			roleData.add(user.getId());
			roleData.add(user.getUserName());
			roleData.add(user.getUserPasswd());
			roleData.add(user.getRoleId());
			roleData.add(user.getUserEmail());
			roleData.add(user.getUserPhone());
			roleData.add(user.getCreatime());
			datas.add(ComUtil.gson.toJson(roleData));
		}
		
		int count = userMapper.count(input);
		int totalpage = (int)Math.ceil((double)count/perpage);
		
		Map<String, String> roleTemplate = new HashMap<String, String>();
		roleTemplate.put("titles", ComUtil.gson.toJson(titles));
		roleTemplate.put("datas", ComUtil.gson.toJson(datas));
		roleTemplate.put("count", String.valueOf(count));
		roleTemplate.put("currpage", String.valueOf(currpage));
		roleTemplate.put("totalpage", String.valueOf(totalpage));
		roleTemplate.put("search_userName", search_userName);
		return roleTemplate;
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
		String roleId = request.getParameter("roleId");
		String userEmail = request.getParameter("userEmail");
		String userPhone = request.getParameter("userPhone");
		
		// 组合用户对象
		User user = new User();
		user.setUserName(userName);
		String md5pwd = DigestUtils.md5DigestAsHex(userPasswd.getBytes());
		user.setUserPasswd(md5pwd);
		user.setRoleId(Integer.parseInt(roleId));
		user.setUserEmail(userEmail);
		user.setUserPhone(userPhone);
		user.setCreatime(TimerUtil.getSystemFormat());
		
		if(userName != null && !"".equals(userName) 
				&& userPasswd != null && !"".equals(userPasswd))
		{
			// 保存
			if(id == null || "".equals(id)) {
				userMapper.saveOneUser(user);
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
		
		// 重定向到用户展示页面
		return scan(request, model);
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
		if(id != null && !"".equals(id)) {
			// 解析更新的用户标识
			int intUserId = 0;
			try {
				intUserId = Integer.parseInt(id);
			} catch(Exception e) {
				intUserId = 0;
			}
			
			// 删除
			if(intUserId != 0) {
				User user = new User();
				user.setId(intUserId);
				userMapper.deleteOneUser(user);
			}
		}
		
		// 重定向到用户展示页面
		return scan(request, model);
	}
}
