package com.product.salary.application.common;

import com.product.salary.application.utils.LanguageUtils;

import java.util.ResourceBundle;

public class SystemConstants {
	// resource :src/
	public static ResourceBundle BUNDLE;
	// language default = 0
	public static Integer LANGUAGE = 1; // 0 VN, 1 EN

	/**
	 * Lấy file bundle
	 */
	public static void initLanguage() {
		LANGUAGE = (Integer) LanguageUtils.getLanguage();
		if (LANGUAGE == null) {
			LANGUAGE = 0;
		}

		if (LANGUAGE == 0) {
			BUNDLE = ResourceBundle.getBundle("VN");
		} else if (LANGUAGE == 1) {
			BUNDLE = ResourceBundle.getBundle("EN");
		}
	}
}
