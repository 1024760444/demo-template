package com.yhaitao.hadoop.hdfs;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * 
 * @author yanghaitao
 *
 */
public class HDFSTemplate {
	/**
	 * 示例代码
	 */
	public static void main(String[] args) throws IOException {
		/** hadoop集群的hdfs-site配置 **/
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://bigcluster");
		conf.set("dfs.nameservices", "bigcluster");
		conf.set("dfs.ha.namenodes.bigcluster", "nn1,nn2");
		conf.set("dfs.namenode.rpc-address.bigcluster.nn1", "172.16.134.240:8020");
		conf.set("dfs.namenode.rpc-address.bigcluster.nn2", "172.16.134.242:8020");
		conf.set("dfs.client.failover.proxy.provider.bigcluster",
				"org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
		// conf.set("fs.default.name", "hdfs://172.16.134.244:8020");
		
		/** 配置链接 **/
		FileSystem hdfs = FileSystem.get(conf);
		
		/** 查询指定路径是否存在 **/
		Path path = new Path("/yht/001");
		boolean exists = hdfs.exists(path);
		System.out.println(exists);
		
		/** 创建路径
		if(!exists) {
			hdfs.setPermission(path, new FsPermission("755"));
			hdfs.mkdirs(path);
		}
		 **/
	}
}
