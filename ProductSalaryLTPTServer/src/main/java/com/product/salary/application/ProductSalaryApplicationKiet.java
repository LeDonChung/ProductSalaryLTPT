package com.product.salary.application;

import com.product.salary.application.entity.*;
import com.product.salary.application.service.*;
import com.product.salary.application.service.impl.*;
import com.product.salary.application.utils.AppUtils;
import com.product.salary.application.utils.RequestDTO;
import com.product.salary.application.utils.ResponseDTO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author Lê Đôn Chủng: Code giao diện
 * @author Lê Đôn Chủng: Code ....
 */

public class ProductSalaryApplicationKiet {
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

				ProductSalaryApplicationKiet serverTemp = new ProductSalaryApplicationKiet();
				new Thread(serverTemp.new handlerClient(socket)).start();
			}
		}
	}

	private class handlerClient implements Runnable {
		private Socket socket;
		private final AccountService accountService;
		private final HopDongService hopDongService;
		private final ToNhomService toNhomService;
		private final TayNgheService tayNgheService;
		private final CongNhanService congNhanService;
		public handlerClient(Socket socket) {
			this.socket = socket;
			this.accountService = new AccountServiceImpl();
			this.hopDongService = new HopDongServiceImpl();
			this.tayNgheService = new TayNgheServiceImpl();
			this.toNhomService = new ToNhomServiceImpl();
			this.congNhanService = new CongNhanServiceImpl();
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
						}
						break;
					}
					case "TayNgheForm": {
						switch (requestObject.getRequest()) {
							case "timKiemTatCaTayNghe": {
								List<TayNghe> tayNghes = tayNgheService.timKiemTatCaTayNghe();
								ResponseDTO response = ResponseDTO.builder()
										.data(tayNghes)
										.build();

								json = AppUtils.GSON.toJson(response);
								//System.out.println("Response: " + json);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
						}
						break;
					}
					case "ToNhomForm": {
						switch (requestObject.getRequest()) {
							case "timKiemTatCaToNhom": {
								List<ToNhom> toNhoms = toNhomService.timKiemTatCaToNhom();
								ResponseDTO response = ResponseDTO.builder()
										.data(toNhoms)
										.build();

								json = AppUtils.GSON.toJson(response);
								//System.out.println("Response: " + json);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
						}
						break;
					}
					case "CongNhanForm": {
						switch (requestObject.getRequest()) {
							case "timKiemTatCaCongNhan": {
								List<CongNhan> congNhans = congNhanService.timKiemTatCaCongNhan();
								ResponseDTO response = ResponseDTO.builder()
										.data(congNhans)
										.build();
								//System.out.println("Response: " + response);
								json = AppUtils.GSON.toJson(response);
								//System.out.println("Response: " + json);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "capNhatTrangThaiCongNhan": {
								String ma = (String) requestObject.getData();
								boolean result = congNhanService.capNhatTrangThaiCongNhan(ma, false);
								ResponseDTO response = ResponseDTO.builder()
										.data(result)
										.build();
								//System.out.println("Response: " + response);
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "themCongNhan": {
								CongNhan congNhan = AppUtils.convert((Map<String, Object>) requestObject.getData(), CongNhan.class);
								CongNhan result = congNhanService.themCongNhan(congNhan);
								ResponseDTO response = ResponseDTO.builder()
										.data(result)
										.build();
								//System.out.println("Response: " + response);
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "capNhatCongNhan": {
								CongNhan congNhan = AppUtils.convert((Map<String, Object>) requestObject.getData(), CongNhan.class);
								CongNhan result = congNhanService.capNhatCongNhan(congNhan);
								ResponseDTO response = ResponseDTO.builder()
										.data(result)
										.build();
								//System.out.println("Response: " + response);
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "themNhieuCongNhan": {
//								List<Map<String, Object>> data = (List<Map<String, Object>>) requestObject.getData();
//								System.out.println("Data: " + data);
//
//								List<CongNhan> result = congNhanService.themNhieuCongNhan(dsCongNhan);
//								ResponseDTO response = ResponseDTO.builder()
//										.data(result)
//										.build();
//								//System.out.println("Response: " + response);
//								json = AppUtils.GSON.toJson(response);
//								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
//								dos.write(bytes);
//								dos.flush();
								break;
							}
						}
						break;
					}
					case "TimKiemCongNhanForm": {
						switch (requestObject.getRequest()) {
							case "timKiemCongNhan": {
								CongNhan congNhan = AppUtils.convert((Map<String, Object>) requestObject.getData(), CongNhan.class);
								List<CongNhan> result = congNhanService.timKiemCongNhan(congNhan);
								ResponseDTO response = ResponseDTO.builder()
										.data(result)
										.build();
								//System.out.println("Response: " + response);
								json = AppUtils.GSON.toJson(response);
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
