package com.product.salary.client.view.other;

import com.product.salary.application.entity.Account;
import com.product.salary.application.utils.AppUtils;
import com.product.salary.application.utils.RequestDTO;
import com.product.salary.application.utils.ResponseDTO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class HandlerAccount implements Callable<Account> {
    private final static ResourceBundle BUNDLE = ResourceBundle.getBundle("app");
    private Account account;

    public HandlerAccount(Account account) {
        this.account = account;
    }

    @Override
    public Account call() throws Exception {
        try (var socket = new Socket(
                BUNDLE.getString("host"),
                Integer.parseInt(BUNDLE.getString("server.port")));
             var dos = new DataOutputStream(socket.getOutputStream());
             var dis = new DataInputStream(socket.getInputStream())) {
            // send request
            RequestDTO request = RequestDTO.builder()
                    .request("timKiemTaiKhoanHoatDongBangTaiKhoanVaMatKhau")
                    .requestType("DangNhapForm")
                    .data(account)
                    .build();

            String json = AppUtils.GSON.toJson(request);
            dos.writeUTF(json);
            dos.flush();

            // receive response
            json = new String(dis.readAllBytes());
            ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
            Map<String, Object> data = (Map<String, Object>) response.getData();
            account = AppUtils.convert(data, Account.class);
            return account;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
