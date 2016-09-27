package com.yhaitao.manager.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yhaitao.manager.dao.mapper.FileMapper;
import com.yhaitao.manager.dao.mapper.TeamMapper;
import com.yhaitao.manager.dao.mapper.UserMapper;
import com.yhaitao.manager.dao.pojo.FilePojo;
import com.yhaitao.manager.dao.pojo.Team;
import com.yhaitao.manager.dao.pojo.User;
import com.yhaitao.manager.util.Cons;
import com.yhaitao.manager.util.TimerUtil;

/**
 * 文件页面操作。
 * @author yanghaitao
 *
 */
@Controller
@RequestMapping("file")
public class FileController {
	private static Logger logger = LoggerFactory.getLogger(FileController.class);
	
	/**
	 * 文件信息表接口
	 */
	@Resource
	private FileMapper fileMapper;
	
	/**
	 * 用户分组
	 */
	@Resource
	private TeamMapper teamMapper;
	
	/**
	 * 用户信息
	 */
	@Resource
	private UserMapper userMapper;
	
	/**
	 * 跳转到用户信息页。
	 * @param model
	 * @return 
	 */
	@RequestMapping("/toFileList")
	public String toFileList(HttpServletRequest request, Model model) {
		String uid = request.getParameter("uid");
		model.addAttribute("domain", Cons.domain);
		model.addAttribute("uid", uid);
		return "docs/_file_list";
	}
	
	/**
	 * 跳转到用户信息页。
	 * @param model
	 * @return 
	 */
	@RequestMapping("/toMyFiles")
	public String toMyFiles(HttpServletRequest request, Model model) {
		String uid = request.getParameter("uid");
		model.addAttribute("domain", Cons.domain);
		model.addAttribute("uid", uid);
		return "docs/_my_file";
	}
	
	/**
	 * 浏览当前文件列表。
	 */
	@ResponseBody
	@RequestMapping(value = "/scan", method = RequestMethod.GET)
	public Map<String, String> scan(HttpServletRequest request, Model model) {
		/** 分页：当前页，每页记录数 **/
		String currPageString = request.getParameter("currpage");
		String perpageString = request.getParameter("perpage");
		String search_fileName = request.getParameter("search_fileName");
		if(search_fileName != null) {
			try {
				search_fileName = new String(search_fileName.getBytes("iso-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				search_fileName = null;
			}
		}
		String search_teamId = request.getParameter("search_teamId");
		int currpage = Cons.strToInt(currPageString, 1);
		int perpage = Cons.strToInt(perpageString, 10);
		
		HttpSession session = request.getSession();
		int userId = -1;
		try {
			userId = (Integer) session.getAttribute("userId");
		} catch (Exception e) {
			userId = -1;
		}
		int teamId = Cons.strToInt(search_teamId, -1);
		
		/** 根据分页查询信息  **/
		Map<String, String> input = new HashMap<String, String>();
		if(search_fileName != null && !"".equals(search_fileName)) {
			input.put("fileName", search_fileName.trim());
		}
		if(userId != -1) {
			input.put("userId", String.valueOf(userId));
		} else {
			input.put("showId", String.valueOf(2));
		}
		if(teamId != -1) {
			input.put("teamId", String.valueOf(teamId));
		}
		input.put("orderby", "createDate");
		input.put("start", String.valueOf((currpage - 1) * perpage));
		input.put("perpage", String.valueOf(perpage));
		List<FilePojo> filePojoList = fileMapper.selectOnPage(input);
		
		/** 获取当前用户总量 **/
		int count = fileMapper.count(input);
		int totalpage = (int)Math.ceil((double)count/perpage);
		
		// 所有分组列表
		List<Team> teamList = teamMapper.selectOnPage(new HashMap<String, String>());
		
		/** 查询到的数据和其他相关数据返回到页面 **/
		Map<String, String> roleTemplate = new HashMap<String, String>();
		roleTemplate.put("datas", Cons.gson.toJson(filePojoList));
		roleTemplate.put("count", String.valueOf(count));
		roleTemplate.put("currpage", String.valueOf(currpage));
		roleTemplate.put("totalpage", String.valueOf(totalpage));
		roleTemplate.put("teamIdList", Cons.gson.toJson(teamList));
		roleTemplate.put("search_fileName", search_fileName);
		return roleTemplate;
	}
	
	/**
	 * 下载文件。
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(HttpServletRequest request, Model model, HttpServletResponse response) {
		/** 获取需要下载的文件编号与下载码 **/
		String idStr = request.getParameter("id");
		String codeStr = request.getParameter("code");
		int id = Cons.strToInt(idStr, -1);
		if(id == -1) {
			return ;
		}
		
		/** 查询文件信息 **/
		Map<String, String> input = new HashMap<String, String>();
		input.put("id", idStr);
		List<FilePojo> selectOnPage = fileMapper.selectOnPage(input);
		if(selectOnPage == null || selectOnPage.isEmpty()) {
			return ;
		}
		
		/** 寻找文件，查看文件是否存在 **/
		FilePojo filePojo = selectOnPage.get(0);
		if(filePojo == null) {
			return ;
		}
		/** 验证下载码 **/
		String fileName = filePojo.getFileName();
		String downCode = filePojo.getDownCode();
		if(downCode != null && !"".equals(downCode) && !codeStr.equals(downCode)) {
			return ;
		}
		String timerPath = "20190921";
		try {
			timerPath = TimerUtil.getDateFormat(filePojo.getCreateDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file = new File(Cons.basePath + timerPath + "/" + fileName);
		if(!file.exists()) {
			return ;
		}
		
		/** 将文件以输出流的方式，写到response中 **/
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			response.setContentType("application/octet-stream; charset=utf-8");
			// response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"),"iso-8859-1") + "\"");
			inputStream = new FileInputStream(file);
			outputStream = response.getOutputStream();
			IOUtils.copy(inputStream, outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 上传文件。
	 */
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Map<String, String> upload(HttpServletRequest request, Model model, 
			@RequestParam MultipartFile uploadFile) {
		// 页面传来的配置信息
		String userIdStr = request.getParameter("userId");
		String showIdStr = request.getParameter("showId");
		String downIdStr = request.getParameter("downId");
		String downCode = request.getParameter("downCode");
		int userId = Cons.strToInt(userIdStr, -1);
		int showId = Cons.strToInt(showIdStr, 2);
		int downId = Cons.strToInt(downIdStr, 0);
		
		// 如果用户不为空
		if (uploadFile != null && !uploadFile.isEmpty()) {
			String originalName = uploadFile.getOriginalFilename();
			if(userId != -1) {
				List<User> selectById = userMapper.selectById(userId);
				if(selectById != null && !selectById.isEmpty()) {
					long fileSize = uploadFile.getSize();
					
					// 组装数据库的文件信息
					User user = selectById.get(0);
					FilePojo filePojo = new FilePojo();
					filePojo.setFileName(originalName);
					filePojo.setFileDesc(String.valueOf(Cons.decimalTwo((double)fileSize/(1024))));
					filePojo.setTeamId(user.getTeamId());
					filePojo.setUserId(userId);
					filePojo.setShowId(showId);
					filePojo.setDownId(downId);
					filePojo.setDownCode(downCode);
					
					// 系统时间
					String timeStamp = TimerUtil.getSystemDate();
					filePojo.setCreateDate(TimerUtil.getSystemDate());
					
					// 文件保存路径的时间戳
					String timerPath = "20190921";
					try {
						timerPath = TimerUtil.getDateFormat(TimerUtil.getSystemDate());
					} catch (ParseException e) {
						timerPath = "20190921";
						logger.error("ParseException When Parse timeStamp : " + timeStamp + ", Use Default : " + timerPath);
					}
					
					// 创建文件夹路径
					File folder = new File(Cons.basePath + timerPath);
					if(!folder.exists()) {
						folder.mkdirs();
					}
					
					// 存储目标文件
					File uploaderFile = new File(folder.getPath() + "/" + originalName);
					try {
						uploadFile.transferTo(uploaderFile);
						fileMapper.insert(filePojo);
					} catch (Exception e) {
						logger.error("Exception : " + e.getMessage() + ", When load file : " + originalName);
					}
				}
			}
		}
		return scan(request, model);
	}
}
