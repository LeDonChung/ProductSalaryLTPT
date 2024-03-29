package com.product.salary.application.view.department;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.entity.PhongBan;
import com.product.salary.application.service.PhongBanService;
import com.product.salary.application.service.impl.PhongBanServiceImpl;
import com.product.salary.application.utils.excels.PhongBanExcelUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class TimKiemPhongBanForm extends JPanel {
	private JTextField txtMaPhongBan;
	private JTextField txtTenPhongBan;
	private JTextField txtSoLuongNhanVien;
	private List<PhongBan> danhSachPhongBan;
	private PhongBanService phongBanService;
	private DefaultTableModel tableModelPhongBan;
	private JButton btnTimKiem;
	private JTable tblPhongBan;
	private JButton btnLamMoi;
	private JComboBox cmbTrangThai;
	private JButton btnXuatDanhSach;

	/**
	 * Create the frame.
	 */
	public TimKiemPhongBanForm() {
		setLayout(null);

		JPanel pnlChinh = new JPanel();
		pnlChinh.setBounds(10, 10, 1273, 821);
		add(pnlChinh);
		pnlChinh.setLayout(null);

		JLabel lblTitle = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("timKiemPhongBan.title")));
		lblTitle.setBounds(0, 0, 1250, 80);
		lblTitle.setBorder(null);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlChinh.add(lblTitle);

		tableModelPhongBan = new DefaultTableModel(new String[] {
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyPhongBan.tablePhongBan.stt")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyPhongBan.tablePhongBan.maPhongBan")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyPhongBan.tablePhongBan.tenPhongBan")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyPhongBan.tablePhongBan.soLuongNhanVien")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyPhongBan.tablePhongBan.trangThai")) },
				0);

		tblPhongBan = new JTable(tableModelPhongBan);
		tblPhongBan.setShowVerticalLines(true);
		tblPhongBan.setShowHorizontalLines(true);
		tblPhongBan.setRowHeight(25);

		JScrollPane scrPhongBan = new JScrollPane(tblPhongBan);
		scrPhongBan.setLocation(20, 482);
		scrPhongBan.setSize(1230, 362);
		tblPhongBan.setBounds(0, 570, 1273, 263);
		pnlChinh.add(scrPhongBan);

		JPanel pnlMaPB = new JPanel();
		pnlMaPB.setLayout(null);
		pnlMaPB.setBounds(62, 111, 566, 62);
		pnlChinh.add(pnlMaPB);

		JLabel lblMaPB = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyPhongBan.lbMaPhongBan")));
		lblMaPB.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaPB.setBounds(10, 11, 150, 40);
		pnlMaPB.add(lblMaPB);

		txtMaPhongBan = new JTextField();
		txtMaPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaPhongBan.setColumns(10);
		txtMaPhongBan.setBounds(188, 11, 354, 40);
		pnlMaPB.add(txtMaPhongBan);

		JPanel pnlTenPhongBan = new JPanel();
		pnlTenPhongBan.setLayout(null);
		pnlTenPhongBan.setBounds(62, 214, 566, 62);
		pnlChinh.add(pnlTenPhongBan);

		JLabel lblTenPhongBan = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyPhongBan.lbTenPhongBan")));
		lblTenPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenPhongBan.setBounds(10, 11, 150, 40);
		pnlTenPhongBan.add(lblTenPhongBan);

		txtTenPhongBan = new JTextField();
		txtTenPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTenPhongBan.setColumns(10);
		txtTenPhongBan.setBounds(188, 11, 354, 40);
		pnlTenPhongBan.add(txtTenPhongBan);

		JPanel pnlSoLuongNhanVien = new JPanel();
		pnlSoLuongNhanVien.setLayout(null);
		pnlSoLuongNhanVien.setBounds(672, 111, 566, 62);
		pnlChinh.add(pnlSoLuongNhanVien);

		JLabel lblSoLuongNhanVien = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyPhongBan.lbSoLuongNhanVien")));
		lblSoLuongNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoLuongNhanVien.setBounds(10, 11, 150, 40);
		pnlSoLuongNhanVien.add(lblSoLuongNhanVien);

		txtSoLuongNhanVien = new JTextField();
		txtSoLuongNhanVien.setEnabled(false);
		txtSoLuongNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtSoLuongNhanVien.setColumns(10);
		txtSoLuongNhanVien.setBounds(188, 11, 354, 40);
		pnlSoLuongNhanVien.add(txtSoLuongNhanVien);

		btnTimKiem = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyPhongBan.btnTimKiem")));
		btnTimKiem.setIcon(new ImageIcon("src/main/resources/icon/png/ic_search.png"));
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnTimKiem.setBounds(672, 328, 233, 44);
		pnlChinh.add(btnTimKiem);

		JLabel lblDSPhongBan = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyPhongBan.lbDSPhongBan")));
		lblDSPhongBan.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDSPhongBan.setBounds(20, 436, 350, 36);
		pnlChinh.add(lblDSPhongBan);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnLamMoi.setIcon(new ImageIcon("src/main/resources/icon/png/ic_refresh.png"));
		btnLamMoi.setBounds(1005, 328, 233, 44);
		pnlChinh.add(btnLamMoi);

		JPanel pnlTrangThai = new JPanel();
		pnlTrangThai.setLayout(null);
		pnlTrangThai.setBounds(672, 214, 566, 62);
		pnlChinh.add(pnlTrangThai);

		JLabel lblTrangThai = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyPhongBan.trangThai")));
		lblTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTrangThai.setBounds(10, 11, 150, 40);
		pnlTrangThai.add(lblTrangThai);

		cmbTrangThai = new JComboBox();
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] { "Tất cả", "Đang hoạt động", "Ngừng hoạt động" }));
		cmbTrangThai.setBounds(189, 10, 352, 41);
		pnlTrangThai.add(cmbTrangThai);

		btnXuatDanhSach = new JButton("Xuất danh sách");
		btnXuatDanhSach
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_exel_.png"));
		btnXuatDanhSach.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnXuatDanhSach.setBounds(1005, 428, 233, 44);
		pnlChinh.add(btnXuatDanhSach);

		init();
		event();
	}

	private void event() {
		btnTimKiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thucHienChucNangTimKiem();

			}
		});

		btnLamMoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thucHienChucNangLamMoi();

			}
		});

		btnXuatDanhSach.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thucHienChucNangXuatDanhSach();

			}
		});

		tblPhongBan.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				thucHienChucNangChonPhongBanTable();

			}
		});
	}

	private void init() {
		danhSachPhongBan = new ArrayList<>();
		phongBanService = new PhongBanServiceImpl();
		loadTablePhongBan();
	}

	private void thucHienChucNangXuatDanhSach() {
		PhongBanExcelUtils.exportExcelPhongBan(danhSachPhongBan);
	}

	private void thucHienChucNangChonPhongBanTable() {
		int select = tblPhongBan.getSelectedRow();

		if (select >= 0) {
			PhongBan phongBan = danhSachPhongBan.get(select);

			txtMaPhongBan.setText(phongBan.getMaPhongBan());
			txtTenPhongBan.setText(phongBan.getTenPhongBan());
			txtSoLuongNhanVien.setText(phongBan.getSoLuongNhanVien() + "");
			if (phongBan.isTrangThai() == true)
				cmbTrangThai.setSelectedItem("Đang hoạt động");
			else
				cmbTrangThai.setSelectedItem("Ngừng hoạt động");
		}
	}

	private void loadTablePhongBan() {
		tableModelPhongBan.setRowCount(0);
		danhSachPhongBan = phongBanService.timKiemTatCaPhongBan();
		int stt = 1;
		for (PhongBan phongBan : danhSachPhongBan) {
			String trangThai = "";
			if (phongBan.isTrangThai())
				trangThai = "Đang hoạt động";
			else
				trangThai = "Ngừng hoạt động";
			tableModelPhongBan.addRow(new Object[] { stt++, phongBan.getMaPhongBan(), phongBan.getTenPhongBan(),
					phongBan.getSoLuongNhanVien(), trangThai });
		}
	}

	private void thucHienChucNangTimKiem() {
		tableModelPhongBan.setRowCount(0);
		String maPhongBan = txtMaPhongBan.getText().trim();
		String tenPhongBan = txtTenPhongBan.getText().trim();
		Boolean trangThai = null;
		if (cmbTrangThai.getSelectedIndex() != 0) {
			if (cmbTrangThai.getSelectedItem() == "Đang hoạt động")
				trangThai = true;
			else
				trangThai = false;
		}

		PhongBan pb = new PhongBan(maPhongBan, tenPhongBan, trangThai);
		danhSachPhongBan = phongBanService.timKiemPhongBan(pb);
		int stt = 1;
		for (PhongBan phongBan : danhSachPhongBan) {
			String trangThaiS = "";
			if (phongBan.isTrangThai())
				trangThaiS = "Đang hoạt động";
			else
				trangThaiS = "Ngừng hoạt động";

			tableModelPhongBan.addRow(new Object[] { stt++, phongBan.getMaPhongBan(), phongBan.getTenPhongBan(),
					phongBan.getSoLuongNhanVien(), trangThaiS });

		}
	}

	private void thucHienChucNangLamMoi() {
		tableModelPhongBan.setRowCount(0);
		txtMaPhongBan.setText("");
		txtTenPhongBan.setText("");
		txtSoLuongNhanVien.setText("");
		cmbTrangThai.setSelectedIndex(0);
		loadTablePhongBan();
	}
}
