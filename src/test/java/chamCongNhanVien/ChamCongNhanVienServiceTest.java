package chamCongNhanVien;

import com.product.salary.application.entity.CaLam;
import com.product.salary.application.entity.ChamCongNhanVien;
import com.product.salary.application.entity.NhanVien;
import com.product.salary.application.service.ChamCongNhanVienService;
import com.product.salary.application.service.impl.ChamCongNhanVienServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ChamCongNhanVienServiceTest {
    private ChamCongNhanVienService chamCongNhanVienService;
    @BeforeEach
    void setUp() {
        chamCongNhanVienService = new ChamCongNhanVienServiceImpl();
    }
    @Test
    void timKiemTatCaChamCongNhanVienTheoCaVaNgay() {

        CaLam caLam = new CaLam();
        caLam.setMaCa("SA");

        LocalDate ngayChamCong = LocalDate.of(2023, 1, 1);
        var result = chamCongNhanVienService.timKiemTatCaChamCongNhanVienTheoCaVaNgay(ngayChamCong, caLam.getMaCa());
        result.forEach(System.out::println);
    }

    @Test
    void timKiemNhanVienChuaChamCongBangCaLamVaNgayChamCong() {
        LocalDate ngayChamCong = LocalDate.of(2023, 1, 1);
        var result = chamCongNhanVienService.timKiemNhanVienChuaChamCongBangCaLamVaNgayChamCong(ngayChamCong, "SA");
        result.forEach(System.out::println);
    }

    @Test
    void themChamCongNhanVien() throws Exception {
        ChamCongNhanVien chamCongNhanVien = new ChamCongNhanVien();
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNhanVien("1020220002");
        CaLam caLam = new CaLam();
        caLam.setMaCa("SA");
        chamCongNhanVien.setNhanVien(nhanVien);
        chamCongNhanVien.setCaLam(caLam);
        chamCongNhanVien.setNgayChamCong(LocalDate.now());
        chamCongNhanVien.setTrangThai(1);
        chamCongNhanVien.setLuongNhanVien(null);
        chamCongNhanVien = chamCongNhanVienService.themChamCongNhanVien(chamCongNhanVien);
        System.out.println(chamCongNhanVien);
    }

    @Test
    void timKiemBangMaChamCongNhanVien() {
        var result = chamCongNhanVienService.timKiemBangMaChamCongNhanVien("SA0304240001");
        System.out.println(result);
    }

    @Test
    void capNhatTrangThaiDiLamCuaNhanVien() {
        var result = chamCongNhanVienService.capNhatTrangThaiDiLamCuaNhanVien("SA0304240001", 0);
        System.out.println(result);
    }
}
