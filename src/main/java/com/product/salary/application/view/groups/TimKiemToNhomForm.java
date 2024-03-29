package com.product.salary.application.view.groups;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.entity.ToNhom;
import com.product.salary.application.service.ToNhomService;
import com.product.salary.application.service.impl.ToNhomServiceImpl;
import com.product.salary.application.utils.excels.ToNhomExcelUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class TimKiemToNhomForm extends JPanel {
	private JTextField txtMaToNhom;
	private JTextField txtTenToNhom;
	private JTextField txtSoLuongCongNhan;
	private List<ToNhom> danhSachToNhom;
	private ToNhomService toNhomService;
	private DefaultTableModel tblModelToNhom;
	private JButton btnTimKiem;
	private JTable tblToNhom;
	private JButton btnLamMoi;
	private JComboBox cmbTrangThai;
	private JButton btnXuat;

	/**
	 * Create the frame.
	 */
	public TimKiemToNhomForm() {
		setLayout(null);

		JPanel pnlChinh = new JPanel();
		pnlChinh.setBounds(10, 10, 1273, 821);
		add(pnlChinh);
		pnlChinh.setLayout(null);

		JLabel lblTitle = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("timKiemToNhom.titleToNhom")));
		lblTitle.setBounds(0, 0, 1250, 80);
		lblTitle.setBorder(null);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlChinh.add(lblTitle);

		tblModelToNhom = new DefaultTableModel(new String[] {
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyToNhom.tableToNhom.stt")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyToNhom.tableToNhom.maToNhom")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyToNhom.tableToNhom.tenToNhom")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyToNhom.tableToNhom.soLuongCongNhan")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyToNhom.tableToNhom.trangThai")) },
				0);

		tblToNhom = new JTable(tblModelToNhom);
		tblToNhom.setShowVerticalLines(true);
		tblToNhom.setShowHorizontalLines(true);
		tblToNhom.setRowHeight(25);

		JScrollPane scrToNhom = new JScrollPane(tblToNhom);
		scrToNhom.setLocation(20, 482);
		scrToNhom.setSize(1230, 362);
		tblToNhom.setBounds(0, 570, 1273, 263);
		pnlChinh.add(scrToNhom);

		JPanel pnlMaToNhom = new JPanel();
		pnlMaToNhom.setLayout(null);
		pnlMaToNhom.setBounds(62, 111, 566, 62);
		pnlChinh.add(pnlMaToNhom);

		JLabel lblMaToNhom = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyToNhom.lbMaToNhom")));
		lblMaToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaToNhom.setBounds(10, 11, 150, 40);
		pnlMaToNhom.add(lblMaToNhom);

		txtMaToNhom = new JTextField();
		txtMaToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaToNhom.setColumns(10);
		txtMaToNhom.setBounds(188, 11, 354, 40);
		pnlMaToNhom.add(txtMaToNhom);

		JPanel pnlTenToNhom = new JPanel();
		pnlTenToNhom.setLayout(null);
		pnlTenToNhom.setBounds(62, 214, 566, 62);
		pnlChinh.add(pnlTenToNhom);

		JLabel lblTenToNhom = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyToNhom.lbTenToNhom")));
		lblTenToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenToNhom.setBounds(10, 11, 150, 40);
		pnlTenToNhom.add(lblTenToNhom);

		txtTenToNhom = new JTextField();
		txtTenToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTenToNhom.setColumns(10);
		txtTenToNhom.setBounds(188, 11, 354, 40);
		pnlTenToNhom.add(txtTenToNhom);

		JPanel pnlSoLuongCongNhan = new JPanel();
		pnlSoLuongCongNhan.setLayout(null);
		pnlSoLuongCongNhan.setBounds(672, 111, 566, 62);
		pnlChinh.add(pnlSoLuongCongNhan);

		JLabel lblSoLuongCongNhan = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyToNhom.lbSoLuongCongNhan")));
		lblSoLuongCongNhan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoLuongCongNhan.setBounds(10, 11, 150, 40);
		pnlSoLuongCongNhan.add(lblSoLuongCongNhan);

		txtSoLuongCongNhan = new JTextField();
		txtSoLuongCongNhan.setEnabled(false);
		txtSoLuongCongNhan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtSoLuongCongNhan.setColumns(10);
		txtSoLuongCongNhan.setBounds(188, 11, 354, 40);
		pnlSoLuongCongNhan.add(txtSoLuongCongNhan);

		btnTimKiem = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyToNhom.btnTimKiem")));
		btnTimKiem.setIcon(new ImageIcon("src/main/resources/icon/png/ic_search.png"));
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnTimKiem.setBounds(672, 328, 233, 44);
		pnlChinh.add(btnTimKiem);

		JLabel lblDSToNhom = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyToNhom.lbDanhSachToNhom")));
		lblDSToNhom.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDSToNhom.setBounds(20, 436, 350, 36);
		pnlChinh.add(lblDSToNhom);

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
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyToNhom.lbTrangThai")));
		lblTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTrangThai.setBounds(10, 11, 150, 40);
		pnlTrangThai.add(lblTrangThai);

		cmbTrangThai = new JComboBox();
		cmbTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] { "Tất cả", "Đang hoạt động", "Ngừng hoạt động" }));
		cmbTrangThai.setBounds(189, 10, 352, 41);
		pnlTrangThai.add(cmbTrangThai);

		btnXuat = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyToNhom.btnXuat")));
		btnXuat.setIcon(new ImageIcon("src/main/resources/icon/png/ic_exel_.png"));
		btnXuat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnXuat.setBounds(1005, 417, 233, 44);
		pnlChinh.add(btnXuat);

		init();
		event();
	}

	private void event() {
		btnXuat.addActionListener((e) -> {
			thucHienChucNangXuatExcel();
		});
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

		tblToNhom.addMouseListener(new MouseListener() {

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

	private void thucHienChucNangXuatExcel() {
		int index = tblToNhom.getSelectedRow();
		if(index >= 0) {
			ToNhom toNhom = danhSachToNhom.get(index);
			List<ToNhom> tn = new ArrayList<ToNhom>();
			tn.add(toNhom);
			ToNhomExcelUtils.exportExcelSanPham(tn);
		}else {
			ToNhomExcelUtils.exportExcelSanPham(danhSachToNhom);
		}
		
	}

	private void init() {
		danhSachToNhom = new ArrayList<>();
		toNhomService = new ToNhomServiceImpl();
		loadTableToNhom();
	}

	private void thucHienChucNangChonPhongBanTable() {
		int select = tblToNhom.getSelectedRow();

		if (select >= 0) {
			ToNhom toNhom = danhSachToNhom.get(select);

			txtMaToNhom.setText(toNhom.getMaToNhom());
			txtTenToNhom.setText(toNhom.getTenToNhom());
			txtSoLuongCongNhan.setText(toNhom.getSoLuongCongNhan() + "");
			if (toNhom.isTrangThai() == true)
				cmbTrangThai.setSelectedItem("Đang hoạt động");
			else
				cmbTrangThai.setSelectedItem("Ngừng hoạt động");
		}
	}

	private void loadTableToNhom() {
		tblModelToNhom.setRowCount(0);
		danhSachToNhom = toNhomService.timKiemTatCaToNhom();
		int stt = 1;
		for (ToNhom toNhom : danhSachToNhom) {
			String trangThai = "";
			if (toNhom.isTrangThai())
				trangThai = "Đang hoạt động";
			else
				trangThai = "Ngừng hoạt động";
			tblModelToNhom.addRow(new Object[] { stt++, toNhom.getMaToNhom(), toNhom.getTenToNhom(),
					toNhom.getSoLuongCongNhan(), trangThai });
		}
	}

	private void thucHienChucNangTimKiem() {
		tblModelToNhom.setRowCount(0);
		String maToNhom = txtMaToNhom.getText().trim();
		String tenToNhom = txtTenToNhom.getText().trim();
		Boolean trangThai = null;
		if (cmbTrangThai.getSelectedIndex() != 0) {
			if (cmbTrangThai.getSelectedItem() == "Đang hoạt động")
				trangThai = true;
			else
				trangThai = false;
		}

		ToNhom tNhom = new ToNhom(maToNhom, tenToNhom, 0, trangThai);

		danhSachToNhom = toNhomService.timKiemToNhom(tNhom);

		int stt = 1;
		for (ToNhom toNhom : danhSachToNhom) {
			String trThai = "";
			if (toNhom.isTrangThai())
				trThai = "Đang hoạt động";
			else
				trThai = "Ngừng hoạt động";

			tblModelToNhom.addRow(new Object[] { stt++, toNhom.getMaToNhom(), toNhom.getTenToNhom(),
					toNhom.getSoLuongCongNhan(), trThai });

		}
	}

	private void thucHienChucNangLamMoi() {
		tblModelToNhom.setRowCount(0);
		txtMaToNhom.setText("");
		txtTenToNhom.setText("");
		txtSoLuongCongNhan.setText("");
		cmbTrangThai.setSelectedIndex(0);
		loadTableToNhom();
	}
}
