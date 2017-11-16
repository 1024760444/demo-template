package com.yhaitao.rmi.server;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yhaitao.rmi.RemoteService;
import com.yhaitao.rmi.ZooKeeperUtil;
import com.yhaitao.rmi.impl.RemoteServiceImpl;

/**
 * 向ZK发布RMI服务
 * @author admin
 *
 */
public class ServerProvider extends ZooKeeperUtil {
	/**
	 * 日志对象
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(ServerProvider.class);
	
	/**
	 * 启动服务，并发布到zk中
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		// 服务实现
		RemoteService remote = new RemoteServiceImpl();
		String ip = "localhost";
		int port = 7103;
		
		// 启动服务，并发布到zk中
		ServerProvider serverProvider = new ServerProvider();
		serverProvider.register(remote, ip, port);
	}
	
	/**
	 * 注册服务。
	 * @param remote 服务提供类
	 * @param ip 服务IP
	 * @param port 服务端口
	 */
	public void register(Remote remote, String ip, int port) {
		// 发布服务，获取rmi服务的地址
		String rmiUrl = publish(remote, ip, port);
		
		// 获取zk连接
		ZooKeeper zk = connServer();
		
		// 将rmi服务地址，发布到zk上
		if(zk != null && rmiUrl != null) {
			createNode(zk, rmiUrl);
		}
	}
	
	/**
	 * 发布RMI服务。
	 * @param remote RMI服务的实现
	 * @param ip 发布的IP地址
	 * @param port 发布的端口
	 * @return 发布的RMI地址
	 */
	private String publish(Remote remote, String ip, int port){
		String rmiUrl = null;
		try {
			// 组建rmi连接
			rmiUrl = String.format("rmi://%s:%s/%s", ip, port, remote.getClass().getName());
			LOGGER.info("publish url : {}. ", rmiUrl);
			
			// 启动rmi服务
			LocateRegistry.createRegistry(port);
			Naming.bind(rmiUrl, remote);
			LOGGER.info("publish Service has started ... ");
		} catch (Exception e) {
			LOGGER.error("publish url : {} failed! Exception : {}. ", rmiUrl, e.getMessage());
			rmiUrl = null;
		}
		return rmiUrl;
	}
}
