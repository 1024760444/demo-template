package main;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import com.yhaitao.mahout.kmeans.utils.HtmlUtils;

/**
 * 模拟登陆， 获取网页。
 * @author admin
 *
 */
public class QzoneCrawlerTest {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		String baseUrl = "http://bjlot.com";
		
		// 设置连接池大小
		PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager();
		connMgr.setMaxTotal(100);
		connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());
		
		// 超时配置
        RequestConfig.Builder configBuilder = RequestConfig.custom();  
        configBuilder.setConnectTimeout(30000);  
        configBuilder.setSocketTimeout(30000);  
        configBuilder.setConnectionRequestTimeout(30000);  
        configBuilder.setStaleConnectionCheckEnabled(true);  
        RequestConfig requestConfig = configBuilder.build();
        
		// 创建https客户端
		HttpClient client = HttpClients.custom()
				.setSSLSocketFactory(createSSLConnSocketFactory())
				.setConnectionManager(connMgr)
				.setDefaultRequestConfig(requestConfig).build();
		
		HttpResponse response = client.execute(new HttpGet(baseUrl));
		HttpEntity resEntity = response.getEntity();
		String result = EntityUtils.toString(resEntity, "UTF-8");
		try {
			
			List<String> pageUrls = HtmlUtils.getPageUrls(result);
			for(String urls : pageUrls) {
				if(!urls.contains("javascript:void(0)") && urls.length() > 1) {
					System.err.println(urls.startsWith("http") ? urls : (baseUrl + urls));
				}
			}
			
			
//			String filterStyle = HtmlUtils.filterStyle(result);
//			String filterScript = HtmlUtils.filterScript(filterStyle);
//			String filterHTML = HtmlUtils.filterHTML(filterScript);
//			System.err.println(filterHTML);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建SSL安全连接
	 * @return
	 */
	private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
		SSLConnectionSocketFactory sslsf = null;
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {
				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
				@Override
				public void verify(String host, SSLSocket ssl) throws IOException {
				}
				@Override
				public void verify(String host, X509Certificate cert) throws SSLException {
				}
				@Override
				public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
				}
			});
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		return sslsf;
	}
}
