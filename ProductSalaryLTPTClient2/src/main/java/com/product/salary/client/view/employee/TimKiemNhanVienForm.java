package com.product.salary.client.view.employee;

/**
 * @author Trần Thị Thanh Tuyền: code giao diện, xử lý chức năng tìm kiếm
 */

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.entity.ChucVu;
import com.product.salary.application.entity.NhanVien;
import com.product.salary.application.entity.PhongBan;
import com.product.salary.application.entity.TrinhDo;
import com.product.salary.application.service.ChucVuService;
import com.product.salary.application.service.NhanVienService;
import com.product.salary.application.service.PhongBanService;
import com.product.salary.application.service.TrinhDoService;
import com.product.salary.application.service.impl.ChucVuServiceImpl;
import com.product.salary.application.service.impl.NhanVienServiceImpl;
import com.product.salary.application.service.impl.PhongBanServiceImpl;
import com.product.salary.application.service.impl.TrinhDoServiceImpl;
import com.product.salary.application.utils.DateConvertUtils;
import com.product.salary.application.utils.ImageUtils;
import com.product.salary.application.utils.PriceFormatterUtils;
import com.product.salary.application.utils.excels.NhanVienExcelUtils;
import com.toedter.calendar.JDateChooser;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class TimKiemNhanVienForm extends JPanel {
	private JTextField txtMaNhanVien;
	private JTextField txtHoTen;
	private JTextField txtCCCD;
	private JTextField txtDienThoai;
	private JTextField txtDiaChi;
	private JTextField txtLuongCoSo;
	private JTextField txtTroCap;
	private JTextField txtEmail;
	private JButton btnTimKiem;
	private JComboBox cmbTrinhDo;
	private JComboBox cmbTrangThai;
	private JDateChooser jcNgayVaoLam;
	private JDateChooser jcNgaySinh;
	private JComboBox cmbPhongBan;
	private JRadioButton radNu;
	private JRadioButton radNam;
	private JComboBox cmbChucVu;
	private JButton btnLamMoi;
	private List<NhanVien> danhSachNhanVien;
	private NhanVienService nhanVienService;
	private List<PhongBan> danhSachPhongBan;
	private PhongBanService phongBanService;
	private List<TrinhDo> danhSachTrinhDo;
	private TrinhDoService trinhDoService;
	private List<ChucVu> danhSachChucVu;
	private ChucVuService chucVuService;
	private JLabel lblHinhAnh;
	private DefaultTableModel tblModel;
	private JTable tblNhanVien;
	private DefaultComboBoxModel<PhongBan> cbbModelPhongBan;
	private DefaultComboBoxModel<TrinhDo> cbbModelTrinhDo;
	private DefaultComboBoxModel<ChucVu> cbbModelChucVu;
	private JComboBox cmbHeSoLuong;
	private JRadioButton radTatCa;
	private JButton btnXuatDanhSach;
	private JLabel lblLoiTroCap;
	private JLabel lblLoiLuongCoSo;

	public TimKiemNhanVienForm() {

		setLayout(null);

		JPanel pnlMain = new JPanel();
		pnlMain.setBounds(0, 11, 1273, 821);
		add(pnlMain);
		pnlMain.setLayout(null);

		JLabel lblTiTle = new JLabel(SystemConstants.BUNDLE.getString("quanLyNhanVien.timKiemNhanVien"));
		lblTiTle.setBounds(0, 0, 1250, 80);
		lblTiTle.setBorder(null);
		lblTiTle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiTle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlMain.add(lblTiTle);

		tblModel = new DefaultTableModel(new String[] {
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.tableDanhSachNhanVien.STT")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.tableDanhSachNhanVien.maNhanVien")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.tableDanhSachNhanVien.hoTen")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.tableDanhSachNhanVien.CCCD")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.tableDanhSachNhanVien.dienThoai")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.tableDanhSachNhanVien.email")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.tableDanhSachNhanVien.diaChi")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.tableDanhSachNhanVien.gioiTinh")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.tableDanhSachNhanVien.ngaySinh")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.tableDanhSachNhanVien.chucVu")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.tableDanhSachNhanVien.phongBan")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.tableDanhSachNhanVien.ngayVaoLam")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.tableDanhSachNhanVien.luongCoSo")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.tableDanhSachNhanVien.heSoLuong")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.tableDanhSachNhanVien.troCap")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.tableDanhSachNhanVien.trinhDo")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.tableDanhSachNhanVien.trangThai")) },
				10);

		tblNhanVien = new JTable(tblModel);
		tblNhanVien.setShowVerticalLines(true);
		tblNhanVien.setShowHorizontalLines(true);
		tblNhanVien.setRowHeight(25);

		// Set cac cot tien can phai
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		tblNhanVien.getColumnModel().getColumn(12).setCellRenderer(rightRenderer);
		tblNhanVien.getColumnModel().getColumn(14).setCellRenderer(rightRenderer);

		JScrollPane jscNhanVien = new JScrollPane(tblNhanVien);
		jscNhanVien.setBounds(10, 605, 1253, 206);
		pnlMain.add(jscNhanVien);

		JPanel pnlMaNV = new JPanel();
		pnlMaNV.setBounds(20, 306, 390, 62);
		pnlMain.add(pnlMaNV);
		pnlMaNV.setLayout(null);

		JLabel lblMaNV = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyNhanVien.lbMaNhanVien")));
		lblMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaNV.setBounds(10, 0, 96, 40);
		pnlMaNV.add(lblMaNV);

		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaNhanVien.setBounds(127, 0, 247, 40);
		pnlMaNV.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);

		JPanel pnlHoTen = new JPanel();
		pnlHoTen.setLayout(null);
		pnlHoTen.setBounds(469, 90, 384, 62);
		pnlMain.add(pnlHoTen);

		JLabel lblHoTen = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.lbHoTen")));
		lblHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblHoTen.setBounds(10, 0, 96, 40);
		pnlHoTen.add(lblHoTen);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(127, 0, 247, 40);
		pnlHoTen.add(txtHoTen);

		JPanel pnlCCCD = new JPanel();
		pnlCCCD.setLayout(null);
		pnlCCCD.setBounds(879, 91, 384, 62);
		pnlMain.add(pnlCCCD);

		JLabel lblCCCD = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.lbCCCD")));
		lblCCCD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCCCD.setBounds(10, 0, 96, 40);
		pnlCCCD.add(lblCCCD);

		txtCCCD = new JTextField();
		txtCCCD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(127, 0, 247, 40);
		pnlCCCD.add(txtCCCD);

		JPanel pnlDienThoai = new JPanel();
		pnlDienThoai.setLayout(null);
		pnlDienThoai.setBounds(469, 162, 384, 62);
		pnlMain.add(pnlDienThoai);

		JLabel lblDienThoai = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyNhanVien.lbDienThoai")));
		lblDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDienThoai.setBounds(10, 0, 96, 40);
		pnlDienThoai.add(lblDienThoai);

		txtDienThoai = new JTextField();
		txtDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtDienThoai.setColumns(10);
		txtDienThoai.setBounds(127, 0, 247, 40);
		pnlDienThoai.add(txtDienThoai);

		JPanel pnlDiaChi = new JPanel();
		pnlDiaChi.setLayout(null);
		pnlDiaChi.setBounds(469, 234, 384, 62);
		pnlMain.add(pnlDiaChi);

		JLabel lblDiaChi = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.lbDiaChi")));
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDiaChi.setBounds(10, 0, 96, 40);
		pnlDiaChi.add(lblDiaChi);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(127, 0, 247, 40);
		pnlDiaChi.add(txtDiaChi);

		JPanel pnlChucVu = new JPanel();
		pnlChucVu.setLayout(null);
		pnlChucVu.setBounds(469, 450, 384, 62);
		pnlMain.add(pnlChucVu);

		JLabel lblChucVu = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.lbChucVu")));
		lblChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblChucVu.setBounds(10, 0, 96, 40);
		pnlChucVu.add(lblChucVu);

		cbbModelChucVu = new DefaultComboBoxModel<ChucVu>();
		cmbChucVu = new JComboBox(cbbModelChucVu);
		cmbChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbChucVu.setBounds(127, 1, 247, 39);
		pnlChucVu.add(cmbChucVu);

		JPanel pnlGioiTinh = new JPanel();
		pnlGioiTinh.setLayout(null);
		pnlGioiTinh.setBounds(879, 234, 384, 62);
		pnlMain.add(pnlGioiTinh);

		JLabel lblGioiTinh = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.lbGioiTinh")));
		lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblGioiTinh.setBounds(10, 6, 76, 39);
		pnlGioiTinh.add(lblGioiTinh);

		radNam = new JRadioButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.rbtnNam")));
		radNam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		radNam.setBounds(127, 6, 76, 39);
		pnlGioiTinh.add(radNam);

		radNu = new JRadioButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.rbtnNu")));
		radNu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		radNu.setBounds(215, 6, 76, 39);
		pnlGioiTinh.add(radNu);

		if (SystemConstants.LANGUAGE == 1) {
			radTatCa = new JRadioButton("All");
		} else {
			radTatCa = new JRadioButton("Tất cả");
		}

		radTatCa.setSelected(true);
		radTatCa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		radTatCa.setBounds(302, 6, 76, 39);
		pnlGioiTinh.add(radTatCa);

		ButtonGroup btnGioiTinh = new ButtonGroup();
		btnGioiTinh.add(radNu);
		btnGioiTinh.add(radNam);
		btnGioiTinh.add(radTatCa);

		JPanel pnlPhongBan = new JPanel();
		pnlPhongBan.setLayout(null);
		pnlPhongBan.setBounds(20, 378, 390, 62);
		pnlMain.add(pnlPhongBan);

		JLabel lblPhongban = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.lbPhongBan")));
		lblPhongban.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPhongban.setBounds(10, 0, 114, 39);
		pnlPhongBan.add(lblPhongban);

		cbbModelPhongBan = new DefaultComboBoxModel<PhongBan>();
		cmbPhongBan = new JComboBox(cbbModelPhongBan);
		cmbPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbPhongBan.setBounds(128, 0, 247, 39);
		pnlPhongBan.add(cmbPhongBan);

		JPanel pnlNgaySinh = new JPanel();
		pnlNgaySinh.setLayout(null);
		pnlNgaySinh.setBounds(879, 450, 384, 62);
		pnlMain.add(pnlNgaySinh);
		jcNgaySinh = new JDateChooser();
		jcNgaySinh.getCalendarButton()
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_calendar.png"));
		jcNgaySinh.getCalendarButton().setBounds(new Rectangle(0, 0, 35, 0));
		jcNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		jcNgaySinh.setDateFormatString("yyyy-MM-dd");
		jcNgaySinh.setLocation(128, 0);
		jcNgaySinh.setSize(246, 40);
		pnlNgaySinh.add(jcNgaySinh);

		JLabel lblNgaySinh = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.lbNgaySinh")));
		lblNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNgaySinh.setBounds(10, 0, 108, 40);
		pnlNgaySinh.add(lblNgaySinh);

		JPanel pnlNgayVaoLam = new JPanel();
		pnlNgayVaoLam.setLayout(null);
		pnlNgayVaoLam.setBounds(469, 306, 384, 62);
		pnlMain.add(pnlNgayVaoLam);

		jcNgayVaoLam = new JDateChooser();
		jcNgayVaoLam.getCalendarButton().setBounds(new Rectangle(0, 0, 35, 0));
		jcNgayVaoLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		jcNgayVaoLam.setDateFormatString("yyyy-MM-dd");
		jcNgayVaoLam.setBounds(127, 0, 247, 50);
		jcNgayVaoLam.getCalendarButton()
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_calendar.png"));
		pnlNgayVaoLam.add(jcNgayVaoLam);

		JLabel lblNgayVaolam = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyNhanVien.lbNgayVaoLam")));
		lblNgayVaolam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNgayVaolam.setBounds(10, 5, 120, 40);
		pnlNgayVaoLam.add(lblNgayVaolam);

		JPanel pnlLuongCoSo = new JPanel();
		pnlLuongCoSo.setLayout(null);
		pnlLuongCoSo.setBounds(879, 522, 384, 73);
		pnlMain.add(pnlLuongCoSo);

		JLabel lblLuongCoSo = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyNhanVien.lbLuongCoSo")));
		lblLuongCoSo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblLuongCoSo.setBounds(10, 0, 107, 40);
		pnlLuongCoSo.add(lblLuongCoSo);

		txtLuongCoSo = new JTextField();
		txtLuongCoSo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtLuongCoSo.setColumns(10);
		txtLuongCoSo.setBounds(127, 0, 247, 40);
		pnlLuongCoSo.add(txtLuongCoSo);

		lblLoiLuongCoSo = new JLabel("");
		lblLoiLuongCoSo.setForeground(Color.RED);
		lblLoiLuongCoSo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblLoiLuongCoSo.setBounds(127, 39, 247, 34);
		pnlLuongCoSo.add(lblLoiLuongCoSo);

		JPanel pnlHeSoLuong = new JPanel();
		pnlHeSoLuong.setLayout(null);
		pnlHeSoLuong.setBounds(469, 522, 384, 62);
		pnlMain.add(pnlHeSoLuong);

		JLabel lblHeSoLuong = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyNhanVien.lbHeSoLuong")));
		lblHeSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblHeSoLuong.setBounds(10, 11, 107, 40);
		pnlHeSoLuong.add(lblHeSoLuong);

		cmbHeSoLuong = new JComboBox(new DefaultComboBoxModel(
				new String[] { "Tìm tất cả", "1.86", "2.26", "2.66", "3.06", "3.46", "3.86", "4.06" }));
		cmbHeSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbHeSoLuong.setBounds(127, 0, 247, 39);
		pnlHeSoLuong.add(cmbHeSoLuong);

		JPanel pnlTroCap = new JPanel();
		pnlTroCap.setLayout(null);
		pnlTroCap.setBounds(469, 378, 384, 73);
		pnlMain.add(pnlTroCap);

		JLabel lblTroCap = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.lbTroCap")));
		lblTroCap.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTroCap.setBounds(10, 0, 107, 40);
		pnlTroCap.add(lblTroCap);

		txtTroCap = new JTextField();
		txtTroCap.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTroCap.setColumns(10);
		txtTroCap.setBounds(127, 0, 247, 40);
		pnlTroCap.add(txtTroCap);

		lblLoiTroCap = new JLabel("");
		lblLoiTroCap.setForeground(Color.RED);
		lblLoiTroCap.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblLoiTroCap.setBounds(127, 39, 247, 34);
		pnlTroCap.add(lblLoiTroCap);

		JPanel pnlTrangThai = new JPanel();
		pnlTrangThai.setLayout(null);
		pnlTrangThai.setBounds(879, 378, 384, 62);
		pnlMain.add(pnlTrangThai);

		JLabel lblTrangThai = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyNhanVien.lbTrangThai")));
		lblTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTrangThai.setBounds(10, 0, 118, 39);
		pnlTrangThai.add(lblTrangThai);

		cmbTrangThai = new JComboBox();
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] { "Tìm tất cả", "Đang làm", "Đã nghỉ làm" }));
		cmbTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbTrangThai.setBounds(126, 0, 248, 39);
		pnlTrangThai.add(cmbTrangThai);

		JPanel pnlEmail = new JPanel();
		pnlEmail.setBounds(879, 163, 384, 62);
		pnlMain.add(pnlEmail);
		pnlEmail.setLayout(null);

		JLabel lblEmail = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.lbEmail")));
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblEmail.setBounds(10, 0, 96, 40);
		pnlEmail.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		txtEmail.setBounds(127, 0, 247, 40);
		pnlEmail.add(txtEmail);

		lblHinhAnh = new JLabel("");
		lblHinhAnh.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinhAnh.setIcon(
				new ImageIcon("src/main/resources/icon/png/ic_employee_120.png"));
		lblHinhAnh.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblHinhAnh.setBounds(118, 92, 160, 160);
		pnlMain.add(lblHinhAnh);

		JPanel pnlTrinhDo = new JPanel();
		pnlTrinhDo.setLayout(null);
		pnlTrinhDo.setBounds(879, 306, 384, 62);
		pnlMain.add(pnlTrinhDo);

		JLabel lblTrinhDo = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.trinhDo")));
		lblTrinhDo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTrinhDo.setBounds(10, 13, 118, 39);
		pnlTrinhDo.add(lblTrinhDo);

		cbbModelTrinhDo = new DefaultComboBoxModel<TrinhDo>();
		cmbTrinhDo = new JComboBox(cbbModelTrinhDo);
		cmbTrinhDo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbTrinhDo.setBounds(126, 13, 248, 39);
		pnlTrinhDo.add(cmbTrinhDo);

		btnTimKiem = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.btnTimKiem")));
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnTimKiem.setIcon(new ImageIcon("src/main/resources/icon/png/ic_search.png"));
		btnTimKiem.setBounds(20, 450, 196, 62);
		pnlMain.add(btnTimKiem);

		btnLamMoi = new JButton(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyNhanVien.btnXoaTrang")));
		btnLamMoi.setIcon(new ImageIcon("src/main/resources/icon/png/ic_refresh.png"));
		btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnLamMoi.setBounds(237, 450, 196, 62);
		pnlMain.add(btnLamMoi);

		btnXuatDanhSach = new JButton(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyNhanVien.btnXuatExcel")));
		btnXuatDanhSach
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_exel_.png"));
		btnXuatDanhSach.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnXuatDanhSach.setBounds(118, 522, 220, 62);
		pnlMain.add(btnXuatDanhSach);

		init();
		event();
	}

	private void event() {
		tblNhanVien.addMouseListener(new MouseListener() {

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
				thucHienChucNangChonTable();

			}
		});

		btnLamMoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thucHienChucNangLamMoi();

			}
		});

		btnTimKiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thucHienChucNangTimKiem();
			}
		});

		btnXuatDanhSach.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thucHienChucNangXuatDanhSach();

			}
		});

	}

	private void init() {
		danhSachNhanVien = new ArrayList<NhanVien>();
		nhanVienService = new NhanVienServiceImpl();

		danhSachPhongBan = new ArrayList<PhongBan>();
		phongBanService = new PhongBanServiceImpl();

		danhSachTrinhDo = new ArrayList<TrinhDo>();
		trinhDoService = new TrinhDoServiceImpl();

		danhSachChucVu = new ArrayList<ChucVu>();
		chucVuService = new ChucVuServiceImpl();

		loadTable();
		loadCombobox();
	}

	private void thucHienChucNangXuatDanhSach() {
		NhanVienExcelUtils.exportExcelNhanVien(danhSachNhanVien);
	}

	private void thucHienChucNangTimKiem() {
		if (!thucHienChucNangKiemTra()) {
			return;
		}
		try {
			String maNhanVien = txtMaNhanVien.getText().trim();
			String hoTen = txtHoTen.getText().trim();
			String diaChi = txtDiaChi.getText().trim();
			String cccd = txtCCCD.getText().trim();
			String email = txtEmail.getText().trim();
			String soDienThoai = txtDienThoai.getText().trim();
			Boolean trangThai = null;
			if (cmbTrangThai.getSelectedItem().equals("Đang làm"))
				trangThai = true;
			else if (cmbTrangThai.getSelectedItem().equals("Đã nghỉ làm"))
				trangThai = false;

			Integer gioiTinh = null;
			if (radNam.isSelected())
				gioiTinh = 1;
			else if (radNu.isSelected())
				gioiTinh = 0;
			double luongCoSo = 0;
			try {
				if (!txtLuongCoSo.getText().trim().equals("")) {
					luongCoSo = Double.parseDouble(txtLuongCoSo.getText().trim());
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			double heSoLuong = 0;
			if (cmbHeSoLuong.getSelectedIndex() != 0) {
				try {
					heSoLuong = Double.parseDouble((String) cmbHeSoLuong.getSelectedItem());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}

			double troCap = 0;
			try {
				if (!txtTroCap.getText().trim().equals(""))
					troCap = Double.parseDouble(txtTroCap.getText().trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

			LocalDate ngaySinh = DateConvertUtils.asLocalDate(jcNgaySinh.getDate(), ZoneId.systemDefault());
			LocalDate ngayVaoLam = DateConvertUtils.asLocalDate(jcNgayVaoLam.getDate(), ZoneId.systemDefault());

			ChucVu chucVu = danhSachChucVu.get(cmbChucVu.getSelectedIndex());
			PhongBan phongBan = danhSachPhongBan.get(cmbPhongBan.getSelectedIndex());
			TrinhDo trinhDo = danhSachTrinhDo.get(cmbTrinhDo.getSelectedIndex());

			NhanVien nv = new NhanVien(maNhanVien, hoTen, email, diaChi, gioiTinh, chucVu, cccd, soDienThoai, ngaySinh,
					phongBan, ngayVaoLam, luongCoSo, heSoLuong, troCap, trinhDo, null, trangThai);
			danhSachNhanVien = nhanVienService.timKiemNhanVien(nv);
			tblModel.setRowCount(0);

			int stt = 1;
			for (NhanVien nhanVien : danhSachNhanVien) {
				String giTinh = nhanVien.getGioiTinh() == 0 ? "Nữ" : "Nam";

				tblModel.addRow(new Object[] { stt++, nhanVien.getMaNhanVien(), nhanVien.getHoTen(), nhanVien.getCccd(),
						nhanVien.getSoDienThoai(), nhanVien.getEmail(), nhanVien.getDiaChi(), giTinh,
						nhanVien.getNgaySinh(), nhanVien.getChucVu().getTenChucVu(),
						nhanVien.getPhongBan().getTenPhongBan(), nhanVien.getNgayVaoLam(),
						PriceFormatterUtils.format(nhanVien.getLuongCoSo()), nhanVien.getHeSoLuong(),
						PriceFormatterUtils.format(nhanVien.getTroCap()), nhanVien.getTrinhDo().getTenTrinhDo(),
						nhanVien.isTrangThai() ? "Đang làm" : "Đã nghỉ làm" });
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean thucHienChucNangKiemTra() {
		thucHienChucNangLamMoiLoi();
		String luongCoSo = txtLuongCoSo.getText().trim();
		String troCap = txtTroCap.getText().trim();
		boolean status = true;
		if (!ObjectUtils.isEmpty(luongCoSo)) {
			try {
				double luongCS = Double.valueOf(luongCoSo.replace(",", ""));
				if (luongCS <= 0) {
					lblLoiLuongCoSo.setText(String.format("<html><p>%s</p></html>",
							SystemConstants.BUNDLE.getString("quanLyNhanVien.loiLuongCoSoLaSo")));
					status = false;
				}
			} catch (NumberFormatException e) {
				lblLoiLuongCoSo.setText(String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.loiLuongCoSoException")));
				status = false;
				e.printStackTrace();
			}
		}

		if (!ObjectUtils.isEmpty(troCap)) {
			double tCap = Double.valueOf(luongCoSo.replace(",", ""));
			try {
				if (tCap < 0) {
					lblLoiTroCap.setText(String.format("<html><p>%s</p></html>",
							SystemConstants.BUNDLE.getString("quanLyNhanVien.loiTroCap")));
					status = false;
				}
			} catch (Exception e) {
				lblLoiTroCap.setText(String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.loiTroCapException")));
				status = false;
			}
		}

		return status;
	}

	private void thucHienChucNangLamMoiLoi() {
		lblLoiTroCap.setText("");
		lblLoiLuongCoSo.setText("");
	}

	private void thucHienChucNangChonTable() {
		thucHienChucNangLamMoiLoi();
		int select = tblNhanVien.getSelectedRow();

		if (select >= 0) {
			NhanVien nhanVien = danhSachNhanVien.get(select);
			txtMaNhanVien.setText(nhanVien.getMaNhanVien());
			txtHoTen.setText(nhanVien.getHoTen());
			txtDiaChi.setText(nhanVien.getDiaChi());
			txtCCCD.setText(nhanVien.getCccd());
			txtDienThoai.setText(nhanVien.getSoDienThoai());
			txtEmail.setText(nhanVien.getEmail());
			txtLuongCoSo.setText(PriceFormatterUtils.format(nhanVien.getLuongCoSo()));
			txtTroCap.setText(PriceFormatterUtils.format(nhanVien.getTroCap()));
			if (nhanVien.getGioiTinh() == 0)
				radNu.setSelected(true);
			else
				radNam.setSelected(true);

			if (nhanVien.getHinhAnh() != null)
				lblHinhAnh.setIcon(ImageUtils.convertToImageIcon(nhanVien.getHinhAnh(), lblHinhAnh.getWidth(),
						lblHinhAnh.getHeight()));
			else
				lblHinhAnh.setIcon(
						new ImageIcon(NhanVien.class.getResource("src/main/resources/icon/png/ic_employee_120.png")));

			cbbModelChucVu.setSelectedItem(nhanVien.getChucVu());
			cbbModelPhongBan.setSelectedItem(nhanVien.getPhongBan());
			cbbModelTrinhDo.setSelectedItem(nhanVien.getTrinhDo());
			cmbHeSoLuong.setSelectedItem(nhanVien.getHeSoLuong());

			if (nhanVien.isTrangThai() == true)
				cmbTrangThai.setSelectedItem("Đang làm");
			else
				cmbTrangThai.setSelectedItem("Đã nghỉ làm");

			jcNgaySinh.setDate(DateConvertUtils.asUtilDate(nhanVien.getNgaySinh(), ZoneId.systemDefault()));
			jcNgayVaoLam.setDate(DateConvertUtils.asUtilDate(nhanVien.getNgayVaoLam(), ZoneId.systemDefault()));
		}
	}

	private void thucHienChucNangLamMoi() {
		thucHienChucNangLamMoiLoi();
		loadTable();
		loadCombobox();
		txtMaNhanVien.setText("");
		txtHoTen.setText("");
		txtDiaChi.setText("");
		txtCCCD.setText("");
		txtDienThoai.setText("");
		txtEmail.setText("");
		txtLuongCoSo.setText("");
		txtTroCap.setText("");
		lblHinhAnh.setIcon(new ImageIcon("src/main/resources/icon/png/ic_employee_120.png"));
		jcNgaySinh.setDate(null);
		jcNgayVaoLam.setDate(null);
		radTatCa.setSelected(true);
	}

	private void loadTable() {
		tblModel.setRowCount(0);
		danhSachNhanVien = nhanVienService.timKiemTatCaNhanVien();
		int stt = 1;
		for (NhanVien nhanVien : danhSachNhanVien) {
			String gioiTinh = "";
			if (nhanVien.getGioiTinh() == 0)
				gioiTinh = "Nữ";
			else
				gioiTinh = "Nam";

			tblModel.addRow(new Object[] { stt++, nhanVien.getMaNhanVien(), nhanVien.getHoTen(), nhanVien.getCccd(),
					nhanVien.getSoDienThoai(), nhanVien.getEmail(), nhanVien.getDiaChi(), gioiTinh,
					nhanVien.getNgaySinh(), nhanVien.getChucVu().getTenChucVu(),
					nhanVien.getPhongBan().getTenPhongBan(), nhanVien.getNgayVaoLam(),
					PriceFormatterUtils.format(nhanVien.getLuongCoSo()), nhanVien.getHeSoLuong(),
					PriceFormatterUtils.format(nhanVien.getTroCap()), nhanVien.getTrinhDo().getTenTrinhDo(),
					nhanVien.isTrangThai() ? "Đang làm" : "Đã nghỉ làm" });
		}

	}

	private void loadCombobox() {
		cbbModelPhongBan.removeAllElements();
		danhSachPhongBan = phongBanService.timKiemTatCaPhongBan();
		PhongBan pb = new PhongBan("xxxx", "Tìm tất cả");
		danhSachPhongBan.add(0, pb);
		cbbModelPhongBan.addAll(danhSachPhongBan);

		cbbModelChucVu.removeAllElements();
		danhSachChucVu = chucVuService.timKiemTatCaChucVu();
		ChucVu cv = new ChucVu("xxxx", "Tìm tất cả");
		danhSachChucVu.add(0, cv);
		cbbModelChucVu.addAll(danhSachChucVu);

		cbbModelTrinhDo.removeAllElements();
		danhSachTrinhDo = trinhDoService.timKiemTatCaTrinhDo();
		TrinhDo td = new TrinhDo("xxxx", "Tìm tất cả");
		danhSachTrinhDo.add(0, td);
		cbbModelTrinhDo.addAll(danhSachTrinhDo);

		cmbHeSoLuong.setSelectedIndex(0);
		cmbTrangThai.setSelectedIndex(0);
		cmbChucVu.setSelectedIndex(0);
		cmbTrinhDo.setSelectedIndex(0);
		cmbPhongBan.setSelectedIndex(0);
	}
}
