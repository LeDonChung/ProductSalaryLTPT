package hopDong;

import com.product.salary.application.entity.ChiTietHopDong;
import com.product.salary.application.entity.HopDong;
import com.product.salary.application.entity.SanPham;
import com.product.salary.application.service.HopDongService;
import com.product.salary.application.service.impl.HopDongServiceImpl;
import jakarta.transaction.Transactional;
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
    @Transactional
    public void testTimTatCaHopDong() {
        List<HopDong> hopDongs = hopDongService.timTatCaHopDong();
        hopDongs.forEach(System.out::println);
    }
    @Test
    public void testThemHopDong() throws Exception {
        String maHopDong = this.hopDongService.generateMaHopDong();
        HopDong hopDong = new HopDong();
        hopDong.setMaHopDong(maHopDong);
        hopDong.setTenHopDong("Khach hang 1");
        hopDong.setTenKhachHang("Khach hang 1");
        hopDong.setTongTien(2000000);
        hopDong.setSoTienCoc(100000);
        hopDong.setNgayBatDau(LocalDate.now());
        hopDong.setNgayKetThuc(null);
        hopDong.setYeuCau("Yeu cau 1");
        hopDong.setTrangThai(false);

        List<ChiTietHopDong> chiTietHopDongs = new ArrayList<>();
        SanPham sanPham1 = new SanPham();
        sanPham1.setMaSanPham("300001");
        sanPham1.setDonGia(1000.0);
        SanPham sanPham2 = new SanPham();
        sanPham2.setMaSanPham("300002");
        sanPham2.setDonGia(1000.0);
        chiTietHopDongs.add(
                new ChiTietHopDong(hopDong, sanPham1, 10, 1000000)
        );
        chiTietHopDongs.add(
                new ChiTietHopDong(hopDong, sanPham2, 10, 1000000)
        );
        this.hopDongService.themHopDong(hopDong, chiTietHopDongs);
    }
    @Test
    public void testThanhLyHopDong() {
        this.hopDongService.thanhLyHopDong("5031032401");
    }

}
