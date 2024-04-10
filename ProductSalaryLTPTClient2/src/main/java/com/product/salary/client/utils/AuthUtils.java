package com.product.salary.client.utils;

import java.io.*;

/**
 * 
 * @author Lê Đôn Chủng Lưu đăng nhập
 */
public class AuthUtils {
	/*
	 * Lưu thông tin đăng nhập o: Tài khoản đăng nhập vào hệ thống
	 */
	public static void login(Object o) {
		try {
			String fileName = "./src/login.bat";
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(o);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Xóa thông tin đăng nhập
	 * 
	 * @return true logout thành công false logout không thành công
	 */
	public static boolean logout() {
		try {
			String fileName = "./src/login.bat";
			File file = new File(fileName);
			return file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Lấy thông tin đăng nhập
	 * 
	 * @return Object: thông tin đăng nhập
	 */
	public static Object getUser() {
		try {
			String fileName = "./src/login.bat";
			FileInputStream fin = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fin);
			Object o = ois.readObject();
			ois.close();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
