package sanPham;

import com.product.salary.application.entity.SanPham;
import com.product.salary.application.service.SanPhamService;
import com.product.salary.application.service.impl.SanPhamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SanPhamServiceTest {
    private SanPhamService sanPhamService;
    @BeforeEach
    void setUp() {
        sanPhamService = new SanPhamServiceImpl();
    }

    @Test
    void testTimKiemTatCaSanPham() {
        List<SanPham> sanPhams = sanPhamService.timKiemTatCaSanPham();
        sanPhams.forEach(sp -> {
            System.out.println(sp.getMaSanPham());
            System.out.println(sp.getTenSanPham());
            System.out.println(sp.getChatLieu());
            System.out.println(sp.getTrangThai());
            System.out.println(sp.getCongDoanSanPhams());
        });
    }

    @Test
    void testCapNhatSanPham() throws Exception {
        SanPham sanPham = sanPhamService.timKiemSanPham(new SanPham("10000")).get(0);
        sanPham.setTenSanPham("San pham 10000");
        sanPham = sanPhamService.capNhatSanPham(sanPham);
        assert sanPham.getTenSanPham().equals("San pham 10000");
    }

    @Test
    void testCapNhatTrangThaiSanPham() {
        boolean result = sanPhamService.capNhatTrangThaiSanPham("10000", true);
        assert result;
    }


    @Test
    void testTimKiemSanPham() throws Exception {
        SanPham sanPham = new SanPham();
        sanPham.setTenSanPham("San pham 1");
        sanPham.setChatLieu("Vai");
        sanPhamService.timKiemSanPham(sanPham).forEach(sp -> {
            System.out.println(sp.getMaSanPham());
            System.out.println(sp.getTenSanPham());
            System.out.println(sp.getChatLieu());
            System.out.println(sp.getTrangThai());
            System.out.println(sp.getCongDoanSanPhams());
        });
    }

    @Test
    void testThemSanPham() throws Exception {
        String maSanPham = sanPhamService.generateMaSanPham();
        SanPham sanPham = new SanPham(maSanPham, "San pham 1", "Cao su", 100, "Cao su", 1000.0, null);
        sanPham = sanPhamService.themSanPham(sanPham);
        assert sanPham.getMaSanPham().equals(maSanPham);
    }


    @Test
    void testGenerateMaSanPham() {
        String maSanPham = sanPhamService.generateMaSanPham();
        System.out.println(maSanPham);
    }


    @Test
    void testTimTatCaSanPhamDangSanXuat() {
        List<SanPham> sanPhams = sanPhamService.timTatCaSanPhamDangSanXuat();
        sanPhams.forEach(sp -> {
            System.out.println(sp.getMaSanPham());
            System.out.println(sp.getTenSanPham());
            System.out.println(sp.getChatLieu());
            System.out.println(sp.getTrangThai());
            System.out.println(sp.getCongDoanSanPhams());
        });
    }


    @Test
    void testTongSoLuongSanPham() {
        int soLuong = sanPhamService.tongSoLuongSanPham();
        System.out.println(soLuong);
    }

    @Test
    void testThemNhieuSanPham() throws Exception {
        List<SanPham> sanPhams = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String maSanPham = sanPhamService.generateMaSanPham();
            SanPham sanPham = new SanPham(maSanPham, "San pham them nhieu: " + i, "Cao su", 100, "Cao su", 1000.0, null);
            sanPhams.add(sanPham);
        }
        List<SanPham> sanPhamThems = sanPhamService.themNhieuSanPham(sanPhams);
        sanPhamThems.forEach(sp -> {
            System.out.println(sp.getMaSanPham());
            System.out.println(sp.getTenSanPham());
            System.out.println(sp.getChatLieu());
            System.out.println(sp.getTrangThai());
            System.out.println(sp.getCongDoanSanPhams());
        });
    }
}
