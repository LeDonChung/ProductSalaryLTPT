package com.product.salary.application.dao.impl;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public abstract class AbstractDAO {

	private final ResourceBundle BUNDLE = ResourceBundle.getBundle("app");

	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(BUNDLE.getString("driver_name"));
			String url = BUNDLE.getString("url");
			String userName = BUNDLE.getString("username");
			String password = BUNDLE.getString("password");
			conn = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Cơ sở dữ liệu hiện đang lỗi. Vui lòng thử lại sau!");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

}
