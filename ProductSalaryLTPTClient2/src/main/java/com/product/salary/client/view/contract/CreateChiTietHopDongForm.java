package com.product.salary.client.view.contract;

/**
 * @author Lê Đôn Chủng: Code giao diện, Xử lý code
 */

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.entity.ChiTietHopDong;
import com.product.salary.application.entity.HopDong;
import com.product.salary.application.entity.SanPham;
import com.product.salary.client.interfaces.ISendChiTietHopDong;
import com.product.salary.application.service.SanPhamService;
import com.product.salary.application.service.impl.SanPhamServiceImpl;
import com.product.salary.application.utils.PriceFormatterUtils;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CreateChiTietHopDongForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblSanPham;
	private JTextField txtSoLuong;
	private JTextField txtGiaDat;
	private DefaultTableModel tabelModelSanPham;
	private JLabel lblLoiSoLuong;
	private JButton btnThemChiTietHopDong;
	private JLabel lblLoiGiaDat;
	private ISendChiTietHopDong sendChiTietHoaDon;
	private List<SanPham> sanPhams;
	private SanPhamService sanPhamService;

	/**
	 * Create the frame.
	 */
	public CreateChiTietHopDongForm(ISendChiTietHopDong iSendChiTietHoaDon) {
		this.sendChiTietHoaDon = iSendChiTietHoaDon;
		SystemConstants.initLanguage();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 707, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel(SystemConstants.BUNDLE.getString("createChiTietHopDong.title"));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTitle.setBounds(10, 10, 672, 51);
		contentPane.add(lblTitle);

		JPanel pnlDanhSachSanPham = new JPanel();
		pnlDanhSachSanPham.setBounds(10, 93, 672, 216);
		contentPane.add(pnlDanhSachSanPham);
		pnlDanhSachSanPham.setLayout(null);

		tabelModelSanPham = new DefaultTableModel(
				new Object[] { SystemConstants.BUNDLE.getString("createChiTietHopDong.danhSachSanPham.stt"),
						SystemConstants.BUNDLE.getString("createChiTietHopDong.danhSachSanPham.maSanPham"),
						SystemConstants.BUNDLE.getString("createChiTietHopDong.danhSachSanPham.tenSanPham"),
						SystemConstants.BUNDLE.getString("createChiTietHopDong.danhSachSanPham.soLuongTon"),
						SystemConstants.BUNDLE.getString("createChiTietHopDong.danhSachSanPham.donGia") },
				0);
		tblSanPham = new JTable(tabelModelSanPham);
		tblSanPham.setBounds(10, 10, 946, 196);
		tblSanPham.setShowVerticalLines(true);
		tblSanPham.setShowHorizontalLines(true);
		tblSanPham.setRowHeight(25);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		tblSanPham.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		JScrollPane scrDanhSachSanPham = new JScrollPane(tblSanPham);
		scrDanhSachSanPham.setLocation(0, 0);
		scrDanhSachSanPham.setSize(673, 216);
		pnlDanhSachSanPham.add(scrDanhSachSanPham);

		JLabel lblDanhSachSanPham = new JLabel(
				SystemConstants.BUNDLE.getString("createChiTietHopDong.lblDanhSachSanPham"));
		lblDanhSachSanPham.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDanhSachSanPham.setBounds(10, 71, 229, 23);
		contentPane.add(lblDanhSachSanPham);

		JPanel pnlSoLuong = new JPanel();
		pnlSoLuong.setBounds(10, 335, 306, 78);
		contentPane.add(pnlSoLuong);
		pnlSoLuong.setLayout(null);

		JLabel lblSoLuong = new JLabel(SystemConstants.BUNDLE.getString("createChiTietHopDong.lblSoLuong"));
		lblSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoLuong.setBounds(0, 0, 86, 40);
		pnlSoLuong.add(lblSoLuong);

		txtSoLuong = new JTextField();
		txtSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtSoLuong.setBounds(96, 0, 200, 40);
		pnlSoLuong.add(txtSoLuong);
		txtSoLuong.setColumns(10);

		lblLoiSoLuong = new JLabel("");
		lblLoiSoLuong.setForeground(Color.RED);
		lblLoiSoLuong.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiSoLuong.setBounds(96, 39, 200, 39);
		pnlSoLuong.add(lblLoiSoLuong);

		JPanel pnlGiaDat = new JPanel();
		pnlGiaDat.setLayout(null);
		pnlGiaDat.setBounds(347, 335, 335, 78);
		contentPane.add(pnlGiaDat);

		JLabel lblGiaDat = new JLabel(SystemConstants.BUNDLE.getString("createChiTietHopDong.lblGiaDat"));
		lblGiaDat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblGiaDat.setBounds(0, 0, 102, 40);
		pnlGiaDat.add(lblGiaDat);

		txtGiaDat = new JTextField();
		txtGiaDat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtGiaDat.setColumns(10);
		txtGiaDat.setBounds(125, 0, 200, 40);
		pnlGiaDat.add(txtGiaDat);

		lblLoiGiaDat = new JLabel("");
		lblLoiGiaDat.setForeground(Color.RED);
		lblLoiGiaDat.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiGiaDat.setBounds(125, 39, 200, 29);
		pnlGiaDat.add(lblLoiGiaDat);

		btnThemChiTietHopDong = new JButton("");
		btnThemChiTietHopDong
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_add.png"));
		btnThemChiTietHopDong.setBounds(236, 423, 200, 40);
		contentPane.add(btnThemChiTietHopDong);

		init();
		event();
	}

	private void event() {
		this.btnThemChiTietHopDong.addActionListener((e) -> {
			thucHienChucNangThemChiTietHopDong();
		});
	}

	private void init() {
		this.sanPhams = new ArrayList<SanPham>();
		this.sanPhamService = new SanPhamServiceImpl();
		this.loadDataSanPham();
	}

	private void loadDataSanPham() {
		tabelModelSanPham.setRowCount(0);
		sanPhams = sanPhamService.timTatCaSanPhamDangSanXuat();

		int stt = 1;
		for (SanPham sanPham : sanPhams) {

			tabelModelSanPham.addRow(new Object[] { stt++, sanPham.getMaSanPham(), sanPham.getTenSanPham(),
					sanPham.getSoLuongTon(), PriceFormatterUtils.format(sanPham.getDonGia()) });
		}
	}

	private void thucHienChucNangThemChiTietHopDong() {
		int index = this.tblSanPham.getSelectedRow();
		if (index < 0) {
			JOptionPane.showMessageDialog(this,
					SystemConstants.BUNDLE.getString("createChiTietHopDong.thongBao.chonSanPham"));
			return;
		}

		if (!kiemTraHopLe()) {
			return;
		}

		SanPham sanPham = this.sanPhams.get(index);
		String soLuong = this.txtSoLuong.getText().trim();
		String giaDat = this.txtGiaDat.getText().trim();
		try {
			ChiTietHopDong chiTietHopDong = new ChiTietHopDong(new HopDong(), sanPham, Integer.parseInt(soLuong),
					PriceFormatterUtils.parse(giaDat));
			sendChiTietHoaDon.send(chiTietHopDong);
			dispose();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this,
					SystemConstants.BUNDLE.getString("createChiTietHopDong.thongBao.taoChiTietHopDongKhongThanhCong"));
			e.printStackTrace();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
					SystemConstants.BUNDLE.getString("createChiTietHopDong.thongBao.taoChiTietHopDongKhongThanhCong"));
			e.printStackTrace();
		}

	}

	private void thucHienChucNangLamMoiLoi() {
		this.lblLoiSoLuong.setText("");
		this.lblLoiGiaDat.setText("");
	}

	private boolean kiemTraHopLe() {
		thucHienChucNangLamMoiLoi();
		String soLuong = this.txtSoLuong.getText().trim();
		String giaDat = this.txtGiaDat.getText().trim();
		boolean status = true;
		if (ObjectUtils.isEmpty(soLuong)) {
			this.lblLoiSoLuong.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("createChiTietHopDong.thongBao.soLuongPhaiNhap")));
			status = false;
		} else {
			try {
				if (Integer.parseInt(soLuong) <= 0) {
					lblLoiSoLuong.setText(String.format("<html><p>%s</p></html>",
							SystemConstants.BUNDLE.getString("createChiTietHopDong.thongBao.soLuongPhaiLaSoNguyen")));
					status = false;
				}
			} catch (Exception e) {
				lblLoiSoLuong.setText(String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("createChiTietHopDong.thongBao.soLuongPhaiLaSoNguyen")));
				status = false;
			}
		}

		if (ObjectUtils.isEmpty(giaDat)) {
			this.lblLoiGiaDat.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("createChiTietHopDong.thongBao.giaDatPhaiNhap")));
			status = false;
		} else {
			try {
				if (PriceFormatterUtils.parse(giaDat) <= 0) {
					lblLoiGiaDat.setText(String.format("<html><p>%s</p></html>",
							SystemConstants.BUNDLE.getString("createChiTietHopDong.thongBao.giaDatPhaiLaSoThuc")));
					status = false;
				} else {
					int index = this.tblSanPham.getSelectedRow();
					SanPham sanPham = this.sanPhams.get(index);
					if (sanPham.getDonGia() > PriceFormatterUtils.parse(giaDat)) {
						lblLoiGiaDat.setText(String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE
								.getString("createChiTietHopDong.thongBao.giaDatPhaiLonHonDonGiaSanPham")));
						status = false;
					}
				}
			} catch (Exception e) {
				lblLoiGiaDat.setText(String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("createChiTietHopDong.thongBao.giaDatPhaiLaSoThuc")));
				status = false;
			}

		}

		return status;
	}
}
