package com.product.salary.application;

import com.google.gson.internal.LinkedTreeMap;
import com.product.salary.application.common.SystemConstants;
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
import java.util.*;

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
			SystemConstants.initLanguage();
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
		private final SanPhamService sanPhamService;
		private final CongDoanSanPhamService congDoanSanPhamService;
		private final PhanCongCongViecService phanCongCongViecService;
		public handlerClient(Socket socket) {
			this.socket = socket;
			this.accountService = new AccountServiceImpl();
			this.hopDongService = new HopDongServiceImpl();
			this.tayNgheService = new TayNgheServiceImpl();
			this.toNhomService = new ToNhomServiceImpl();
			this.congNhanService = new CongNhanServiceImpl();
			this.sanPhamService = new SanPhamServiceImpl();
			this.congDoanSanPhamService = new CongDoanSanPhamServiceImpl();
			this.phanCongCongViecService = new PhanCongCongViecServiceImpl();
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
							case "themTayNghe": {
								TayNghe tayNghe = AppUtils.convert((Map<String, Object>) requestObject.getData(), TayNghe.class);
								TayNghe result = tayNgheService.themTayNghe(tayNghe);
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
							case "capNhatTayNghe": {
								TayNghe tayNghe = AppUtils.convert((Map<String, Object>) requestObject.getData(), TayNghe.class);
								TayNghe result = tayNgheService.capNhatTayNghe(tayNghe);
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
							case "xoaTayNgheBangMa": {
								String ma = (String) requestObject.getData();
								boolean result = tayNgheService.xoaTayNgheBangMa(ma);
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
							case "themToNhom": {
								ToNhom toNhom = AppUtils.convert((Map<String, Object>) requestObject.getData(), ToNhom.class);
								System.out.println("ToNhom: " + toNhom);
								ToNhom result = toNhomService.themToNhom(toNhom);
								ResponseDTO response = ResponseDTO.builder()
										.data(result)
										.build();
								System.out.println("Response: " + response);
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "capNhatTrangThaiToNhom": {
								String ma = (String) requestObject.getData();
								boolean result = toNhomService.capNhatTrangThaiToNhom(ma, false);
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
							case "capNhatToNhom": {
								ToNhom toNhom = AppUtils.convert((Map<String, Object>) requestObject.getData(), ToNhom.class);
								ToNhom result = toNhomService.capNhatToNhom(toNhom);
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
							case "timKiemCongNhanBangMaToNhom": {
								String maToNhom = (String) requestObject.getData();
								List<CongNhan> congNhans = congNhanService.timKiemCongNhanBangMaToNhom(maToNhom);
								ResponseDTO response = ResponseDTO.builder()
										.data(congNhans)
										.build();
								//System.out.println("Response: " + response);
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "timKiemToNhom": {
								ToNhom toNhom = AppUtils.convert((Map<String, Object>) requestObject.getData(), ToNhom.class);
								List<ToNhom> result = toNhomService.timKiemToNhom(toNhom);
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
					case "PhanCongCongNhanForm": {
						switch (requestObject.getRequest()) {
							case "phanCongCongNhan": {
								List<Map<String, Object>> data = (List<Map<String, Object>>) requestObject.getData();
								System.out.println("Data: " + data);
								List<PhanCongCongNhan> danhSachPhanCong = data.stream()
										.map(e -> AppUtils.convert(e, PhanCongCongNhan.class))
										.toList();
								System.out.println("DanhSachPhanCong: " + danhSachPhanCong);
								List<PhanCongCongNhan> dsPhanCong = phanCongCongViecService.phanCongNhieuCongNhan(danhSachPhanCong);
								ResponseDTO response = ResponseDTO.builder()
										.data(dsPhanCong)
										.build();
								//System.out.println("Response: " + response);
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "timKiemTatCaSanPham": {
								List<SanPham> sanPhams = sanPhamService.timKiemTatCaSanPham();
								ResponseDTO response = ResponseDTO.builder()
										.data(sanPhams)
										.build();
								//System.out.println("Response: " + response);
								json = AppUtils.GSON.toJson(response);
								//System.out.println("Response: " + json);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
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
							case "timTatCaCongDoanSanPham": {
								List<CongDoanSanPham> congDoanSanPhams = congDoanSanPhamService.timTatCaCongDoanSanPham(requestObject.getData().toString());
								ResponseDTO response = ResponseDTO.builder()
										.data(congDoanSanPhams)
										.build();
								//System.out.println("Response: " + response);
								json = AppUtils.GSON.toJson(response);
								//System.out.println("Response: " + json);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "timTatCaCongNhanChuaPhanCongVaoCongDoan": {
								PhanCongCongNhan phanCongCongNhan = AppUtils.convert((Map<String, Object>) requestObject.getData(), PhanCongCongNhan.class);
								List<CongNhan> CongNhans = phanCongCongViecService.timTatCaCongNhanChuaPhanCongVaoCongDoan(phanCongCongNhan.getCongDoanSanPham().getSanPham().getMaSanPham(), phanCongCongNhan.getCongDoanSanPham().getMaCongDoan());
								ResponseDTO response = ResponseDTO.builder()
										.data(CongNhans)
										.build();
								//System.out.println("Response: " + response);
								json = AppUtils.GSON.toJson(response);
								//System.out.println("Response: " + json);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "timTatCaPhanCongTheoMaCongDoan": {
								List<PhanCongCongNhan> phanCongCongNhans = phanCongCongViecService.timTatCaPhanCongTheoMaCongDoan(requestObject.getData().toString());
								ResponseDTO response = ResponseDTO.builder()
										.data(phanCongCongNhans)
										.build();
								//System.out.println("Response: " + response);
								json = AppUtils.GSON.toJson(response);
								//System.out.println("Response: " + json);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "timTatCaPhanCongTheoMaCongNhanChuaHoanThanh": {
								List<PhanCongCongNhan> phanCongCongNhans = phanCongCongViecService.timTatCaPhanCongTheoMaCongNhanChuaHoanThanh(requestObject.getData().toString());
								ResponseDTO response = ResponseDTO.builder()
										.data(phanCongCongNhans)
										.build();
								//System.out.println("Response: " + response);
								json = AppUtils.GSON.toJson(response);
								//System.out.println("Response: " + json);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "xoaPhanCongCongNhan": {
								boolean result = phanCongCongViecService.xoaPhanCongCongNhan(requestObject.getData().toString());
								ResponseDTO response = ResponseDTO.builder()
										.data(result)
										.build();
								//System.out.println("Response: " + response);
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
