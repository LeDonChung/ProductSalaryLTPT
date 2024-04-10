/*
 * @author Trần Thị Thanh Tuyền code giao diện
 */

package com.product.salary.client.view.other;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.entity.NhanVien;
import com.product.salary.application.service.AccountService;
import com.product.salary.application.service.NhanVienService;
import com.product.salary.application.service.impl.AccountServiceImpl;
import com.product.salary.application.service.impl.NhanVienServiceImpl;
import com.product.salary.application.utils.PhoneUtils;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class QuenMatKhauForm extends JFrame {
	private JTextField txtMaNhanVien;
	private JButton btnGuiMatKhauMoi;
	private JButton btnDangNhap;
	private NhanVienService nhanVienService;
	private JTextField txtCccd;
	private AccountService accountService;

	public QuenMatKhauForm() {

		SystemConstants.initLanguage();
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("src/main/resources/icon/png/ic_logo.png"));
		this.setTitle("Quên mật khẩu");
		this.setSize(900, 510);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(8, 92, 255));

		JLabel lblTieuDeDangNhap = new JLabel(SystemConstants.BUNDLE.getString("quenMatKhau.title"));
		lblTieuDeDangNhap.setForeground(Color.WHITE);
		lblTieuDeDangNhap.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDeDangNhap.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTieuDeDangNhap.setBounds(447, 37, 429, 43);
		getContentPane().add(lblTieuDeDangNhap);

		JPanel pnlMaNhanVien = new JPanel();
		pnlMaNhanVien.setBackground(new Color(8, 92, 255));
		pnlMaNhanVien.setBounds(471, 105, 380, 88);
		getContentPane().add(pnlMaNhanVien);
		pnlMaNhanVien.setLayout(null);

		JLabel lblMaNhanVien = new JLabel(SystemConstants.BUNDLE.getString("quenMatKhau.maNhanVien"));
		lblMaNhanVien.setForeground(Color.WHITE);
		lblMaNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaNhanVien.setBounds(10, 11, 146, 24);
		pnlMaNhanVien.add(lblMaNhanVien);

		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaNhanVien.setBounds(10, 38, 360, 39);
		pnlMaNhanVien.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);

		JPanel pnlCccd = new JPanel();
		pnlCccd.setLayout(null);
		pnlCccd.setBackground(new Color(8, 92, 255));
		pnlCccd.setBounds(471, 190, 380, 88);
		getContentPane().add(pnlCccd);

		JLabel lblCccd = new JLabel(SystemConstants.BUNDLE.getString("quenMatKhau.cccd"));
		lblCccd.setForeground(Color.WHITE);
		lblCccd.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCccd.setBounds(10, 11, 146, 24);
		pnlCccd.add(lblCccd);

		txtCccd = new JTextField();
		txtCccd.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtCccd.setColumns(10);
		txtCccd.setBounds(10, 39, 360, 39);
		pnlCccd.add(txtCccd);

		btnGuiMatKhauMoi = new JButton(SystemConstants.BUNDLE.getString("quenMatKhau.btnGuiLaiMatKhau"));
		btnGuiMatKhauMoi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnGuiMatKhauMoi.setBounds(481, 289, 359, 37);
		getContentPane().add(btnGuiMatKhauMoi);

		btnDangNhap = new JButton(SystemConstants.BUNDLE.getString("quenMatKhau.btnDangNhap"));

		btnDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnDangNhap.setBounds(481, 347, 359, 37);
		getContentPane().add(btnDangNhap);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("src/main/resources/icon/png/logo.png"));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(82, 90, 350, 350);
		getContentPane().add(lblLogo);

		this.setVisible(true);
		init();
		event();
	}

	public void init() {
		nhanVienService = new NhanVienServiceImpl();
		accountService = new AccountServiceImpl();
	}

	public void event() {
		btnDangNhap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DangNhapForm().setVisible(true);
				dispose();
			}
		});
		btnGuiMatKhauMoi.addActionListener((e) -> {
			thucHienChucNangGuiLaiMatKhau();
		});

	}

	private void thucHienChucNangGuiLaiMatKhau() {
		String maNhanVien = txtMaNhanVien.getText().trim();
		String cccd = txtCccd.getText().trim();
		if (ObjectUtils.isEmpty(cccd) || ObjectUtils.isEmpty(cccd)) {
			if (SystemConstants.LANGUAGE == 1) {
				JOptionPane.showMessageDialog(this, "Both employee code and ID Card cannot be empty");
			} else {
				JOptionPane.showMessageDialog(this, "Cả mã nhân viên và căn cước công dân đều không được rỗng");
			}
		}

		NhanVien nhanVien = nhanVienService.timKiemBangMaNhanVienVaCccd(maNhanVien, cccd);

		Random random = new Random();
		int max = 999999;
		int min = 100000;
		String matKhauMoi = String.format("%s", random.nextInt(max - min + 1) + min);
		accountService.capNhatMatKhau(nhanVien.getMaNhanVien(), matKhauMoi);

		String message = "Xin chào " + nhanVien.getHoTen()
				+ "! Mật khẩu mới của tài khoản đăng nhập vào CTK Home của bạn là : " + matKhauMoi;
		String phone = "84" + nhanVien.getSoDienThoai().substring(1);
		PhoneUtils.sendNotification(phone, message);

		if (SystemConstants.LANGUAGE == 1) {
			JOptionPane.showMessageDialog(this, "Resended new password to phone number successfull!");
		} else {
			JOptionPane.showMessageDialog(this, "Gửi lại mật khẩu đến số điện thoại thành công!");
		}

		new DangNhapForm().setVisible(true);
		dispose();
	}
}
