package com.product.salary.application;

import com.product.salary.application.service.HopDongService;
import com.product.salary.application.service.LuongNhanVienService;
import com.product.salary.application.service.impl.HopDongServiceImpl;
import com.product.salary.application.service.impl.LuongNhanVienServiceImpl;
import com.product.salary.application.utils.AppUtils;
import jakarta.persistence.Persistence;

public class TestApp {
    public static void main(String[] args) {
        var em = Persistence.createEntityManagerFactory("LuongSanPham MSSQL").createEntityManager();
    }
}
