package trinhDo;

import com.product.salary.application.service.TrinhDoService;
import com.product.salary.application.service.impl.TrinhDoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrinhDoServiceTest {
    private TrinhDoService trinhDoService;
    @BeforeEach
    void setUp(){
        trinhDoService = new TrinhDoServiceImpl();
    }

    @Test
    void timKiemTatCaTrinhDoTest(){
        trinhDoService.timKiemTatCaTrinhDo().forEach(System.out::println);
    }

    @Test
    void timKiemBangMaTrinhDoTest(){
        System.out.println(trinhDoService.timKiemBangMaTrinhDo("2"));
    }
}
