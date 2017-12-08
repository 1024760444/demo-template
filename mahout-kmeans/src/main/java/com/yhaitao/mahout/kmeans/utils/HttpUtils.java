package com.yhaitao.mahout.kmeans.utils;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

/**
 * Http请求工具。
 * @author yhaitao
 *
 */
public class HttpUtils {
	/**
	 * 抓取指定网页，获取网页文本。
	 * @param url 需要抓取的网页地址
	 * @return 网页文本
	 * @throws IOException 
	 * @throws Exception get请求网页错误时或者解析响应时
	 */
	public static String httpGet(String baseUrl) throws IOException {
		// 设置连接池大小
		PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager();
		connMgr.setMaxTotal(100);
		connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

		// 超时配置
		RequestConfig.Builder configBuilder = RequestConfig.custom();
		configBuilder.setConnectTimeout(5000);
		configBuilder.setSocketTimeout(5000);
		configBuilder.setConnectionRequestTimeout(5000);
		configBuilder.setStaleConnectionCheckEnabled(true);
		RequestConfig requestConfig = configBuilder.build();

		// 创建http客户端
		HttpClient client = HttpClients.custom()
				.setConnectionManager(connMgr)
				.setDefaultRequestConfig(requestConfig)
				.build();

		// 抓取网页， 解析响应获取网页文本。
		HttpResponse response = client.execute(new HttpGet(baseUrl));
		return EntityUtils.toString(response.getEntity(), "UTF-8");
	}
}
