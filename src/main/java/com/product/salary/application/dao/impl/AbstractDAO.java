package com.product.salary.application.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public abstract class AbstractDAO {

	private final ResourceBundle resourceBundle = ResourceBundle.getBundle("app");

	public EntityManager getEntityManager() {
		return Persistence
				.createEntityManagerFactory(resourceBundle.getString("persistence-unit"))
				.createEntityManager();
	}

	public Connection getConnection() {
		Connection connect = null;
		try {
			Class.forName(resourceBundle.getString("driver"));
			connect = DriverManager.getConnection(resourceBundle.getString("url"), resourceBundle.getString("user"),
					resourceBundle.getString("password"));
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Không tìm thấy driver");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể kết nối với cơ sở dữ liệu");
		}
		return connect;
	}

}
