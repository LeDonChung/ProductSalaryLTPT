package com.product.salary.client.view.worker;

/**
 * @author Trần Tuấn Kiệt: Code giao diện
 */

import com.product.salary.client.common.SystemConstants;
import com.product.salary.application.entity.*;
import com.product.salary.application.utils.AppUtils;
import com.product.salary.application.utils.DateConvertUtils;
import com.product.salary.application.utils.RequestDTO;
import com.product.salary.application.utils.ResponseDTO;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class PhanCongCongNhanForm extends JPanel {
	private final static ResourceBundle BUNDLE = ResourceBundle.getBundle("app");
	private final JTextField txtMaPhanCong;
	private final JTextField txtMaCongDoan;
	private final JTextField txtSoLuongCan;
	private List<SanPham> dsSanPham;
	private List<CongDoanSanPham> dsCongDoan;
	private List<CongNhan> dsCongNhan;
	private List<PhanCongCongNhan> dsPhanCong;
	private final DefaultComboBoxModel<SanPham> modelSanPham;
	private final DefaultComboBoxModel<CongDoanSanPham> modelCongDoan;
	private final JComboBox cmbTenCongDoan;
	private final JComboBox cmbTenSanPham;
	private final DefaultTableModel tableMoelCongNhan;
	private final JTable tblCongNhan;
	private final JDateChooser jcNgayPhanCong;
	private final JLabel lblNgayPhanCong;
	private final JLabel lblMaPhanCong;
	private final JLabel lblTenSanPham;
	private final JLabel lblMaCongDoan;
	private final JLabel lblTenCongDoan;
	private final DefaultTableModel tableModel;
	private final JTable tblPhanCong;
	private final JButton btnPhanCong;
	private final JButton btnXoa;
	private final JLabel lblSoLuongCan;
	private final JButton btnLamMoi;
	private final JLabel lblLoiSanPham;
	private final JLabel lblLoiCongDoan;

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
		new Thread(() -> {
			try (var socket = new Socket(
					BUNDLE.getString("host"),
					Integer.parseInt(BUNDLE.getString("server.port")));
				 var dos = new DataOutputStream(socket.getOutputStream());
				 var dis = new DataInputStream(socket.getInputStream())
			){
				// Send Data
				RequestDTO request = RequestDTO.builder()
						.requestType("PhanCongCongNhanForm")
						.request("timTatCaPhanCongTheoMaCongDoan")
						.data(maCongDoan)
						.build();
				//System.out.println("Sending request: " + request);
				String json = AppUtils.GSON.toJson(request);
				dos.writeUTF(json);
				dos.flush();

				// Receive Data
				json = new String(dis.readAllBytes());
				ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
				//System.out.println("Receive response: " + response);
				List<Map<String, Object>> data = (List<Map<String, Object>>) response.getData();
				dsPhanCong = data.stream().map((value) -> AppUtils.convert(value, PhanCongCongNhan.class)).collect(Collectors.toList());
				tableModel.setRowCount(0);
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

	}

	private void loadTableCongNhanChuaPhanCongDoan(String maSanPham, String maCongDoan) {
		new Thread(() -> {
			try (var socket = new Socket(
					BUNDLE.getString("host"),
					Integer.parseInt(BUNDLE.getString("server.port")));
				 var dos = new DataOutputStream(socket.getOutputStream());
				 var dis = new DataInputStream(socket.getInputStream())
			){
				PhanCongCongNhan phanCong = new PhanCongCongNhan();
				CongDoanSanPham congDoanSanPham = new CongDoanSanPham();
				SanPham sanPham = new SanPham();
				sanPham.setMaSanPham(maSanPham);
				congDoanSanPham.setMaCongDoan(maCongDoan);
				congDoanSanPham.setSanPham(sanPham);
				phanCong.setCongDoanSanPham(congDoanSanPham);
				// Send Data
				RequestDTO request = RequestDTO.builder()
						.requestType("PhanCongCongNhanForm")
						.request("timTatCaCongNhanChuaPhanCongVaoCongDoan")
						.data(phanCong)
						.build();
				//System.out.println("Sending request: " + request);
				String json = AppUtils.GSON.toJson(request);
				dos.writeUTF(json);
				dos.flush();

				// Receive Data
				json = new String(dis.readAllBytes());
				ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
				//System.out.println("Receive response: " + response);
				List<Map<String, Object>> data = (List<Map<String, Object>>) response.getData();
				dsCongNhan = data.stream().map((value) -> AppUtils.convert(value, CongNhan.class)).collect(Collectors.toList());
				tableMoelCongNhan.setRowCount(0);
				int stt = 1;

				for (CongNhan congNhan : this.dsCongNhan) {
					tableMoelCongNhan.addRow(new Object[] { stt++, congNhan.getMaCongNhan(), congNhan.getHoTen(),
							congNhan.getSoDienThoai(), congNhan.getTayNghe() });
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

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
			if (choose == JOptionPane.OK_OPTION) {
				new Thread(() -> {
					try (var socket = new Socket(
							BUNDLE.getString("host"),
							Integer.parseInt(BUNDLE.getString("server.port")));
						 var dos = new DataOutputStream(socket.getOutputStream());
						 var dis = new DataInputStream(socket.getInputStream())
					){
						// Send Data
						RequestDTO request = RequestDTO.builder()
								.requestType("PhanCongCongNhanForm")
								.request("xoaPhanCongCongNhan")
								.data(phanCon.getMaPhanCong())
								.build();
						//System.out.println("Sending request: " + request);
						String json = AppUtils.GSON.toJson(request);
						dos.writeUTF(json);
						dos.flush();

						// Receive Data
						json = new String(dis.readAllBytes());
						ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
						//System.out.println("Receive response: " + response);
						boolean trangThai = (boolean) response.getData();
						if (trangThai) {
							JOptionPane.showMessageDialog(this,
									SystemConstants.BUNDLE.getString("phanCongCongNhan.xoaThanhCong"));
							thucHienChucNangLamMoi();
							//loadTablePhanCong(phanCon.getCongNhan().getMaCongNhan());
						} else {
							JOptionPane.showMessageDialog(this,
									SystemConstants.BUNDLE.getString("phanCongCongNhan.xoaKhongThanhCong"));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}).start();
			}
		} else {
			JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("phanCongCongNhan.chonPhanCong"));
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
//			dsCongDoan = congDoanService
//					.timTatCaCongDoanSanPham(phanCong.getCongDoanSanPham().getSanPham().getMaSanPham());
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
			new Thread(() -> {
				try (var socket = new Socket(
						BUNDLE.getString("host"),
						Integer.parseInt(BUNDLE.getString("server.port")));
					 var dos = new DataOutputStream(socket.getOutputStream());
					 var dis = new DataInputStream(socket.getInputStream())
				){
					// Send Data
					RequestDTO request = RequestDTO.builder()
							.requestType("PhanCongCongNhanForm")
							.request("timTatCaCongDoanSanPham")
							.data(sp.getMaSanPham())
							.build();
					//System.out.println("Sending request: " + request);
					String json = AppUtils.GSON.toJson(request);
					dos.writeUTF(json);
					dos.flush();

					// Receive Data
					json = new String(dis.readAllBytes());
					ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
					//System.out.println("Receive response: " + response);
					List<Map<String, Object>> data = (List<Map<String, Object>>) response.getData();

					dsCongDoan = data.stream().map((value) -> AppUtils.convert(value, CongDoanSanPham.class)).collect(Collectors.toList());
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();


		}
	}

	private void thucHienChucNangPhanCong() {
		int index = tblCongNhan.getSelectedRow();
		if (index >= 0) {
			if (!thucHienChucNangKiemTra()) {
				return;
			}
			new Thread(() -> {
				try (var socket = new Socket(
						BUNDLE.getString("host"),
						Integer.parseInt(BUNDLE.getString("server.port")));
					 var dos = new DataOutputStream(socket.getOutputStream());
					 var dis = new DataInputStream(socket.getInputStream())
				){
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
					CongDoanSanPham congDoanNew = new CongDoanSanPham();
					String maCongDoan = this.txtMaCongDoan.getText();
//					System.out.println("Ma cong doan: " + maCongDoan);
					congDoanNew.setTenCongDoan(congDoan.getTenCongDoan());
					congDoanNew.setMaCongDoan(congDoan.getMaCongDoan());
					SanPham sp = (SanPham) modelSanPham.getSelectedItem();
					// Danh sách phân công được thêm
					List<PhanCongCongNhan> danhSachPhanCong = new ArrayList<PhanCongCongNhan>();
					for (int i = 0; i < viTriCongNhanDuocChon.size(); i++) {
						CongNhan congNhan = dsCongNhan.get(viTriCongNhanDuocChon.get(i));
						CongNhan congNhanNew = new CongNhan();
						congNhanNew.setMaCongNhan(congNhan.getMaCongNhan());
						PhanCongCongNhan phanCong = new PhanCongCongNhan(null, congNhanNew, congDoanNew, ngayPhanCong);
						danhSachPhanCong.add(phanCong);
					}
					// Send Data
					RequestDTO request = RequestDTO.builder()
							.requestType("PhanCongCongNhanForm")
							.request("phanCongCongNhan")
							.data(danhSachPhanCong)
							.build();
					System.out.println("PhanCong request: " + request);
					String json = AppUtils.GSON.toJson(request);
//					System.out.println("PhanCong request json1020170001: " + json);
					dos.writeUTF(json);
					dos.flush();

					// Receive Data
					json = new String(dis.readAllBytes());
					ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
//					System.out.println("Receive response: " + response);
					List<PhanCongCongNhan> data = (List<PhanCongCongNhan>) response.getData();

					if (!data.isEmpty()) {
						JOptionPane.showMessageDialog(this,
								SystemConstants.BUNDLE.getString("phanCongCongNhan.phanCongThanhCong"));
						thucHienChucNangChonCongDoan();
					} else {
						JOptionPane.showMessageDialog(this,
								SystemConstants.BUNDLE.getString("phanCongCongNhan.phanCongKhongThanhCong"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		} else {
			JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("phanCongCongNhan.chonCongNhan"));
		}
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
		this.dsSanPham = new ArrayList<SanPham>();
		this.dsCongDoan = new ArrayList<CongDoanSanPham>();
		this.dsCongNhan = new ArrayList<CongNhan>();
		this.dsPhanCong = new ArrayList<PhanCongCongNhan>();

		loadCombobox();
		loadTableCongNhan();
		// loadTablePhanCong();
	}

	private void loadCombobox() {
		new Thread(() -> {
			try (var socket = new Socket(
					BUNDLE.getString("host"),
					Integer.parseInt(BUNDLE.getString("server.port")));
				 var dos = new DataOutputStream(socket.getOutputStream());
				 var dis = new DataInputStream(socket.getInputStream())
			){
				// Send Data
				RequestDTO request = RequestDTO.builder()
						.requestType("PhanCongCongNhanForm")
						.request("timKiemTatCaSanPham")
						.data("")
						.build();
				System.out.println("Sending request: " + request);
				String json = AppUtils.GSON.toJson(request);
				dos.writeUTF(json);
				dos.flush();

				// Receive Data
				json = new String(dis.readAllBytes());
				ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
				System.out.println("Receive response: " + response);
				List<Map<String, Object>> data = (List<Map<String, Object>>) response.getData();
				dsSanPham = data.stream().map((value) -> AppUtils.convert(value, SanPham.class)).collect(Collectors.toList());
				modelSanPham.addAll(dsSanPham);

				if (!dsSanPham.isEmpty()) {
					cmbTenSanPham.setSelectedIndex(-1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	private void loadTableCongNhan() {
		new Thread(() -> {
			try (var socket = new Socket(
					BUNDLE.getString("host"),
					Integer.parseInt(BUNDLE.getString("server.port")));
				 var dos = new DataOutputStream(socket.getOutputStream());
				 var dis = new DataInputStream(socket.getInputStream())
			){
				// Send Data
				RequestDTO request = RequestDTO.builder()
						.requestType("PhanCongCongNhanForm")
						.request("timKiemTatCaCongNhan")
						.data("")
						.build();
				System.out.println("Sending request: " + request);
				String json = AppUtils.GSON.toJson(request);
				dos.writeUTF(json);
				dos.flush();

				// Receive Data
				json = new String(dis.readAllBytes());
				ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
				List<Map<String, Object>> data = (List<Map<String, Object>>) response.getData();
				dsCongNhan = data.stream().map((value) -> AppUtils.convert(value, CongNhan.class)).collect(Collectors.toList());
				tableMoelCongNhan.setRowCount(0);
				int stt = 1;

				for (CongNhan congNhan : this.dsCongNhan) {
					tableMoelCongNhan.addRow(new Object[] { stt++, congNhan.getMaCongNhan(), congNhan.getHoTen(),
							congNhan.getSoDienThoai(), congNhan.getTayNghe(), false });
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	private void loadTablePhanCong(String maCongNhan) {
		new Thread(() -> {
			try (var socket = new Socket(
					BUNDLE.getString("host"),
					Integer.parseInt(BUNDLE.getString("server.port")));
				 var dos = new DataOutputStream(socket.getOutputStream());
				 var dis = new DataInputStream(socket.getInputStream())
			){
				// Send Data
				RequestDTO request = RequestDTO.builder()
						.requestType("PhanCongCongNhanForm")
						.request("timTatCaPhanCongTheoMaCongNhanChuaHoanThanh")
						.data(maCongNhan)
						.build();
				System.out.println("Sending request: " + request);
				String json = AppUtils.GSON.toJson(request);
				dos.writeUTF(json);
				dos.flush();

				// Receive Data
				json = new String(dis.readAllBytes());
				ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
				List<Map<String, Object>> data = (List<Map<String, Object>>) response.getData();
				dsPhanCong = data.stream().map((value) -> AppUtils.convert(value, PhanCongCongNhan.class)).collect(Collectors.toList());
				tableModel.setRowCount(0);
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

	}
}
