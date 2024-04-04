package congNhan;

import com.product.salary.application.entity.CongNhan;
import com.product.salary.application.entity.TayNghe;
import com.product.salary.application.entity.ToNhom;
import com.product.salary.application.service.CongNhanService;
import com.product.salary.application.service.impl.CongNhanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class CongNhanTest {
    private CongNhanService congNhanService;

    @BeforeEach
    void setUp(){
        congNhanService = new CongNhanServiceImpl();
    }
    @Test
    public void testTimKiemTatCaCongNhan(){
        List<CongNhan> congNhans = congNhanService.timKiemTatCaCongNhan();
        for (CongNhan congNhan : congNhans){
            System.out.println(congNhan);
        }
    }
    @Test
    public void testCapNhatTrangThaiCongNhan(){
        boolean result = congNhanService.capNhatTrangThaiCongNhan("1", true);
        assert result;
    }
    @Test
    public void testTimKimBangMaCongNhan(){
        CongNhan congNhan = congNhanService.timKiemBangMaCongNhan("1");
        System.out.println(congNhan);
    }
    @Test
    public void testTimKiemBangCCCD() throws Exception{
        boolean c = congNhanService.kiemTraCccd("1231231231");
        System.out.println(c);
    }
    @Test
    public void testTimKiemCongNhanBangMaToNhom(){
        List<CongNhan> congNhans = congNhanService.timKiemCongNhanBangMaToNhom("1");
        for (CongNhan congNhan : congNhans){
            System.out.println(congNhan);
        }
    }
    @Test
    public void testTongSoLuongCongNhan(){
        int soLuong = congNhanService.tongSoLuongCongNhan();
        System.out.println(soLuong);
    }
    @Test
    public void testCapNhatCongNhan() throws Exception{
        CongNhan congNhan = congNhanService.timKiemBangMaCongNhan("1");
        congNhan.setHoTen("Tran Tuan Kiet");
        CongNhan update = congNhanService.capNhatCongNhan(congNhan);
        System.out.println(update);
    }
    @Test
    public void testTimKiemCongNhan() throws Exception{
        CongNhan congNhan = new CongNhan();
        congNhan.setHoTen("Kiet");
        congNhan.setGioiTinh(1);
        List<CongNhan> congNhans = congNhanService.timKiemCongNhan(congNhan);
        for (CongNhan cn : congNhans){
            System.out.println(cn.getMaCongNhan());
            System.out.println(cn.getHoTen());
            System.out.println(cn.getNgaySinh());
            System.out.println(cn.getDiaChi());
            System.out.println(cn.getSoDienThoai());
            System.out.println(cn.getEmail());
            System.out.println(cn.getGioiTinh());
            System.out.println(cn.getTrangThai());
            System.out.println(cn.getToNhom());

        }
    }
    @Test
    public void testThemCongNhan() throws Exception{
        CongNhan congNhan = new CongNhan();
        congNhan.setMaCongNhan("1");
        congNhan.setCccd("1231231231");
        congNhan.setDiaChi("Phu Yen");
        congNhan.setEmail("test3@gmail.com");
        congNhan.setGioiTinh(1);
        congNhan.setHoTen("Test 3");
        congNhan.setNgaySinh(LocalDate.of(1999, 12, 12));
        congNhan.setNgayVaoLam(LocalDate.now());
        congNhan.setSoDienThoai("0384976471");
        congNhan.setTroCap(1000.0);
        ToNhom toNhom = new ToNhom();
        toNhom.setMaToNhom("1");
        congNhan.setToNhom(toNhom);
        TayNghe tayNghe = new TayNghe();
        tayNghe.setMaTayNghe("1");
        congNhan.setTayNghe(tayNghe);
        CongNhan cn = congNhanService.themCongNhan(congNhan);
        System.out.println(cn);
    }
}
