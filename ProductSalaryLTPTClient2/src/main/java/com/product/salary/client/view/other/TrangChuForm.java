package com.product.salary.client.view.other;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.entity.Account;
import com.product.salary.application.entity.NhanVien;
import com.product.salary.application.service.NhanVienService;
import com.product.salary.application.service.impl.NhanVienServiceImpl;
import com.product.salary.application.utils.AuthUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class TrangChuForm extends JPanel {

	private final JPanel pnlChinh;

	public TrangChuForm() {
		setBorder(new LineBorder(new Color(0, 0, 0)));

		setLayout(null);
		pnlChinh = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				BufferedImage image;
				try {
					image = ImageIO.read(new FileInputStream("src/main/resources/icon/png/banner.png"));

					g.drawImage(image, 0, 0, this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
		};
		pnlChinh.setBounds(10, 10, 1273, 825);
		add(pnlChinh);
		pnlChinh.setLayout(null);

		JLabel lblXinChao = new JLabel(SystemConstants.BUNDLE.getString("trangChu.xinChao"));
		lblXinChao.setHorizontalAlignment(SwingConstants.CENTER);
		lblXinChao.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblXinChao.setForeground(Color.BLACK);
		lblXinChao.setBounds(132, 439, 297, 54);
		pnlChinh.add(lblXinChao);

		Account account = (Account) AuthUtils.getUser();
		NhanVien nhanVien = account.getNhanVien();
		String fullName = nhanVien.getHoTen();
		String[] name = fullName.split(" ");

		JLabel lblTen = new JLabel(name[name.length - 1]);
		lblTen.setHorizontalAlignment(SwingConstants.CENTER);
		lblTen.setForeground(Color.BLACK);
		lblTen.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblTen.setBounds(132, 503, 297, 54);
		pnlChinh.add(lblTen);

		JLabel lblChucVu = new JLabel(nhanVien.getChucVu().getTenChucVu());
		lblChucVu.setHorizontalAlignment(SwingConstants.CENTER);
		lblChucVu.setForeground(Color.BLACK);
		lblChucVu.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblChucVu.setBounds(132, 567, 297, 54);
		pnlChinh.add(lblChucVu);
	}
}
