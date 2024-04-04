package phanCongCongViec;


import com.product.salary.application.entity.CongDoanSanPham;
import com.product.salary.application.entity.CongNhan;
import com.product.salary.application.entity.PhanCongCongNhan;
import com.product.salary.application.service.PhanCongCongViecService;
import com.product.salary.application.service.impl.PhanCongCongViecServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class PhanCongCongViecTest {
    private PhanCongCongViecService phanCongCongViecService;

    @BeforeEach
    public void setUp() {
        phanCongCongViecService = new PhanCongCongViecServiceImpl();
    }
    @Test
    public void testCapNhatPhanCongCongNhan() {
        PhanCongCongNhan phanCongCongNhan = new PhanCongCongNhan();
        phanCongCongNhan.setMaPhanCong("202200010101005");
        phanCongCongNhan.setNgayPhanCong(LocalDate.now());
        phanCongCongNhan.setTrangThai(true);
        System.out.println(phanCongCongViecService.capNhatPhanCongCongNhan(phanCongCongNhan));
    }
    @Test
    public void testXoaPhanCongCongNhan() {
        System.out.println(phanCongCongViecService.xoaPhanCongCongNhan("202200010101005"));
    }
    @Test
    public void testTimKiemBangMaPhanCong() {
        System.out.println(phanCongCongViecService.xoaPhanCongCongNhan("202200010101005"));
    }
    @Test
    public void testGenerateMaPhanCongCongNhan(){
        System.out.println(phanCongCongViecService.generateMaPhanCongCongNhan("2020220001","000101"));
    }
    @Test
    public void testTimTatCaPhanCongTheoMaCongNhanChuaHoanThanh(){
        List<PhanCongCongNhan> phanCongCongNhans = phanCongCongViecService.timTatCaPhanCongTheoMaCongNhanChuaHoanThanh("2020220001");
        phanCongCongNhans.forEach(System.out::println);
    }
    @Test
    public void testTimTatCaCongNhanChuaPhanCongVaoCongDoan(){
        List<CongNhan> congNhans = phanCongCongViecService.timTatCaCongNhanChuaPhanCongVaoCongDoan("300005","000101");
        congNhans.forEach(System.out::println);
    }
    @Test
    public void testTimTatCaPhanCongTheoMaCongDoan(){
        List<PhanCongCongNhan> phanCongCongNhans = phanCongCongViecService.timTatCaPhanCongTheoMaCongDoan("000101");
        phanCongCongNhans.forEach(System.out::println);
    }
    @Test
    public void testPhanCongCongNhan(){
        PhanCongCongNhan phanCongCongNhan = new PhanCongCongNhan();
        phanCongCongNhan.setMaPhanCong(phanCongCongViecService.generateMaPhanCongCongNhan("2020220001","000101"));
        phanCongCongNhan.setNgayPhanCong(LocalDate.now());
        System.out.println(phanCongCongViecService.phanCongCongNhan(phanCongCongNhan));
    }
}
