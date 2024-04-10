package com.product.salary.client.view.employee;
/**
 * @author Trần Thị Thanh Tuyền code giao diện, xử lý các chức năng
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
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class NhanVienForm extends JPanel {
	private JTextField txtMaNhanVien;
	private JTextField txtHoTen;
	private JTextField txtCCCD;
	private JTextField txtDienThoai;
	private JTextField txtDiaChi;
	private JTextField txtLuongCoSo;
	private JTextField txtTroCap;
	private JTextField txtEmail;
	private DefaultTableModel tblModel;
	private JTable tblNhanVien;
	private JScrollPane scrNhanVien;
	private JRadioButton radNam;
	private JRadioButton radNu;
	private ButtonGroup btnGioiTinh;
	private JComboBox cmbPhongBan;
	private JDateChooser jcNgaySinh;
	private JDateChooser jcNgayVaoLam;
	private JComboBox cmbHeSoLuong;
	private JComboBox cmbTrangThai;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnCapNhat;
	private JButton btnXoaTrang;
	private JComboBox cmbTrinhDo;
	private JButton btnChonAnh;
	private JLabel lblHinhAnh;
	private List<NhanVien> danhSachNhanVien;
	private NhanVienService nhanVienService;
	private List<PhongBan> danhSachPhongBan;
	private PhongBanService phongBanService;
	private List<ChucVu> danhSachChucVu;
	private ChucVuService chucVuService;
	private JComboBox cmbChucVu;
	private List<TrinhDo> danhSachTrinhDo;
	private TrinhDoService trinhDoService;
	private JLabel lblLoiHoTen;
	private JLabel lblLoiEmail;
	private JLabel lblLoiCccd;
	private JLabel lblLoiSoDienThoai;
	private JLabel lblLoiDiaChi;
	private JLabel lblLoiNgaySinh;
	private JLabel lblLoiNgayVaoLam;
	private JLabel lblLoiLuongCoSo;
	private JLabel lblLoiTroCap;
	private DefaultComboBoxModel cbbModelChucVu;
	private DefaultComboBoxModel<PhongBan> cbbModelPhongBan;
	private DefaultComboBoxModel<TrinhDo> cbbModelTrinhDo;
	private JButton btnThemNhieu;

	/**
	 * Create the panel.
	 */
	public NhanVienForm() {
		setLayout(null);

		JPanel pnlChinh = new JPanel();
		pnlChinh.setBounds(10, 11, 1275, 821);
		add(pnlChinh);
		pnlChinh.setLayout(null);

		JLabel lblTiTleNhanVien = new JLabel(SystemConstants.BUNDLE.getString("quanLyNhanVien.title"));
		lblTiTleNhanVien.setBounds(0, 0, 1250, 80);
		lblTiTleNhanVien.setBorder(null);
		lblTiTleNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiTleNhanVien.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlChinh.add(lblTiTleNhanVien);

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
				7);

		tblNhanVien = new JTable(tblModel);
		tblNhanVien.setShowVerticalLines(true);
		tblNhanVien.setShowHorizontalLines(true);
		tblNhanVien.setRowHeight(25);

		// set cột có số tiền căn phải
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		tblNhanVien.getColumnModel().getColumn(12).setCellRenderer(rightRenderer);
		tblNhanVien.getColumnModel().getColumn(14).setCellRenderer(rightRenderer);

		scrNhanVien = new JScrollPane(tblNhanVien);
		scrNhanVien.setBounds(0, 605, 1275, 206);
		pnlChinh.add(scrNhanVien);

		JPanel pnlMaNV = new JPanel();
		pnlMaNV.setBounds(20, 450, 390, 62);
		pnlChinh.add(pnlMaNV);
		pnlMaNV.setLayout(null);

		JLabel lblMaNV = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyNhanVien.lbMaNhanVien")));
		lblMaNV.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaNV.setBounds(10, 0, 96, 40);
		pnlMaNV.add(lblMaNV);

		txtMaNhanVien = new JTextField();
		txtMaNhanVien.setEnabled(false);
		txtMaNhanVien.setEditable(false);
		txtMaNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaNhanVien.setBounds(133, 0, 247, 40);
		pnlMaNV.add(txtMaNhanVien);
		txtMaNhanVien.setColumns(10);

		JPanel pnlHoTen = new JPanel();
		pnlHoTen.setBounds(469, 90, 384, 72);
		pnlHoTen.setLayout(null);
		pnlChinh.add(pnlHoTen);

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

		lblLoiHoTen = new JLabel("");
		lblLoiHoTen.setForeground(Color.RED);
		lblLoiHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblLoiHoTen.setBounds(127, 42, 247, 20);
		pnlHoTen.add(lblLoiHoTen);

		JPanel pnlCCCD = new JPanel();
		pnlCCCD.setBounds(879, 91, 384, 71);
		pnlCCCD.setLayout(null);
		pnlChinh.add(pnlCCCD);

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

		lblLoiCccd = new JLabel("");
		lblLoiCccd.setForeground(Color.RED);
		lblLoiCccd.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblLoiCccd.setBounds(127, 41, 247, 20);
		pnlCCCD.add(lblLoiCccd);

		JPanel pnlDienThoai = new JPanel();
		pnlDienThoai.setBounds(469, 162, 384, 73);
		pnlDienThoai.setLayout(null);
		pnlChinh.add(pnlDienThoai);

		JLabel lblDienThoai = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyNhanVien.lbDienThoai")));
		lblDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDienThoai.setBounds(10, -6, 96, 40);
		pnlDienThoai.add(lblDienThoai);

		txtDienThoai = new JTextField();
		txtDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtDienThoai.setColumns(10);
		txtDienThoai.setBounds(127, 0, 247, 40);
		pnlDienThoai.add(txtDienThoai);

		lblLoiSoDienThoai = new JLabel("");
		lblLoiSoDienThoai.setForeground(Color.RED);
		lblLoiSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblLoiSoDienThoai.setBounds(127, 43, 247, 20);
		pnlDienThoai.add(lblLoiSoDienThoai);

		JPanel pnlDiaChi = new JPanel();
		pnlDiaChi.setBounds(469, 234, 384, 72);
		pnlDiaChi.setLayout(null);
		pnlChinh.add(pnlDiaChi);

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

		lblLoiDiaChi = new JLabel("");
		lblLoiDiaChi.setForeground(Color.RED);
		lblLoiDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblLoiDiaChi.setBounds(127, 42, 247, 20);
		pnlDiaChi.add(lblLoiDiaChi);

		JPanel pnlChucVu = new JPanel();
		pnlChucVu.setBounds(469, 450, 384, 62);
		pnlChucVu.setLayout(null);
		pnlChinh.add(pnlChucVu);

		JLabel lblChucVu = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.lbChucVu")));
		lblChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblChucVu.setBounds(10, 0, 96, 40);
		pnlChucVu.add(lblChucVu);

		cbbModelChucVu = new DefaultComboBoxModel<>();
		cmbChucVu = new JComboBox(cbbModelChucVu);
		cmbChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbChucVu.setBounds(127, 0, 247, 39);
		pnlChucVu.add(cmbChucVu);

		JPanel pnlGioiTinh = new JPanel();
		pnlGioiTinh.setBounds(879, 234, 384, 62);
		pnlGioiTinh.setLayout(null);
		pnlChinh.add(pnlGioiTinh);

		JLabel lblGioiTinh = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.lbGioiTinh")));
		lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblGioiTinh.setBounds(10, 0, 76, 39);
		pnlGioiTinh.add(lblGioiTinh);

		radNam = new JRadioButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.rbtnNam")));
		radNam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		radNam.setBounds(127, 0, 85, 39);
		pnlGioiTinh.add(radNam);

		radNu = new JRadioButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.rbtnNu")));
		radNu.setSelected(true);
		radNu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		radNu.setBounds(229, 0, 85, 39);
		pnlGioiTinh.add(radNu);

		btnGioiTinh = new ButtonGroup();
		btnGioiTinh.add(radNu);
		btnGioiTinh.add(radNam);

		JPanel pnlPhongBan = new JPanel();
		pnlPhongBan.setBounds(879, 378, 384, 62);
		pnlPhongBan.setLayout(null);
		pnlChinh.add(pnlPhongBan);

		JLabel lblPhongban = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.lbPhongBan")));
		lblPhongban.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPhongban.setBounds(10, 0, 114, 39);
		pnlPhongBan.add(lblPhongban);

		cbbModelPhongBan = new DefaultComboBoxModel<PhongBan>();
		cmbPhongBan = new JComboBox(cbbModelPhongBan);
		cmbPhongBan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbPhongBan.setBounds(134, 0, 247, 39);
		pnlPhongBan.add(cmbPhongBan);

		JPanel pnlNgaySinh = new JPanel();
		pnlNgaySinh.setBounds(879, 450, 384, 72);
		pnlNgaySinh.setLayout(null);
		pnlChinh.add(pnlNgaySinh);
		jcNgaySinh = new JDateChooser();
		jcNgaySinh.getCalendarButton()
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_calendar.png"));
		jcNgaySinh.getCalendarButton().setBounds(new Rectangle(0, 0, 35, 0));
		jcNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		jcNgaySinh.setDateFormatString("yyyy-MM-dd");
		jcNgaySinh.setLocation(128, 0);
		jcNgaySinh.setSize(246, 40);
		pnlNgaySinh.add(jcNgaySinh);
		jcNgaySinh.setDate(DateConvertUtils.asUtilDate(LocalDate.now(), ZoneId.systemDefault()));

		JLabel lblNgaySinh = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.lbNgaySinh")));
		lblNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNgaySinh.setBounds(10, 0, 108, 40);
		pnlNgaySinh.add(lblNgaySinh);

		lblLoiNgaySinh = new JLabel("");
		lblLoiNgaySinh.setForeground(Color.RED);
		lblLoiNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblLoiNgaySinh.setBounds(127, 42, 247, 20);
		pnlNgaySinh.add(lblLoiNgaySinh);

		JPanel pnlNgayVaoLam = new JPanel();
		pnlNgayVaoLam.setBounds(469, 306, 384, 72);
		pnlNgayVaoLam.setLayout(null);
		pnlChinh.add(pnlNgayVaoLam);

		jcNgayVaoLam = new JDateChooser();
		jcNgayVaoLam.getCalendarButton().setBounds(new Rectangle(0, 0, 35, 0));
		jcNgayVaoLam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		jcNgayVaoLam.setDateFormatString("yyyy-MM-dd");
		jcNgayVaoLam.setBounds(127, 0, 247, 40);
		jcNgayVaoLam.getCalendarButton()
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_calendar.png"));
		pnlNgayVaoLam.add(jcNgayVaoLam);
		jcNgayVaoLam.setDate(DateConvertUtils.asUtilDate(LocalDate.now(), ZoneId.systemDefault()));

		JLabel lblNgayVaolam = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyNhanVien.lbNgayVaoLam")));
		lblNgayVaolam.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNgayVaolam.setBounds(10, 0, 120, 40);
		pnlNgayVaoLam.add(lblNgayVaolam);

		lblLoiNgayVaoLam = new JLabel("");
		lblLoiNgayVaoLam.setForeground(Color.RED);
		lblLoiNgayVaoLam.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblLoiNgayVaoLam.setBounds(127, 42, 247, 20);
		pnlNgayVaoLam.add(lblLoiNgayVaoLam);

		JPanel pnlLuongCoSo = new JPanel();
		pnlLuongCoSo.setBounds(879, 522, 384, 73);
		pnlLuongCoSo.setLayout(null);
		pnlChinh.add(pnlLuongCoSo);

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
		lblLoiLuongCoSo.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblLoiLuongCoSo.setBounds(127, 43, 247, 20);
		pnlLuongCoSo.add(lblLoiLuongCoSo);

		JPanel pnlHeSoLuong = new JPanel();
		pnlHeSoLuong.setBounds(469, 522, 384, 62);
		pnlHeSoLuong.setLayout(null);
		pnlChinh.add(pnlHeSoLuong);

		JLabel lblHeSoLuong = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyNhanVien.lbHeSoLuong")));
		lblHeSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblHeSoLuong.setBounds(10, 0, 107, 40);
		pnlHeSoLuong.add(lblHeSoLuong);

		cmbHeSoLuong = new JComboBox();
		cmbHeSoLuong.setModel(
				new DefaultComboBoxModel(new String[] { "1.86", "2.26", "2.66", "3.06", "3.46", "3.86", "4.06" }));
		cmbHeSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbHeSoLuong.setBounds(127, 0, 247, 39);
		pnlHeSoLuong.add(cmbHeSoLuong);

		JPanel pnlTroCap = new JPanel();
		pnlTroCap.setBounds(469, 378, 384, 72);
		pnlTroCap.setLayout(null);
		pnlChinh.add(pnlTroCap);

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
		lblLoiTroCap.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblLoiTroCap.setBounds(127, 42, 247, 20);
		pnlTroCap.add(lblLoiTroCap);

		JPanel pnlTrangThai = new JPanel();
		pnlTrangThai.setBounds(20, 522, 390, 62);
		pnlTrangThai.setLayout(null);
		pnlChinh.add(pnlTrangThai);

		JLabel lblTrangThai = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyNhanVien.lbTrangThai")));
		lblTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTrangThai.setBounds(10, 0, 118, 39);
		pnlTrangThai.add(lblTrangThai);

		cmbTrangThai = new JComboBox();
		cmbTrangThai.setModel(new DefaultComboBoxModel(new String[] { "Đang làm", "Đã nghỉ làm" }));
		cmbTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbTrangThai.setBounds(132, 0, 248, 39);
		pnlTrangThai.add(cmbTrangThai);

		btnThem = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.btnThem")));
		btnThem.setBounds(20, 339, 190, 44);
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnThem.setIcon(new ImageIcon("src/main/resources/icon/png/ic_add.png"));
		pnlChinh.add(btnThem);

		btnXoa = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.btnXoa")));
		btnXoa.setBounds(220, 396, 190, 44);
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnXoa.setIcon(new ImageIcon("src/main/resources/icon/png/ic_remove.png"));
		pnlChinh.add(btnXoa);

		btnCapNhat = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.btnCapNhat")));
		btnCapNhat.setBounds(20, 393, 190, 44);
		btnCapNhat.setIcon(new ImageIcon("src/main/resources/icon/png/ic_update.png"));
		btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		pnlChinh.add(btnCapNhat);

		btnXoaTrang = new JButton(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyNhanVien.btnXoaTrang")));
		btnXoaTrang.setBounds(220, 339, 190, 44);
		btnXoaTrang.setIcon(new ImageIcon("src/main/resources/icon/png/ic_refresh.png"));
		btnXoaTrang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		pnlChinh.add(btnXoaTrang);

		JPanel pnlEmail = new JPanel();
		pnlEmail.setBounds(879, 163, 384, 72);
		pnlChinh.add(pnlEmail);
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

		lblLoiEmail = new JLabel("");
		lblLoiEmail.setForeground(Color.RED);
		lblLoiEmail.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblLoiEmail.setBounds(127, 42, 247, 20);
		pnlEmail.add(lblLoiEmail);

		JPanel pnlTrinhDo = new JPanel();
		pnlTrinhDo.setBounds(879, 306, 384, 62);
		pnlChinh.add(pnlTrinhDo);
		pnlTrinhDo.setLayout(null);

		cbbModelTrinhDo = new DefaultComboBoxModel<TrinhDo>();
		cmbTrinhDo = new JComboBox(cbbModelTrinhDo);
		cmbTrinhDo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbTrinhDo.setBounds(127, 0, 247, 39);
		pnlTrinhDo.add(cmbTrinhDo);

		JLabel lblTrinhDo = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.lbTrinhDo")));
		lblTrinhDo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTrinhDo.setBounds(10, 0, 107, 40);
		pnlTrinhDo.add(lblTrinhDo);

		lblHinhAnh = new JLabel("");
		lblHinhAnh.setBounds(31, 90, 160, 160);
		lblHinhAnh.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinhAnh.setIcon(new ImageIcon("src/main/resources/icon/png/ic_employee_120.png"));
		lblHinhAnh.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlChinh.add(lblHinhAnh);

		btnChonAnh = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyNhanVien.btnChonAnh")));
		btnChonAnh.setBounds(31, 272, 160, 44);
		btnChonAnh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		pnlChinh.add(btnChonAnh);

		btnThemNhieu = new JButton(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyNhanVien.btnThemNhieu")));
		btnThemNhieu.setIcon(new ImageIcon("src/main/resources/icon/png/ic_add.png"));
		btnThemNhieu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnThemNhieu.setBounds(220, 285, 190, 44);
		pnlChinh.add(btnThemNhieu);

		init();
		event();
	}

	private void event() {
		this.btnChonAnh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon ic = ImageUtils.chooseImage(lblHinhAnh.getWidth(), lblHinhAnh.getHeight());
				lblHinhAnh.setIcon(ic);

			}
		});

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

		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thucHienChucNangThem();

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

		btnXoaTrang.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thucHienChucNangLamMoi();

			}
		});

		btnThemNhieu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thucHienChucNangThemNhieu();
			}
		});

	}

	private void init() {
		danhSachNhanVien = new ArrayList<NhanVien>();
		nhanVienService = new NhanVienServiceImpl();

		danhSachChucVu = new ArrayList<ChucVu>();
		chucVuService = new ChucVuServiceImpl();

		danhSachPhongBan = new ArrayList<PhongBan>();
		phongBanService = new PhongBanServiceImpl();

		danhSachTrinhDo = new ArrayList<TrinhDo>();
		trinhDoService = new TrinhDoServiceImpl();

		loadTable();
		loadCombobox();
	}

	private void thucHienChucNangThemNhieu() {
		List<NhanVien> dsNhanVien = NhanVienExcelUtils.importExcelNhanVien();
		if (!dsNhanVien.isEmpty()) {
			nhanVienService.themNhieuNhanVien(dsNhanVien);

			thucHienChucNangLamMoi();
		}
	}

	private void thucHienChucNangXoa() {
		int select = tblNhanVien.getSelectedRow();

		if (select >= 0) {
			NhanVien nhanVien = danhSachNhanVien.get(select);
			int xacNhan = -1;
			if (SystemConstants.LANGUAGE == 1) {
				xacNhan = JOptionPane
						.showConfirmDialog(this,
								"Staff information can't delete. \nYou want to change the employee's status "
										+ nhanVien.getHoTen() + " 'stopped working'?",
								"Xác nhận", JOptionPane.YES_NO_OPTION);
			} else {
				xacNhan = JOptionPane.showConfirmDialog(this,
						"Thông tin nhân viên không thể xóa. \nBạn có muốn thay đổi trạng thái của nhân viên "
								+ nhanVien.getHoTen() + " thành 'Đã nghỉ làm' không?",
						"Xác nhận", JOptionPane.YES_NO_OPTION);
			}

			if (xacNhan == JOptionPane.YES_OPTION) {
				boolean status = nhanVienService.capNhatTrangThaiNghiLamCuaNhanVien(nhanVien.getMaNhanVien());

				if (status == true) {
					if (SystemConstants.LANGUAGE == 1) {
						JOptionPane.showMessageDialog(this,
								"Update an employee's 'stopped working' status has employee ID "
										+ nhanVien.getMaNhanVien() + " successfully!");
					} else {
						JOptionPane.showMessageDialog(this, "Cập nhật trạng thái 'Đã nghỉ làm' của nhân viên có mã "
								+ nhanVien.getMaNhanVien() + " thành công!");
					}

					thucHienChucNangLamMoi();
				} else {
					if (SystemConstants.LANGUAGE == 1) {
						JOptionPane.showMessageDialog(this,
								"Update an employee's 'stopped working' status has employee ID "
										+ nhanVien.getMaNhanVien() + " unsuccessfully!");
					} else {
						JOptionPane.showMessageDialog(this, "Cập nhật trạng thái 'Đã nghỉ làm' của nhân viên có mã "
								+ nhanVien.getMaNhanVien() + " không thành công!");
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("quanLyNhanVien.thongBaoChonNhanVienCanXoa")));
		}
	}

	private void thucHienChucNangCapNhat() {
		if (!thucHienChucNangKiemTra()) {
			return;
		}

		int select = tblNhanVien.getSelectedRow();
		if (select >= 0) {
			NhanVien nhanVienCu = danhSachNhanVien.get(select);

			String hoTen = txtHoTen.getText().trim();
			String diaChi = txtDiaChi.getText().trim();
			String cccd = txtCCCD.getText().trim();
			String email = txtEmail.getText().trim();
			String soDienThoai = txtDienThoai.getText().trim();
			String luongCoSo = txtLuongCoSo.getText().trim();
			String troCap = txtTroCap.getText().trim();

			LocalDate ngaySinh = DateConvertUtils.asLocalDate(jcNgaySinh.getDate(), ZoneId.systemDefault());
			LocalDate ngayVaoLam = DateConvertUtils.asLocalDate(jcNgayVaoLam.getDate(), ZoneId.systemDefault());

			PhongBan phongBan = (PhongBan) cbbModelPhongBan.getSelectedItem();
			ChucVu chucVu = (ChucVu) cbbModelChucVu.getSelectedItem();
			TrinhDo trinhDo = (TrinhDo) cbbModelTrinhDo.getSelectedItem();
			String heSoLuong = (String) cmbHeSoLuong.getSelectedItem();
			byte[] hinhAnh = ImageUtils.convertToByteArray(lblHinhAnh.getIcon());

			int gioiTinh = radNam.isSelected() ? 1 : 0;

			try {
				NhanVien nhanVienCapNhat = new NhanVien(nhanVienCu.getMaNhanVien(), hoTen, email, diaChi, gioiTinh,
						chucVu, cccd, soDienThoai, ngaySinh, phongBan, ngayVaoLam,
						luongCoSo.equals("") ? 0 : PriceFormatterUtils.parse(luongCoSo), Double.valueOf(heSoLuong),
						troCap.equals("") ? 0 : PriceFormatterUtils.parse(troCap), trinhDo, hinhAnh,
						cmbTrangThai.getSelectedItem().equals("Đang làm") ? true : false);

				int xacNhan = -1;
				if (SystemConstants.LANGUAGE == 1) {
					xacNhan = JOptionPane
							.showConfirmDialog(this,
									"You want to update employee's information has employee ID "
											+ nhanVienCapNhat.getMaNhanVien() + " ?",
									"Xác nhận", JOptionPane.YES_NO_OPTION);
				} else {
					xacNhan = JOptionPane.showConfirmDialog(
							this, "Bạn có muốn cập nhật thông tin của nhân viên có mã là "
									+ nhanVienCapNhat.getMaNhanVien() + " không?",
							"Xác nhận", JOptionPane.YES_NO_OPTION);
				}

				if (xacNhan == JOptionPane.YES_OPTION) {
					nhanVienCapNhat = nhanVienService.capNhatNhanVien(nhanVienCapNhat);

					if (nhanVienCapNhat != null) {
						if (SystemConstants.LANGUAGE == 1) {
							JOptionPane.showMessageDialog(this, "Update employee's information has employee ID "
									+ nhanVienCapNhat.getMaNhanVien() + " successfully!");
						} else {
							JOptionPane.showMessageDialog(this, "Cập nhật thông tin của nhân viên có mã "
									+ nhanVienCapNhat.getMaNhanVien() + " thành công!");
						}

						thucHienChucNangLamMoi();
					} else {
						if (SystemConstants.LANGUAGE == 1) {
							JOptionPane.showMessageDialog(this, "Update employee's information has employee ID "
									+ nhanVienCapNhat.getMaNhanVien() + " unsuccessfully!");
						} else {
							JOptionPane.showMessageDialog(this, "Cập nhật thông tin của nhân viên có mã "
									+ nhanVienCapNhat.getMaNhanVien() + " không thành công!");
						}
					}

				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Hệ thống đang có lỗi!");
				e.printStackTrace();
			}

		} else {
			JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("quanLyNhanVien.thongBaoChonNhanVienCanCapNhat")));
		}
	}

	private void thucHienChucNangThem() {
		if (!thucHienChucNangKiemTra()) {
			return;
		}
		String hoTen = txtHoTen.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String cccd = txtCCCD.getText().trim();
		String email = txtEmail.getText().trim();
		String soDienThoai = txtDienThoai.getText().trim();
		String luongCoSo = txtLuongCoSo.getText().trim();
		String troCap = txtTroCap.getText().trim();

		LocalDate ngaySinh = DateConvertUtils.asLocalDate(jcNgaySinh.getDate(), ZoneId.systemDefault());
		LocalDate ngayVaoLam = DateConvertUtils.asLocalDate(jcNgayVaoLam.getDate(), ZoneId.systemDefault());

		PhongBan phongBan = (PhongBan) cbbModelPhongBan.getSelectedItem();
		ChucVu chucVu = (ChucVu) cbbModelChucVu.getSelectedItem();
		TrinhDo trinhDo = (TrinhDo) cbbModelTrinhDo.getSelectedItem();
		String heSoLuong = (String) cmbHeSoLuong.getSelectedItem();
		byte[] hinhAnh = ImageUtils.convertToByteArray(lblHinhAnh.getIcon());

		int gioiTinh = radNam.isSelected() ? 1 : 0;

		try {
			NhanVien nhanVien = new NhanVien(null, hoTen, email, diaChi, gioiTinh, chucVu, cccd, soDienThoai, ngaySinh,
					phongBan, ngayVaoLam, PriceFormatterUtils.parse(luongCoSo), Double.valueOf(heSoLuong),
					troCap.equals("") ? 0 : PriceFormatterUtils.parse(troCap), trinhDo, hinhAnh, true);
			nhanVien = nhanVienService.themNhanVien(nhanVien);
			if (nhanVien != null) {
				if (SystemConstants.LANGUAGE == 1) {
					JOptionPane.showMessageDialog(this, "Add an employee successfully. \nEmployee ID of "
							+ nhanVien.getHoTen() + ": " + nhanVien.getMaNhanVien());
				} else {
					JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công. \nMã nhân viên của "
							+ nhanVien.getHoTen() + " là: " + nhanVien.getMaNhanVien());
				}

				thucHienChucNangLamMoi();
			} else {
				JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.thongBaoThemNhanVienKhongThanhCong")));
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Hệ thống đang lỗi!");
			e.printStackTrace();
		}

	}

	private boolean thucHienChucNangKiemTra() {
		thucHienChucNangLamMoiLoi();

		// Lấy ra các giá trị trong các text

		String hoTen = txtHoTen.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String cccd = txtCCCD.getText().trim();
		String email = txtEmail.getText().trim();
		String soDienThoai = txtDienThoai.getText().trim();
		String luongCoSo = txtLuongCoSo.getText().trim();
		String troCap = txtTroCap.getText().trim();

		LocalDate ngaySinh = DateConvertUtils.asLocalDate(jcNgaySinh.getDate(), ZoneId.systemDefault());
		LocalDate ngayVaoLam = DateConvertUtils.asLocalDate(jcNgayVaoLam.getDate(), ZoneId.systemDefault());

		boolean status = true;
		if (ObjectUtils.isEmpty(hoTen)) {
			lblLoiHoTen.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("quanLyNhanVien.loiHoTen")));
			status = false;
		}

		if (ObjectUtils.isEmpty(diaChi)) {
			lblLoiDiaChi.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("quanLyNhanVien.loiDiaChi")));
			status = false;
		}

		if (ObjectUtils.isEmpty(soDienThoai)) {
			lblLoiSoDienThoai.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("quanLyNhanVien.loiSoDienThoai")));
			status = false;
		} else if (!soDienThoai.matches("^0[0-9]{9}$")) {
			lblLoiSoDienThoai.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("quanLyNhanVien.loiSoDienThoaiRegex")));
			status = false;
		}

		if (ObjectUtils.isEmpty(cccd)) {
			lblLoiCccd.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("quanLyNhanVien.loiCccd")));
			status = false;
		} else if (!cccd.matches("^0[0-9]{11}$") && cccd.length() != 12) {
			lblLoiCccd.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("quanLyNhanVien.loiCccdRegex")));
			status = false;
		}

		if (ObjectUtils.isEmpty(email)) {
			lblLoiEmail.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("quanLyNhanVien.loiEmail")));
			status = false;
		} else if (!email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
			lblLoiEmail.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("quanLyNhanVien.loiEmailRegex")));
			status = false;
		}

		try {
			if (ObjectUtils.isEmpty(luongCoSo)) {
				lblLoiLuongCoSo.setText(String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyNhanVien.loiLuongCoSo")));
				status = false;
			} else {
				double luongCS = PriceFormatterUtils.parse(luongCoSo);
				if (luongCS <= 0) {
					lblLoiLuongCoSo.setText(String.format("<html><p>%s</p></html>",
							SystemConstants.BUNDLE.getString("quanLyNhanVien.loiLuongCoSoLaSo")));
					status = false;
				}
			}

		} catch (Exception e) {
			lblLoiLuongCoSo.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("quanLyNhanVien.loiLuongCoSoException")));
			status = false;
			e.printStackTrace();
		}

		if (!ObjectUtils.isEmpty(troCap)) {
			double tCap = PriceFormatterUtils.parse(troCap);
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

		int tuoi = Period.between(ngaySinh, LocalDate.now()).getYears();
		if (tuoi < 18) {
			if (SystemConstants.LANGUAGE == 1) {
				lblLoiNgaySinh.setText("Employee's age >= 18! Current age: " + tuoi);
			} else {
				lblLoiNgaySinh.setText("Tuổi của nhân viên phải >= 18! Tuổi hiện tại: " + tuoi);
			}

			status = false;
		}

		if (ngayVaoLam.isAfter(LocalDate.now())) {
			lblLoiNgayVaoLam.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("quanLyNhanVien.loiNgayVaoLam")));
			status = false;
		}

		return status;
	}

	private void thucHienChucNangLamMoiLoi() {
		lblLoiCccd.setText("");
		lblLoiDiaChi.setText("");
		lblLoiEmail.setText("");
		lblLoiHoTen.setText("");
		lblLoiLuongCoSo.setText("");
		lblLoiNgaySinh.setText("");
		lblLoiNgayVaoLam.setText("");
		lblLoiSoDienThoai.setText("");
		lblLoiTroCap.setText("");
	}

	private void thucHienChucNangChonTable() {
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

			cmbChucVu.setSelectedItem(nhanVien.getChucVu());
			cmbPhongBan.setSelectedItem(nhanVien.getPhongBan());
			cmbTrinhDo.setSelectedItem(nhanVien.getTrinhDo());
			cmbHeSoLuong.setSelectedItem(nhanVien.getHeSoLuong() + "");
			if (nhanVien.isTrangThai() == true)
				cmbTrangThai.setSelectedIndex(0);
			else
				cmbTrangThai.setSelectedIndex(1);

			jcNgaySinh.setDate(DateConvertUtils.asUtilDate(nhanVien.getNgaySinh(), ZoneId.systemDefault()));
			jcNgayVaoLam.setDate(DateConvertUtils.asUtilDate(nhanVien.getNgayVaoLam(), ZoneId.systemDefault()));
		}
	}

	private void thucHienChucNangLamMoi() {
		loadCombobox();
		loadTable();
		thucHienChucNangLamMoiLoi();
		cmbHeSoLuong.setSelectedIndex(0);
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
		radNu.setSelected(true);

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
		cbbModelPhongBan.addAll(danhSachPhongBan);
		if (!danhSachPhongBan.isEmpty()) {
			cmbPhongBan.setSelectedIndex(0);
		}

		cbbModelChucVu.removeAllElements();
		danhSachChucVu = chucVuService.timKiemTatCaChucVu();
		cbbModelChucVu.addAll(danhSachChucVu);
		if (!danhSachChucVu.isEmpty()) {
			cmbChucVu.setSelectedIndex(0);
		}

		cbbModelTrinhDo.removeAllElements();
		danhSachTrinhDo = trinhDoService.timKiemTatCaTrinhDo();
		cbbModelTrinhDo.addAll(danhSachTrinhDo);
		if (!danhSachTrinhDo.isEmpty()) {
			cmbTrinhDo.setSelectedIndex(0);
		}
	}
}
