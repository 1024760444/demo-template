package com.yhaitao.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import com.yhaitao.rmi.Constant;
import com.yhaitao.rmi.RemoteService;
import com.yhaitao.rmi.ZooKeeperUtil;

/**
 * 
 * @author admin
 *
 */
public class ClientConsumer extends ZooKeeperUtil {
	/**
	 * rmi服务列表。
	 */
	private List<String> rmiUrlList = new ArrayList<String>();
	
	/**
	 * 初始化消费端，连接ZK。
	 */
	public ClientConsumer() {
		// 连接ZK
		ZooKeeper zk = this.connServer();
		if(zk != null) {
			watchNode(zk);
		}
	}
	
	/**
	 * 监控ZK的节点。
	 * @param zk zk连接
	 */
	private void watchNode(final ZooKeeper zk) {
		try {
			// 获取所有子节点。
			List<String> nodeList = zk.getChildren(Constant.ZK_RMI_PATH, new Watcher() {
				@Override
				public void process(WatchedEvent event) {
					if(event.getType() == Event.EventType.NodeChildrenChanged) {
						watchNode(zk);
					}
				}
			});
			
			// 遍历子节点，获取所有子节点数据，即获取所有rmi服务地址列表
			rmiUrlList.clear();
			for(String node : nodeList) {
				byte[] data = zk.getData(Constant.ZK_RMI_PATH + "/" + node, false, null);
				if(data != null) {
					rmiUrlList.add(new String(data));
				}
			}
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取远程服务对象。
	 * @return 远程服务对象
	 * @throws NotBoundException 
	 * @throws RemoteException 
	 * @throws MalformedURLException 
	 */
	@SuppressWarnings("unchecked")
	private <T extends Remote> T lookup() throws MalformedURLException, RemoteException, NotBoundException {
		T service = null;
		int size = rmiUrlList.size();
		if(size > 0) {
			String rmiUrl = null;
			if(size == 1) {
				rmiUrl = rmiUrlList.get(0);
			} else {
				rmiUrl = rmiUrlList.get(ThreadLocalRandom.current().nextInt(size));
			}
			service = (T) Naming.lookup(rmiUrl);
			System.err.println(rmiUrl);
		}
		return service;
	}
	
	/**
	 * 客户端调用
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws Exception {
		// 入参
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("key", 20);
		
		// 消费创建
		ClientConsumer consumer = new ClientConsumer();
		
		while(true) {
			RemoteService service = consumer.lookup();
			Map<String, Object> service2 = service.service(params);
			System.err.println(service2.get("key"));
			Thread.sleep(3000);
		}
	}
	
}
