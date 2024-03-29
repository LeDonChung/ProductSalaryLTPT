package com.product.salary.application.view.worker;

/**
 * @author Trần Tuấn Kiệt: Code giao diện
 */

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.entity.CongDoanSanPham;
import com.product.salary.application.entity.CongNhan;
import com.product.salary.application.entity.PhanCongCongNhan;
import com.product.salary.application.entity.SanPham;
import com.product.salary.application.service.CongDoanSanPhamService;
import com.product.salary.application.service.CongNhanService;
import com.product.salary.application.service.PhanCongCongViecService;
import com.product.salary.application.service.SanPhamService;
import com.product.salary.application.service.impl.CongDoanSanPhamServiceImpl;
import com.product.salary.application.service.impl.CongNhanServiceImpl;
import com.product.salary.application.service.impl.PhanCongCongViecServiceImpl;
import com.product.salary.application.service.impl.SanPhamServiceImpl;
import com.product.salary.application.utils.DateConvertUtils;
import com.product.salary.application.view.employee.NhanVienForm;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class PhanCongCongNhanForm extends JPanel {
	private JTextField txtMaPhanCong;
	private JTextField txtMaCongDoan;
	private JTextField txtSoLuongCan;
	private SanPhamService sanPhamService;
	private CongDoanSanPhamService congDoanService;
	private CongNhanService congNhanService;
	private PhanCongCongViecService phanCongService;
	private List<SanPham> dsSanPham;
	private List<CongDoanSanPham> dsCongDoan;
	private List<CongNhan> dsCongNhan;
	private List<PhanCongCongNhan> dsPhanCong;
	private DefaultComboBoxModel<SanPham> modelSanPham;
	private DefaultComboBoxModel<CongDoanSanPham> modelCongDoan;
	private JComboBox cmbTenCongDoan;
	private JComboBox cmbTenSanPham;
	private DefaultTableModel tableMoelCongNhan;
	private JTable tblCongNhan;
	private JDateChooser jcNgayPhanCong;
	private JLabel lblNgayPhanCong;
	private JLabel lblMaPhanCong;
	private JLabel lblTenSanPham;
	private JLabel lblMaCongDoan;
	private JLabel lblTenCongDoan;
	private DefaultTableModel tableModel;
	private JTable tblPhanCong;
	private JButton btnPhanCong;
	private JButton btnXoa;
	private JLabel lblSoLuongCan;
	private JButton btnLamMoi;
	private JLabel lblLoiSanPham;
	private JLabel lblLoiCongDoan;

	/**
	 * Create the panel.
	 */
	public PhanCongCongNhanForm() {

		JLabel lblNewLabel = new JLabel("Phân công công nhân");
		add(lblNewLabel);

		setLayout(null);

		JPanel pnlMain = new JPanel();
		pnlMain.setBounds(10, 10, 1213, 821);
		pnlMain.setLayout(null);
		add(pnlMain);

		JLabel lblTitle = new JLabel(SystemConstants.BUNDLE.getString("phanCongCongNhan.title"));
		lblTitle.setBorder(null);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTitle.setBounds(0, 0, 1250, 80);
		pnlMain.add(lblTitle);

		tableMoelCongNhan = new DefaultTableModel(new String[] {
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("phanCongCongNhan.tableCongNhan.stt")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("phanCongCongNhan.tableCongNhan.maCongNhan")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("phanCongCongNhan.tableCongNhan.tenCongNhan")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("phanCongCongNhan.tableCongNhan.soDienThoai")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyCongNhan.tableCongNhan.tayNghe")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("quanLyCongNhan.tableCongNhan.phanCong")) },
				15);

		tblCongNhan = new JTable(tableMoelCongNhan) {
			@Override
			public Class<?> getColumnClass(int column) {
				if (column == 5) {
					return Boolean.class;
				}
				return super.getColumnClass(column);
			}
		};
		tblCongNhan.setShowVerticalLines(true);
		tblCongNhan.setShowHorizontalLines(true);
		tblCongNhan.setRowHeight(25);

		JScrollPane scrCongNhan = new JScrollPane(tblCongNhan);
		scrCongNhan.setLocation(10, 145);
		scrCongNhan.setSize(721, 315);
		tblCongNhan.setBounds(0, 570, 1273, 263);
		pnlMain.add(scrCongNhan);

		JPanel pnlNgayPhanCong = new JPanel();
		pnlNgayPhanCong.setLayout(null);
		pnlNgayPhanCong.setBounds(741, 112, 462, 62);
		pnlMain.add(pnlNgayPhanCong);
		jcNgayPhanCong = new JDateChooser();
		jcNgayPhanCong.getCalendarButton()
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_calendar.png"));
		jcNgayPhanCong.getCalendarButton().setBounds(new Rectangle(0, 0, 35, 0));
		jcNgayPhanCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		jcNgayPhanCong.setDateFormatString("yyyy-MM-dd");
		jcNgayPhanCong.setLocation(160, 6);
		jcNgayPhanCong.setSize(292, 50);

		pnlNgayPhanCong.add(jcNgayPhanCong);

		lblNgayPhanCong = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("phanCongCongNhan.ngayPhanCong")));
		lblNgayPhanCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNgayPhanCong.setBounds(10, 11, 140, 45);
		pnlNgayPhanCong.add(lblNgayPhanCong);

		JPanel pnlMaPhanCong = new JPanel();
		pnlMaPhanCong.setLayout(null);
		pnlMaPhanCong.setBounds(741, 173, 462, 62);
		pnlMain.add(pnlMaPhanCong);

		lblMaPhanCong = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("phanCongCongNhan.maPhanCong")));
		lblMaPhanCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaPhanCong.setBounds(10, 11, 142, 40);
		pnlMaPhanCong.add(lblMaPhanCong);

		txtMaPhanCong = new JTextField();
		txtMaPhanCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaPhanCong.setEnabled(false);
		txtMaPhanCong.setEditable(false);
		txtMaPhanCong.setColumns(10);
		txtMaPhanCong.setBounds(162, 11, 290, 40);
		pnlMaPhanCong.add(txtMaPhanCong);

		JPanel pnlSanPham = new JPanel();
		pnlSanPham.setLayout(null);
		pnlSanPham.setBounds(741, 247, 462, 62);
		pnlMain.add(pnlSanPham);

		lblTenSanPham = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("phanCongCongNhan.tenSanPham")));
		lblTenSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenSanPham.setBounds(10, 0, 140, 39);
		pnlSanPham.add(lblTenSanPham);

		modelSanPham = new DefaultComboBoxModel<SanPham>();
		cmbTenSanPham = new JComboBox(modelSanPham);
		cmbTenSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		cmbTenSanPham.setBounds(160, 0, 292, 40);
		pnlSanPham.add(cmbTenSanPham);

		lblLoiSanPham = new JLabel("");
		lblLoiSanPham.setForeground(Color.RED);
		lblLoiSanPham.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiSanPham.setBounds(160, 39, 289, 23);
		pnlSanPham.add(lblLoiSanPham);

		JPanel pnlMaCongDoan = new JPanel();
		pnlMaCongDoan.setLayout(null);
		pnlMaCongDoan.setBounds(741, 319, 462, 62);
		pnlMain.add(pnlMaCongDoan);

		lblMaCongDoan = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("phanCongCongNhan.maCongDoan")));
		lblMaCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaCongDoan.setBounds(10, 0, 140, 39);
		pnlMaCongDoan.add(lblMaCongDoan);

		txtMaCongDoan = new JTextField();
		txtMaCongDoan.setBounds(163, 0, 289, 40);
		pnlMaCongDoan.add(txtMaCongDoan);
		txtMaCongDoan.setEnabled(false);
		txtMaCongDoan.setEditable(false);
		txtMaCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaCongDoan.setColumns(10);

		JPanel pnlTenCongDoan = new JPanel();
		pnlTenCongDoan.setLayout(null);
		pnlTenCongDoan.setBounds(741, 391, 462, 62);
		pnlMain.add(pnlTenCongDoan);

		lblTenCongDoan = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("phanCongCongNhan.tenCongDoan")));
		lblTenCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenCongDoan.setBounds(10, 0, 143, 40);
		pnlTenCongDoan.add(lblTenCongDoan);

		modelCongDoan = new DefaultComboBoxModel<CongDoanSanPham>();
		cmbTenCongDoan = new JComboBox(modelCongDoan);
		cmbTenCongDoan.setBounds(163, 0, 292, 40);
		pnlTenCongDoan.add(cmbTenCongDoan);
		cmbTenCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		lblLoiCongDoan = new JLabel("");
		lblLoiCongDoan.setForeground(Color.RED);
		lblLoiCongDoan.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiCongDoan.setBounds(163, 39, 289, 23);
		pnlTenCongDoan.add(lblLoiCongDoan);

		tableModel = new DefaultTableModel(new String[] {
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("phanCongCongNhan.tablePhanCong.STT")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("phanCongCongNhan.tablePhanCong.maPhanCong")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("phanCongCongNhan.tablePhanCong.maCongNhan")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("phanCongCongNhan.tablePhanCong.tenCongNhan")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("phanCongCongNhan.tablePhanCong.tenSanPham")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("phanCongCongNhan.tablePhanCong.maCongDoan")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("phanCongCongNhan.tablePhanCong.tenCongDoan")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("phanCongCongNhan.tablePhanCong.ngayPhanCong")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("phanCongCongNhan.tablePhanCong.trangThai")), },
				10);

		tblPhanCong = new JTable(tableModel);
		tblPhanCong.setShowVerticalLines(true);
		tblPhanCong.setShowHorizontalLines(true);
		tblPhanCong.setRowHeight(25);

		JScrollPane scrPhanCong = new JScrollPane(tblPhanCong);
		scrPhanCong.setBounds(10, 570, 1193, 263);
		pnlMain.add(scrPhanCong);

		btnPhanCong = new JButton(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("phanCongCongNhan.btnPhanCong")));
		btnPhanCong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnPhanCong.setIcon(new ImageIcon("src/main/resources/icon/png/ic_add.png"));
		btnPhanCong.setBounds(10, 481, 160, 44);
		pnlMain.add(btnPhanCong);

		btnXoa = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("phanCongCongNhan.btnXoa")));
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnXoa.setIcon(new ImageIcon("src/main/resources/icon/png/ic_remove.png"));
		btnXoa.setBounds(265, 481, 160, 44);
		pnlMain.add(btnXoa);

		JPanel pnlSoLuongCan = new JPanel();
		pnlSoLuongCan.setLayout(null);
		pnlSoLuongCan.setBounds(741, 463, 462, 62);
		pnlMain.add(pnlSoLuongCan);

		lblSoLuongCan = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("phanCongCongNhan.soLuongCan")));
		lblSoLuongCan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoLuongCan.setBounds(10, 11, 143, 40);
		pnlSoLuongCan.add(lblSoLuongCan);

		txtSoLuongCan = new JTextField();
		txtSoLuongCan.setEditable(false);
		txtSoLuongCan.setEnabled(false);
		txtSoLuongCan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtSoLuongCan.setColumns(10);
		txtSoLuongCan.setBounds(163, 11, 289, 40);
		pnlSoLuongCan.add(txtSoLuongCan);

		JLabel lblDSCongNhan = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("phanCongCongNhan.dsCongNhan")));
		lblDSCongNhan.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblDSCongNhan.setBounds(10, 111, 190, 24);
		pnlMain.add(lblDSCongNhan);

		JLabel lblDSPhanCong = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("phanCongCongNhan.dsPhanCong")));
		lblDSPhanCong.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblDSPhanCong.setBounds(10, 536, 190, 24);
		pnlMain.add(lblDSPhanCong);

		btnLamMoi = new JButton(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyCongNhan.btnXoaTrang")));
		btnLamMoi
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_refresh.png"));
		btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnLamMoi.setBounds(520, 481, 190, 44);
		pnlMain.add(btnLamMoi);

		jcNgayPhanCong.setDate(Date.valueOf(LocalDate.now()));

		init();
		event();

	}

	private void event() {
		this.btnLamMoi.addActionListener((e) -> {
			thucHienChucNangLamMoi();
		});
		this.btnPhanCong.addActionListener((e) -> {
			thucHienChucNangPhanCong();
		});
		this.btnXoa.addActionListener((e) -> {
			thucHienChucNangXoa();
		});
		this.cmbTenSanPham.addActionListener((e) -> {
			loadThongTinSanPham();
		});
		this.cmbTenCongDoan.addActionListener((e) -> {
			loadThongTinCongDoan();
		});
		this.tblPhanCong.addMouseListener(new MouseListener() {

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
				thucHienChucNangClickPhanCong();

			}
		});
		tblCongNhan.addMouseListener(new MouseListener() {

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
				thucHienChucNangChonCongNhan();

			}

		});
		cmbTenCongDoan.addActionListener((e) -> {
			thucHienChucNangChonCongDoan();
		});
	}

	private void thucHienChucNangChonCongDoan() {
		int sanPhamIndex = cmbTenSanPham.getSelectedIndex();
		if (sanPhamIndex >= 0) {
			SanPham sanPham = dsSanPham.get(sanPhamIndex);
			int congDoanIndex = cmbTenCongDoan.getSelectedIndex();
			if (congDoanIndex >= 0) {
				CongDoanSanPham congDoanSanPham = dsCongDoan.get(congDoanIndex);
				// Thực hiện hiển thị danh sách công nhân chưa phân công
				loadTableCongNhanChuaPhanCongDoan(sanPham.getMaSanPham(), congDoanSanPham.getMaCongDoan());
				// Thực hiện hiển thị danh sách phân công của công đoạn
				loadTablePhanCongBangMaCongDoan(congDoanSanPham.getMaCongDoan());
			}
		}
	}

	private void loadTablePhanCongBangMaCongDoan(String maCongDoan) {
		tableModel.setRowCount(0);
		this.dsPhanCong = this.phanCongService.timTatCaPhanCongTheoMaCongDoan(maCongDoan);
		int stt = 1;

		for (PhanCongCongNhan phanCong : dsPhanCong) {
			String trangThai = "";
			if (phanCong.isTrangThai()) {
				trangThai = "Hoàn thành";
			} else {
				trangThai = "Chưa hoàn thành";
			}
			tableModel.addRow(new Object[] { stt++, phanCong.getMaPhanCong(), phanCong.getCongNhan().getMaCongNhan(),
					phanCong.getCongNhan().getHoTen(), phanCong.getCongDoanSanPham().getSanPham(),
					phanCong.getCongDoanSanPham().getMaCongDoan(), phanCong.getCongDoanSanPham().getTenCongDoan(),
					phanCong.getNgayPhanCong(), trangThai });
		}
	}

	private void loadTableCongNhanChuaPhanCongDoan(String maSanPham, String maCongDoan) {
		tableMoelCongNhan.setRowCount(0);
		this.dsCongNhan = this.phanCongService.timTatCaCongNhanChuaPhanCongVaoCongDoan(maSanPham, maCongDoan);
		int stt = 1;

		for (CongNhan congNhan : this.dsCongNhan) {
			tableMoelCongNhan.addRow(new Object[] { stt++, congNhan.getMaCongNhan(), congNhan.getHoTen(),
					congNhan.getSoDienThoai(), congNhan.getTayNghe() });
		}
	}

	private void thucHienChucNangChonCongNhan() {
		int index = tblCongNhan.getSelectedRow();
		if (index >= 0) {
			int sanPhamDangChonDePhanCong = this.cmbTenSanPham.getSelectedIndex();
			if (sanPhamDangChonDePhanCong < 0) {
				CongNhan congNhan = dsCongNhan.get(index);
				loadTablePhanCong(congNhan.getMaCongNhan());
			}
		}
	}

	private void thucHienChucNangXoa() {
		int index = this.tblPhanCong.getSelectedRow();
		if (index >= 0) {

			PhanCongCongNhan phanCon = this.dsPhanCong.get(index);
			int choose = JOptionPane.showConfirmDialog(this,
					SystemConstants.BUNDLE.getString("phanCongCongNhan.xoaPhanCong"),
					SystemConstants.BUNDLE.getString("phanCongCongNhan.xacNhan"), JOptionPane.YES_NO_OPTION);
//			int choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa phân công?", "Xác nhận",
//					JOptionPane.YES_NO_OPTION);
			if (choose == JOptionPane.OK_OPTION) {
				boolean trangThai = this.phanCongService.xoaPhanCongCongNhan(phanCon.getMaPhanCong());
				if (trangThai) {
					JOptionPane.showMessageDialog(this,
							SystemConstants.BUNDLE.getString("phanCongCongNhan.xoaThanhCong"));
					// JOptionPane.showMessageDialog(this, "Xóa phân công thành công.");
					loadTablePhanCong(phanCon.getCongNhan().getMaCongNhan());
				} else {
					JOptionPane.showMessageDialog(this,
							SystemConstants.BUNDLE.getString("phanCongCongNhan.xoaKhongThanhCong"));
					// JOptionPane.showMessageDialog(this, "Xóa phân công không thành công.");
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("phanCongCongNhan.chonPhanCong"));
			// JOptionPane.showMessageDialog(this, "Vui lòng chọn phân công để xóa.");
		}

	}

	private void thucHienChucNangCapNhat() {
		int index = tblPhanCong.getSelectedRow();
		int index_1 = tblCongNhan.getSelectedRow();
		if (index >= 0) {
			if (!thucHienChucNangKiemTra()) {
				return;
			}
			int soLuongCan = Integer.parseInt(txtSoLuongCan.getText().trim());
			String maPhanCong = txtMaPhanCong.getText().trim();
			Boolean trangThai = null;
			CongNhan cn = dsCongNhan.get(index_1);

			if (soLuongCan == 0) {
				trangThai = true;
			} else {
				trangThai = false;
			}
			try {
				PhanCongCongNhan phanCong = new PhanCongCongNhan(maPhanCong, trangThai);
				int choose = JOptionPane.showConfirmDialog(this,
						SystemConstants.BUNDLE.getString("phanCongCongNhan.capNhatPhanCong"),
						SystemConstants.BUNDLE.getString("phanCongCongNhan.xacNhan"), JOptionPane.YES_NO_OPTION);
//				int choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật phân công?.", "Xác nhận",
//						JOptionPane.YES_NO_OPTION);
				if (choose == JOptionPane.OK_OPTION) {
					phanCong = this.phanCongService.capNhatPhanCongCongNhan(phanCong);
					if (phanCong != null) {
						JOptionPane.showMessageDialog(this,
								SystemConstants.BUNDLE.getString("phanCongCongNhan.capNhatThanhCong"));
						// JOptionPane.showMessageDialog(this, "Cập nhật công nhân thành công.");
						this.thucHienChucNangLamMoi();
						loadTablePhanCong(cn.getMaCongNhan());
					} else {
						JOptionPane.showMessageDialog(this,
								SystemConstants.BUNDLE.getString("phanCongCongNhan.capNhatKhongThanhCong"));
						// JOptionPane.showMessageDialog(this, "Cập công nhân không thành công.");
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(this,
					SystemConstants.BUNDLE.getString("phanCongCongNhan.chonPhanCongCapNhat"));
		}
	}

	private void thucHienChucNangClickPhanCong() {
		int index = tblPhanCong.getSelectedRow();
		if (index >= 0) {
			PhanCongCongNhan phanCong = this.dsPhanCong.get(index);
			this.jcNgayPhanCong
					.setDate(DateConvertUtils.asUtilDate(phanCong.getNgayPhanCong(), ZoneId.systemDefault()));
			this.txtMaPhanCong.setText(phanCong.getMaPhanCong());
			this.modelSanPham.setSelectedItem(phanCong.getCongDoanSanPham().getSanPham().getTenSanPham());
			this.txtMaCongDoan.setText(phanCong.getCongDoanSanPham().getMaCongDoan());
			this.modelCongDoan.setSelectedItem(phanCong.getCongDoanSanPham().getTenCongDoan());
			dsCongDoan = congDoanService
					.timTatCaCongDoanSanPham(phanCong.getCongDoanSanPham().getSanPham().getMaSanPham());
			this.txtSoLuongCan.setText(String.valueOf(dsCongDoan.get(index).getSoLuongCanLam()));
		}
	}

	private void loadThongTinCongDoan() {
		int selectedIndex = cmbTenCongDoan.getSelectedIndex();
		if (selectedIndex >= 0 && selectedIndex < dsCongDoan.size()) {
			CongDoanSanPham congDoanSanPham = dsCongDoan.get(selectedIndex);
			txtMaCongDoan.setText(congDoanSanPham.getMaCongDoan());
			txtSoLuongCan.setText(String.valueOf(congDoanSanPham.getSoLuongCanLam()));
		}
	}

	private void loadThongTinSanPham() {
		if (cmbTenSanPham.getSelectedIndex() != -1) {
			modelCongDoan.removeAllElements();
			SanPham sp = (SanPham) modelSanPham.getSelectedItem();
			dsCongDoan = congDoanService.timTatCaCongDoanSanPham(sp.getMaSanPham());

			if (dsCongDoan.size() > 0) {
				for (CongDoanSanPham congDoanSanPham : dsCongDoan) {
					modelCongDoan.addElement(congDoanSanPham);
				}
				cmbTenCongDoan.setSelectedIndex(0);
				CongDoanSanPham congDoanSanPham = dsCongDoan.get(0);
				txtMaCongDoan.setText(congDoanSanPham.getMaCongDoan());
				txtSoLuongCan.setText(String.valueOf(congDoanSanPham.getSoLuongCanLam()));
			} else {
				cmbTenCongDoan.setSelectedIndex(-1);
				txtMaCongDoan.setText("");
				txtSoLuongCan.setText("");
			}
		}
	}

	private void thucHienChucNangPhanCong() {

		// ================ Phân nhiều công nhân ================

		int index = tblCongNhan.getSelectedRow();
		if (index >= 0) {
			if (!thucHienChucNangKiemTra()) {
				return;
			}
			List<Integer> viTriCongNhanDuocChon = new ArrayList<Integer>();
			for (int i = 0; i < dsCongNhan.size(); i++) {
				Boolean isSelected = (Boolean) this.tblCongNhan.getValueAt(i, 5);
				if (isSelected != null) {
					if(isSelected == true) {
						viTriCongNhanDuocChon.add(i);
					}
				}
			}
			// Thông tin chung
			LocalDate ngayPhanCong = DateConvertUtils.asLocalDate(jcNgayPhanCong.getDate(), ZoneId.systemDefault());
			CongDoanSanPham congDoan = (CongDoanSanPham) modelCongDoan.getSelectedItem();
			SanPham sp = (SanPham) modelSanPham.getSelectedItem();
			// Danh sách phân công được thêm
			List<PhanCongCongNhan> danhSachPhanCong = new ArrayList<PhanCongCongNhan>();
			for (int i = 0; i < viTriCongNhanDuocChon.size(); i++) {
				CongNhan congNhan = dsCongNhan.get(viTriCongNhanDuocChon.get(i));
				String ma = this.phanCongService.generateMaPhanCongCongNhan(congNhan.getMaCongNhan(),
						congDoan.getMaCongDoan());
				PhanCongCongNhan phanCong = new PhanCongCongNhan(ma, congNhan, congDoan, ngayPhanCong);
				danhSachPhanCong.add(phanCong);
			}

			danhSachPhanCong = this.phanCongService.phanCongNhieuCongNhan(danhSachPhanCong);

			if (!danhSachPhanCong.isEmpty()) {
				JOptionPane.showMessageDialog(this,
						SystemConstants.BUNDLE.getString("phanCongCongNhan.phanCongThanhCong"));

				// Thực hiện hiển thị danh sách phân công vừa được phân
				thucHienChucNangChonCongDoan();

			} else {
				JOptionPane.showMessageDialog(this,
						SystemConstants.BUNDLE.getString("phanCongCongNhan.phanCongKhongThanhCong"));
			}
		} else {
			JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("phanCongCongNhan.chonCongNhan"));
		}
		// ================ Phân một công nhân ================
//		int index = tblCongNhan.getSelectedRow();
//		if (index >= 0) {
//			if (!thucHienChucNangKiemTra()) {
//				return;
//			}
//			CongNhan cn = dsCongNhan.get(index);
//			LocalDate ngayPhanCong = DateConvertUtils.asLocalDate(jcNgayPhanCong.getDate(), ZoneId.systemDefault());
//			String maCongNhan = tblCongNhan.getValueAt(index, 1).toString();
//			String tenCongNhan = tblCongNhan.getValueAt(index, 2).toString();
//			CongNhan congNhan = new CongNhan(maCongNhan, tenCongNhan);
//
//			String maCongDoan = txtMaCongDoan.getText().trim();
//			CongDoanSanPham cd = (CongDoanSanPham) modelCongDoan.getSelectedItem();
//			SanPham sp = (SanPham) modelSanPham.getSelectedItem();
//
//			CongDoanSanPham congDoan = new CongDoanSanPham(maCongDoan, cd.getTenCongDoan(), sp);
//
//			String ma = this.phanCongService.generateMaPhanCongCongNhan(cn.getMaCongNhan(), maCongDoan);
//			try {
//				// String checkSoLuongPhanCong = congDoanService
//				PhanCongCongNhan phanCong = new PhanCongCongNhan(ma, congNhan, congDoan, ngayPhanCong);
//				phanCong = this.phanCongService.phanCongCongNhan(phanCong);
//				if (phanCong != null) {
//					JOptionPane.showMessageDialog(this,
//							SystemConstants.BUNDLE.getString("phanCongCongNhan.phanCongThanhCong"));
//
//					// Thực hiện hiển thị danh sách phân công vừa được phân
//					thucHienChucNangChonCongDoan();
//
//					// JOptionPane.showMessageDialog(this, "Phân công công việc cho công nhân thành
//					// công.");
////					this.thucHienChucNangLamMoi();
////					loadTablePhanCong(maCongNhan);
//
//				} else {
//					JOptionPane.showMessageDialog(this,
//							SystemConstants.BUNDLE.getString("phanCongCongNhan.phanCongKhongThanhCong"));
//					// JOptionPane.showMessageDialog(this, "Phân công công việc cho công nhân không
//					// thành công.");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} else {
//			JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("phanCongCongNhan.chonSanPham"));
//			// JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm");
//		}

	}

	private void thucHienChucNangLamMoi() {
		thucHienChucNangLamMoiLoi();
		txtMaPhanCong.setText("");
		jcNgayPhanCong.setDate(Date.valueOf(LocalDate.now()));
		cmbTenSanPham.setSelectedIndex(-1);
		txtMaCongDoan.setText("");
		cmbTenCongDoan.setSelectedIndex(-1);
		txtSoLuongCan.setText("");

		// Load danh sách công nhân để người dùng chọn và xem phân công
		loadTableCongNhan();
		// Làm mới model phân công
		this.tableModel.setRowCount(0);

	}

	private boolean thucHienChucNangKiemTra() {
		thucHienChucNangLamMoiLoi();
		boolean status = true;

		if (cmbTenSanPham.getSelectedIndex() == -1) {
			this.lblLoiSanPham.setText(SystemConstants.BUNDLE.getString("phanCongCongNhan.chonSanPham"));
			// this.lblLoiSanPham.setText("Vui lòng chọn sản phẩm");
			status = false;
		}
		int congDoanIndex = this.cmbTenCongDoan.getSelectedIndex();
		if (congDoanIndex >= 0) {
			CongDoanSanPham congDoanSanPhamDangChon = this.dsCongDoan.get(congDoanIndex);
			if (congDoanSanPhamDangChon.getCongDoanLamTruoc() != null) {
				if (congDoanSanPhamDangChon.getCongDoanLamTruoc().getSoLuongCanLam() > 0) {
					this.lblLoiCongDoan
							.setText(String.format(SystemConstants.BUNDLE.getString("phanCongCongNhan.loiCongDoan"),
									congDoanSanPhamDangChon.getCongDoanLamTruoc().getTenCongDoan()));
					status = false;
				}
			}
		}
		return status;
	}

	private void thucHienChucNangLamMoiLoi() {
		lblLoiSanPham.setText("");
		lblLoiCongDoan.setText("");
	}

	private void init() {
		this.sanPhamService = new SanPhamServiceImpl();
		this.congDoanService = new CongDoanSanPhamServiceImpl();
		this.congNhanService = new CongNhanServiceImpl();
		this.phanCongService = new PhanCongCongViecServiceImpl();

		this.dsSanPham = new ArrayList<SanPham>();
		this.dsCongDoan = new ArrayList<CongDoanSanPham>();
		this.dsCongNhan = new ArrayList<CongNhan>();
		this.dsPhanCong = new ArrayList<PhanCongCongNhan>();

		loadCombobox();
		loadTableCongNhan();
		// loadTablePhanCong();
	}

	private void loadCombobox() {
		dsSanPham = sanPhamService.timKiemTatCaSanPham();

		modelSanPham.addAll(dsSanPham);

		if (!dsSanPham.isEmpty()) {
			cmbTenSanPham.setSelectedIndex(-1);
		}

	}

	private void loadTableCongNhan() {
		tableMoelCongNhan.setRowCount(0);
		this.dsCongNhan = this.congNhanService.timKiemTatCaCongNhan();
		int stt = 1;

		for (CongNhan congNhan : this.dsCongNhan) {
			tableMoelCongNhan.addRow(new Object[] { stt++, congNhan.getMaCongNhan(), congNhan.getHoTen(),
					congNhan.getSoDienThoai(), congNhan.getTayNghe(), false });
		}
	}

	private void loadTablePhanCong(String maCongNhan) {
		tableModel.setRowCount(0);
		this.dsPhanCong = this.phanCongService.timTatCaPhanCongTheoMaCongNhanChuaHoanThanh(maCongNhan);
		int stt = 1;

		for (PhanCongCongNhan phanCong : dsPhanCong) {
			String trangThai = "";
			if (phanCong.isTrangThai()) {
				trangThai = "Hoàn thành";
			} else {
				trangThai = "Chưa hoàn thành";
			}
			tableModel.addRow(new Object[] { stt++, phanCong.getMaPhanCong(), phanCong.getCongNhan().getMaCongNhan(),
					phanCong.getCongNhan().getHoTen(), phanCong.getCongDoanSanPham().getSanPham(),
					phanCong.getCongDoanSanPham().getMaCongDoan(), phanCong.getCongDoanSanPham().getTenCongDoan(),
					phanCong.getNgayPhanCong(), trangThai });
		}
	}
}
