package com.yhaitao.mahout.kmeans;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yhaitao.mahout.kmeans.bean.BaseUrlBean;
import com.yhaitao.mahout.kmeans.utils.HtmlUtils;
import com.yhaitao.mahout.kmeans.utils.HttpUtils;
import com.yhaitao.mahout.kmeans.utils.MysqlUtils;

/**
 * 抓取网页
 * @author yhaitao
 *
 */
public class Crawler {
	/**
	 * 分词器。
	 */
	private KmeansIKAnalyzer kmeansIK;
	
	/**
	 * 爬虫，抓取网页
	 */
	public void crawler(){
		// 解析获取数据库链接信息
		String password = "Iot-IG123";
		String user = "root";
		String url = "jdbc:mysql://172.19.10.11:3306/kmeans";
		kmeansIK = new KmeansIKAnalyzer();
		
		// 基础网页信息
		List<BaseUrlBean> beanUrls = null;
		try {
			beanUrls = getBeanUrls(url, user, password);
		} catch (Exception e) {
			System.err.println(errMesg("crawler", null, e.getMessage()));
			return ;
		}
		
		// 网页抓取与分词
		for(BaseUrlBean urlBean : beanUrls) {
			crawler(urlBean);
		}
	}
	
	/**
	 * 爬取一个基础网页的信息。
	 * @param urlBean 基础网页对象
	 */
	private void crawler(BaseUrlBean urlBean) {
		// 基本地址组织
		String baseUrl = urlBean.getUrl().startsWith("http://") ? urlBean.getUrl() : ("http://" + urlBean.getUrl());
		
		// 抓取网页
		String htmlContext = httpGet(baseUrl);
		if(htmlContext == null) {
			return ;
		}
		
		// 网页内容文本 
		String filterHTML = filterHTML(htmlContext);
		
		// 网页分词
		Map<String, Double> termFreq = analyzer(filterHTML);
		
		// 获取网页中的子网页
		List<String> pageUrls = HtmlUtils.getPageUrls(htmlContext);
	}
	
	/**
	 * 分词获取词频。
	 * @param filterHTML 需要分词的网页内容文本
	 * @return 网页词频
	 */
	private Map<String, Double> analyzer(String filterHTML) {
		Map<String, Double> termFreq = new HashMap<String, Double>();
		try {
			termFreq = kmeansIK.analyzer(filterHTML);
		} catch (IOException e) {
			System.err.println(errMesg("analyzer", null, e.getMessage()));
		}
		return termFreq;
	}
	
	/**
	 * 过滤掉html标签，获取网页内容文本。
	 * @param htmlContext 网页文本
	 * @return 网页内容文本 
	 */
	private String filterHTML(String htmlContext) {
		// 过滤html标签，获取网页内容文本。
		String context = null;
		try {
			String filterStyle = HtmlUtils.filterStyle(htmlContext);
			String filterScript = HtmlUtils.filterScript(filterStyle);
			context = HtmlUtils.filterHTML(filterScript);
		} catch (Exception e) {
			System.err.println(errMesg("filterHTML", null, e.getMessage()));
		}
		return context;
	}
	
	/**
	 * 抓取网页
	 * @param url 网页地址
	 * @return 网页全部文本
	 */
	private String httpGet(String baseUrl) {
		String htmlContext = null;
		try {
			htmlContext = HttpUtils.httpGet(baseUrl);
		} catch (IOException e) {
			System.err.println(errMesg("httpGet", baseUrl, e.getMessage()));
		}
		return htmlContext;
	}
	
	/**
	 * 查询基础网页信息。
	 * @param args 数据库信息
	 * @return 基础网页信息
	 * @throws Exception 
	 */
	private List<BaseUrlBean> getBeanUrls(String url, String user, String password) throws Exception {
		String sql = "select id, sname, superId, url from t_mark_chinaz_urls";
		// 创建数据库链接
		Connection connection = MysqlUtils.getConnection(url, user, password);
		Statement statement = connection.createStatement();
		
		// 查询解析网页信息
		ResultSet resultSet = statement.executeQuery(sql);
		List<BaseUrlBean> beanUrls = new ArrayList<BaseUrlBean>();
		while(resultSet.next()) {
			BaseUrlBean urlBean = new BaseUrlBean();
			urlBean.setId(resultSet.getInt("id"));
			urlBean.setSuperId(resultSet.getInt("superId"));
			urlBean.setSname(resultSet.getString("sname"));
			urlBean.setUrl(resultSet.getString("url"));
			beanUrls.add(urlBean);
		}
		
		// 关闭数据库相关连接
		try {
			MysqlUtils.close(connection, statement, resultSet);
		} catch (Exception e) {
			System.err.println(errMesg("getBeanUrls", url, e.getMessage()));
		}
		return beanUrls;
	}
	
	/**
	 * 规范化错误信息
	 * @param url 抓取异常的url地址
	 * @param eMsg 异常信息。
	 * @return 规范的异常信息
	 */
	private String errMesg(String method, String url, String message) {
		return new StringBuffer()
				.append("Crawler.")
				.append(method)
				.append(" : ")
				.append(url)
				.append(", Message : ")
				.append(message).toString();
	}
}
