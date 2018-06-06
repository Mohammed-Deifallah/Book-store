package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connector {

	private static Connection conn = null;
	
	private Connector() {
		
	}
	
	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://127.0.0.1:3306/book_store";
		String username = "root";
		String password = "root";
		Connection connection = null;
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(url, username, password);
		return connection;
	}
	
	public static Connection getInstance() throws ClassNotFoundException, SQLException {
		if(conn == null) {
			conn = getConnection();
		}
		return conn;
	}
}
