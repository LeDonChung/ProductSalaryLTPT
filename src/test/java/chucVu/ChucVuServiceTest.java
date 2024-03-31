package chucVu;

import com.product.salary.application.entity.ChucVu;
import com.product.salary.application.service.ChucVuService;
import com.product.salary.application.service.impl.ChucVuServiceImpl;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChucVuServiceTest {
    private ChucVuService chucVuService;

    @BeforeEach
    void setUp() {
        chucVuService = new ChucVuServiceImpl();
    }

    @Test
    void themChucVuTest(){
        ChucVu chucVu = new ChucVu();
        chucVu.setTenChucVu("Quản lý");
        System.out.println(chucVuService.themChucVu(chucVu));
    }

    @Test
    void timKiemTatCaChucVuTest(){
        chucVuService.timKiemTatCaChucVu().forEach(System.out::println);
    }

    @Test
    void capNhatChucVuTest(){
        ChucVu chucVu = new ChucVu();
        chucVu.setMaChucVu("990002");
        chucVu.setTenChucVu("Giám sát");
        System.out.println(chucVuService.capNhatChucVu(chucVu));
    }

    @Test
    void xoaChucVuBangMaTest(){
        System.out.println(chucVuService.xoaChucVuBangMa("990004"));
    }

}
