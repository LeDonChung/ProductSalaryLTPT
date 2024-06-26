package com.product.salary.client.view.worker;

import com.product.salary.client.common.SystemConstants;
import com.product.salary.application.entity.CongNhan;
import com.product.salary.application.utils.AppUtils;
import com.product.salary.application.utils.PriceFormatterUtils;
import com.product.salary.application.utils.RequestDTO;
import com.product.salary.application.utils.ResponseDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ChiTietLuongCongNhanForm extends JFrame {
	private final ResourceBundle BUNDLE = ResourceBundle.getBundle("app");

	private final JPanel pnlChinh;
	private final JTable tblChiTietLuongNhanVien;
	private final DefaultTableModel tblModelChiTietLuong;
	private final JTextField txtMaCongNhan;
	private final JTextField txtHoTen;
	private final JTextField txtSoDienThoai;
	private final JTextField txtToNhom;
	private final JTextField txtTayNghe;
	private final JTextField txtGioiTinh;
	private List<Map<String, Object>> danhSachChiTietLuong;

	/**
	 * Create the frame.
	 */
	public ChiTietLuongCongNhanForm(String maCongNhan, int thang, int nam) {
		SystemConstants.initLanguage();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 965, 506);
		getContentPane().setLayout(null);

		pnlChinh = new JPanel();
		pnlChinh.setBounds(0, 0, 941, 469);
		getContentPane().add(pnlChinh);
		pnlChinh.setLayout(null);

		JLabel lblTitle = new JLabel(SystemConstants.BUNDLE.getString("chiTietLuongCongNhan.title"));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblTitle.setBounds(0, 5, 941, 46);
		pnlChinh.add(lblTitle);

		tblModelChiTietLuong = new DefaultTableModel(
				new String[] { SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.stt"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.maChamCong"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.tenSanPham"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.tenCongDoan"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.ngayCham"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.caLam"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.soLuongHoanThanh"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.trangThai"),
						SystemConstants.BUNDLE.getString("chamCongCongNhan.tableDanhSachChamCong.tongTien") },
				10);
		tblChiTietLuongNhanVien = new JTable(tblModelChiTietLuong);
		tblChiTietLuongNhanVien.setShowVerticalLines(true);
		tblChiTietLuongNhanVien.setShowHorizontalLines(true);
		tblChiTietLuongNhanVien.setRowHeight(25);
		JScrollPane scrChiTietLuong = new JScrollPane(tblChiTietLuongNhanVien);
		scrChiTietLuong.setLocation(10, 164);
		scrChiTietLuong.setSize(921, 292);
		pnlChinh.add(scrChiTietLuong);

		JPanel pnlMaCongNhan = new JPanel();
		pnlMaCongNhan.setBounds(10, 52, 365, 46);
		pnlChinh.add(pnlMaCongNhan);
		pnlMaCongNhan.setLayout(null);

		JLabel lblMaCongNhan = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chiTietLuongCongNhan.lblMaCongNhan")));
		lblMaCongNhan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblMaCongNhan.setBounds(10, 15, 118, 15);
		pnlMaCongNhan.add(lblMaCongNhan);

		txtMaCongNhan = new JTextField();
		txtMaCongNhan.setEditable(false);
		txtMaCongNhan.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaCongNhan.setBounds(104, 10, 251, 26);
		pnlMaCongNhan.add(txtMaCongNhan);
		txtMaCongNhan.setColumns(10);

		JPanel pnlHoTen = new JPanel();
		pnlHoTen.setLayout(null);
		pnlHoTen.setBounds(10, 108, 365, 46);
		pnlChinh.add(pnlHoTen);

		JLabel lblHoTen = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chiTietLuongCongNhan.lblHoTen")));
		lblHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblHoTen.setBounds(10, 15, 84, 15);
		pnlHoTen.add(lblHoTen);

		txtHoTen = new JTextField();
		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtHoTen.setEditable(false);
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(104, 10, 251, 26);
		pnlHoTen.add(txtHoTen);

		JPanel pnlSoDienThoai = new JPanel();
		pnlSoDienThoai.setLayout(null);
		pnlSoDienThoai.setBounds(374, 52, 283, 46);
		pnlChinh.add(pnlSoDienThoai);

		JLabel lblSoDienThoai = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chiTietLuongCongNhan.lblSoDienThoai")));
		lblSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblSoDienThoai.setBounds(10, 15, 118, 15);
		pnlSoDienThoai.add(lblSoDienThoai);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtSoDienThoai.setEditable(false);
		txtSoDienThoai.setColumns(10);
		txtSoDienThoai.setBounds(104, 10, 173, 26);
		pnlSoDienThoai.add(txtSoDienThoai);

		JPanel pnlToNhom = new JPanel();
		pnlToNhom.setLayout(null);
		pnlToNhom.setBounds(374, 108, 283, 46);
		pnlChinh.add(pnlToNhom);

		JLabel lblToNhom = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chiTietLuongCongNhan.lblToNhom")));
		lblToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblToNhom.setBounds(10, 15, 74, 15);
		pnlToNhom.add(lblToNhom);

		txtToNhom = new JTextField();
		txtToNhom.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtToNhom.setEditable(false);
		txtToNhom.setColumns(10);
		txtToNhom.setBounds(104, 10, 173, 26);
		pnlToNhom.add(txtToNhom);

		JPanel pnlTayNghe = new JPanel();
		pnlTayNghe.setLayout(null);
		pnlTayNghe.setBounds(659, 52, 272, 46);
		pnlChinh.add(pnlTayNghe);

		JLabel lblTayNghe = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chiTietLuongCongNhan.lblTayNghe")));
		lblTayNghe.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTayNghe.setBounds(10, 15, 92, 15);
		pnlTayNghe.add(lblTayNghe);

		txtTayNghe = new JTextField();
		txtTayNghe.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTayNghe.setEditable(false);
		txtTayNghe.setColumns(10);
		txtTayNghe.setBounds(104, 10, 158, 26);
		pnlTayNghe.add(txtTayNghe);

		JPanel pnlGioiTinh = new JPanel();
		pnlGioiTinh.setLayout(null);
		pnlGioiTinh.setBounds(659, 108, 272, 46);
		pnlChinh.add(pnlGioiTinh);

		JLabel lblGioiTinh = new JLabel(String.format("<html><p>%s</p></html>",
				SystemConstants.BUNDLE.getString("chiTietLuongCongNhan.lblGioiTinh")));
		lblGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblGioiTinh.setBounds(10, 15, 92, 15);
		pnlGioiTinh.add(lblGioiTinh);

		txtGioiTinh = new JTextField();
		txtGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtGioiTinh.setEditable(false);
		txtGioiTinh.setColumns(10);
		txtGioiTinh.setBounds(104, 10, 158, 26);
		pnlGioiTinh.add(txtGioiTinh);

		init();
		thucHienChucNangTimKiemChiTietLuong(maCongNhan, thang, nam);
	}

	private void init() {
		this.danhSachChiTietLuong = new ArrayList<Map<String, Object>>();
	}

	private void thucHienChucNangTimKiemChiTietLuong(String maCongNhan, int thang, int nam) {
		new Thread(() -> {

			try (var socket = new Socket(
					BUNDLE.getString("host"),
					Integer.parseInt(BUNDLE.getString("server.port")));
				 var dos = new DataOutputStream(socket.getOutputStream());
				 var dis = new DataInputStream(socket.getInputStream())) {

				Map<String, Object> data = Map.of(
						"maCongNhan", maCongNhan,
						"thang", thang,
						"nam", nam
				);
				// send data to server
				RequestDTO request = RequestDTO
						.builder()
						.requestType("ChiTietLuongForm")
						.request("chiTietLuongCongNhan")
						.data(data)
						.build();
				String json = AppUtils.GSON.toJson(request);
				dos.writeUTF(json);
				dos.flush();

				// receive data from server
				json = new String(dis.readAllBytes());
				ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
				Map<String, Object> result = (Map<String, Object>) response.getData();

				this.danhSachChiTietLuong = (List<Map<String, Object>>) result.get("danhSachChiTietLuong");
				CongNhan congNhan = AppUtils.convert((Map<String, Object>) result.get("congNhan"), CongNhan.class);

				tblModelChiTietLuong.setRowCount(0);

				txtMaCongNhan.setText(congNhan.getMaCongNhan());
				txtHoTen.setText(congNhan.getHoTen());
				txtTayNghe.setText(congNhan.getTayNghe().getTenTayNghe());
				txtGioiTinh.setText(congNhan.getGioiTinh() == 1 ? "Nam" : "Nữ");
				txtToNhom.setText(congNhan.getToNhom().getTenToNhom());
				txtSoDienThoai.setText(congNhan.getSoDienThoai());
				int stt = 1;

				for (Map<String, Object> chiTietLuong : this.danhSachChiTietLuong) {
					tblModelChiTietLuong.addRow(new Object[] { stt++, chiTietLuong.get("MaChamCong"),
							chiTietLuong.get("TenSanPham"), chiTietLuong.get("TenCongDoan"), chiTietLuong.get("NgayCham"),
							chiTietLuong.get("CaLam"), chiTietLuong.get("SoLuongHoanThanh"), chiTietLuong.get("TrangThai"),
							PriceFormatterUtils.format(Double.valueOf(chiTietLuong.get("TongTien").toString())) });
				}

			}
			catch (Exception e) {
				e.printStackTrace();
			}

		}).start();

	}
}
