package com.product.salary.application;

import com.product.salary.application.service.HopDongService;
import com.product.salary.application.service.impl.HopDongServiceImpl;

public class TestApp {
    public static void main(String[] args) {
        HopDongService hopDongService = new HopDongServiceImpl();
        hopDongService.timTatCaChiTietHopDongBangMaHopDong("5014112301").forEach(System.out::println);
    }
}
