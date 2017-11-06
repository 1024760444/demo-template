package main;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;
import org.glassfish.jersey.SslConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.container.ContainerFactory;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

/**
 * 启动服务。
 * 参考文章： http://blog.csdn.net/cc41798520101/article/details/52510693
 * @author admin
 *
 */
public class StartRest {
	/**
	 * slfj工具
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(StartRest.class);
	
	/**
	 * 数字认证参数
	 */
	public static final Map<String, String> SSLDATAMAP = new HashMap<String, String>();
	
	/**
	 * 启动协议  1 http， 2 https
	 * 基础地址  http://localhost/ https://localhost/
	 * 端口 : 8080
	 * 数字证书地址： D:/yanghaitao/conf/rest/server.jks
	 * storepass
	 * keypass
	 * 例如： 1 http://localhost/ 8980 
	 * 例如： 2 https://localhost/ 8980 D:/yanghaitao/conf/rest/server.jks rms123 iot123
	 * 启动本地服务。
	 */
	public static void main(String[] args) {
		// 
		if(args == null) {
			LOGGER.error("Has no parameters ! ");
			return ;
		}
		
		// 启动类型
		int type = strToInt(args[0], 1);
		try {
			if(type == 1) {
				startHttp(args);
			} else {
				startHttps(args);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * https的方式启动。
	 * @param args 
	 * @throws IOException 
	 * @throws NullPointerException 
	 * @throws IllegalArgumentException 
	 */
	public static void startHttps(String[] args) throws IllegalArgumentException, NullPointerException, IOException {
		// 参数获取
		int authType = 1;
		int port = 8980;
		String url = "http://localhost/";
		String serverCer = null;
		String serverCerPwd = null;
		String serverKeyPwd = null;
		if(args != null && args.length >= 6) {
			url = args[1];
			port = strToInt(args[2], 8980);
			serverCer = args[3];
			serverCerPwd = args[4];
			serverKeyPwd = args[5];
		}
		
		// 参数校验
		if(serverCer == null || serverCerPwd == null || serverKeyPwd == null) {
			LOGGER.error("serverCer or serverCerPwd or serverKeyPwd is null!");
			return ;
		}
		
		/**
		 * 服务器数字认证
		 * trustStoreFile(SSLDATAMAP.get("serverTrustCer"))
		 * trustStorePassword(SSLDATAMAP.get("serverTrustCerPwd"));
		 */
		SslConfigurator sslConfig = SslConfigurator.newInstance();
		sslConfig.keyStoreFile(serverCer)
				.keyStorePassword(serverCerPwd)
				.keyPassword(serverKeyPwd);
		sslConfig.securityProtocol("SSL");
		SSLContext sslContext = sslConfig.createSSLContext();
		SSLEngineConfigurator sslEngineConfig = new SSLEngineConfigurator(sslContext);
		
		/**
		 * 默认情况下是客户端模式，如果忘记修改模式
		 * SSLProtocolException: Handshake message sequence
		 */
		sslEngineConfig.setClientMode(false);
		if (authType == 1)
			sslEngineConfig.setWantClientAuth(true);
		else if (authType == 2)
			sslEngineConfig.setNeedClientAuth(true);
		
		// 构建启动服务
		ResourceConfig rc = new PackagesResourceConfig("com.yhaitao.rest");
		HttpHandler handler = ContainerFactory.createContainer(HttpHandler.class, rc);
		URI base_uri = UriBuilder.fromUri(url).port(port).build();
		HttpServer httpServer = GrizzlyServerFactory.createHttpServer(base_uri, handler, true, sslEngineConfig);
		httpServer.start();
		System.out.println("Jersey app https started!");
		System.in.read();
		httpServer.stop();
	}
	
	/**
	 * Http的方式启动。
	 * @param args
	 * @throws IllegalArgumentException
	 * @throws NullPointerException
	 * @throws IOException
	 */
	public static void startHttp(String[] args) throws IllegalArgumentException, NullPointerException, IOException {
		// 参数解析和默认设置
		int port = 8980;
		String url = "http://localhost/";
		if(args != null && args.length >= 3) {
			url = args[1];
			port = strToInt(args[2], 8980);
		}
		
		// 构建本地服务
		URI base_uri = UriBuilder.fromUri(url).port(port).build();
		ResourceConfig config = new PackagesResourceConfig("com.yhaitao.rest");
		HttpServer httpServer = GrizzlyServerFactory.createHttpServer(base_uri, config);
		System.out.println("Jersey app http started!");
		System.in.read();
		httpServer.stop();
	}
	
	/**
	 * 字符串转换为数字，如果异常返回默认def。
	 * @param str 数字字符串
	 * @param def 默认返回数字
	 * @return 指定数字
	 */
	private static int strToInt(String str, int def) {
		try {
			return Integer.valueOf(str);
		} catch (Exception e) {
			return def;
		}
	}
}
