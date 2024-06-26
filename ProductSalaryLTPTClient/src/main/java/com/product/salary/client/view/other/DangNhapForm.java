/*
 * @author Trần Thị Thanh Tuyền code giao diện
 */

package com.product.salary.client.view.other;

import com.product.salary.application.entity.Account;
import com.product.salary.application.utils.*;
import com.product.salary.client.common.SystemConstants;
import com.product.salary.client.view.app.Application;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.ResourceBundle;

public class DangNhapForm extends JFrame {
    private final static ResourceBundle BUNDLE = ResourceBundle.getBundle("app");
    private final JTextField txtTaiKhoan;
    private final JButton btnDangNhap;
    private final JButton btnNgonNgu;
    private final JPasswordField txtPassword;
    private boolean isShow = false;
    private final JButton btnNhinMatKhau;
    private final JButton btnQuenMatKhau;

    public DangNhapForm() {
        SystemConstants.initLanguage();
        setIconImage(Toolkit.getDefaultToolkit()
                .getImage("src/main/resources/icon/png/ic_logo.png"));
        this.setTitle(SystemConstants.BUNDLE.getString("loginForm.title"));
        this.setSize(900, 510);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(8, 92, 255));

        JLabel lblTieuDeDangNhap = new JLabel(SystemConstants.BUNDLE.getString("loginForm.title"));
        lblTieuDeDangNhap.setForeground(Color.WHITE);
        lblTieuDeDangNhap.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDeDangNhap.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblTieuDeDangNhap.setBounds(447, 37, 429, 43);
        getContentPane().add(lblTieuDeDangNhap);

        JPanel pnlTaiKhoan = new JPanel();
        pnlTaiKhoan.setBackground(new Color(8, 92, 255));
        pnlTaiKhoan.setBounds(471, 105, 380, 88);
        getContentPane().add(pnlTaiKhoan);
        pnlTaiKhoan.setLayout(null);

        JLabel lblTaiKhoan = new JLabel(SystemConstants.BUNDLE.getString("loginForm.username"));
        lblTaiKhoan.setForeground(Color.WHITE);
        lblTaiKhoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblTaiKhoan.setBounds(10, 11, 146, 24);
        pnlTaiKhoan.add(lblTaiKhoan);

        txtTaiKhoan = new JTextField();
        txtTaiKhoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtTaiKhoan.setBounds(10, 38, 360, 39);
        pnlTaiKhoan.add(txtTaiKhoan);
        txtTaiKhoan.setColumns(10);

        JPanel pnlMatKhau = new JPanel();
        pnlMatKhau.setLayout(null);
        pnlMatKhau.setBackground(new Color(8, 92, 255));
        pnlMatKhau.setBounds(471, 190, 380, 88);
        getContentPane().add(pnlMatKhau);

        JLabel lblMatKhau = new JLabel(SystemConstants.BUNDLE.getString("loginForm.password"));
        lblMatKhau.setForeground(Color.WHITE);
        lblMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblMatKhau.setBounds(10, 11, 146, 24);
        pnlMatKhau.add(lblMatKhau);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        txtPassword.setBounds(10, 45, 299, 39);
        pnlMatKhau.add(txtPassword);

        btnNhinMatKhau = new JButton("");
        btnNhinMatKhau.setIcon(new ImageIcon("src/main/resources/icon/png/icon_unsaw.png"));
        btnNhinMatKhau.setBounds(317, 45, 53, 39);
        pnlMatKhau.add(btnNhinMatKhau);

        btnDangNhap = new JButton(SystemConstants.BUNDLE.getString("loginForm.cmdLogin"));
        btnDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnDangNhap.setBounds(481, 289, 359, 37);
        getContentPane().add(btnDangNhap);

        btnNgonNgu = new JButton(SystemConstants.BUNDLE.getString("loginForm.cmdLanguage"));
        btnNgonNgu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnNgonNgu.setBounds(481, 403, 359, 37);
        getContentPane().add(btnNgonNgu);

        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon("src/main/resources/icon/png/logo.png"));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setBounds(82, 90, 350, 350);
        getContentPane().add(lblLogo);

        btnQuenMatKhau = new JButton(SystemConstants.BUNDLE.getString("loginForm.forgotPassword"));
        btnQuenMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        btnQuenMatKhau.setBounds(481, 347, 359, 37);
        getContentPane().add(btnQuenMatKhau);

        this.setVisible(true);
        init();
        event();
    }

    public void language() {
        if (SystemConstants.LANGUAGE == 0) {
            LanguageUtils.saveLanguage(1);
        } else if (SystemConstants.LANGUAGE == 1) {
            LanguageUtils.saveLanguage(0);
        }
        SystemConstants.initLanguage();
    }

    public void init() {

    }

    public void event() {
        btnNgonNgu.addActionListener((e) -> {
            language();
            dispose();
            new DangNhapForm().setVisible(true);
        });
        btnDangNhap.addActionListener((e) -> thucHienThucNangDangNhap());
        btnNhinMatKhau.addActionListener((e) -> thucHienChucNangNhinMatKhau());
        btnQuenMatKhau.addActionListener((e) -> thucHienChucNangQuenMatKhau());

    }

    private void thucHienChucNangQuenMatKhau() {
        String taiKhoan = this.txtTaiKhoan.getText().trim();
        if (ObjectUtils.isEmpty(taiKhoan)) {
            if (SystemConstants.LANGUAGE == 1) {
                JOptionPane.showMessageDialog(this, "Please fill out account login.");
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tài khoản đăng nhập.");
            }
        } else {
            Account account = new Account();
//			accountService.timKiemBangTaiKhoan(taiKhoan);
            if (account == null) {
                if (SystemConstants.LANGUAGE == 1) {
                    JOptionPane.showMessageDialog(this, "Account is not exists.");
                    return;
                } else {
                    JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại.");
                    return;
                }
            }
            new QuenMatKhauForm().setVisible(true);
            dispose();
        }

    }

    private void thucHienChucNangNhinMatKhau() {
        if (isShow) {
            isShow = false;
            txtPassword.setEchoChar((char) 0);
            btnNhinMatKhau.setIcon(new ImageIcon("src/main/resources/icon/png/ic_saw.png"));
        } else {
            isShow = true;
            txtPassword.setEchoChar('*');
            btnNhinMatKhau
                    .setIcon(new ImageIcon("src/main/resources/icon/png/icon_unsaw.png"));
        }
    }

    private void thucHienThucNangDangNhap() {
        new Thread(() -> {
            String taiKhoan = txtTaiKhoan.getText().trim();
            String matKhau = String.copyValueOf(txtPassword.getPassword());
            Account account = new Account(taiKhoan, matKhau);

            try (var socket = new Socket(
                    BUNDLE.getString("host"),
                    Integer.parseInt(BUNDLE.getString("server.port")));
                 var dos = new DataOutputStream(socket.getOutputStream());
                 var dis = new DataInputStream(socket.getInputStream())
            ) {
                RequestDTO request = RequestDTO.builder()
                        .requestType("DangNhapForm")
                        .request("timKiemTaiKhoanHoatDongBangTaiKhoanVaMatKhau")
                        .data(account)
                        .build();
                System.out.println("Request: " + request);

                String json = AppUtils.GSON.toJson(request);
                dos.writeUTF(json);
                dos.flush();

                json = new String(dis.readAllBytes());
                ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
                System.out.println("Response: " + response);
                // response.getData() -> LinkedHashMap
                Map<String, Object> data = (Map<String, Object>) response.getData();
                account = AppUtils.convert(data, Account.class);

                if (account != null) {
                    AuthUtils.login(account);
                    new Application().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không chính xác.");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();

    }
}
