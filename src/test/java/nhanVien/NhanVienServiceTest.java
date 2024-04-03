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
        nv.setHoTen("Tran Thi Thanh Tuyen");
        nv.setCccd("089303002765");
        nv.setEmail("thanhtuyen9623@gmail.com");
        nv.setChucVu(new ChucVu("990002", "Giám sát"));
        nv.setDiaChi("An Giang");
        nv.setGioiTinh(0);
        nv.setHeSoLuong(3.33);
        nv.setLuongCoSo(8500000);
        nv.setNgaySinh(LocalDate.of(2003,6,9));
        nv.setNgayVaoLam(LocalDate.of(2022, 10, 25));
        nv.setPhongBan(new PhongBan("400001", "Phòng nhân sự"));
        nv.setTrinhDo(new TrinhDo("1", "Dai hoc"));
        nv.setSoDienThoai("0396172224");
        System.out.println( nhanVienService.themNhanVien(nv));
    }
}
