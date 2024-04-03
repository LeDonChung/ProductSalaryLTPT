package luongCongNhan;

import com.product.salary.application.service.impl.LuongCongNhanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class LuongCongNhanServiceTest {
    private com.product.salary.application.service.LuongCongNhanService luongCongNhanService;
    @BeforeEach
    void setUp() {
        luongCongNhanService = new LuongCongNhanServiceImpl();
    }
    @Test
    void testTinhLuongCongNhan() {
        var result = luongCongNhanService.tinhLuongCongNhan(4, 2024);
        assert(result);
    }
    @Test
    void testTimTatCaLuongCongNhanTheoThangVaNam() {
        var result = luongCongNhanService.timTatCaLuongCongNhanTheoThangVaNam(4, 2024);
        result.forEach(System.out::println);
    }

    @Test
    void testGenerateMaLuong() {

    }

    @Test
    void testTimTatCaChiTietLuongTheoThangVaNam() {
        var result = luongCongNhanService.timTatCaChiTietLuongTheoThangVaNam("2020220001", 1, 2023);
        result.forEach(System.out::println);
    }

    @Test
    void testThongKeLuongCongNhanTheoNam() {
        var result = luongCongNhanService.thongKeLuongCongNhanTheoNam();
        result.forEach((k, v) -> System.out.println(k + " " + v));
    }

    @Test
    void testThongKeLuongCongNhanBangThangVaNam() {
        var result = luongCongNhanService.thongKeLuongCongNhanBangThangVaNam(1, 2023);
        result.forEach((k, v) -> System.out.println(k + " " + v));
    }

    @Test
    void testCapNhatLuongThuong() {
        luongCongNhanService.capNhatLuongThuong("0420242020220001", 1000000);
    }
}
