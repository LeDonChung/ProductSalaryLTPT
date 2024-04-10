package com.product.salary.application.utils;

import java.text.DecimalFormat;

public class PriceFormatterUtils {

	public static String format(double price) {
		return new DecimalFormat("#,###").format(price);
	}

	public static double parse(String price) {
		double result = 0;
		try {
			price = price.replace(",", "");
			result = Double.valueOf(price);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
