package account;

import com.product.salary.application.entity.Account;
import com.product.salary.application.service.AccountService;
import com.product.salary.application.service.impl.AccountServiceImpl;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccounTest {
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        accountService = new AccountServiceImpl();
    }
    @Test
    void testTimKiemTaiKhoanHoatDongBangTaiKhoanVaMatKhau() {
        Account a = accountService.timKiemTaiKhoanHoatDongBangTaiKhoanVaMatKhau("kiet", "test");
        System.out.println(a);
    }
    @Test
    public void testTimKiemTatCaTaiKhoan() {
        List<Account> accounts = accountService.timKiemTatCaTaiKhoan();
        for (Account account : accounts) {
            System.out.println(account.getMatKhau());
        }
    }
    @Test
    public void testCapNhatMatKhau() {
        boolean a = accountService.capNhatMatKhau("kiet", "test");
        System.out.println(a);
    }
    @Test
    public void testTimKiembangTaiKhoan() {
        Account a = accountService.timKiemBangTaiKhoan("kiet");
        System.out.println(a.getTaiKhoan());
    }
}
