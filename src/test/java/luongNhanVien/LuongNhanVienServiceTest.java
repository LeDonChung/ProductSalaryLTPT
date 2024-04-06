package luongNhanVien;

import com.product.salary.application.service.LuongNhanVienService;
import com.product.salary.application.service.impl.LuongNhanVienServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LuongNhanVienServiceTest {
    private LuongNhanVienService luongNhanVienService;
    @BeforeEach
    public void setUp() {
        luongNhanVienService = new LuongNhanVienServiceImpl();
    }

    @Test
    void timKiemTatCaLuongNhanVienTheoThangVaNamTest(){
        luongNhanVienService.timKiemTatCaLuongNhanVienTheoThangVaNam(1, 2023).forEach(System.out::println);
    }

    @Test
    void tinhLuongNhanVienTest(){
        luongNhanVienService.tinhLuongNhanVien( 4, 2024);
    }

    @Test
    void timTatCaChiTietLuongTheoThangVaNamTest(){
        luongNhanVienService.timTatCaChiTietLuongTheoThangVaNam("1020170002", 1, 2023).forEach(System.out::println);
    }

    @Test
    void thongKeLuongNhanVienBangThangVaNamTest(){
        luongNhanVienService.thongKeLuongNhanVienBangThangVaNam(1, 2023)
                .forEach((key, value) -> System.out.println(key + " : " + value));
    }

    @Test
    void capNhatLuongThuongTest(){
        luongNhanVienService.capNhatLuongThuong("0420241020220002", 1000000);
    }
}
