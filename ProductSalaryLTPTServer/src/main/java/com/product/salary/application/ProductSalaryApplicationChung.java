package com.product.salary.application;

import com.product.salary.application.entity.*;
import com.product.salary.application.service.AccountService;
import com.product.salary.application.service.CongDoanSanPhamService;
import com.product.salary.application.service.HopDongService;
import com.product.salary.application.service.SanPhamService;
import com.product.salary.application.service.impl.AccountServiceImpl;
import com.product.salary.application.service.impl.CongDoanSanPhamServiceImpl;
import com.product.salary.application.service.impl.HopDongServiceImpl;
import com.product.salary.application.service.impl.SanPhamServiceImpl;
import com.product.salary.application.utils.AppUtils;
import com.product.salary.application.utils.RequestDTO;
import com.product.salary.application.utils.ResponseDTO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * @author Lê Đôn Chủng: Code giao diện
 * @author Lê Đôn Chủng: Code ....
 */

public class ProductSalaryApplicationChung {
	private final static ResourceBundle BUNDLE = ResourceBundle.getBundle("app");


	public static void main(String[] args) throws IOException {
		try(
                var sever = new ServerSocket(
						Integer.parseInt(BUNDLE.getString("server.port"))
				);
				) {
			System.out.println("Server is running on port 23862");
			while(true) {
				Socket socket = sever.accept();
				System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());

				ProductSalaryApplicationChung serverTemp = new ProductSalaryApplicationChung();
				new Thread(serverTemp.new handlerClient(socket)).start();
			}
		}
	}

	private class handlerClient implements Runnable {
		private Socket socket;
		private final AccountService accountService;
		private final HopDongService hopDongService;
		private final SanPhamService sanPhamService;
		private final CongDoanSanPhamService congDoanSanPhamService;
		public handlerClient(Socket socket) {
			this.socket = socket;
			this.accountService = new AccountServiceImpl();
			this.hopDongService = new HopDongServiceImpl();
			this.sanPhamService = new SanPhamServiceImpl();
			this.congDoanSanPhamService = new CongDoanSanPhamServiceImpl();
		}


		@Override
		public void run() {
			try{
				var dis = new DataInputStream(socket.getInputStream());
				var dos = new DataOutputStream(socket.getOutputStream());
				String json = dis.readUTF();
				RequestDTO requestObject = AppUtils.GSON.fromJson(json, RequestDTO.class);
				System.out.println("Request: " + requestObject);
				String request = requestObject.getRequestType();
				switch (request) {
					case "DoiMatKhauForm": {
						switch (requestObject.getRequest()) {
							case "capNhatMatKhau": {
								Account account = AppUtils.convert((Map<String, Object>) requestObject.getData(), Account.class);
								boolean result = accountService.capNhatMatKhau(account.getTaiKhoan(), account.getMatKhau());

								ResponseDTO response = ResponseDTO.builder()
										.data(result)
										.build();

								System.out.println("Response: " + response);
								// Send Response
								json = AppUtils.GSON.toJson(response);
								dos.writeBytes(json);
								dos.flush();
								break;
							}
							default:
								break;
						}
						break;
					}
					case "DangNhapForm": {
						switch (requestObject.getRequest()) {
							case "timKiemTaiKhoanHoatDongBangTaiKhoanVaMatKhau": {
								// Data la Map<> nen phai cast
								Account account = AppUtils.convert((Map<String, Object>) requestObject.getData(), Account.class);
								account =
										accountService
												.timKiemTaiKhoanHoatDongBangTaiKhoanVaMatKhau(account.getTaiKhoan(), account.getMatKhau());

								ResponseDTO response = ResponseDTO.builder()
										.data(account)
										.build();

								System.out.println("Response: " + response);
								// Send Response
								json = AppUtils.GSON.toJson(response);
								dos.writeBytes(json);
								dos.flush();
								break;
							}

							default:
								break;
						}
						break;
					}

					case "SanPhamForm": {
						switch (requestObject.getRequest()) {
							case "timTatCaSanPhamDangSanXuat": {
								List<SanPham> sanPhams = sanPhamService.timTatCaSanPhamDangSanXuat();
								ResponseDTO response = ResponseDTO.builder()
										.data(sanPhams)
										.build();

								json = AppUtils.GSON.toJson(response);
								System.out.println("Response: " + json);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
						}
					}
					case "CongDoanSanPhamForm": {
						switch (requestObject.getRequest()) {
							case "timTatCaCongDoanSanPhamBangMaSanPham": {
								String maSanPham = (String) requestObject.getData();
								List<CongDoanSanPham> congDoanSanPhams = congDoanSanPhamService.timTatCaCongDoanSanPhamDangHoatDongBangMaSanPham(maSanPham);
								ResponseDTO response = ResponseDTO.builder()
										.data(congDoanSanPhams)
										.build();

								json = AppUtils.GSON.toJson(response);
								System.out.println("Response: " + json);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}

							case "themCongDoanSanPham": {
								CongDoanSanPham congDoanSanPham = AppUtils.convert((Map<String, Object>) requestObject.getData(), CongDoanSanPham.class);
								congDoanSanPham = congDoanSanPhamService.themCongDoanSanPham(congDoanSanPham);
								ResponseDTO response = ResponseDTO.builder()
										.data(congDoanSanPham)
										.build();

								json = AppUtils.GSON.toJson(response);
								System.out.println("Response: " + json);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "capNhatCongDoanSanPham": {
								CongDoanSanPham congDoanSanPham = AppUtils.convert((Map<String, Object>) requestObject.getData(), CongDoanSanPham.class);
								congDoanSanPham = congDoanSanPhamService.capNhatCongDoanSanPham(congDoanSanPham);
								ResponseDTO response = ResponseDTO.builder()
										.data(congDoanSanPham)
										.build();

								json = AppUtils.GSON.toJson(response);
								System.out.println("Response: " + json);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "capNhatTrangThaiCongDoanSanPham": {
								Map<String, Object> data = (Map<String, Object>) requestObject.getData();
								String maCongDoanSanPham = (String) data.get("maCongDoan");
								boolean trangThai = (boolean) data.get("trangThai");
								boolean result = congDoanSanPhamService.capNhatTrangThaiCongDoanSanPham(maCongDoanSanPham, trangThai);

								ResponseDTO response = ResponseDTO.builder()
										.data(result)
										.build();

								json = AppUtils.GSON.toJson(response);
								System.out.println("Response: " + json);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
						}
					}
					case "HopDongForm": {
						switch (requestObject.getRequest()) {
							case "timTatCaHopDong": {
								List<HopDong> hopDongs = hopDongService.timTatCaHopDong();
								ResponseDTO response = ResponseDTO.builder()
										.data(hopDongs)
										.build();

								json = AppUtils.GSON.toJson(response);
								System.out.println("Response: " + json);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "timTatCaChiTietHopDongBangMaHopDong": {
								String maHopDong = (String) requestObject.getData();
								System.out.println("maHopDong: " + maHopDong);
								List<ChiTietHopDong> chiTietHopDongs = hopDongService.timTatCaChiTietHopDongBangMaHopDong(maHopDong);

								ResponseDTO response = ResponseDTO.builder()
										.data(chiTietHopDongs)
										.build();

								json = AppUtils.GSON.toJson(response);
								System.out.println("Response: " + json);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "themHopDong": {
								Map<String, Object> data = (Map<String, Object>) requestObject.getData();

								HopDong hopDong = AppUtils.convert((Map<String, Object>) data.get("hopDong"), HopDong.class);

								List<ChiTietHopDong> chiTietHopDongs = ((List<Map<String, Object>>) data.get("chiTietHopDongs")).stream()
										.map(map -> AppUtils.convert(map, ChiTietHopDong.class)).collect(Collectors.toList());
								hopDong = hopDongService.themHopDong(hopDong, chiTietHopDongs);

								ResponseDTO response = ResponseDTO.builder()
										.data(hopDong)
										.build();

								json = AppUtils.GSON.toJson(response);
								System.out.println("Response: " + json);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "thanhLyHopDong": {
								String maHopDong = (String) requestObject.getData();
								boolean result = hopDongService.thanhLyHopDong(maHopDong);

								ResponseDTO response = ResponseDTO.builder()
										.data(result)
										.build();

								json = AppUtils.GSON.toJson(response);
								System.out.println("Response: " + json);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
						}
						break;
					}
					default:
						break;
				}
				dos.close();
				dis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
	}
}
