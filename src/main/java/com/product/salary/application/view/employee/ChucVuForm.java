package com.product.salary.application.view.employee;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.entity.ChucVu;
import com.product.salary.application.service.ChucVuService;
import com.product.salary.application.service.impl.ChucVuServiceImpl;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class ChucVuForm extends JPanel {
	private JTextField txtMaChucVu;
	private JTextField txtenChucVu;
	private DefaultTableModel tableModelChucVu;
	private JTable tblChucVu;
	private JButton btnCapNhat;
	private JButton btnXoa;
	private JButton btnThem;
	private JButton btnLamMoi;
	private List<ChucVu> chucVus;
	private ChucVuService chucVuService;
	private JLabel lblLoiTenChucVu;

	/**
	 * Create the panel.
	 */
	public ChucVuForm() {

		setLayout(null);

		JPanel pnlChinh = new JPanel();
		pnlChinh.setBounds(10, 10, 1273, 821);
		add(pnlChinh);
		pnlChinh.setLayout(null);

		JLabel lblTitle = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("chucVu.titleChucVu")));
		lblTitle.setBounds(0, 0, 1250, 80);
		lblTitle.setBorder(null);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlChinh.add(lblTitle);

		JPanel pnlMaChucVu = new JPanel();
		pnlMaChucVu.setLayout(null);
		pnlMaChucVu.setBounds(20, 118, 566, 62);
		pnlChinh.add(pnlMaChucVu);

		JLabel lblMaChucVu = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("chucVu.maChucVu")));
		lblMaChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaChucVu.setBounds(10, 0, 150, 40);
		pnlMaChucVu.add(lblMaChucVu);

		txtMaChucVu = new JTextField();
		txtMaChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaChucVu.setEnabled(false);
		txtMaChucVu.setEditable(false);
		txtMaChucVu.setColumns(10);
		txtMaChucVu.setBounds(188, 0, 354, 40);
		pnlMaChucVu.add(txtMaChucVu);

		JPanel pnlTenChucVu = new JPanel();
		pnlTenChucVu.setLayout(null);
		pnlTenChucVu.setBounds(684, 118, 566, 62);
		pnlChinh.add(pnlTenChucVu);

		JLabel lblTenChucVu = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("chucVu.tenChucVu")));
		lblTenChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenChucVu.setBounds(10, 0, 150, 40);
		pnlTenChucVu.add(lblTenChucVu);

		txtenChucVu = new JTextField();
		txtenChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtenChucVu.setColumns(10);
		txtenChucVu.setBounds(188, 0, 354, 40);
		pnlTenChucVu.add(txtenChucVu);

		lblLoiTenChucVu = new JLabel("");
		lblLoiTenChucVu.setForeground(Color.RED);
		lblLoiTenChucVu.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiTenChucVu.setBounds(188, 39, 354, 23);
		pnlTenChucVu.add(lblLoiTenChucVu);

		btnLamMoi = new JButton(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("quanLyPhongBan.btnXoaTrang")));
		btnLamMoi.setIcon(new ImageIcon("src/main/resources/icon/png/ic_refresh.png"));
		btnLamMoi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnLamMoi.setBounds(1060, 240, 190, 44);
		pnlChinh.add(btnLamMoi);

		btnThem = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyPhongBan.btnThem")));
		btnThem.setIcon(new ImageIcon("src/main/resources/icon/png/ic_add.png"));
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnThem.setBounds(27, 240, 190, 44);
		pnlChinh.add(btnThem);

		btnXoa = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyPhongBan.btnXoa")));
		btnXoa.setIcon(new ImageIcon("src/main/resources/icon/png/ic_remove.png"));
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnXoa.setBounds(684, 240, 190, 44);
		pnlChinh.add(btnXoa);

		btnCapNhat = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("quanLyPhongBan.btnCapNhat")));
		btnCapNhat.setIcon(new ImageIcon("src/main/resources/icon/png/ic_update.png"));
		btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnCapNhat.setBounds(396, 240, 190, 44);
		pnlChinh.add(btnCapNhat);

		JLabel lblDanhSachChucVu = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("chucVu.danhSachChucVu")));
		lblDanhSachChucVu.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDanhSachChucVu.setBounds(10, 386, 350, 36);
		pnlChinh.add(lblDanhSachChucVu);

		tableModelChucVu = new DefaultTableModel(new String[] {
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("chucVu.tblChucVu.stt")),
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("chucVu.tblChucVu.maChucVu")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("chucVu.tblChucVu.tenChucVu")) },
				15);

		tblChucVu = new JTable(tableModelChucVu);
		tblChucVu.setShowVerticalLines(true);
		tblChucVu.setShowHorizontalLines(true);
		tblChucVu.setRowHeight(25);

		JScrollPane scrChucVu = new JScrollPane(tblChucVu);
		scrChucVu.setLocation(10, 432);
		scrChucVu.setSize(1240, 379);
		tblChucVu.setBounds(0, 570, 1273, 263);
		pnlChinh.add(scrChucVu);

		init();
		event();
	}

	private void event() {
		tblChucVu.addMouseListener(new MouseListener() {

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
				thucHienChucNangClickChucVu();
			}
		});

		this.btnLamMoi.addActionListener((e) -> {
			thucHienChucNangLamMoi();
		});

		this.btnThem.addActionListener((e) -> {
			thucHienChucNangThem();
		});
		this.btnCapNhat.addActionListener((e) -> {
			thucHienThucNangCapNhat();
		});
		this.btnXoa.addActionListener((e) -> {
			thucHienChucNangXoa();
		});
	}

	private void init() {
		this.chucVus = new ArrayList<ChucVu>();
		this.chucVuService = new ChucVuServiceImpl();
		this.loadTable();
	}

	private void loadTable() {
		tableModelChucVu.setRowCount(0);
		this.chucVus = this.chucVuService.timKiemTatCaChucVu();
		int stt = 1;
		for (ChucVu chucVu : this.chucVus) {
			tableModelChucVu.addRow(new Object[] { stt++, chucVu.getMaChucVu(), chucVu.getTenChucVu() });
		}
	}

	private void thucHienChucNangClickChucVu() {
		int index = tblChucVu.getSelectedRow();
		if (index >= 0) {
			ChucVu cv = this.chucVus.get(index);
			this.txtenChucVu.setText(cv.getTenChucVu());
			this.txtMaChucVu.setText(cv.getMaChucVu());
		}
	}

	private void thucHienChucNangLamMoi() {
		this.txtenChucVu.setText("");
		this.txtMaChucVu.setText("");
		this.loadTable();
		thucHienThucLamLamMoiLoi();
	}

	private void thucHienThucLamLamMoiLoi() {
		this.lblLoiTenChucVu.setText("");
	}

	private void thucHienChucNangThem() {
		if (!thucHienChucNangKiemTra()) {
			return;
		}
		String maChucVu = this.chucVuService.generateMaChucVu();
		String tenChucVu = this.txtenChucVu.getText().trim();
		try {
			ChucVu chucVu = new ChucVu(maChucVu, tenChucVu);
			chucVu = this.chucVuService.themChucVu(chucVu);
			if (chucVu != null) {
				JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("chucVu.thongBaoThemThanhCong")));
				this.thucHienChucNangLamMoi();
			} else {
				JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("chucVu.thongBaoThemKhongThanhCong")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean thucHienChucNangKiemTra() {
		thucHienThucLamLamMoiLoi();

		String tenChucVu = this.txtenChucVu.getText().trim();

		boolean status = true;
		if (ObjectUtils.isEmpty(tenChucVu)) {
			lblLoiTenChucVu.setText(String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("chucVu.thongBaoLoiTenChucVu")));
			status = false;
		}

		return status;
	}

	private void thucHienThucNangCapNhat() {
		int isSelected = this.tblChucVu.getSelectedRow();
		if (isSelected >= 0) {
			if (!thucHienChucNangKiemTra()) {
				return;
			}

			String maChucVu = this.txtMaChucVu.getText().trim();
			String tenChucVu = this.txtenChucVu.getText().trim();
			try {
				ChucVu chucVu = new ChucVu(maChucVu, tenChucVu);

				int choose = -1;
				if (SystemConstants.LANGUAGE == 1) {
					choose = JOptionPane.showConfirmDialog(this,
							"You want to update position " + chucVu.getTenChucVu() + " ?.", "Confirm",
							JOptionPane.YES_NO_OPTION);
				} else {
					choose = JOptionPane.showConfirmDialog(this,
							"Bạn có muốn cập nhật chức vụ " + chucVu.getTenChucVu() + " ?.", "Xác nhận",
							JOptionPane.YES_NO_OPTION);
				}

				if (choose == JOptionPane.OK_OPTION) {
					chucVu = this.chucVuService.capNhatChucVu(chucVu);
					if (chucVu != null) {
						JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
								SystemConstants.BUNDLE.getString("chucVu.thongBaoCapNhatThanhCong")));
						this.thucHienChucNangLamMoi();
					} else {
						JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
								SystemConstants.BUNDLE.getString("chucVu.thonBaoCapNhatKhongThanhCong")));
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("chucVu.thongBaoChonChucVuCanCapNhat")));
		}
	}

	private void thucHienChucNangXoa() {
		int isSelected = this.tblChucVu.getSelectedRow();
		if (isSelected >= 0) {

			ChucVu chucVu = this.chucVus.get(isSelected);
			int choose = -1;
			if (SystemConstants.LANGUAGE == 1) {
				choose = JOptionPane.showConfirmDialog(this,
						"You want to delete position " + chucVu.getTenChucVu() + " ?.", "Confirm",
						JOptionPane.YES_NO_OPTION);
			} else {
				choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa chức vụ " + chucVu.getTenChucVu() + " ?.",
						"Xác nhận", JOptionPane.YES_NO_OPTION);
			}
			if (choose == JOptionPane.OK_OPTION) {
				boolean trangThai = this.chucVuService.xoaChucVuBangMa(chucVu.getMaChucVu());
				if (trangThai) {
					JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
							SystemConstants.BUNDLE.getString("chucVu.thongBaoXoaThanhCong")));
					this.thucHienChucNangLamMoi();
				} else {
					JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
							SystemConstants.BUNDLE.getString("chucVu.thongBaoXoaKhongThanhCong")));
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, String.format("<html><p>%s</p></html>",
					SystemConstants.BUNDLE.getString("chucVu.thongBaoChonChucVuDeXoa")));
		}
	}
}
