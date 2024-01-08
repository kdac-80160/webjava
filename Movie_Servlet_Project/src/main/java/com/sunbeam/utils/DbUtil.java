package com.sunbeam.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/mahendra";
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "manager";
	
	static{
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static Connection getConnection() throws Exception
	{
		Connection con = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
		return con;
	}
}
