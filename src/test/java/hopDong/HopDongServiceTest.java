package hopDong;

import com.product.salary.application.entity.ChiTietHopDong;
import com.product.salary.application.entity.HopDong;
import com.product.salary.application.entity.SanPham;
import com.product.salary.application.service.HopDongService;
import com.product.salary.application.service.impl.HopDongServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HopDongServiceTest {
    private HopDongService hopDongService;
    @BeforeEach
    void setUp() {
        hopDongService = new HopDongServiceImpl();
    }
    @Test
    public void testTimTatCaHopDong() {
        List<HopDong> hopDongs = hopDongService.timTatCaHopDong();
        hopDongs.forEach(System.out::println);
    }
    @Test
    public void testThemHopDong() throws Exception {
        String maHopDong = this.hopDongService.generateMaHopDong();
        HopDong hopDong = new HopDong();
        hopDong.setMaHopDong(maHopDong);
        hopDong.setTenHopDong("Khach hang 3");
        hopDong.setTenKhachHang("Khach hang 3");
        hopDong.setTongTien(2000000);
        hopDong.setSoTienCoc(100000);
        hopDong.setNgayBatDau(LocalDate.now());
        hopDong.setNgayKetThuc(null);
        hopDong.setYeuCau("Yeu cau 1");
        hopDong.setTrangThai(false);
        List<ChiTietHopDong> chiTietHopDongs = new ArrayList<>();
        SanPham sanPham = new SanPham();
        sanPham.setMaSanPham("10000");
        sanPham.setDonGia(1000.0);
        chiTietHopDongs.add(
                new ChiTietHopDong(hopDong, sanPham, 2, 1000000)
        );
        this.hopDongService.themHopDong(hopDong, chiTietHopDongs);
    }
    @Test
    public void testThanhLyHopDong() {
        this.hopDongService.thanhLyHopDong("5030032406");
    }

}
