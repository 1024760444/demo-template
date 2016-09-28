package com.yhaitao.hadoop.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * java调用hive的api。
 * 
 * @author yanghaitao
 *
 */
public class HiveTemplate {
	/**
	 * 测试代码，对hive的语句类似mysql。 
	 */
	public static void main(String[] args) throws Throwable {
		Properties properties = new Properties();
		properties.setProperty("hive.drivername", "org.apache.hive.jdbc.HiveDriver");
		properties.setProperty("hive.url", "jdbc:hive2://172.16.134.244:10000/default");
		properties.setProperty("hive.user", "hive");
		properties.setProperty("hive.password", "000000");
		
		/** 创建链接 **/
		Connection connection = getConnection(properties);
		
		/** 建表
		create(connection);
		 **/
		
		/** 查询当前库中所有表名称 **/
		showTables(connection);
	}
	
	/**
	 * 创建到hive的链接。
	 * 
	 * @param properties
	 *            创建hive链接需要的参数。
	 *            驱动(hive.drivername): org.apache.hive.jdbc.HiveDriver 
	 *            URL(hive.url): jdbc:hive2://172.16.134.244:10000/default 
	 *            USER(hive.user): hive 
	 *            PASSED(hive.password): 000000
	 * 
	 * @return hive的链接
	 * @throws Throwable
	 */
	public static Connection getConnection(Properties properties) throws Throwable {
		Class.forName(properties.getProperty("hive.drivername"));
		return DriverManager.getConnection(properties.getProperty("hive.url"), properties.getProperty("hive.user", ""),
				properties.getProperty("hive.password", ""));
	}
	
	/**
	 * 创建表，设置分区等。
	 * sql : create external table yht_user1 (id int, name string, height double, weight double) partitioned by (par_date string);
	 * @throws SQLException 
	 */
	public static void create(Connection connection) throws SQLException {
		String sql = "create external table yht_user1 (id int, name string, height double, weight double) partitioned by (par_date string)";
		Statement stmt = connection.createStatement();
		boolean execute = stmt.execute(sql);
		System.out.println(execute); // false： 执行成功。
	}
	
	/**
	 * 获取当前库中所有表名
	 * sql : show tables;
	 * @throws SQLException 
	 * 
	 */
	public static void showTables(Connection connection) throws SQLException {
		String sql = "show tables";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()) {
			String string = rs.getString("tab_name");
			System.out.println(string);
		}
	}
}
