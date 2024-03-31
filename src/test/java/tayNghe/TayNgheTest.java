package tayNghe;

import com.product.salary.application.entity.TayNghe;
import com.product.salary.application.service.TayNgheService;
import com.product.salary.application.service.impl.TayNgheServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TayNgheTest {
    private TayNgheService tayNgheService;

    @BeforeEach
    public void setUp() {
        tayNgheService = new TayNgheServiceImpl();
    }
    @Test
    public void testTimTatCaTayNghe(){
        List<TayNghe> tayNgheList = tayNgheService.timKiemTatCaTayNghe();
        for (TayNghe tayNghe : tayNgheList) {
            System.out.println(tayNghe);
        }
    }
    @Test
    public void testCapNhatTayNghe(){
        TayNghe tayNghe = new TayNghe();
        tayNghe.setMaTayNghe(tayNgheService.timKiemBangMaTayNghe("1").getMaTayNghe());
        tayNghe.setTenTayNghe("Tay Nghề 1");
        tayNgheService.capNhatTayNghe(tayNghe);
    }
    @Test
    public void testThemTayNghe(){
        TayNghe tayNghe = new TayNghe();
        tayNghe.setMaTayNghe(tayNgheService.generegateMaTayNghe());
        tayNghe.setTenTayNghe("Tay Nghề 2");
        tayNgheService.themTayNghe(tayNghe);
    }
    @Test
    public void testTimKiemBangMaTayNghe(){
        TayNghe tayNghe = new TayNghe();
        tayNghe.setMaTayNghe("1");
        TayNghe tn = tayNgheService.timKiemBangMaTayNghe(tayNghe.getMaTayNghe());
        System.out.println(tn);
    }
    @Test
    public void testGenerateMa(){
        System.out.println(tayNgheService.generegateMaTayNghe());
    }
    @Test
    public void testXoaTayNgheBangMa(){
        tayNgheService.xoaTayNgheBangMa("780003");
    }
}
