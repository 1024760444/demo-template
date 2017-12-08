package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yhaitao.mahout.kmeans.bean.BaseUrlBean;
import com.yhaitao.mahout.kmeans.utils.HtmlUtils;
import com.yhaitao.mahout.kmeans.utils.HttpUtils;
import com.yhaitao.mahout.kmeans.utils.MysqlUtils;

public class TestMysql {
	/**
	 * 爬虫抓取网页，并分词统计单纯的词频。
	 * @param args 基础网页所村数据库信息
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args){
		// 解析获取数据库链接信息
		String password = "Iot-IG123";
		String user = "root";
		String url = "jdbc:mysql://172.19.10.11:3306/kmeans";
		
		// 基础网页信息
		List<BaseUrlBean> beanUrls = null;
		try {
			beanUrls = getBeanUrls(url, user, password);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return ;
		}
		
		// 网页抓取与分词
		for(BaseUrlBean urlBean : beanUrls) {
			// 基本地址组织
			String baseUrl = urlBean.getUrl().startsWith("http://") ? urlBean.getUrl() : ("http://" + urlBean.getUrl());
			
			// 抓取网页
			String htmlContext = httpGet(baseUrl);
			if(htmlContext == null) {
				continue;
			}
			
			// 网页内容文本 
			filterHTML(htmlContext);
			
			// 获取网页中的子网页
			HtmlUtils.getPageUrls(htmlContext);
		}
	}
	
	/**
	 * 过滤掉html标签，获取网页内容文本。
	 * @param htmlContext 网页文本
	 * @return 网页内容文本 
	 */
	private static String filterHTML(String htmlContext) {
		// 过滤html标签，获取网页内容文本。
		String context = null;
		try {
			String filterStyle = HtmlUtils.filterStyle(htmlContext);
			String filterScript = HtmlUtils.filterScript(filterStyle);
			context = HtmlUtils.filterHTML(filterScript);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return context;
	}
	
	/**
	 * 抓取网页
	 * @param url 网页地址
	 * @return 网页全部文本
	 */
	private static String httpGet(String baseUrl) {
		String htmlContext = null;
		try {
			htmlContext = HttpUtils.httpGet(baseUrl);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return htmlContext;
	}
	
	/**
	 * 查询基础网页信息。
	 * @param args 数据库信息
	 * @return 基础网页信息
	 * @throws Exception 
	 */
	private static List<BaseUrlBean> getBeanUrls(String url, String user, String password) throws Exception {
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
			System.err.println(e.getMessage());
		}
		return beanUrls;
	}
}
