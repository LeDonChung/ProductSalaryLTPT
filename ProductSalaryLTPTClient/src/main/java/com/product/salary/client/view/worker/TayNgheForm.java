package com.product.salary.client.view.worker;

import com.product.salary.client.common.SystemConstants;
import com.product.salary.application.entity.TayNghe;
import com.product.salary.application.utils.AppUtils;
import com.product.salary.application.utils.RequestDTO;
import com.product.salary.application.utils.ResponseDTO;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TayNgheForm extends JPanel {
	private final static ResourceBundle BUNDLE = ResourceBundle.getBundle("app");
	private final JTextField txtMaTayNghe;
	private final JTextField txtTenTayNghe;
	private final DefaultTableModel tableModelTayNghe;
	private final JTable tblTayNghe;
	private final JButton btnCapNhat;
	private final JButton btnXoa;
	private final JButton btnThem;
	private final JButton btnLamMoi;
	private List<TayNghe> tayNghes;
	private final JLabel lblLoiTenTayNghe;

	/**
	 * Create the panel.
	 */
	public TayNgheForm() {

		setLayout(null);

		JPanel pnlChinh = new JPanel();
		pnlChinh.setBounds(10, 10, 1273, 821);
		add(pnlChinh);
		pnlChinh.setLayout(null);

		JLabel lblTitle = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("tayNghe.title")));
		lblTitle.setBounds(0, 0, 1250, 80);
		lblTitle.setBorder(null);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlChinh.add(lblTitle);

		JPanel pnlMaTayNghe = new JPanel();
		pnlMaTayNghe.setLayout(null);
		pnlMaTayNghe.setBounds(20, 118, 566, 62);
		pnlChinh.add(pnlMaTayNghe);

		JLabel lblMaTayNghe = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("tayNghe.maTayNghe")));
		lblMaTayNghe.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaTayNghe.setBounds(10, 0, 150, 40);
		pnlMaTayNghe.add(lblMaTayNghe);

		txtMaTayNghe = new JTextField();
		txtMaTayNghe.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaTayNghe.setEnabled(false);
		txtMaTayNghe.setEditable(false);
		txtMaTayNghe.setColumns(10);
		txtMaTayNghe.setBounds(188, 0, 354, 40);
		pnlMaTayNghe.add(txtMaTayNghe);

		JPanel pnlTenTayNghe = new JPanel();
		pnlTenTayNghe.setLayout(null);
		pnlTenTayNghe.setBounds(684, 118, 566, 62);
		pnlChinh.add(pnlTenTayNghe);

		JLabel lblTenTayNghe = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("tayNghe.tenTayNghe")));
		lblTenTayNghe.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTenTayNghe.setBounds(10, 0, 150, 40);
		pnlTenTayNghe.add(lblTenTayNghe);

		txtTenTayNghe = new JTextField();
		txtTenTayNghe.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTenTayNghe.setColumns(10);
		txtTenTayNghe.setBounds(188, 0, 354, 40);
		pnlTenTayNghe.add(txtTenTayNghe);

		lblLoiTenTayNghe = new JLabel("");
		lblLoiTenTayNghe.setForeground(Color.RED);
		lblLoiTenTayNghe.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblLoiTenTayNghe.setBounds(188, 39, 354, 23);
		pnlTenTayNghe.add(lblLoiTenTayNghe);

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

		JLabel lblDanhSachTayNghe = new JLabel(
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("tayNghe.danhSachTayNghe")));
		lblDanhSachTayNghe.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDanhSachTayNghe.setBounds(10, 386, 350, 36);
		pnlChinh.add(lblDanhSachTayNghe);

		tableModelTayNghe = new DefaultTableModel(new String[] {
				String.format("<html><p>%s</p></html>", SystemConstants.BUNDLE.getString("tayNghe.tblTayNghe.stt")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("tayNghe.tblTayNghe.maTayNghe")),
				String.format("<html><p>%s</p></html>",
						SystemConstants.BUNDLE.getString("tayNghe.tblTayNghe.tenTayNghe")) },
				15);

		tblTayNghe = new JTable(tableModelTayNghe);
		tblTayNghe.setShowVerticalLines(true);
		tblTayNghe.setShowHorizontalLines(true);
		tblTayNghe.setRowHeight(25);

		JScrollPane scrTayNghe = new JScrollPane(tblTayNghe);
		scrTayNghe.setLocation(10, 432);
		scrTayNghe.setSize(1240, 379);
		tblTayNghe.setBounds(0, 570, 1273, 263);
		pnlChinh.add(scrTayNghe);

		init();
		event();
	}

	private void event() {
		tblTayNghe.addMouseListener(new MouseListener() {

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
				thucHienChucNangClickTayNghe();
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
		this.tayNghes = new ArrayList<TayNghe>();
		this.loadTable();
	}

	private void loadTable() {
		new Thread(() -> {
			try (var socket = new Socket(
					BUNDLE.getString("host"),
					Integer.parseInt(BUNDLE.getString("server.port")));
				 var dos = new DataOutputStream(socket.getOutputStream());
				 var dis = new DataInputStream(socket.getInputStream())
			){
				// Send Data
				RequestDTO request = RequestDTO.builder()
						.requestType("TayNgheForm")
						.request("timKiemTatCaTayNghe")
						.data("")
						.build();
//				System.out.println("Sending request: " + request);
				String json = AppUtils.GSON.toJson(request);
				dos.writeUTF(json);
				dos.flush();

				// Receive Data
				json = new String(dis.readAllBytes());
				ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
				List<Map<String, Object>> data = (List<Map<String, Object>>) response.getData();
				tayNghes = data.stream().map((value) -> AppUtils.convert(value, TayNghe.class)).collect(Collectors.toList());
				tableModelTayNghe.setRowCount(0);
				int stt = 1;
				for (TayNghe tayNghe : this.tayNghes) {
					tableModelTayNghe.addRow(new Object[] { stt++, tayNghe.getMaTayNghe(), tayNghe.getTenTayNghe() });
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	private void thucHienChucNangClickTayNghe() {
		int index = tblTayNghe.getSelectedRow();
		if (index >= 0) {
			TayNghe tn = this.tayNghes.get(index);
			this.txtMaTayNghe.setText(tn.getMaTayNghe());
			this.txtTenTayNghe.setText(tn.getTenTayNghe());
		}
	}

	private void thucHienChucNangLamMoi() {
		this.txtTenTayNghe.setText("");
		this.txtMaTayNghe.setText("");
		this.loadTable();
		thucHienThucLamLamMoiLoi();
	}

	private void thucHienThucLamLamMoiLoi() {
		this.lblLoiTenTayNghe.setText("");
	}

	private void thucHienChucNangThem() {
		if (!thucHienChucNangKiemTra()) {
			return;
		}
		String maTayNghe = "";
		String tenTayNghe = this.txtTenTayNghe.getText().trim();
		try {
			new Thread(() -> {
				try (var socket = new Socket(
						BUNDLE.getString("host"),
						Integer.parseInt(BUNDLE.getString("server.port")));
					 var dos = new DataOutputStream(socket.getOutputStream());
					 var dis = new DataInputStream(socket.getInputStream())
				){
					TayNghe tayNghe = new TayNghe(maTayNghe, tenTayNghe);
					// Send Data
					RequestDTO request = RequestDTO.builder()
							.requestType("TayNgheForm")
							.request("themTayNghe")
							.data(tayNghe)
							.build();
//					System.out.println("Sending request: " + request);
					String json = AppUtils.GSON.toJson(request);
					dos.writeUTF(json);
					dos.flush();

					// Receive Data
					json = new String(dis.readAllBytes());
					ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
					TayNghe result = AppUtils.convert((Map<String, Object>) response.getData(), TayNghe.class);
					if (result != null) {
						JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("tayNghe.themTayNgheThanh"));
						//JOptionPane.showMessageDialog(this, "Thêm tay nghề thành công.");
						this.thucHienChucNangLamMoi();
					} else {
						JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("tayNghe.themTayNgheKhongThanh"));
						//JOptionPane.showMessageDialog(this, "Thêm tay nghề không thành công.");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean thucHienChucNangKiemTra() {
		thucHienThucLamLamMoiLoi();

		String tenTayNghe = this.txtTenTayNghe.getText().trim();

		boolean status = true;
		if (ObjectUtils.isEmpty(tenTayNghe)) {
			lblLoiTenTayNghe.setText(SystemConstants.BUNDLE.getString("tayNghe.loiTayNghe"));
			//lblLoiTenTayNghe.setText("Tên tay nghề không được rỗng.");
			status = false;
		}

		return status;
	}

	private void thucHienThucNangCapNhat() {
		int isSelected = this.tblTayNghe.getSelectedRow();
		if (isSelected >= 0) {
			if (!thucHienChucNangKiemTra()) {
				return;
			}

			String maTayNghe = this.txtMaTayNghe.getText().trim();
			String tenTayNghe = this.txtTenTayNghe.getText().trim();
			try {


				int choose = JOptionPane.showConfirmDialog(this,
						SystemConstants.BUNDLE.getString("tayNghe.capNhat"), SystemConstants.BUNDLE.getString("congNhan.xacNhan"),
						JOptionPane.YES_NO_OPTION);
				if (choose == JOptionPane.OK_OPTION) {
					new Thread(() ->{
						try (var socket = new Socket(
								BUNDLE.getString("host"),
								Integer.parseInt(BUNDLE.getString("server.port")));
							 var dos = new DataOutputStream(socket.getOutputStream());
							 var dis = new DataInputStream(socket.getInputStream())
						){
							TayNghe tayNghe = new TayNghe(maTayNghe, tenTayNghe);
							// Send Data
							RequestDTO request = RequestDTO.builder()
									.requestType("TayNgheForm")
									.request("capNhatTayNghe")
									.data(tayNghe)
									.build();
							//System.out.println("Sending request: " + request);
							String json = AppUtils.GSON.toJson(request);
							dos.writeUTF(json);
							dos.flush();

							// Receive Data
							json = new String(dis.readAllBytes());
							ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
							TayNghe result = AppUtils.convert((Map<String, Object>) response.getData(), TayNghe.class);
							if (result != null) {
								JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("tayNghe.capNhatThanh"));
								//JOptionPane.showMessageDialog(this, "Cập nhật tay nghề thành công.");
								this.thucHienChucNangLamMoi();
							} else {
								JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("tayNghe.capNhatKhongThanh"));
								//JOptionPane.showMessageDialog(this, "Cập nhật tay nghề không thành công.");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}).start();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("tayNghe.chonTayNgheCapNhat"));
			//JOptionPane.showMessageDialog(this, "Vui lòng chọn tay nghề để cập nhật.");
		}
	}

	private void thucHienChucNangXoa() {
		int isSelected = this.tblTayNghe.getSelectedRow();
		if (isSelected >= 0) {

			TayNghe tayNghe = this.tayNghes.get(isSelected);
			int choose = JOptionPane.showConfirmDialog(this,
					SystemConstants.BUNDLE.getString("tayNghe.xoaTayNghe"), SystemConstants.BUNDLE.getString("congNhan.xacNhan"),
					JOptionPane.YES_NO_OPTION);
			if (choose == JOptionPane.OK_OPTION) {
				new Thread(() ->{
					try (var socket = new Socket(
							BUNDLE.getString("host"),
							Integer.parseInt(BUNDLE.getString("server.port")));
						 var dos = new DataOutputStream(socket.getOutputStream());
						 var dis = new DataInputStream(socket.getInputStream())
					){
						// Send Data
						RequestDTO request = RequestDTO.builder()
								.requestType("TayNgheForm")
								.request("xoaTayNgheBangMa")
								.data(tayNghe.getMaTayNghe())
								.build();
						//System.out.println("Sending request: " + request);
						String json = AppUtils.GSON.toJson(request);
						dos.writeUTF(json);
						dos.flush();

						// Receive Data
						json = new String(dis.readAllBytes());
						ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
						boolean result = (boolean) response.getData();
						if (result) {
							JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("tayNghe.xoaTayNgheThanh"));
							//JOptionPane.showMessageDialog(this, "Xóa tay nghề thành công.");
							this.thucHienChucNangLamMoi();
						} else {
							JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("tayNghe.xoaTayNgheKhongThanh"));
							//	JOptionPane.showMessageDialog(this, "Xóa tay nghề không thành công.");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}).start();

			}
		} else {
			JOptionPane.showMessageDialog(this, SystemConstants.BUNDLE.getString("tayNghe.chonTayNgheXoa"));
			//JOptionPane.showMessageDialog(this, "Vui lòng chọn tay nghề để xóa.");
		}
	}
}
