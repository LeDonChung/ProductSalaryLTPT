package chamCongNhanVien;

import com.product.salary.application.entity.ChamCongNhanVien;
import com.product.salary.application.service.ChamCongNhanVienService;
import com.product.salary.application.service.impl.ChamCongNhanVienServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ChamCongNhanVienServiceTest {
    private ChamCongNhanVienService chamCongNhanVienService;

    @BeforeEach
    void setUp(){
        chamCongNhanVienService = new ChamCongNhanVienServiceImpl();
    }

    @Test
    void timKiemTatCaChamCongNhanVienTheoCaVaNgayTest(){
        chamCongNhanVienService.timKiemTatCaChamCongNhanVienTheoCaVaNgay(LocalDate.of(2022, 2, 3), "CH").forEach(System.out::println);
    }

    @Test
    void timKiemNhanVienChuaChamCongBangCaLamVaNgayChamCongTest(){
        chamCongNhanVienService.timKiemNhanVienChuaChamCongBangCaLamVaNgayChamCong(LocalDate.of(2024, 2, 3), "CH").forEach(System.out::println);
    }

    @Test
    void themChamCongNhanVienTest() throws Exception {
        ChamCongNhanVien ccnv = new ChamCongNhanVien();
        ccnv.setNgayChamCong(LocalDate.of(2024, 2, 3));
        ccnv.getCaLam().setMaCa("CH");
        ccnv.getNhanVien().setMaNhanVien("1020210003");
         chamCongNhanVienService.themChamCongNhanVien(ccnv);
    }

    @Test
    void timKiemBangMaChamCongNhanVienTest(){
        System.out.println(chamCongNhanVienService.timKiemBangMaChamCongNhanVien("CH0311230001"));
    }

    @Test
    void capNhatTrangThaiDiLamCuaNhanVienTest(){
        chamCongNhanVienService.capNhatTrangThaiDiLamCuaNhanVien("CH0302220002", 2);
    }
}
