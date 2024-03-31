package toNhom;

import com.product.salary.application.entity.ToNhom;
import com.product.salary.application.service.ToNhomService;
import com.product.salary.application.service.impl.ToNhomServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ToNhomTest {
    private ToNhomService toNhomService;

    @BeforeEach
    public void setUp() {
        toNhomService = new ToNhomServiceImpl();
    }
    @Test
    public void testTimTatCaToNhom(){
        List<ToNhom> toNhoms = toNhomService.timKiemTatCaToNhom();
        for (ToNhom toNhom : toNhoms) {
            System.out.println(toNhom);
        }
    }
    @Test
    public void testtimKiemTatCaToNhomDangHoatDong(){
        List<ToNhom> toNhoms = toNhomService.timKiemTatCaToNhomDangHoatDong();
        for (ToNhom toNhom : toNhoms) {
            System.out.println(toNhom);
        }
    }
    @Test
    public void testTimKiemToNhom(){
        ToNhom toNhom = new ToNhom();
        toNhom.setTrangThai(true);
        List<ToNhom> toNhoms = toNhomService.timKiemToNhom(toNhom);
        for (ToNhom toNhom1 : toNhoms) {
            System.out.println(toNhom1);
        }
    }
    @Test
    public void testTimBangMaToNhom(){
        ToNhom toNhom = new ToNhom();
        toNhom.setMaToNhom("1");
        List<ToNhom> toNhoms = toNhomService.timKiemToNhom(toNhom);
        for (ToNhom toNhom1 : toNhoms) {
            System.out.println(toNhom1);
        }
    }
    @Test
    public void testCapNhatToNhom(){
        ToNhom toNhom = new ToNhom();
        toNhom.setMaToNhom("1");
        toNhom.setTenToNhom("To Nhom 1");
        toNhom.setTrangThai(true);
        toNhomService.capNhatToNhom(toNhom);
    }
    @Test
    public void testCapNhatTrangThaiToNhom(){
        ToNhom toNhom = new ToNhom();
        toNhom.setMaToNhom("1");
        toNhom.setTrangThai(true);
        toNhomService.capNhatTrangThaiToNhom(toNhom.getMaToNhom(), toNhom.isTrangThai());
    }
    @Test
    public void testCapNhatSoLuongCongNhanBangMaToNhom(){
        ToNhom toNhom = new ToNhom();
        toNhom.setMaToNhom("1");
        toNhom.setSoLuongCongNhan(10);
        boolean r = toNhomService.capNhatSoLuongCongNhanBangMaToNhom(toNhom.getMaToNhom(), toNhom.getSoLuongCongNhan());
    }
    @Test
    public void testTimMaToNhomCuoiCung(){
        String maToNhom = toNhomService.generateMaToNhom();
        System.out.println(maToNhom);
    }
    @Test
    public void testThemToNhom(){
        ToNhom toNhom = new ToNhom();
        String ma = toNhomService.generateMaToNhom();
        toNhom.setMaToNhom(ma);
        toNhom.setTenToNhom("To Nhom x");
        toNhom.setTrangThai(true);
        toNhom.setSoLuongCongNhan(1);
        toNhomService.themToNhom(toNhom);
    }
}
