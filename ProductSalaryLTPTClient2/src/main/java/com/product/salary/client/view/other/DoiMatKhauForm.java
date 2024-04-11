/*
 * @author Trần Thị Thanh Tuyền code giao diện
 */

package com.product.salary.client.view.other;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.entity.Account;
import com.product.salary.application.utils.AppUtils;
import com.product.salary.application.utils.RequestDTO;
import com.product.salary.application.utils.ResponseDTO;
import com.product.salary.client.interfaces.ISendResponse;
import com.product.salary.application.service.AccountService;
import com.product.salary.application.service.impl.AccountServiceImpl;
import com.product.salary.application.utils.AuthUtils;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class DoiMatKhauForm extends JFrame {
	private final static ResourceBundle BUNDLE = ResourceBundle.getBundle("app");
	private final ISendResponse iSendResponse;
	private final JPanel contentPane;
	private final JTextField txtMatKhauCu;
	private final JTextField txtMatKhauMoi;
	private final JTextField txtNhapLaiMatKhau;
	private final JButton btnDoiMatKhau;
	private final JButton btnHuy;
	private final JLabel lblLoiNhapLaiMatKhau;
	private final JLabel lblLoiMatKhauCu;
	private final JLabel lblMatKhauMoi;
	private final JLabel lblLoiMatKhauMoi;

	public DoiMatKhauForm(ISendResponse iSendResponse) {
		this.iSendResponse = iSendResponse;
		setTitle(SystemConstants.BUNDLE.getString("doiMatKhau.title"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 600, 400);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("doiMatKhau.title")));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTitle.setBounds(5, 5, 576, 50);
		contentPane.add(lblTitle);

		JPanel pnlMatKhauCu = new JPanel();
		pnlMatKhauCu.setBounds(64, 65, 461, 50);
		contentPane.add(pnlMatKhauCu);
		pnlMatKhauCu.setLayout(null);

		JLabel lblMatKhauCu = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("doiMatKhau.matKhauCu")));
		lblMatKhauCu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMatKhauCu.setBounds(0, 0, 118, 50);
		pnlMatKhauCu.add(lblMatKhauCu);

		txtMatKhauCu = new JTextField();
		txtMatKhauCu.setBounds(128, 0, 323, 50);
		pnlMatKhauCu.add(txtMatKhauCu);
		txtMatKhauCu.setColumns(10);

		JPanel pnlMatKhauMoi = new JPanel();
		pnlMatKhauMoi.setLayout(null);
		pnlMatKhauMoi.setBounds(64, 137, 461, 50);
		contentPane.add(pnlMatKhauMoi);

		lblMatKhauMoi = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("doiMatKhau.matKhauMoi")));
		lblMatKhauMoi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMatKhauMoi.setBounds(0, 0, 118, 50);
		pnlMatKhauMoi.add(lblMatKhauMoi);

		txtMatKhauMoi = new JTextField();
		txtMatKhauMoi.setColumns(10);
		txtMatKhauMoi.setBounds(128, 0, 323, 50);
		pnlMatKhauMoi.add(txtMatKhauMoi);

		JPanel pnlNhapLaiMatKhau = new JPanel();
		pnlNhapLaiMatKhau.setLayout(null);
		pnlNhapLaiMatKhau.setBounds(64, 209, 461, 50);
		contentPane.add(pnlNhapLaiMatKhau);

		JLabel lblNhapLaiMatKhau = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("doiMatKhau.nhapLaiMatKhau")));
		lblNhapLaiMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNhapLaiMatKhau.setBounds(0, 0, 118, 50);
		pnlNhapLaiMatKhau.add(lblNhapLaiMatKhau);

		txtNhapLaiMatKhau = new JTextField();
		txtNhapLaiMatKhau.setColumns(10);
		txtNhapLaiMatKhau.setBounds(128, 0, 323, 50);
		pnlNhapLaiMatKhau.add(txtNhapLaiMatKhau);

		JPanel pnlButton = new JPanel();
		pnlButton.setBounds(64, 290, 461, 50);
		contentPane.add(pnlButton);
		pnlButton.setLayout(null);

		btnDoiMatKhau = new JButton(SystemConstants.BUNDLE.getString("doiMatKhau.btnDoiMatKhau"));
		btnDoiMatKhau.setIcon(
				new ImageIcon("src/main/resources/icon/png/ic_change_password.png"));
		btnDoiMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnDoiMatKhau.setBounds(0, 0, 215, 50);
		pnlButton.add(btnDoiMatKhau);

		btnHuy = new JButton(SystemConstants.BUNDLE.getString("doiMatKhau.huy"));
		btnHuy.setIcon(new ImageIcon("src/main/resources/icon/png/ic_cancel.png"));
		btnHuy.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnHuy.setBounds(277, 0, 174, 50);
		btnHuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		pnlButton.add(btnHuy);

		lblLoiMatKhauCu = new JLabel("");
		lblLoiMatKhauCu.setForeground(Color.RED);
		lblLoiMatKhauCu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLoiMatKhauCu.setBounds(195, 113, 317, 20);
		contentPane.add(lblLoiMatKhauCu);

		lblLoiMatKhauMoi = new JLabel("");
		lblLoiMatKhauMoi.setForeground(Color.RED);
		lblLoiMatKhauMoi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLoiMatKhauMoi.setBounds(195, 185, 317, 20);
		contentPane.add(lblLoiMatKhauMoi);

		lblLoiNhapLaiMatKhau = new JLabel("");
		lblLoiNhapLaiMatKhau.setForeground(Color.RED);
		lblLoiNhapLaiMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLoiNhapLaiMatKhau.setBounds(195, 258, 317, 20);
		contentPane.add(lblLoiNhapLaiMatKhau);

		init();
		event();
	}

	private void init() {

	}

	private void event() {
		this.btnDoiMatKhau.addActionListener((e) -> thucHienThucNangDoiMatKhau());

	}

	private void thucHieChucNangLamMoiLoi() {
		this.lblLoiMatKhauCu.setText("");
		this.lblLoiNhapLaiMatKhau.setText("");
		this.lblLoiMatKhauMoi.setText("");
	}

	private boolean thucHienChucNangKiemTra() {
		thucHieChucNangLamMoiLoi();

		String matKhauCu = this.txtMatKhauCu.getText().trim();
		String matKhauMoi = this.txtMatKhauMoi.getText().trim();
		String nhapLaiMatKhau = this.txtNhapLaiMatKhau.getText().trim();

		boolean status = true;
		if (ObjectUtils.isEmpty(matKhauCu)) {
			lblLoiMatKhauCu.setText(SystemConstants.BUNDLE.getString("doiMatKhau.nhapMKCu"));
			//lblLoiMatKhauCu.setText("Vui lòng nhập mật khẩu cũ!");
			status = false;
		}
		if (ObjectUtils.isEmpty(matKhauMoi)) {
			lblLoiMatKhauMoi.setText(SystemConstants.BUNDLE.getString("doiMatKhau.nhapMKMoi"));
			//lblLoiMatKhauMoi.setText("Vui lòng nhập mật khẩu mới!");
			status = false;
		}
		if (!nhapLaiMatKhau.equals(matKhauMoi)) {
			lblLoiNhapLaiMatKhau.setText(SystemConstants.BUNDLE.getString("doiMatKhau.nhapLaiMKMoi"));
			//lblLoiNhapLaiMatKhau.setText("Mật khẩu nhập lại không khớp!");
			status = false;
		}
		return status;
	}

	private void thucHienThucNangDoiMatKhau() {
		Account ac = (Account) AuthUtils.getUser();
		String matKhauCu = this.txtMatKhauCu.getText().trim();
		String matKhauMoi = this.txtMatKhauMoi.getText().trim();
		Account account_1 = new Account(ac.getTaiKhoan(), matKhauCu);
		Callable<Account> accountTest = new HandlerAccount(account_1);
		FutureTask<Account> futureTask = new FutureTask<>(accountTest);
		Thread t1 = new Thread(futureTask);
		t1.start();
		while(t1.isAlive()) {}

        try {
            account_1 = futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        if (!thucHienChucNangKiemTra()) {
			return;
		}

		if (account_1 != null) {
			new Thread(() -> {
				try (var socket = new Socket(
						BUNDLE.getString("host"),
						Integer.parseInt(BUNDLE.getString("server.port")));
					 var dos = new DataOutputStream(socket.getOutputStream());
					 var dis = new DataInputStream(socket.getInputStream())) {
					Map<String, Object> data = new HashMap<>();
					data.put("taiKhoan", ac.getTaiKhoan());
					data.put("matKhau", matKhauMoi);
					// send request
					RequestDTO request = RequestDTO.builder()
							.request("capNhatMatKhau")
							.requestType("DoiMatKhauForm")
							.data(data)
							.build();

					String json = AppUtils.GSON.toJson(request);
					dos.writeUTF(json);
					dos.flush();

					// receive response
					json = new String(dis.readAllBytes());
					ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);

					boolean capNhatMatKhau = (boolean) response.getData();
					if (capNhatMatKhau) {
						JOptionPane.showMessageDialog(this,
								SystemConstants.BUNDLE.getString("doiMatKhau.capNhatMK"));
						dispose();
						this.iSendResponse.confirm(true);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}).start();
		} else {
			JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("doiMatKhau.matKhauKhongDung"));
		}
	}
}
