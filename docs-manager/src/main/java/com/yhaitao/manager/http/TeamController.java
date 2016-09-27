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

import com.yhaitao.manager.dao.mapper.TeamMapper;
import com.yhaitao.manager.dao.pojo.Team;
import com.yhaitao.manager.util.Cons;
import com.yhaitao.manager.util.TimerUtil;

/**
 * 项目页面控制操作。
 * @author yanghaitao
 *
 */
@Controller
@RequestMapping("team")
public class TeamController {
	/**
	 * 分组数据接口
	 */
	@Resource
	private TeamMapper teamMapper;
	
	/**
	 * 跳转到分组页面。
	 */
	@RequestMapping("/toTeam")
	public String toTeam(Model model) {
		model.addAttribute("domain", Cons.domain);
		return "system/_team";
	}
	
	/**
	 * 新增一个分组
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Map<String, String> add(HttpServletRequest request, Model model) {
		/** 获取页面传来的参数 **/
		String teamName = request.getParameter("teamName");
		String teamByname = request.getParameter("teamByname");
		String teamDesc = request.getParameter("teamDesc");
		
		if(teamName != null && !"".equals(teamName) 
				&& teamByname != null && !"".equals(teamByname)) {
			/** 查询指定分组名称是否存在 **/
			Map<String, String> input = new HashMap<String, String>();
			if(teamName != null && !"".equals(teamName)) {
				input.put("teamName", teamName.trim());
			}
			List<Team> selectTeamList = teamMapper.selectOnPage(input);
			
			/** 如果不存在，添加 **/
			if(selectTeamList == null || selectTeamList.isEmpty()) {
				Team team = new Team();
				team.setTeamName(teamName);
				team.setTeamByname(teamByname);
				team.setTeamDesc(teamDesc);
				team.setCreateDate(TimerUtil.getSystemDate());
				teamMapper.insert(team);
			}
		}
		return scan(request, model);
	}
	
	/**
	 * 删除一个分组
	 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Map<String, String> delete(HttpServletRequest request, Model model) {
		// 删除用户
		String idStr = request.getParameter("id");
		int id = Cons.strToInt(idStr, -1);
		if(id != -1) {
			teamMapper.delete(id);
		}
		return scan(request, model);
	}
	
	/**
	 * 查询分组数据。
	 */
	@ResponseBody
	@RequestMapping(value = "/scan", method = RequestMethod.GET)
	public Map<String, String> scan(HttpServletRequest request, Model model) {
		/** 分页：当前页，每页记录数 **/
		String currPageString = request.getParameter("currpage");
		String perpageString = request.getParameter("perpage");
		String search_teamName = request.getParameter("search_teamName");
		int currpage = Cons.strToInt(currPageString, 1);
		int perpage = Cons.strToInt(perpageString, 10);
		
		/** 根据分组查询信息 **/
		List<String> datas = new ArrayList<String>();
		Map<String, String> input = new HashMap<String, String>();
		if(search_teamName != null && !"".equals(search_teamName)) {
			input.put("teamName", search_teamName.trim());
		}
		input.put("orderby", "createDate");
		input.put("start", String.valueOf((currpage - 1) * perpage));
		input.put("perpage", String.valueOf(perpage));
		List<Team> selectTeamList = teamMapper.selectOnPage(input);
		
		/** 解析查询到的用户列表 **/
		if(selectTeamList != null && !selectTeamList.isEmpty()) {
			for(Team team : selectTeamList) {
				List<Object> teamData = new ArrayList<Object>();
				teamData.add(team.getId()); // 分组唯一标识
				teamData.add(team.getTeamName()); // 分组名称
				teamData.add(team.getTeamByname()); // 分组别名
				teamData.add(team.getTeamDesc()); // 分组描述
				teamData.add(team.getCreateDate()); // 分组别名创建日期
				datas.add(Cons.gson.toJson(teamData));
			}
		}
		
		/** 获取当前用户总量 **/
		int count = teamMapper.count(input);
		int totalpage = (int)Math.ceil((double)count/perpage);
		
		/** 查询到的数据和其他相关数据返回到页面 **/
		Map<String, String> teamTemplate = new HashMap<String, String>();
		teamTemplate.put("datas", Cons.gson.toJson(datas));
		teamTemplate.put("count", String.valueOf(count));
		teamTemplate.put("currpage", String.valueOf(currpage));
		teamTemplate.put("totalpage", String.valueOf(totalpage));
		teamTemplate.put("search_teamName", search_teamName);
		return teamTemplate;
	}
}
