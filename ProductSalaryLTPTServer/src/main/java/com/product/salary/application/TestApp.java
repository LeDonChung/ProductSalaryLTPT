package com.product.salary.application;

import com.product.salary.application.service.HopDongService;
import com.product.salary.application.service.LuongNhanVienService;
import com.product.salary.application.service.impl.HopDongServiceImpl;
import com.product.salary.application.service.impl.LuongNhanVienServiceImpl;

public class TestApp {
    public static void main(String[] args) {
        LuongNhanVienService luongNhanVienService = new LuongNhanVienServiceImpl();
    }
}
