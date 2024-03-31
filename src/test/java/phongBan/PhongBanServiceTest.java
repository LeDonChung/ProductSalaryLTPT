package phongBan;

import com.product.salary.application.entity.PhongBan;
import com.product.salary.application.service.PhongBanService;
import com.product.salary.application.service.impl.PhongBanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PhongBanServiceTest {
    private PhongBanService phongBanService;

    @BeforeEach
    void setUp(){
        phongBanService = new PhongBanServiceImpl();
    }

    @Test
    void themPhongBanTest() throws Exception {
        PhongBan phongBan = new PhongBan();

        phongBan.setTenPhongBan("Phòng tài chính");
        phongBanService.themPhongBan(phongBan);
    }

    @Test
    void timKiemPhongBanTest() throws Exception {
        PhongBan phongBan = new PhongBan();
        phongBan.setTenPhongBan("tài");
        phongBanService.timKiemPhongBan(phongBan).forEach(System.out::println);
    }


    @Test
    void capNhatPhongBanTest() throws Exception {
        PhongBan phongBan = new PhongBan();
        phongBan.setMaPhongBan("400001");
        phongBan.setTenPhongBan("Phòng nhân sự");
        phongBan.setTrangThai(true);
        System.out.println(phongBanService.capNhatPhongBan(phongBan));
    }
    @Test
    void timKiemTatCaPhongBanTest(){
        phongBanService.timKiemTatCaPhongBan().forEach(System.out::println);
    }
}
