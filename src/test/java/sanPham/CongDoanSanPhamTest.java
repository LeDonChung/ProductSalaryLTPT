package sanPham;

import com.product.salary.application.dao.CongDoanSanPhamDAO;
import com.product.salary.application.entity.CongDoanSanPham;
import com.product.salary.application.entity.SanPham;
import com.product.salary.application.service.CongDoanSanPhamService;
import com.product.salary.application.service.SanPhamService;
import com.product.salary.application.service.impl.CongDoanSanPhamServiceImpl;
import com.product.salary.application.service.impl.SanPhamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CongDoanSanPhamTest {
    private CongDoanSanPhamService congDoanSanPhamService;
    private SanPhamService sanPhamService;

    @BeforeEach
    public void setUp() {
        this.congDoanSanPhamService = new CongDoanSanPhamServiceImpl();
        this.sanPhamService = new SanPhamServiceImpl();
    }

    @Test
    void timTatCaCongDoanSanPham() {
        List<CongDoanSanPham> congDoanSanPhams = congDoanSanPhamService.timTatCaCongDoanSanPham("300001");
        congDoanSanPhams.forEach(System.out::println);
    }

    @Test
    void timTatCaCongDoanSanPhamDangHoatDongBangMaSanPham() {
        List<CongDoanSanPham> congDoanSanPhams = congDoanSanPhamService.timTatCaCongDoanSanPhamDangHoatDongBangMaSanPham("300001");
        congDoanSanPhams.forEach(System.out::println);
    }

    @Test
    void capNhatCongDoanSanPham() throws Exception {
        CongDoanSanPham congDoanSanPham = congDoanSanPhamService.timTatCaCongDoanSanPham("300001").get(0);
        congDoanSanPham.setTenCongDoan("Cắt vải test");
        congDoanSanPhamService.capNhatCongDoanSanPham(congDoanSanPham);

    }

    @Test
    void capNhatTrangThaiCongDoanSanPham() {
        congDoanSanPhamService.capNhatTrangThaiCongDoanSanPham("000101", false);
    }

    @Test
    void themCongDoanSanPham() throws Exception {
        SanPham sanPham = new SanPham("300001");
        String maCongDoan = congDoanSanPhamService.generateMaCongDoanSanPham("300001");
        CongDoanSanPham congDoanSanPhamLamTruoc = new CongDoanSanPham();
        congDoanSanPhamLamTruoc.setMaCongDoan("000101");
        CongDoanSanPham congDoanSanPham = new CongDoanSanPham(
                maCongDoan,
                "Cắt vải 2",
                100,
                100000,
                LocalDate.now(),
                null,
                sanPham,
                true
        );
        CongDoanSanPham congDoanSanPhamNew = congDoanSanPhamService.themCongDoanSanPham(congDoanSanPham);
        System.out.println(congDoanSanPhamNew);
    }

    @Test
    void generateMaCongDoanSanPham() {
        String maSanPham = "300001";
        String maCongDoanSanPham = congDoanSanPhamService.generateMaCongDoanSanPham(maSanPham);
        System.out.println(maCongDoanSanPham);
    }

    @Test
    void themNhieuCongDoanSanPham() throws Exception {
        SanPham sanPham = new SanPham("300002");
        List<CongDoanSanPham> congDoanSanPhams = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            CongDoanSanPham congDoanSanPham = new CongDoanSanPham(
                    "",
                    "Cắt vải " + i,
                    100,
                    100000,
                    LocalDate.now(),
                    null,
                    new SanPham(),
                    true
            );
            congDoanSanPhams.add(congDoanSanPham);
        }
        this.congDoanSanPhamService.themNhieuCongDoanSanPham(sanPham, congDoanSanPhams);

    }

    @Test
    void capNhatSoLuongCanCuaCongDoan() {

    }

}
