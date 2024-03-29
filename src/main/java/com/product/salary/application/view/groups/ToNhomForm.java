/*
 *@author Trần Thị Thanh Tuyền code giao diện
 */

package com.product.salary.application.view.groups;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.entity.CongNhan;
import com.product.salary.application.entity.ToNhom;
import com.product.salary.application.service.CongNhanService;
import com.product.salary.application.service.ToNhomService;
import com.product.salary.application.service.impl.CongNhanServiceImpl;
import com.product.salary.application.service.impl.ToNhomServiceImpl;
import com.product.salary.application.utils.excels.ToNhomExcelUtils;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class ToNhomForm extends JPanel {
	private JTextField txtMaToNhom;
	private JTextField txtTenToNhom;
	private JTextField txtSoLuongCongNhan;
	private DefaultTableModel tblModelToNhom;
	private JTable tblToNhom;
	private JButton btnCapNhat;
	private JButton btnXoa;
	private JButton btnThem;
	private JButton btnXoaTrang;
	private DefaultTableModel tblModelCongNhan;
	private JTable tblDanhSachCongNhan;
	private List<ToNhom> danhSachToNhom;
	private ToNhomService toNhomService;
	private JLabel lblLoiTenToNhom;
	private JComboBox cmbTrangThai;
	private List<CongNhan> danhSachCongNhanCuaToNhom;
	private CongNhanService congNhanService;
	private JButton btnThemNhiu;

	/**
	 * Create the panel.
	 */
	public ToNhomForm() {

		setLayout(null);

		JPanel pnlChinh = new JPanel();
		pnlChinh.setBounds(10, 10, 1273, 821);
		add(pnlChinh);
		pnlChinh.setLayout(null);

		JLabel lblTitle = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyToNhom.titleToNhom")));
		lblTitle.setBounds(0, 0, 1250, 80);
		lblTitle.setBorder(null);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlChinh.add(lblTitle);

		JPanel pnlMaToNhom = new JPanel();
		pnlMaToNhom.setLayout(null);
		pnlMaToNhom.setBounds(684, 74, 566, 62);
		pnlChinh.add(pnlMaToNhom);

		JLabel lblMaToNhom = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyToNhom.lbMaToNhom")));
		lblMaToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaToNhom.setBounds(10, 11, 150, 40);
		pnlMaToNhom.add(lblMaToNhom);

		txtMaToNhom = new JTextField();
		txtMaToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaToNhom.setEnabled(false);
		txtMaToNhom.setEditable(false);
		txtMaToNhom.setColumns(10);
		txtMaToNhom.setBounds(188, 11, 354, 40);
		pnlMaToNhom.add(txtMaToNhom);

		JPanel pnlTenToNhom = new JPanel();
		pnlTenToNhom.setLayout(null);
		pnlTenToNhom.setBounds(684, 146, 566, 94);
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

		lblLoiTenToNhom = new JLabel(" ");
		lblLoiTenToNhom.setForeground(Color.RED);
		lblLoiTenToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblLoiTenToNhom.setBounds(188, 60, 354, 24);
		pnlTenToNhom.add(lblLoiTenToNhom);

		JPanel pnlSoLuongCongNhan = new JPanel();
		pnlSoLuongCongNhan.setLayout(null);
		pnlSoLuongCongNhan.setBounds(684, 250, 566, 62);
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

		btnXoaTrang = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyToNhom.btnXoaTrang")));
		btnXoaTrang.setIcon(new ImageIcon("src/main/resources/icon/png/ic_refresh.png"));
		btnXoaTrang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnXoaTrang.setBounds(884, 393, 190, 44);
		pnlChinh.add(btnXoaTrang);

		btnThem = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyToNhom.btnThem")));
		btnThem.setIcon(new ImageIcon("src/main/resources/icon/png/ic_add.png"));
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnThem.setBounds(684, 393, 190, 44);
		pnlChinh.add(btnThem);

		btnXoa = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyToNhom.btnXoa")));
		btnXoa.setIcon(new ImageIcon("src/main/resources/icon/png/ic_remove.png"));
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnXoa.setBounds(884, 473, 190, 44);
		pnlChinh.add(btnXoa);

		btnCapNhat = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyToNhom.btnCapNhat")));
		btnCapNhat.setIcon(new ImageIcon("src/main/resources/icon/png/ic_update.png"));
		btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnCapNhat.setBounds(684, 473, 190, 44);
		pnlChinh.add(btnCapNhat);

		JLabel lblDanhSachToNhom = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyToNhom.lbDanhSachToNhom")));
		lblDanhSachToNhom.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDanhSachToNhom.setBounds(10, 79, 284, 36);
		pnlChinh.add(lblDanhSachToNhom);

		JLabel lblDanhSachCongNhan = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyToNhom.lblDanhSachCongNhan")));
		lblDanhSachCongNhan.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDanhSachCongNhan.setBounds(10, 501, 350, 36);
		pnlChinh.add(lblDanhSachCongNhan);

		tblModelCongNhan = new DefaultTableModel(new String[] {
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyToNhom.tableDanhSachCongNhan.stt")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyToNhom.tableDanhSachCongNhan.maSo")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyToNhom.tableDanhSachCongNhan.hoTen")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyToNhom.tableDanhSachCongNhan.cccd")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyToNhom.tableDanhSachCongNhan.ngaySinh")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyToNhom.tableDanhSachCongNhan.ngayVaoLam")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyToNhom.tableDanhSachCongNhan.dienThoai")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyToNhom.tableDanhSachCongNhan.gioiTinh")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyToNhom.tableDanhSachCongNhan.email")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyToNhom.tableDanhSachCongNhan.diaChi")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyToNhom.tableDanhSachCongNhan.trangThai")) },
				10);

		tblDanhSachCongNhan = new JTable(tblModelCongNhan);
		tblDanhSachCongNhan.setShowVerticalLines(true);
		tblDanhSachCongNhan.setShowHorizontalLines(true);
		tblDanhSachCongNhan.setRowHeight(25);

		JScrollPane scrDanhSachNhanVien = new JScrollPane(tblDanhSachCongNhan);
		scrDanhSachNhanVien.setSize(1273, 285);
		scrDanhSachNhanVien.setLocation(0, 536);
		tblDanhSachCongNhan.setBounds(0, 570, 1273, 263);
		pnlChinh.add(scrDanhSachNhanVien);

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
				15);

		tblToNhom = new JTable(tblModelToNhom);
		tblToNhom.setShowVerticalLines(true);
		tblToNhom.setShowHorizontalLines(true);
		tblToNhom.setRowHeight(25);

		JScrollPane scrToNhom = new JScrollPane(tblToNhom);
		scrToNhom.setLocation(10, 117);
		scrToNhom.setSize(578, 362);
		tblToNhom.setBounds(0, 570, 1273, 263);
		pnlChinh.add(scrToNhom);

		JPanel pnlTrangThai = new JPanel();
		pnlTrangThai.setLayout(null);
		pnlTrangThai.setBounds(684, 321, 566, 62);
		pnlChinh.add(pnlTrangThai);

		JLabel lblTrangThai = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyToNhom.lbTrangThai")));
		lblTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTrangThai.setBounds(10, 11, 150, 40);
		pnlTrangThai.add(lblTrangThai);

		cmbTrangThai = new JComboBox();
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] { "Đang hoạt động", "Ngừng hoạt động" }));
		cmbTrangThai.setBounds(189, 10, 352, 41);
		pnlTrangThai.add(cmbTrangThai);

		btnThemNhiu = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyToNhom.btnThemNhieu")));
		btnThemNhiu.setIcon(new ImageIcon("src/main/resources/icon/png/ic_add.png"));
		btnThemNhiu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnThemNhiu.setBounds(1083, 393, 167, 44);
		pnlChinh.add(btnThemNhiu);

		init();
		event();
	}

	private void init() {
		danhSachToNhom = new ArrayList<ToNhom>();
		danhSachCongNhanCuaToNhom = new ArrayList<CongNhan>();
		toNhomService = new ToNhomServiceImpl();
		congNhanService = new CongNhanServiceImpl();

		loadTableToNhom();
	}

	private void event() {
		btnThemNhiu.addActionListener((e) -> {
			thucHienChucNangThemNhieu();
		});
		btnXoaTrang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thucHienChucNangLamMoi();

			}
		});
		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thucHienChucNangThem();
				thucHienChucNangLamMoi();
			}
		});
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thucHienChucNangXoa();

			}
		});
		btnCapNhat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thucHienChucNangCapNhat();

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
				thucHienChucNangChonToNhom();
			}
		});
		tblDanhSachCongNhan.addMouseListener(new MouseListener() {

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
				// TODO Auto-generated method stub

			}
		});
	}

	private void thucHienChucNangThemNhieu() {
		List<ToNhom> dsToNhom = ToNhomExcelUtils.importExcelToNhom();
		if (!dsToNhom.isEmpty()) {
			dsToNhom = this.toNhomService.themNhieuToNhom(dsToNhom);
			JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("toNhom.themToNhomThanh"));
			loadTableToNhom();
			thucHienChucNangLamMoi();
		}

	}

	private void thucHienChucNangCapNhat() {
		int select = tblToNhom.getSelectedRow();
		if (select < 0) {
			JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("toNhom.chonToNhomCapNhat"));
			// JOptionPane.showMessageDialog(this, "Vui lòng chọn tổ nhóm cần cập nhật!");
			return;
		}
		ToNhom toNhom = danhSachToNhom.get(select);
		try {

			int choose = JOptionPane.showConfirmDialog(this, SystemConstants.BUNDLE.getString("toNhom.capNhatToNhom"),
					SystemConstants.BUNDLE.getString("toNhom.xacNhan"), JOptionPane.YES_NO_OPTION);
			if (choose == JOptionPane.YES_OPTION) {
				String tenToNhom = txtTenToNhom.getText().trim();
				String trangThai = (String) cmbTrangThai.getSelectedItem();
				boolean isTrangThai = false;
				if (trangThai == "Đang hoạt động")
					isTrangThai = true;

				ToNhom toNhomCapNhat = toNhomService
						.capNhatToNhom(new ToNhom(toNhom.getMaToNhom(), tenToNhom, 0, isTrangThai));

				if (toNhomCapNhat == null) {
					JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("toNhom.capNhatToNhomThanh"));
					// JOptionPane.showMessageDialog(this, "Cập nhật tổ nhóm không thành công!");
				} else {
					JOptionPane.showMessageDialog(this,
							SystemConstants.BUNDLE.getString("toNhom.capNhatToNhomKhongThanh"));
					// JOptionPane.showMessageDialog(this, "Cập nhật tổ nhóm thành công!");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		thucHienChucNangLamMoi();
	}

	private void thucHienChucNangXoa() {
		int select = tblToNhom.getSelectedRow();
		if (select < 0) {
			JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("toNhom.chonToNhom"));
			// JOptionPane.showMessageDialog(this, "Vui lòng chọn tổ nhóm cần xóa!");
			return;
		}
		ToNhom toNhom = danhSachToNhom.get(select);

		int choose = JOptionPane.showConfirmDialog(this, SystemConstants.BUNDLE.getString("toNhom.xoaToNhom"),
				SystemConstants.BUNDLE.getString("toNhom.xacNhan"), JOptionPane.YES_NO_OPTION);
		if (choose == JOptionPane.YES_OPTION) {
			boolean status = toNhomService.capNhatTrangThaiToNhom(toNhom.getMaToNhom(), false);

			if (status == true)
				JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("toNhom.xoaToNhomThanh"));
			else
				JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("toNhom.xoaToNhomKhongThanh"));

			thucHienChucNangLamMoi();
		}

	}

	private void thucHienChucNangChonToNhom() {
		int select = tblToNhom.getSelectedRow();

		if (select >= 0) {
			ToNhom toNhom = danhSachToNhom.get(select);

			// String maToNhom = txtMaToNhom.getText().trim();

			txtMaToNhom.setText(toNhom.getMaToNhom());
			txtTenToNhom.setText(toNhom.getTenToNhom());
			txtSoLuongCongNhan.setText(toNhom.getSoLuongCongNhan() + "");
			if (toNhom.isTrangThai() == true)
				cmbTrangThai.setSelectedItem("Đang hoạt động");
			else
				cmbTrangThai.setSelectedItem("Ngừng hoạt động");

			tblModelCongNhan.setRowCount(0);
			this.danhSachCongNhanCuaToNhom = this.congNhanService.timKiemCongNhanBangMaToNhom(toNhom.getMaToNhom());
			int stt = 1;

			for (CongNhan congNhan : this.danhSachCongNhanCuaToNhom) {
				String gioiTinh = congNhan.getGioiTinh() == 1 ? "Nam" : (congNhan.getGioiTinh() == 0 ? "Nữ" : "Khác");
				String trangThai = congNhan.isTrangThai() ? "Đang làm" : "Đã nghỉ";

				tblModelCongNhan.addRow(new Object[] { stt++, congNhan.getMaCongNhan(), congNhan.getHoTen(),
						congNhan.getCccd(), congNhan.getNgaySinh(), congNhan.getNgayVaoLam(), congNhan.getSoDienThoai(),
						gioiTinh, congNhan.getEmail(), congNhan.getDiaChi(), trangThai });
			}
		}
	}

	private void thucHienChucNangThem() {
		if (!thucHienChucNangKiemTra())
			return;

		String maToNhom = toNhomService.generateMaToNhom();
		String tenToNhom = txtTenToNhom.getText().trim();
		boolean trangThai = true;
		try {
			ToNhom toNhom = new ToNhom(maToNhom, tenToNhom, 0, trangThai);

			toNhom = toNhomService.themToNhom(toNhom);
			if (toNhom != null)
				JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("toNhom.themToNhomThanh"));
				//JOptionPane.showMessageDialog(this, "Thêm tổ nhóm thành công!");
			else
				JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("toNhom.themToNhomKhongThanh"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private boolean thucHienChucNangKiemTra() {
		lblLoiTenToNhom.setText("");

		String tenPhongBan = txtTenToNhom.getText().trim();
		boolean status = true;

		if (ObjectUtils.isEmpty(tenPhongBan)) {
			lblLoiTenToNhom.setText(SystemConstants.BUNDLE.getString("toNhom.loiTenToNhom"));
			//lblLoiTenToNhom.setText("Tên tổ nhóm không được rỗng");
			status = false;
		}
		return status;
	}
	
	private void thucHienChucNangLamMoiLoi() {
		lblLoiTenToNhom.setText("");
	}

	private void thucHienChucNangLamMoi() {
		thucHienChucNangLamMoiLoi();
		txtMaToNhom.setText("");
		txtTenToNhom.setText("");
		txtSoLuongCongNhan.setText("");
		cmbTrangThai.setSelectedIndex(0);
		tblModelCongNhan.setRowCount(0);
		loadTableToNhom();
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
}
