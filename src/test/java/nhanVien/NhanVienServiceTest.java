package nhanVien;

import com.product.salary.application.entity.ChucVu;
import com.product.salary.application.entity.NhanVien;
import com.product.salary.application.entity.PhongBan;
import com.product.salary.application.entity.TrinhDo;
import com.product.salary.application.service.NhanVienService;
import com.product.salary.application.service.impl.NhanVienServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class NhanVienServiceTest {
    private NhanVienService nhanVienService;
    @BeforeEach
    void setUp(){
        nhanVienService = new NhanVienServiceImpl();
    }

    @Test
    void themNhanVienTest() throws Exception {
        NhanVien nv = new NhanVien();
        nv.setHoTen("Trần Thanh Tuyền");
        nv.setCccd("089783002765");
        nv.setEmail("thanhtuyen9623@gmail.com");
        nv.setChucVu(new ChucVu("990002", "Giám sát"));
        nv.setDiaChi("An Giang");
        nv.setGioiTinh(0);
        nv.setHeSoLuong(3.33);
        nv.setLuongCoSo(8500000);
        nv.setNgaySinh(LocalDate.of(1978,6,9));
        nv.setNgayVaoLam(LocalDate.of(2020, 10, 25));
        nv.setPhongBan(new PhongBan("400001", "Phòng nhân sự"));
        nv.setTrinhDo(new TrinhDo("1", "Đại học"));
        nv.setSoDienThoai("0396172224");
        NhanVien nvAdd = nhanVienService.themNhanVien(nv);
        System.out.println(nvAdd);
    }

    @Test
    void timKiemBangMaNhanVienVaCccdTest(){
        NhanVien nv = nhanVienService.timKiemBangMaNhanVienVaCccd("1020210001", "050211114679");
        System.out.println(nv);
    }

    @Test
    void timKiemNhanVienBangMaPhongBanTest(){
        nhanVienService.timKiemNhanVienBangMaPhongBan("400001").forEach(System.out::println);
    }

    @Test
    void timKiemNhanVienTest() throws Exception {
        NhanVien nv = new NhanVien();
        nv.setHoTen("Tuyền");
        nhanVienService.timKiemNhanVien(nv).forEach(System.out::println);
    }

    @Test
    void capNhatTrangThaiNghiLamCuaNhanVienTest(){
        boolean result = nhanVienService.capNhatTrangThaiNghiLamCuaNhanVien("1020210001");
        System.out.println(result);
    }

    @Test
    void capNhatNhanVienTest() throws Exception {
        NhanVien nv = nhanVienService.timKiemBangMaNhanVien("1020210001");
        nv.setHoTen("Võ Thanh Tuyền");
        nv.setTroCap(350000);
        NhanVien nvUpdate = nhanVienService.capNhatNhanVien(nv);
        System.out.println(nvUpdate);
    }
}
