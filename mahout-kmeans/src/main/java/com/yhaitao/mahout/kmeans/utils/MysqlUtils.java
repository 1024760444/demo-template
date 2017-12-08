package com.yhaitao.mahout.kmeans.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Mysql链接工具。
 * @author yhaitao
 *
 */
public class MysqlUtils {
	/**
	 * Mysql驱动类
	 */
	private final static String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	
	/**
	 * 独立创建一个Mysql链接。
	 * @param url 数据库地址
	 * @param user 数据库用户
	 * @param password 数据库密码
	 * @return 数据库链接
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static Connection getConnection(String url, String user, String password) throws ClassNotFoundException, SQLException {
		Class.forName(MYSQL_DRIVER);
		return DriverManager.getConnection(url, user, password);
	}
	
	/**
	 * 关闭连接。
	 * @param connection 连接
	 * @param statement 状态
	 * @param resultSet 结果
	 * @throws SQLException 
	 */
	public static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
		if(connection != null && !connection.isClosed()) {
			connection.close();
		}
		if(statement != null && !statement.isClosed()) {
			statement.close();
		}
		if(resultSet != null && !resultSet.isClosed()) {
			resultSet.close();
		}
	}
}
