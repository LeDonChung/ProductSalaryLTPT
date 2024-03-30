package sanPham;

import com.product.salary.application.entity.SanPham;
import com.product.salary.application.service.SanPhamService;
import com.product.salary.application.service.impl.SanPhamServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    void testCapNhatSanPham() {

    }

    void testCapNhatTrangThaiSanPham() {

    }


    void testTimKiemSanPham() {

    }


    void testThemSanPham() {

    }


    void testGenerateMaSanPham() {

    }


    void testTimTatCaSanPhamDangSanXuat() {

    }


    void testTongSoLuongSanPham() {

    }

    void testThemNhieuSanPham() {

    }
}
