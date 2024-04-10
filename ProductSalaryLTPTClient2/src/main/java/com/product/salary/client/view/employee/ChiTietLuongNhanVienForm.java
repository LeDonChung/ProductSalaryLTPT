package com.product.salary.client.view.employee;

/**
 * @author Trần Thị Thanh Tuyền code giao diện, xử lý hiển thị
 */

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.entity.NhanVien;
import com.product.salary.application.service.LuongNhanVienService;
import com.product.salary.application.service.NhanVienService;
import com.product.salary.application.service.impl.LuongNhanVienServiceImpl;
import com.product.salary.application.service.impl.NhanVienServiceImpl;
import com.product.salary.application.utils.PriceFormatterUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChiTietLuongNhanVienForm extends JFrame {
	private JPanel pnlChinh;
	private JTable tblChiTietLuongNhanVien;
	private DefaultTableModel tblModelChiTietLuong;
	private JTextField txtMaNhanVien;
	private JTextField txtHoTen;
	private JTextField txtSoDienThoai;
	private JTextField txtPhongBan;
	private JTextField txtChucVu;
	private JTextField txtGioiTinh;
	private LuongNhanVienService luongNhanVienService;
	private List<Map<String, Object>> danhSachChiTietLuong;
	private NhanVienService nhanVienService;
	private JTextField txtSoLanDiTre;
	private JTextField txtTienPhat;
	private JTextField txtSoNgayNghi;
	private JTextField txtSoNgayLam;

	public ChiTietLuongNhanVienForm(String maNhanVien, int thang, int nam) {
		setResizable(false);
		SystemConstants.initLanguage();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 956, 562);
		getContentPane().setLayout(null);

		pnlChinh = new JPanel();
		pnlChinh.setBounds(0, 10, 941, 525);
		getContentPane().add(pnlChinh);
		pnlChinh.setLayout(null);

		JLabel lblTitle = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chiTietLuongNhanVien.title")));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTitle.setBounds(0, 5, 941, 46);
		pnlChinh.add(lblTitle);

		tblModelChiTietLuong = new DefaultTableModel(
				new String[] { SystemConstants.BUNDLE.getString("chamCongNhanVien.table.stt"),
						SystemConstants.BUNDLE.getString("chamCongNhanVien.table.maChamCong"),
						SystemConstants.BUNDLE.getString("chamCongNhanVien.table.caLam"),
						SystemConstants.BUNDLE.getString("chamCongNhanVien.table.ngayCham"),
						SystemConstants.BUNDLE.getString("chamCongNhanVien.table.trangThai") },
				0);
		tblChiTietLuongNhanVien = new JTable(tblModelChiTietLuong);
		tblChiTietLuongNhanVien.setShowVerticalLines(true);
		tblChiTietLuongNhanVien.setShowHorizontalLines(true);
		tblChiTietLuongNhanVien.setRowHeight(25);
		JScrollPane scrChiTietLuong = new JScrollPane(tblChiTietLuongNhanVien);
		scrChiTietLuong.setLocation(10, 275);
		scrChiTietLuong.setSize(921, 243);
		pnlChinh.add(scrChiTietLuong);

		JPanel pnlMaNhanVien = new JPanel();
		pnlMaNhanVien.setBounds(10, 52, 365, 46);
		pnlChinh.add(pnlMaNhanVien);
		pnlMaNhanVien.setLayout(null);

		JLabel lblMaNhanVien = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chiTietLuongNhanVien.maNhanVien")));
		lblMaNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaNhanVien.setBounds(10, 0, 90, 46);
		pnlMaNhanVien.add(lblMaNhanVien);

		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setEditable(false);
		txtMaNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaNhanVien.setBounds(104, 8, 251, 30);
		pnlMaNhanVien.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);

		JPanel pnlMaNhanVien_1 = new JPanel();
		pnlMaNhanVien_1.setLayout(null);
		pnlMaNhanVien_1.setBounds(10, 108, 365, 46);
		pnlChinh.add(pnlMaNhanVien_1);

		JLabel lblHoTen = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chiTietLuongNhanVien.hoTen")));
		lblHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblHoTen.setBounds(10, 0, 90, 46);
		pnlMaNhanVien_1.add(lblHoTen);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtHoTen.setEditable(false);
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(104, 8, 251, 30);
		pnlMaNhanVien_1.add(txtHoTen);

		JPanel pnlSoDienThoai = new JPanel();
		pnlSoDienThoai.setLayout(null);
		pnlSoDienThoai.setBounds(374, 52, 283, 46);
		pnlChinh.add(pnlSoDienThoai);

		JLabel lblSoDienThoai = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chiTietLuongNhanVien.soDienThoai")));
		lblSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoDienThoai.setBounds(10, 0, 86, 46);
		pnlSoDienThoai.add(lblSoDienThoai);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtSoDienThoai.setEditable(false);
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBounds(104, 8, 173, 30);
		pnlSoDienThoai.add(txtSoDienThoai);

		JPanel pnlPhongBan = new JPanel();
		pnlPhongBan.setLayout(null);
		pnlPhongBan.setBounds(374, 108, 283, 46);
		pnlChinh.add(pnlPhongBan);

		JLabel lblPhongBan = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chiTietLuongNhanVien.phongBan")));
		lblPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPhongBan.setBounds(10, 0, 84, 46);
		pnlPhongBan.add(lblPhongBan);

		txtPhongBan = new JTextField();
		txtPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtPhongBan.setEditable(false);
		txtPhongBan.setColumns(10);
		txtPhongBan.setBounds(104, 8, 173, 30);
		pnlPhongBan.add(txtPhongBan);

		JPanel pnlChucVu = new JPanel();
		pnlChucVu.setLayout(null);
		pnlChucVu.setBounds(659, 52, 272, 46);
		pnlChinh.add(pnlChucVu);

		JLabel lblChucVu = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chiTietLuongNhanVien.chucVu")));
		lblChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblChucVu.setBounds(10, 0, 85, 46);
		pnlChucVu.add(lblChucVu);

		txtChucVu = new JTextField();
		txtChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtChucVu.setEditable(false);
		txtChucVu.setColumns(10);
		txtChucVu.setBounds(87, 8, 175, 30);
		pnlChucVu.add(txtChucVu);

		JPanel pnlGioiTinh = new JPanel();
		pnlGioiTinh.setLayout(null);
		pnlGioiTinh.setBounds(659, 108, 272, 46);
		pnlChinh.add(pnlGioiTinh);

		JLabel lblGioiTinh = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chiTietLuongNhanVien.gioiTinh")));
		lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblGioiTinh.setBounds(10, 0, 92, 46);
		pnlGioiTinh.add(lblGioiTinh);

		txtGioiTinh = new JTextField();
		txtGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtGioiTinh.setEditable(false);
		txtGioiTinh.setColumns(10);
		txtGioiTinh.setBounds(87, 8, 175, 30);
		pnlGioiTinh.add(txtGioiTinh);

		JPanel pnlSoLanDiTre = new JPanel();
		pnlSoLanDiTre.setLayout(null);
		pnlSoLanDiTre.setBounds(10, 219, 365, 46);
		pnlChinh.add(pnlSoLanDiTre);

		JLabel lblSoLanDiTre = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chiTietLuongNhanVien.soLanDiTre")));
		lblSoLanDiTre.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoLanDiTre.setBounds(10, 0, 90, 46);
		pnlSoLanDiTre.add(lblSoLanDiTre);

		txtSoLanDiTre = new JTextField();
		txtSoLanDiTre.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtSoLanDiTre.setEditable(false);
		txtSoLanDiTre.setColumns(10);
		txtSoLanDiTre.setBounds(104, 8, 251, 30);
		pnlSoLanDiTre.add(txtSoLanDiTre);

		JPanel pnlTienPhat = new JPanel();
		pnlTienPhat.setLayout(null);
		pnlTienPhat.setBounds(374, 219, 412, 46);
		pnlChinh.add(pnlTienPhat);

		JLabel lblTienPhat = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chiTietLuongNhanVien.tienPhat")));
		lblTienPhat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTienPhat.setBounds(10, 0, 131, 46);
		pnlTienPhat.add(lblTienPhat);

		txtTienPhat = new JTextField();
		txtTienPhat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTienPhat.setEditable(false);
		txtTienPhat.setColumns(10);
		txtTienPhat.setBounds(151, 8, 251, 30);
		pnlTienPhat.add(txtTienPhat);

		JPanel pnlSoNgayNghi = new JPanel();
		pnlSoNgayNghi.setLayout(null);
		pnlSoNgayNghi.setBounds(374, 163, 412, 46);
		pnlChinh.add(pnlSoNgayNghi);

		JLabel lblSoNgayNghi = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chiTietLuongNhanVien.soNgayNghi")));
		lblSoNgayNghi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoNgayNghi.setBounds(10, 0, 131, 46);
		pnlSoNgayNghi.add(lblSoNgayNghi);

		txtSoNgayNghi = new JTextField();
		txtSoNgayNghi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtSoNgayNghi.setEditable(false);
		txtSoNgayNghi.setColumns(10);
		txtSoNgayNghi.setBounds(151, 8, 251, 30);
		pnlSoNgayNghi.add(txtSoNgayNghi);

		JPanel pnlSoNgayDiLam = new JPanel();
		pnlSoNgayDiLam.setLayout(null);
		pnlSoNgayDiLam.setBounds(10, 163, 365, 46);
		pnlChinh.add(pnlSoNgayDiLam);

		JLabel lblSoNgayLam = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chiTietLuongNhanVien.soNgayLam")));
		lblSoNgayLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoNgayLam.setBounds(10, 0, 90, 46);
		pnlSoNgayDiLam.add(lblSoNgayLam);

		txtSoNgayLam = new JTextField();
		txtSoNgayLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtSoNgayLam.setEditable(false);
		txtSoNgayLam.setColumns(10);
		txtSoNgayLam.setBounds(104, 8, 251, 30);
		pnlSoNgayDiLam.add(txtSoNgayLam);

		init();
		thucHienChucNangTimKiemChiTietLuong(maNhanVien, thang, nam);
	}

	private void init() {
		this.danhSachChiTietLuong = new ArrayList<Map<String, Object>>();
		this.luongNhanVienService = new LuongNhanVienServiceImpl();
		this.nhanVienService = new NhanVienServiceImpl();
	}

	private void thucHienChucNangTimKiemChiTietLuong(String maNhanVien, int thang, int nam) {
		tblModelChiTietLuong.setRowCount(0);

		danhSachChiTietLuong = luongNhanVienService.timTatCaChiTietLuongTheoThangVaNam(maNhanVien, thang, nam);
		NhanVien nv = nhanVienService.timKiemBangMaNhanVien(maNhanVien);
		txtMaNhanVien.setText(maNhanVien);
		txtHoTen.setText(nv.getHoTen());
		txtChucVu.setText(nv.getChucVu().getTenChucVu());
		txtGioiTinh.setText(nv.getGioiTinh() == 1 ? "Nam" : "Nữ");
		txtPhongBan.setText(nv.getPhongBan().getTenPhongBan());
		txtSoDienThoai.setText(nv.getSoDienThoai());
		int soLanDiTre = 0;
		int soLanNghi = 0;
		int stt = 1;
		for (Map<String, Object> chiTietLuong : danhSachChiTietLuong) {
			tblModelChiTietLuong.addRow(new Object[] { stt++, chiTietLuong.get("MaChamCong"), chiTietLuong.get("CaLam"),
					chiTietLuong.get("NgayChamCong"), chiTietLuong.get("TrangThai") });
			if (chiTietLuong.get("TrangThai").equals("Đi trễ")) {
				soLanDiTre++;
			}

			if (chiTietLuong.get("TrangThai").equals("Nghỉ")) {
				soLanNghi++;
			}
		}

		txtSoLanDiTre.setText(String.format("%d", soLanDiTre));
		txtSoNgayNghi.setText(String.format("%d", soLanNghi));
		txtSoNgayLam.setText(String.format("%d", danhSachChiTietLuong.size()));
		double tienPhat = soLanDiTre * 50000;
		txtTienPhat.setText(PriceFormatterUtils.format(tienPhat));
	}
}
