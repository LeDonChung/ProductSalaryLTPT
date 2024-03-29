package com.product.salary.application.view.product;

/**
 * @author Trần Tuấn Kiệt: Code giao diện
 * @author Lê Đôn Chủng: Code xử lý
 */

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.entity.SanPham;
import com.product.salary.application.service.SanPhamService;
import com.product.salary.application.service.impl.SanPhamServiceImpl;
import com.product.salary.application.utils.ImageUtils;
import com.product.salary.application.utils.PriceFormatterUtils;
import com.product.salary.application.utils.excels.SanPhamExcelUtils;
import com.product.salary.application.view.employee.NhanVienForm;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class SanPhamForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMaSanPham;
	private JTextField txtTenSanPham;
	private JTextField txtSoLuongTon;
	private JTextField txtChatLieu;
	private JTextField txtSoCongDoan;
	private JTextField txtDonViTinh;
	private JComboBox cmbTrangThai;
	private SanPhamService sanPhamService;
	private List<SanPham> sanPhams;
	private DefaultTableModel tableModelSanPham;
	private JTable tblSanPham;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnCapNhat;
	private JButton btnXoaTrang;
	private JButton btnAnh;
	private JTextField txtDonGia;
	private DefaultComboBoxModel cbmdfTrangThai;
	private JLabel lblHinhAnh;
	private JLabel lblLoiMaSanPham;
	private JLabel lblLoiTenSanPham;
	private JLabel lblLoiSoLuongTon;
	private JLabel lblLoiChatLieu;
	private JLabel lblLoiDonViTinh;
	private JLabel lblLoiSoCongDoan;
	private JLabel lblLoiTrangThai;
	private JLabel lblLoiDonGia;
	private JButton btnThemNhieu;

	/**
	 * Create the panel.
	 */
	public SanPhamForm() {
		setLayout(null);

		JPanel pnlMain = new JPanel();
		pnlMain.setBorder((Border) new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlMain.setBounds(0, 0, 1213, 821);
		pnlMain.setLayout(null);
		add(pnlMain);

		JLabel lblTitle = new JLabel(SystemConstants.BUNDLE.getString("sanPham.title"));
		lblTitle.setBorder(null);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTitle.setBounds(0, 0, 1250, 80);
		pnlMain.add(lblTitle);

		tableModelSanPham = new DefaultTableModel(new String[] {
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("sanPham.tableSanPham.STT")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("sanPham.tableSanPham.maSanPham")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("sanPham.tableSanPham.tenSanPham")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("sanPham.tableSanPham.soLuongTon")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("sanPham.tableSanPham.chatLieu")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("sanPham.tableSanPham.donViTinh")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("sanPham.tableSanPham.trangThai")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("sanPham.tableSanPham.soCongDoan")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("sanPham.tableSanPham.donGia")) },
				0);

		tblSanPham = new JTable(tableModelSanPham);
		tblSanPham.setShowVerticalLines(true);
		tblSanPham.setShowHorizontalLines(true);
		tblSanPham.setRowHeight(25);

		// Căn phải giá tiền
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		tblSanPham.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);

		JScrollPane scrSanPham = new JScrollPane(tblSanPham);
		scrSanPham.setBounds(10, 570, 1193, 241);
		pnlMain.add(scrSanPham);

		JPanel pnlMaSanPham = new JPanel();
		pnlMaSanPham.setBounds(451, 90, 384, 62);
		pnlMain.add(pnlMaSanPham);
		pnlMaSanPham.setLayout(null);

		JLabel lblMaSanPham = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("sanPham.maSanPham")));
		lblMaSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaSanPham.setBounds(10, 0, 96, 40);
		pnlMaSanPham.add(lblMaSanPham);

		txtMaSanPham = new JTextField();
		txtMaSanPham.setEnabled(false);
		txtMaSanPham.setEditable(false);
		txtMaSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaSanPham.setBounds(127, 0, 247, 40);
		pnlMaSanPham.add(txtMaSanPham);
		txtMaSanPham.setColumns(10);

		lblLoiMaSanPham = new JLabel("");
		lblLoiMaSanPham.setForeground(Color.RED);
		lblLoiMaSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiMaSanPham.setBounds(127, 43, 247, 20);
		pnlMaSanPham.add(lblLoiMaSanPham);

		JPanel pnlTenSanPham = new JPanel();
		pnlTenSanPham.setLayout(null);
		pnlTenSanPham.setBounds(451, 155, 384, 62);
		pnlMain.add(pnlTenSanPham);

		JLabel lblTenSanPham = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("sanPham.tenSanPham")));
		lblTenSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenSanPham.setBounds(10, 0, 96, 40);
		pnlTenSanPham.add(lblTenSanPham);

		txtTenSanPham = new JTextField();
		txtTenSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTenSanPham.setColumns(10);
		txtTenSanPham.setBounds(127, 0, 247, 40);
		pnlTenSanPham.add(txtTenSanPham);

		lblLoiTenSanPham = new JLabel("");
		lblLoiTenSanPham.setForeground(Color.RED);
		lblLoiTenSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiTenSanPham.setBounds(127, 42, 247, 20);
		pnlTenSanPham.add(lblLoiTenSanPham);

		JPanel pnlSoLuongTon = new JPanel();
		pnlSoLuongTon.setLayout(null);
		pnlSoLuongTon.setBounds(451, 220, 384, 62);
		pnlMain.add(pnlSoLuongTon);

		JLabel lblSoLuongTon = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("sanPham.soLuongTon")));
		lblSoLuongTon.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoLuongTon.setBounds(10, 0, 96, 40);
		pnlSoLuongTon.add(lblSoLuongTon);

		txtSoLuongTon = new JTextField();
		txtSoLuongTon.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtSoLuongTon.setColumns(10);
		txtSoLuongTon.setBounds(127, 0, 247, 40);
		pnlSoLuongTon.add(txtSoLuongTon);

		lblLoiSoLuongTon = new JLabel("");
		lblLoiSoLuongTon.setForeground(Color.RED);
		lblLoiSoLuongTon.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiSoLuongTon.setBounds(127, 42, 247, 20);
		pnlSoLuongTon.add(lblLoiSoLuongTon);

		JPanel pnlChatLieu = new JPanel();
		pnlChatLieu.setLayout(null);
		pnlChatLieu.setBounds(451, 284, 384, 62);
		pnlMain.add(pnlChatLieu);

		JLabel lblChatLieu = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("sanPham.chatLieu")));
		lblChatLieu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblChatLieu.setBounds(10, 0, 96, 40);
		pnlChatLieu.add(lblChatLieu);

		txtChatLieu = new JTextField();
		txtChatLieu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtChatLieu.setColumns(10);
		txtChatLieu.setBounds(127, 0, 247, 40);
		pnlChatLieu.add(txtChatLieu);

		lblLoiChatLieu = new JLabel("");
		lblLoiChatLieu.setForeground(Color.RED);
		lblLoiChatLieu.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiChatLieu.setBounds(127, 42, 247, 20);
		pnlChatLieu.add(lblLoiChatLieu);

		JPanel pnlDonViTinh = new JPanel();
		pnlDonViTinh.setLayout(null);
		pnlDonViTinh.setBounds(845, 90, 345, 62);
		pnlMain.add(pnlDonViTinh);

		JLabel lblDonViTinh = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("sanPham.donViTinh")));
		lblDonViTinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDonViTinh.setBounds(10, 0, 107, 40);
		pnlDonViTinh.add(lblDonViTinh);

		txtDonViTinh = new JTextField();
		txtDonViTinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtDonViTinh.setColumns(10);
		txtDonViTinh.setBounds(127, 0, 202, 40);
		pnlDonViTinh.add(txtDonViTinh);

		lblLoiDonViTinh = new JLabel("");
		lblLoiDonViTinh.setForeground(Color.RED);
		lblLoiDonViTinh.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiDonViTinh.setBounds(127, 42, 202, 20);
		pnlDonViTinh.add(lblLoiDonViTinh);

		JPanel pnlSoCongDoan = new JPanel();
		pnlSoCongDoan.setLayout(null);
		pnlSoCongDoan.setBounds(845, 155, 345, 62);
		pnlMain.add(pnlSoCongDoan);

		JLabel lblSoCongDoan = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("sanPham.soCongDoan")));
		lblSoCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoCongDoan.setBounds(10, 0, 107, 40);
		pnlSoCongDoan.add(lblSoCongDoan);

		txtSoCongDoan = new JTextField();
		txtSoCongDoan.setEnabled(false);
		txtSoCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtSoCongDoan.setColumns(10);
		txtSoCongDoan.setBounds(127, 0, 202, 40);
		pnlSoCongDoan.add(txtSoCongDoan);

		lblLoiSoCongDoan = new JLabel("");
		lblLoiSoCongDoan.setForeground(Color.RED);
		lblLoiSoCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiSoCongDoan.setBounds(127, 42, 202, 20);
		pnlSoCongDoan.add(lblLoiSoCongDoan);

		JPanel pnlTrangThai = new JPanel();
		pnlTrangThai.setLayout(null);
		pnlTrangThai.setBounds(845, 219, 345, 62);
		pnlMain.add(pnlTrangThai);

		JLabel lblTrangThai = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("sanPham.trangThai")));
		lblTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTrangThai.setBounds(10, 0, 118, 39);
		pnlTrangThai.add(lblTrangThai);

		cbmdfTrangThai = new DefaultComboBoxModel(new String[] { "Đang sản xuất", "Ngưng sản xuất" });

		cmbTrangThai = new JComboBox(cbmdfTrangThai);
		cmbTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbTrangThai.setBounds(128, 0, 202, 40);
		pnlTrangThai.add(cmbTrangThai);

		lblLoiTrangThai = new JLabel("");
		lblLoiTrangThai.setForeground(Color.RED);
		lblLoiTrangThai.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiTrangThai.setBounds(128, 42, 202, 20);
		pnlTrangThai.add(lblLoiTrangThai);

		btnThem = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("sanPham.btnThem")));
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnThem.setIcon(new ImageIcon("src/main/resources/icon/png/ic_add.png"));
		btnThem.setBounds(451, 387, 190, 44);
		pnlMain.add(btnThem);

		btnXoa = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("sanPham.btnXoa")));
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnXoa.setIcon(new ImageIcon("src/main/resources/icon/png/ic_remove.png"));
		btnXoa.setBounds(940, 387, 190, 44);
		pnlMain.add(btnXoa);

		btnCapNhat = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("sanPham.btnCapNhat")));
		btnCapNhat.setIcon(new ImageIcon("src/main/resources/icon/png/ic_update.png"));
		btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnCapNhat.setBounds(451, 473, 190, 44);
		pnlMain.add(btnCapNhat);

		btnXoaTrang = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("sanPham.btnLamMoi")));
		btnXoaTrang.setIcon(new ImageIcon("src/main/resources/icon/png/ic_refresh.png"));
		btnXoaTrang.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnXoaTrang.setBounds(691, 473, 190, 44);
		pnlMain.add(btnXoaTrang);

		JPanel pnlAnh = new JPanel();
		pnlAnh.setLayout(null);
		pnlAnh.setBounds(30, 85, 345, 389);
		pnlMain.add(pnlAnh);

		lblHinhAnh = new JLabel("");
		lblHinhAnh.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinhAnh.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblHinhAnh.setBounds(0, 0, 345, 329);
		this.lblHinhAnh
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_product_120.png"));
		pnlAnh.add(lblHinhAnh);

		btnAnh = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("sanPham.btnAnh")));
		btnAnh.setBounds(102, 339, 132, 40);
		pnlAnh.add(btnAnh);

		JPanel pnlDonGia = new JPanel();
		pnlDonGia.setLayout(null);
		pnlDonGia.setBounds(845, 284, 345, 62);
		pnlMain.add(pnlDonGia);

		JLabel lblDonGia = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("sanPham.donGia")));
		lblDonGia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDonGia.setBounds(10, 0, 107, 40);
		pnlDonGia.add(lblDonGia);

		txtDonGia = new JTextField();
		txtDonGia.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(127, 0, 202, 40);
		pnlDonGia.add(txtDonGia);

		lblLoiDonGia = new JLabel("");
		lblLoiDonGia.setForeground(Color.RED);
		lblLoiDonGia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiDonGia.setBounds(127, 42, 202, 20);
		pnlDonGia.add(lblLoiDonGia);

		btnThemNhieu = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("sanPham.btnThemNhieu")));
		btnThemNhieu.setIcon(new ImageIcon("src/main/resources/icon/png/ic_add.png"));
		btnThemNhieu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnThemNhieu.setBounds(691, 387, 190, 44);
		pnlMain.add(btnThemNhieu);

		init();

		event();
	}

	private void event() {
		this.btnAnh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon ic = ImageUtils.chooseImage(lblHinhAnh.getWidth(), lblHinhAnh.getHeight());
				lblHinhAnh.setIcon(ic);
			}
		});

		this.btnCapNhat.addActionListener((e) -> {
			this.thucHienChucNangCapNhat();
		});

		this.btnXoaTrang.addActionListener((e) -> {
			this.thucHienChucNangLamMoi();
		});

		this.btnThem.addActionListener((e) -> {
			this.thucHienChucNangThem();
		});

		this.btnXoa.addActionListener((v) -> {
			this.thucHienChucNangXoa();
		});

		this.tblSanPham.addMouseListener(new MouseListener() {

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
				thucHienChucNangClickSanPham();
			}
		});
		this.btnThemNhieu.addActionListener((e) -> {
			thucHienChucNangThemNhieu();
		});
	}

	private void thucHienChucNangThemNhieu() {
		List<SanPham> sanPhams = SanPhamExcelUtils.importExcelSanPham();
		if (!sanPhams.isEmpty()) {
			sanPhams = this.sanPhamService.themNhieuSanPham(sanPhams);
			String message = "";
			if (SystemConstants.LANGUAGE == 0) {
				message = String.format("Đã thêm %d sản phẩm.", sanPhams.size());
			} else {
				message = String.format("Added %d products", sanPhams.size());
			}
			JOptionPane.showMessageDialog(this, message);
			thucHienChucNangLamMoi();
		}

	}

	private void init() {
		this.sanPhamService = new SanPhamServiceImpl();
		this.sanPhams = new ArrayList<SanPham>();

		loadTable();
	}

	private void loadTable() {
		tableModelSanPham.setRowCount(0);
		this.sanPhams = this.sanPhamService.timKiemTatCaSanPham();
		int stt = 1;
		for (SanPham sanPham : this.sanPhams) {
			tableModelSanPham.addRow(new Object[] { stt++, sanPham.getMaSanPham(), sanPham.getTenSanPham(),
					sanPham.getSoLuongTon(), sanPham.getChatLieu(), sanPham.getDonViTinh(),
					sanPham.isTrangThai() ? "Đang sản xuất" : "Ngưng sản xuất", sanPham.getSoCongDoan(),
					PriceFormatterUtils.format(sanPham.getDonGia()) });

		}
	}

	private void thucHienChucNangClickSanPham() {
		int index = tblSanPham.getSelectedRow();
		if (index >= 0) {
			SanPham sp = this.sanPhams.get(index);
			this.txtMaSanPham.setText(sp.getMaSanPham());
			this.txtTenSanPham.setText(sp.getTenSanPham());
			this.txtChatLieu.setText(sp.getChatLieu());
			this.txtDonViTinh.setText(sp.getDonViTinh());
			this.txtSoCongDoan.setText(sp.getSoCongDoan() + "");
			this.txtSoLuongTon.setText(sp.getSoLuongTon() + "");
			this.txtDonGia.setText(PriceFormatterUtils.format(sp.getDonGia()));

			if (sp.getHinhAnh() != null) {
				this.lblHinhAnh.setIcon(
						ImageUtils.convertToImageIcon(sp.getHinhAnh(), lblHinhAnh.getWidth(), lblHinhAnh.getHeight()));
			} else {
				this.lblHinhAnh.setIcon(
						new ImageIcon("src/main/resources/icon/png/ic_product_120.png"));
			}

			if (sp.isTrangThai()) {
				this.cmbTrangThai.setSelectedIndex(0);
			} else {
				this.cmbTrangThai.setSelectedIndex(1);
			}
		}
	}

	private void thucHienChucNangLamMoi() {
		this.txtMaSanPham.setText("");
		this.txtTenSanPham.setText("");
		this.txtChatLieu.setText("");
		this.txtDonViTinh.setText("");
		this.txtSoCongDoan.setText("");
		this.txtTenSanPham.setText("");
		this.txtSoLuongTon.setText("");
		this.txtDonGia.setText("");
		this.lblHinhAnh
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_product_120.png"));
		this.loadTable();
		thucHienThucLamLamMoiLoi();
	}

	private void thucHienChucNangThem() {
		if (!thucHienChucNangKiemTra()) {
			return;
		}
		String maSanPham = this.sanPhamService.generateMaSanPham();
		String tenSanPham = this.txtTenSanPham.getText().trim();
		String donViTinh = this.txtDonViTinh.getText().trim();
		int soLuongTon = Integer.parseInt(this.txtSoLuongTon.getText().trim());
		String chatLieu = this.txtChatLieu.getText().trim();

		Double donGia = PriceFormatterUtils.parse(this.txtDonGia.getText().trim());

		byte[] hinhAnh = ImageUtils.convertToByteArray(lblHinhAnh.getIcon());
		try {
			SanPham sanPham = new SanPham(maSanPham, tenSanPham, donViTinh, soLuongTon, chatLieu, donGia, hinhAnh);
			sanPham = this.sanPhamService.themSanPham(sanPham);
			if (sanPham != null) {
				JOptionPane.showMessageDialog(this,
						SystemConstants.BUNDLE.getString("sanPham.thongBao.themSanPhamThanhCong"));
				this.thucHienChucNangLamMoi();
			} else {
				JOptionPane.showMessageDialog(this,
						SystemConstants.BUNDLE.getString("sanPham.thongBao.themSanPhamKhongThanhCong"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void thucHienChucNangXoa() {
		int isSelected = this.tblSanPham.getSelectedRow();
		if (isSelected >= 0) {

			SanPham sanPham = this.sanPhams.get(isSelected);
			String message = "";
			String title = "";
			if (SystemConstants.LANGUAGE == 0) {
				message = "Bạn có muốn xóa sản phẩm " + sanPham.getTenSanPham() + " ?.";
				title = "Xác nhận";
			} else {
				message = "Do you want to delete product " + sanPham.getTenSanPham() + " ?.";
				title = "Confirm";
			}
			int choose = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION);
			if (choose == JOptionPane.OK_OPTION) {
				boolean trangThai = this.sanPhamService.capNhatTrangThaiSanPham(sanPham.getMaSanPham(), false);
				if (trangThai) {
					JOptionPane.showMessageDialog(this,
							SystemConstants.BUNDLE.getString("sanPham.thongBao.xoaSanPhamThanhCong"));
					this.thucHienChucNangLamMoi();
				} else {
					JOptionPane.showMessageDialog(this,
							SystemConstants.BUNDLE.getString("sanPham.thongBao.xoaSanPhamKhongThanhCong"));
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("sanPham.thongBao.chonSanPhamDeXoa"));
		}
	}

	private void thucHienChucNangCapNhat() {

		int isSelected = this.tblSanPham.getSelectedRow();
		if (isSelected >= 0) {
			if (!thucHienChucNangKiemTra()) {
				return;
			}

			String maSanPham = this.txtMaSanPham.getText().trim();
			String tenSanPham = this.txtTenSanPham.getText().trim();
			String donViTinh = this.txtDonViTinh.getText().trim();
			int soLuongTon = Integer.parseInt(this.txtSoLuongTon.getText().trim());
			String chatLieu = this.txtChatLieu.getText().trim();

			Double donGia = PriceFormatterUtils.parse(this.txtDonGia.getText().trim());

			byte[] hinhAnh = ImageUtils.convertToByteArray(lblHinhAnh.getIcon());
			boolean trangThai = this.cmbTrangThai.getSelectedIndex() == 0 ? true : false;

			try {

				SanPham sanPham = new SanPham(maSanPham, tenSanPham, soLuongTon, hinhAnh, chatLieu, donViTinh, 0,
						donGia, trangThai);
				String message = "";
				String title = "";
				if (SystemConstants.LANGUAGE == 0) {
					message = "Bạn có muốn cập nhật sản phẩm " + sanPham.getTenSanPham() + " ?.";
					title = "Xác nhận";
				} else {
					message = "Do you want to update product " + sanPham.getTenSanPham() + " ?.";
					title = "Confirm";
				}

				int choose = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION);
				if (choose == JOptionPane.OK_OPTION) {
					sanPham = this.sanPhamService.capNhatSanPham(sanPham);
					if (sanPham != null) {
						JOptionPane.showMessageDialog(this,
								SystemConstants.BUNDLE.getString("sanPham.thongBao.capNhatSanPhamThanhCong"));
						this.thucHienChucNangLamMoi();
					} else {
						JOptionPane.showMessageDialog(this,
								SystemConstants.BUNDLE.getString("sanPham.thongBao.capNhatSanPhamKhongThanhCong"));
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(this,
					SystemConstants.BUNDLE.getString("sanPham.thongBao.chonSanPhamDeCapNhat"));
		}
	}

	public void thucHienThucLamLamMoiLoi() {
		lblLoiMaSanPham.setText("");
		lblLoiTenSanPham.setText("");
		lblLoiSoLuongTon.setText("");
		lblLoiChatLieu.setText("");
		lblLoiDonViTinh.setText("");
		lblLoiSoCongDoan.setText("");
		lblLoiTrangThai.setText("");
		lblLoiDonGia.setText("");
	}

	public boolean thucHienChucNangKiemTra() {
		thucHienThucLamLamMoiLoi();

		String tenSanPham = this.txtTenSanPham.getText().trim();
		String soLuongTon = this.txtSoLuongTon.getText().trim();
		String donGia = this.txtDonGia.getText().trim();

		boolean status = true;
		if (ObjectUtils.isEmpty(tenSanPham)) {
			lblLoiTenSanPham.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("sanPham.thongBao.tenSanPham")));
			status = false;
		}

		if (ObjectUtils.isEmpty(soLuongTon)) {
			lblLoiSoLuongTon.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("sanPham.thongBao.soLuongTon")));
			status = false;
		} else {
			try {
				if (Integer.parseInt(soLuongTon) < 0) {
					lblLoiSoLuongTon.setText(String.format("<html><p>%s</p></html>",
							SystemConstants.BUNDLE.getString("sanPham.thongBao.soLuongTonLaSoNguyen")));
					status = false;
				}
			} catch (Exception e) {
				lblLoiSoLuongTon.setText(String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("sanPham.thongBao.soLuongTonLaSoNguyen")));
				status = false;
			}
		}

		if (ObjectUtils.isEmpty(donGia)) {
			lblLoiDonGia.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("sanPham.thongBao.donGia")));
			status = false;
		} else {
			try {
				Double price = Double.valueOf(donGia.replace(",", ""));
				if (price < 0) {
					lblLoiDonGia.setText(String.format("<html><p>%s</p></html>",
							SystemConstants.BUNDLE.getString("sanPham.thongBao.donGiaLaSoThuc")));
					status = false;
				}
			} catch (Exception e) {
				lblLoiDonGia.setText(String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("sanPham.thongBao.donGiaLaSoThuc")));
				status = false;
			}
		}

		return status;
	}
}
