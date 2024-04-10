package com.product.salary.application.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * SHA-1 là một trong những thuật toán băm mã hóa, 
 * được dùng trong việc kiểm tra tính toàn vẹn của dữ liệu ở phía người nhận. 
 * SHA-1 checksum được so sánh giữa người cung cấp và người nhận, 
 * dữ liệu được cho là toàn vẹn nếu hai chuỗi checksum là giống nhau.
 * VD: 123456: 7c4a8d09ca3762af61e59520943dc26494f8941b
*/

public class PasswordUtils {
	/* Mã hóa */
	public static String toSHA1(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] messageDigest = md.digest(password.getBytes());

			BigInteger no = new BigInteger(1, messageDigest);

			String hashtext = no.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			return hashtext;
		}

		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
