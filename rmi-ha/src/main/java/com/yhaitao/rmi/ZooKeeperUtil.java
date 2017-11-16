package com.yhaitao.rmi;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ZooKeeper操作工具
 * @author admin
 *
 */
public class ZooKeeperUtil {
	/**
	 * 日志对象
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(ZooKeeperUtil.class);
	
	/**
	 * 同步工具类
	 */
	private CountDownLatch latch = new CountDownLatch(1);

	/**
	 * 连接ZK服务器。
	 * @return ZK连接
	 */
	public ZooKeeper connServer() {
		ZooKeeper zk = null;
		try {
			// 创建ZK连接，通过Watcher和CountDownLatch，等待创建成功后再返回
			zk = new ZooKeeper(Constant.ZK_HOSTS, Constant.ZK_CONN_TIMEOUT, new Watcher() {
				@Override
				public void process(WatchedEvent event) {
					if(event.getState() == Event.KeeperState.SyncConnected) {
						latch.countDown();
					}
				}
			});
			latch.await();
			LOGGER.info("connServer ZK : {} success ... ", Constant.ZK_HOSTS);
		} catch (Exception e) {
			LOGGER.error("connServer ZK : {} failed! Exception : {}. ", Constant.ZK_HOSTS, e.getMessage());
		}
		return zk;
	}

	/**
	 * 创建一个ZK节点，并将rmi服务地址保存到该节点中
	 * @param zk zk连接
	 * @param rmiUrl 需要写入节点的数据
	 */
	public void createNode(ZooKeeper zk, String rmiUrl) {
		try {
			String path = zk.create(Constant.ZK_RMI_BASIS, 
					rmiUrl.getBytes(), 
					ZooDefs.Ids.OPEN_ACL_UNSAFE, 
					CreateMode.EPHEMERAL_SEQUENTIAL);
			LOGGER.info("createNode rmiUrl : {} to path : {}. ", rmiUrl, path);
		} catch (Exception e) {
			LOGGER.error("createNode Exception : {}. ", e.getMessage());
		}
	}
}
