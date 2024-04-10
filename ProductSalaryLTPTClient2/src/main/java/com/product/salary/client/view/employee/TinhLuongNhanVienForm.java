package com.product.salary.client.view.employee;

/**
 * @author Trần Tuấn Kiệt: Code giao diện
 * @author Trần Thị Thanh Tuyền: xử lý các chức năng
 */

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.entity.NhanVien;
import com.product.salary.application.service.LuongNhanVienService;
import com.product.salary.application.service.NhanVienService;
import com.product.salary.application.service.impl.LuongNhanVienServiceImpl;
import com.product.salary.application.service.impl.NhanVienServiceImpl;
import com.product.salary.application.utils.PhoneUtils;
import com.product.salary.application.utils.PriceFormatterUtils;
import com.product.salary.application.utils.pdf.LuongNhanVienPdfUtils;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TinhLuongNhanVienForm extends JPanel {

	private DefaultTableModel tblModelLuongNhanVien;
	private JTable tblLuong;
	private JYearChooser yearChooser;
	private JMonthChooser monthChooser;
	private JButton btnTinhLuong;
	private JButton btnXuatBaoCao;
	private List<Map<String, Object>> danhSachLuongNhanVien;
	private LuongNhanVienService luongNhanVienService;
	private JButton btnGuiLuong;
	private NhanVienService nhanVienService;

	/**
	 * Create the panel.
	 */
	public TinhLuongNhanVienForm() {

		setLayout(null);

		JPanel panelMain = new JPanel();
		panelMain.setBounds(10, 10, 1213, 821);
		panelMain.setLayout(null);
		add(panelMain);

		JLabel lblMain = new JLabel(SystemConstants.BUNDLE.getString("luongNhanVien.title"));
		lblMain.setBorder(null);
		lblMain.setHorizontalAlignment(SwingConstants.CENTER);
		lblMain.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblMain.setBounds(0, 0, 1250, 80);
		panelMain.add(lblMain);

		JPanel pnlField = new JPanel();
		pnlField.setBounds(10, 90, 1193, 100);
		panelMain.add(pnlField);
		pnlField.setLayout(null);

		yearChooser = new JYearChooser();
		yearChooser.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		yearChooser.getSpinner().setFont(new Font("Times New Roman", Font.PLAIN, 16));
		yearChooser.setBounds(396, 10, 100, 40);
		pnlField.add(yearChooser);
		monthChooser = new JMonthChooser();
		monthChooser.getComboBox().setFont(new Font("Times New Roman", Font.PLAIN, 16));
		monthChooser.getSpinner().setFont(new Font("Times New Roman", Font.PLAIN, 16));
		monthChooser.setBounds(151, 10, 120, 40);
		pnlField.add(monthChooser);

		JLabel lblThang = new JLabel(SystemConstants.BUNDLE.getString("luongNhanVien.thang"));
		lblThang.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblThang.setBounds(81, 10, 60, 40);
		pnlField.add(lblThang);

		JLabel lblNam = new JLabel(SystemConstants.BUNDLE.getString("luongNhanVien.nam"));
		lblNam.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNam.setBounds(336, 10, 60, 40);
		pnlField.add(lblNam);

		btnTinhLuong = new JButton(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("luongNhanVien.btnTinhLuong")));
		btnTinhLuong.setBounds(609, 6, 170, 44);
		pnlField.add(btnTinhLuong);
		btnTinhLuong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnTinhLuong.setIcon(new ImageIcon("src/main/resources/icon/png/ic_add.png"));

		btnXuatBaoCao = new JButton(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("luongNhanVien.btnXuatBaoCao")));
		btnXuatBaoCao
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_pdf.png"));
		btnXuatBaoCao.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnXuatBaoCao.setBounds(806, 6, 170, 44);
		pnlField.add(btnXuatBaoCao);

		btnGuiLuong = new JButton(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("luongNhanVien.btnGuiLuong")));
		btnGuiLuong
				.setIcon(new ImageIcon("src/main/resources/icon/png/ic_send.png"));
		btnGuiLuong.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnGuiLuong.setBounds(1001, 6, 170, 44);
		pnlField.add(btnGuiLuong);

		String hanhDong = "Cập nhật lương thưởng";
		if (SystemConstants.LANGUAGE == 1) {
			hanhDong = "Update salary bonus";
		}

		tblModelLuongNhanVien = new DefaultTableModel(
				new String[] {
						String.format("<html><p>%s</p></html>",
								SystemConstants.BUNDLE.getString("luongNhanVien.tableLuongNhanVien.STT")),
						String.format("<html><p>%s</p></html>",
								SystemConstants.BUNDLE.getString("luongNhanVien.tableLuongNhanVien.maLuong")),
						String.format("<html><p>%s</p></html>",
								SystemConstants.BUNDLE.getString("luongNhanVien.tableLuongNhanVien.maNhanVien")),
						String.format("<html><p>%s</p></html>",
								SystemConstants.BUNDLE.getString("luongNhanVien.tableLuongNhanVien.tenNhanVien")),
						String.format("<html><p>%s</p></html>",
								SystemConstants.BUNDLE.getString("luongNhanVien.tableLuongNhanVien.gioiTinh")),
						String.format("<html><p>%s</p></html>",
								SystemConstants.BUNDLE.getString("luongNhanVien.tableLuongNhanVien.soDienThoai")),
						String.format("<html><p>%s</p></html>",
								SystemConstants.BUNDLE.getString("luongNhanVien.tableLuongNhanVien.luongThang")),
						String.format("<html><p>%s</p></html>",
								SystemConstants.BUNDLE.getString("luongNhanVien.tableLuongNhanVien.troCap")),
						String.format("<html><p>%s</p></html>",
								SystemConstants.BUNDLE.getString("luongNhanVien.tableLuongNhanVien.luongThuong")),
						String.format("<html><p>%s</p></html>",
								SystemConstants.BUNDLE.getString("luongNhanVien.tableLuongNhanVien.tongLuong")),
						String.format("<html><p>%s</p></html>",
								SystemConstants.BUNDLE.getString("luongNhanVien.tableLuongNhanVien.ngayTinhLuong")),
						hanhDong },
				0);

		tblLuong = new JTable(tblModelLuongNhanVien) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return true;
			}
		};
		tblLuong.getColumn(hanhDong).setCellRenderer(new ButtonRenderer());
		tblLuong.getColumn(hanhDong).setCellEditor(new ButtonEditor(new JCheckBox()));

		tblLuong.setShowVerticalLines(true);
		tblLuong.setShowHorizontalLines(true);
		tblLuong.setRowHeight(25);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		tblLuong.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		tblLuong.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
		tblLuong.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);

		JScrollPane scrLuong = new JScrollPane(tblLuong);
		scrLuong.setBounds(0, 200, 1213, 611);
		panelMain.add(scrLuong);

		init();
		event();
	}

	private void init() {
		this.danhSachLuongNhanVien = new ArrayList<Map<String, Object>>();
		this.luongNhanVienService = new LuongNhanVienServiceImpl();
		this.nhanVienService = new NhanVienServiceImpl();
	}

	private void event() {
		this.btnGuiLuong.addActionListener((e) -> {
			thucHienChucNangGuiThongTin();
		});
		tblLuong.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (tblLuong.getSelectedRow() != -1 && e.getClickCount() == 2) {
					try {
						int index = tblLuong.getSelectedRow();
						Map<String, Object> result = danhSachLuongNhanVien.get(index);

						new ChiTietLuongNhanVienForm(result.get("MaNhanVien").toString(),
								Integer.valueOf(result.get("LuongThang").toString()), yearChooser.getYear())
								.setVisible(true);
					} catch (Exception x) {
						x.printStackTrace();
					}
				}

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

		btnTinhLuong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thucHienChucNangTinhLuong();

			}
		});

		btnXuatBaoCao.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thucHienChucNangXuatBaoCao();

			}
		});

	}

	private void thucHienChucNangGuiThongTin() {
		boolean status = false;
		for (Map<String, Object> luongNhanVien : danhSachLuongNhanVien) {
			String maNhanVien = luongNhanVien.get("MaNhanVien").toString();
			NhanVien nhanVien = nhanVienService.timKiemBangMaNhanVien(maNhanVien);

			int year = yearChooser.getYear();
			int month = monthChooser.getMonth() + 1;
			String message = String.format("CTK Home thông báo: Lương tháng %s năm %s của nhân viên %s là: %s", month,
					year, nhanVien.getHoTen() + " - " + nhanVien.getMaNhanVien(),
					PriceFormatterUtils.format(Double.valueOf(luongNhanVien.get("TongLuong").toString())));
			String phone = String.format("84%s", nhanVien.getSoDienThoai().substring(1));
			PhoneUtils.sendNotification(phone, message);
			status = true;
		}
		if (status) {
			JOptionPane.showMessageDialog(this,
					SystemConstants.BUNDLE.getString("luongCongNhan.thongBao.guiThanhCong"));
		} else {
			JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("luongCongNhan.thongBao.guiThatBai"));
		}
	}

	class ButtonRenderer extends JButton implements TableCellRenderer {
		public ButtonRenderer() {
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(UIManager.getColor("Button.background"));
			}
			String txtUpDateLuongThuong = "Cập nhật";
			if(SystemConstants.LANGUAGE == 1) {
				txtUpDateLuongThuong = "Update";
			}
			setText(txtUpDateLuongThuong);
			return this;
		}
	}

	private void thucHienChucNangXuatBaoCao() {
		int thang = monthChooser.getMonth() + 1;
		int nam = yearChooser.getYear();
		LuongNhanVienPdfUtils.writeLuongNhanVien(thang, nam, danhSachLuongNhanVien);
	}

	private void thucHienChucNangTinhLuong() {
		int thang = monthChooser.getMonth() + 1;
		int nam = yearChooser.getYear();

		try {
			boolean status = luongNhanVienService.tinhLuongNhanVien(thang, nam);

			if (status == true) {
				if (SystemConstants.LANGUAGE == 1) {
					JOptionPane.showMessageDialog(this,
							String.format("Payroll in %02d - %04d of employees successfully!", thang, nam));
				} else {
					JOptionPane.showMessageDialog(this,
							String.format("Tính lương tháng %02d năm %04d của nhân viên thành công!", thang, nam));
				}

				loadDanhSachLuong(thang, nam);
				return;
			} else {
				if (SystemConstants.LANGUAGE == 1) {
					JOptionPane.showMessageDialog(this,
							String.format("Payroll in %02d - %04d of employees unsuccessfully!", thang, nam));
				} else {
					JOptionPane.showMessageDialog(this, String
							.format("Tính lương tháng %02d năm %04d của nhân viên không thành công!", thang, nam));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadDanhSachLuong(int thang, int nam) {
		tblModelLuongNhanVien.setRowCount(0);
		danhSachLuongNhanVien = luongNhanVienService.timKiemTatCaLuongNhanVienTheoThangVaNam(thang, nam);

		int stt = 1;
		for (Map<String, Object> luongNV : danhSachLuongNhanVien) {

			tblModelLuongNhanVien.addRow(
					new Object[] { stt++, luongNV.get("MaLuong"), luongNV.get("MaNhanVien"), luongNV.get("TenNhanVien"),
							luongNV.get("GioiTinh"), luongNV.get("SoDienThoai"), luongNV.get("LuongThang"),
							PriceFormatterUtils.format(Double.valueOf(luongNV.get("TroCap").toString())),
							PriceFormatterUtils.format(Double.valueOf(luongNV.get("LuongThuong").toString())),
							PriceFormatterUtils.format(Double.valueOf(luongNV.get("TongLuong").toString())),
							luongNV.get("NgayTinhLuong") });
		}
	}

	private void thucHienChucNangLamMoi() {
		yearChooser.setYear(LocalDate.now().getYear());
		monthChooser.setMonth(LocalDate.now().getMonthValue());
		tblModelLuongNhanVien.setRowCount(0);
	}

	class ButtonEditor extends DefaultCellEditor {
		protected JButton button;
		private String label;
		private boolean isPushed;

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						String value = JOptionPane.showInputDialog(null, "Nhập lương thưởng", 0);
						thucHienChucNangCapNhatLuongThuong(PriceFormatterUtils.parse(value));
					} catch (Exception xe) {

					}
				}

			});
		}

		private void thucHienChucNangCapNhatLuongThuong(double luongThuong) {
			int select = tblLuong.getSelectedRow();
			if (select >= 0) {
				int thang = monthChooser.getMonth() + 1;
				int nam = yearChooser.getYear();

				Map<String, Object> luongNV = danhSachLuongNhanVien.get(select);

				luongNhanVienService.capNhatLuongThuong(luongNV.get("MaLuong").toString(), luongThuong);

				luongNhanVienService.tinhLuongNhanVien(thang, nam);

				loadDanhSachLuong(thang, nam);
			}
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			if (isSelected) {
				button.setForeground(table.getSelectionForeground());
				button.setBackground(table.getSelectionBackground());
			} else {
				button.setForeground(table.getForeground());
				button.setBackground(table.getBackground());
			}
			label = "Cập nhật";
			button.setText(label);
			isPushed = true;
			return button;
		}

		@Override
		public Object getCellEditorValue() {
			if (isPushed) {
				JOptionPane.showMessageDialog(button, label + ": Ouch!");
			}
			isPushed = false;
			return label;
		}

		@Override
		public boolean stopCellEditing() {
			isPushed = false;
			return super.stopCellEditing();
		}
	}

}
