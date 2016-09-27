package com.apm.echart.http;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.apm.echart.http.model.HomeData;
import com.apm.echart.util.ComUtil;

/**
 * 首页操作控制。
 * @author yanghaitao
 *
 */
@Controller
public class HomeController {
	
	/**
	 * 根据一般信息，跳转到首页。
	 */
	@RequestMapping("/home")
	public String toHome(Model model) {
		HomeData homeData = new HomeData();
		homeData.setTitle("H+ 后台主题UI框架 - 主页");
		homeData.setKeyword("");
		homeData.setDesc("");
		
		model.addAttribute("homeData", homeData);
		model.addAttribute("domain", ComUtil.DOMAIN);
		return "index";
	}
}
