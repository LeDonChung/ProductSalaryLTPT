package chamCongCongNhan;

import com.product.salary.application.entity.CaLam;
import com.product.salary.application.entity.ChamCongCongNhan;
import com.product.salary.application.entity.CongNhan;
import com.product.salary.application.entity.PhanCongCongNhan;
import com.product.salary.application.service.ChamCongCongNhanService;
import com.product.salary.application.service.impl.ChamCongCongNhanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class ChamCongCongNhanServiceTest {
    private ChamCongCongNhanService chamCongCongNhanService;

    @BeforeEach
    void setUp() {
        this.chamCongCongNhanService = new ChamCongCongNhanServiceImpl();
    }

    @Test
    void testTimKiemTatCaChamCongCongNhan() {
        LocalDate ngayChamCong = LocalDate.of(2023, 1, 5);
        List<ChamCongCongNhan> dsChamCong = chamCongCongNhanService.timKiemTatCaChamCongCongNhan(ngayChamCong);
        dsChamCong.forEach(System.out::println);
    }

    @Test
    void testTimTatCaCongNhanChuaChamCongTheoCaVaNgayChamCong() {
        LocalDate ngayChamCong = LocalDate.of(2023, 1, 5);
        String caLam = "CH";
        List<CongNhan> dsCongNhanChuaChamCong = chamCongCongNhanService.timTatCaCongNhanChuaChamCongTheoCaVaNgayChamCong(ngayChamCong, caLam);
        dsCongNhanChuaChamCong.forEach(System.out::println);
    }

    @Test
    void testThemChamCongCongNhan() throws Exception {
        CaLam caLam = new CaLam();
        caLam.setMaCa("SA");
        PhanCongCongNhan phanCongCongNhan = new PhanCongCongNhan();
        phanCongCongNhan.setMaPhanCong("202200010201005");

        ChamCongCongNhan chamCong = new ChamCongCongNhan();
        chamCong.setPhanCongCongNhan(phanCongCongNhan);
        chamCong.setNgayChamCong(LocalDate.now());
        chamCong.setSoLuongHoanThanh(10);
        chamCong.setTrangThai(1);
        chamCong.setCaLam(caLam);
        chamCong.setMaChamCong(chamCongCongNhanService.genertateMaChamCongCongNhan(LocalDate.now(), caLam));

        ChamCongCongNhan chamCongCongNhan = chamCongCongNhanService.themChamCongCongNhan(chamCong);
        System.out.println(chamCongCongNhan);
    }


    @Test
    void testGenertateMaChamCongCongNhan() {
        LocalDate ngayChamCong = LocalDate.of(2023, 1, 5);
        CaLam caLam = new CaLam();
        caLam.setMaCa("CH");
        String maChamCong = chamCongCongNhanService.genertateMaChamCongCongNhan(ngayChamCong, caLam);
        System.out.println(maChamCong);
    }


    @Test
    void testCapNhatChamCongCongNhan() {
        String maChamCong = "SA0204240001";
        int trangThai = 0;
        int soLuongHoanThanh = 10;
        boolean result = chamCongCongNhanService.capNhatChamCongCongNhan(maChamCong, trangThai, soLuongHoanThanh);
        System.out.println(result);
    }
}
