package com.product.salary.application;

import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.entity.*;
import com.product.salary.application.service.*;
import com.product.salary.application.service.impl.*;
import com.product.salary.application.utils.AppUtils;
import com.product.salary.application.utils.RequestDTO;
import com.product.salary.application.utils.ResponseDTO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Lê Đôn Chủng: Code giao diện
 * @author Lê Đôn Chủng: Code ....
 */

public class ProductSalaryApplicationServer {
	private final static ResourceBundle BUNDLE = ResourceBundle.getBundle("app");


	public static void main(String[] args) throws IOException {
		try(
                var sever = new ServerSocket(
						Integer.parseInt(BUNDLE.getString("server.port"))
				)
				) {
			System.out.println("Server is running on port 23862");
			SystemConstants.initLanguage();
			while(true) {
				Socket socket = sever.accept();
				System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());

				ProductSalaryApplicationServer serverTemp = new ProductSalaryApplicationServer();
				new Thread(serverTemp.new handlerClient(socket)).start();
			}
		}
	}

	private class handlerClient implements Runnable {
		private Socket socket;
		private final AccountService accountService;
		private final HopDongService hopDongService;
		private final SanPhamService sanPhamService;
		private final CongNhanService congNhanService;
		private final NhanVienService nhanVienService;
		private final CongDoanSanPhamService congDoanSanPhamService;
		private final LuongCongNhanService luongCongNhanService;
		private final LuongNhanVienService luongNhanVienService;
		private final ChamCongCongNhanService chamCongCongNhanService;
		private final PhanCongCongViecService phanCongCongViecService;
		private final TayNgheService tayNgheService;
		private final ToNhomService toNhomService;
		private final PhongBanService phongBanService;
		private final ChucVuService chucVuService;
		private final TrinhDoService trinhDoService;
		private final ChamCongNhanVienService chamCongNhanVienService;


		public handlerClient(Socket socket) {
			this.socket = socket;
			this.accountService = new AccountServiceImpl();
			this.hopDongService = new HopDongServiceImpl();
			this.sanPhamService = new SanPhamServiceImpl();
			this.congNhanService = new CongNhanServiceImpl();
			this.nhanVienService = new NhanVienServiceImpl();
			this.luongCongNhanService = new LuongCongNhanServiceImpl();
			this.luongNhanVienService = new LuongNhanVienServiceImpl();
			this.congDoanSanPhamService = new CongDoanSanPhamServiceImpl();
			this.chamCongCongNhanService = new ChamCongCongNhanServiceImpl();
			this.phanCongCongViecService = new PhanCongCongViecServiceImpl();
			this.tayNgheService = new TayNgheServiceImpl();
			this.toNhomService = new ToNhomServiceImpl();
			this.phongBanService = new PhongBanServiceImpl();
			this.chucVuService = new ChucVuServiceImpl();
			this.trinhDoService = new TrinhDoServiceImpl();
			this.chamCongNhanVienService = new ChamCongNhanVienServiceImpl();
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
					case "ChamCongNhanVienForm":{
						switch (requestObject.getRequest()){
							case "timTatCaChamCongNhanVienTheoNgayVaCa": {
								ChamCongNhanVien ccnv = AppUtils.convert((Map<String, Object>) requestObject.getData(), ChamCongNhanVien.class);
								List<ChamCongNhanVien> chamCongNhanViens = chamCongNhanVienService.timKiemTatCaChamCongNhanVienTheoCaVaNgay(ccnv.getNgayChamCong(), ccnv.getCaLam().getMaCa());

								ResponseDTO response = ResponseDTO.builder()
										.data(chamCongNhanViens)
										.build();
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "timKiemNhanVienChuaChamCongBangCaLamVaNgayChamCong": {
								ChamCongNhanVien ccnv = AppUtils.convert((Map<String, Object>) requestObject.getData(), ChamCongNhanVien.class);
								List<NhanVien> nhanViens = chamCongNhanVienService.timKiemNhanVienChuaChamCongBangCaLamVaNgayChamCong(ccnv.getNgayChamCong(), ccnv.getCaLam().getMaCa());

								ResponseDTO response = ResponseDTO.builder()
										.data(nhanViens)
										.build();
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "themChamCongNhanVien": {
								ChamCongNhanVien ccnv = AppUtils.convert((Map<String, Object>) requestObject.getData(), ChamCongNhanVien.class);

								ChamCongNhanVien chamCongNhanVienThem = chamCongNhanVienService.themChamCongNhanVien(ccnv);

								ResponseDTO response = ResponseDTO.builder()
										.data(chamCongNhanVienThem)
										.build();
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "capNhatTrangThaiDiLamCuaNhanVien": {
								Map<String, Object> data = (Map<String, Object>) requestObject.getData();
								boolean status = chamCongNhanVienService.capNhatTrangThaiDiLamCuaNhanVien((String) data.get("maChamCong"), Integer.parseInt(data.get("trangThai").toString().replace(".0", "")));

								ResponseDTO response = ResponseDTO.builder()
										.data(status)
										.build();
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;

							}
							case "chamCongTatCaNhanVien":{
								List<Map<String, Object>> chamCongNhanViens = (List<Map<String, Object>>) requestObject.getData();
								List<ChamCongNhanVien> chamCongNhanViensThem = chamCongNhanViens.stream().map((value) -> AppUtils.convert(value, ChamCongNhanVien.class)).toList();
								int status = 0;
								for (ChamCongNhanVien ccnv : chamCongNhanViensThem){
									status += chamCongNhanVienService.themChamCongNhanVien(ccnv) != null ? 1 : 0;
								}
								if (status != 0){
									ResponseDTO response = ResponseDTO.builder()
											.data(true)
											.build();
									json = AppUtils.GSON.toJson(response);
									byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
									dos.write(bytes);
									dos.flush();
								} else {
									ResponseDTO response = ResponseDTO.builder()
											.data(false)
											.build();
									json = AppUtils.GSON.toJson(response);
									byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
									dos.write(bytes);
									dos.flush();
								}
							}
						}
						break;
					}
					case "NhanVienForm": {
						switch (requestObject.getRequest()) {
							case "timKiemTatCaTrinhDo": {
								List<TrinhDo> trinhDos = trinhDoService.timKiemTatCaTrinhDo();
								ResponseDTO response = ResponseDTO.builder()
										.data(trinhDos)
										.build();
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "timKiemTatCaNhanVien": {
								List<NhanVien> nhanViens = nhanVienService.timKiemTatCaNhanVien();
								ResponseDTO response = ResponseDTO.builder()
										.data(nhanViens)
										.build();
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "themNhanVien": {
								NhanVien nhanVien = AppUtils.convert((Map<String, Object>) requestObject.getData(), NhanVien.class);
								NhanVien nhanVienThem = nhanVienService.themNhanVien(nhanVien);

								ResponseDTO response = ResponseDTO.builder()
										.data(nhanVienThem)
										.build();
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "capNhatNhanVien": {
								NhanVien nhanVien = AppUtils.convert((Map<String, Object>) requestObject.getData(), NhanVien.class);
								NhanVien nhanVienCapNhat = nhanVienService.capNhatNhanVien(nhanVien);

								ResponseDTO response = ResponseDTO.builder()
										.data(nhanVienCapNhat)
										.build();
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "capNhatTrangThaiNhanVien": {
								String maNhanVien = (String) requestObject.getData();
								boolean status = nhanVienService.capNhatTrangThaiNghiLamCuaNhanVien(maNhanVien);

								ResponseDTO response = ResponseDTO.builder()
										.data(status)
										.build();
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "timKiemNhanVien": {
								NhanVien nhanVien = AppUtils.convert((Map<String, Object>) requestObject.getData(), NhanVien.class);
								List<NhanVien> nhanViens = nhanVienService.timKiemNhanVien(nhanVien);

								ResponseDTO response = ResponseDTO.builder()
										.data(nhanViens)
										.build();
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
						}
						break;
					}
					case "ChucVuForm": {
						switch (requestObject.getRequest()) {
							case "timKiemTatCaChucVu": {
								List<ChucVu> chucVus = chucVuService.timKiemTatCaChucVu();
								ResponseDTO response = ResponseDTO.builder()
										.data(chucVus)
										.build();
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "themChucVu": {
								ChucVu chucVu = AppUtils.convert((Map<String, Object>) requestObject.getData(), ChucVu.class);
								ChucVu chucVuThem = chucVuService.themChucVu(chucVu);

								ResponseDTO response = ResponseDTO.builder()
										.data(chucVuThem)
										.build();
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "capNhatChucVu": {
								ChucVu chucVu = AppUtils.convert((Map<String, Object>) requestObject.getData(), ChucVu.class);
								ChucVu chucVuCapNhat = chucVuService.capNhatChucVu(chucVu);

								ResponseDTO response = ResponseDTO.builder()
										.data(chucVuCapNhat)
										.build();
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;

							}
							case "xoaChucVu": {
								ChucVu chucVu = AppUtils.convert((Map<String, Object>) requestObject.getData(), ChucVu.class);
								boolean status = chucVuService.xoaChucVuBangMa(chucVu.getMaChucVu());

								ResponseDTO response = ResponseDTO.builder()
										.data(status)
										.build();
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;

							}
						}
						break;
					}
					case "PhongBanForm": {
						switch (requestObject.getRequest()) {
							case "timKiemTatCaPhongBan": {
								List<PhongBan> phongBans = phongBanService.timKiemTatCaPhongBan();
//								phongBans.forEach(System.out::println);
								ResponseDTO response = ResponseDTO.builder()
										.data(phongBans)
										.build();

								json = AppUtils.GSON.toJson(response);
								System.out.println("Response: " + json);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "themPhongBan": {
								PhongBan phongBan = AppUtils.convert((Map<String, Object>) requestObject.getData(), PhongBan.class);
								PhongBan phongBanThem = phongBanService.themPhongBan(phongBan);

								ResponseDTO response = ResponseDTO.builder()
										.data(phongBanThem)
										.build();
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "capNhatPhongBan": {
								PhongBan phongBan = AppUtils.convert((Map<String, Object>) requestObject.getData(), PhongBan.class);
								PhongBan phongBanCapNhat = phongBanService.capNhatPhongBan(phongBan);

								ResponseDTO response = ResponseDTO.builder()
										.data(phongBanCapNhat)
										.build();
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "xoaPhongBan": {
								PhongBan phongBan = AppUtils.convert((Map<String, Object>) requestObject.getData(), PhongBan.class);
								phongBanService.capNhatTrangThaiPhongBan(phongBan.getMaPhongBan(), false);

								ResponseDTO response = ResponseDTO.builder()
										.data(null)
										.build();
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;

							}
							case "timKiemPhongBan": {
								PhongBan phongBan = AppUtils.convert((Map<String, Object>) requestObject.getData(), PhongBan.class);
								List<PhongBan> phongBans = phongBanService.timKiemPhongBan(phongBan);

								ResponseDTO response = ResponseDTO.builder()
										.data(phongBans)
										.build();
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;

							}
							case "timKiemNhanVienBangMaPhongBan": {
								String maPhongBan = (String) requestObject.getData();
								List<NhanVien> nhanViens = nhanVienService.timKiemNhanVienBangMaPhongBan(maPhongBan);

								ResponseDTO response = ResponseDTO.builder()
										.data(nhanViens)
										.build();
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
					case "ChamCongCongNhanForm": {
						switch (requestObject.getRequest()) {
							case "themNhieuChamCongCongNhan": {
								List<Map<String, Object>> data = (List<Map<String, Object>>) requestObject.getData();
								List<ChamCongCongNhan> chamCongCongNhans = data.stream()
										.map(map -> AppUtils.convert(map, ChamCongCongNhan.class))
										.toList();

								for (ChamCongCongNhan chamCongCongNhan : chamCongCongNhans) {
									chamCongCongNhanService.themChamCongCongNhan(chamCongCongNhan);
								}

								ResponseDTO response = ResponseDTO.builder()
										.data(true)
										.build();

								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "timTatCaCongNhanChuaChamCongTheoCaVaNgayChamCong": {
								Map<String, Object> data = (Map<String, Object>) requestObject.getData();
								List<CongNhan> congNhans = chamCongCongNhanService.timTatCaCongNhanChuaChamCongTheoCaVaNgayChamCong(
                                        LocalDate.parse(data.get("ngayChamCong").toString()),
										data.get("maCa").toString()
								);
								ResponseDTO response = ResponseDTO.builder()
										.data(congNhans)
										.build();

								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "timKiemTatCaChamCongCongNhan":  {
								LocalDate ngayChamCong = LocalDate.parse(requestObject.getData().toString());
								List<ChamCongCongNhan> chamCongCongNhans = chamCongCongNhanService.timKiemTatCaChamCongCongNhan(ngayChamCong);
								ResponseDTO response = ResponseDTO.builder()
										.data(chamCongCongNhans)
										.build();

								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "timTatCaPhanCongTheoMaCongNhanChuaHoanThanh": {
								String maCongNhan = (String) requestObject.getData();
								List<PhanCongCongNhan> phanCongCongNhans = phanCongCongViecService.timTatCaPhanCongTheoMaCongNhanChuaHoanThanh(maCongNhan);
								ResponseDTO response = ResponseDTO.builder()
										.data(phanCongCongNhans)
										.build();

								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "themChamCongCongNhan": {
								ChamCongCongNhan chamCongCongNhan = AppUtils.convert((Map<String, Object>) requestObject.getData(), ChamCongCongNhan.class);
								chamCongCongNhan = chamCongCongNhanService.themChamCongCongNhan(chamCongCongNhan);

								if (chamCongCongNhan != null) {
									PhanCongCongNhan phanCongCongNhan = phanCongCongViecService.timPhanCongTheoMa(chamCongCongNhan.getPhanCongCongNhan().getMaPhanCong());
									CongDoanSanPham  congDoanSanPham = phanCongCongNhan.getCongDoanSanPham();
									congDoanSanPhamService.capNhatSoLuongCanCuaCongDoan(congDoanSanPham.getMaCongDoan(), chamCongCongNhan.getSoLuongHoanThanh());
								}

								ResponseDTO response = ResponseDTO.builder()
										.data(chamCongCongNhan)
										.build();

								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "capNhatChamCongCongNhan": {
								Map<String, Object> data = (Map<String, Object>) requestObject.getData();
								boolean result = chamCongCongNhanService.capNhatChamCongCongNhan(data.get("maChamCong").toString(), Integer.parseInt(data.get("trangThai").toString().replace(".0", "")), Integer.parseInt(data.get("soLuongHoanThanh").toString().replace(".0", "")));
								ResponseDTO response = ResponseDTO.builder()
										.data(result)
										.build();

								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
						}

					}
					case "ChiTietLuongForm": {
						switch (requestObject.getRequest()) {
							case "chiTietLuongCongNhan": {
								Map<String, Object> data = (Map<String, Object>) requestObject.getData();
								int thang = Integer.parseInt(data.get("thang").toString().replace(".0", ""));
								int nam = Integer.parseInt(data.get("nam").toString().replace(".0", ""));
								String maCongNhan = data.get("maCongNhan").toString();
								List<Map<String, Object>> result = this.luongCongNhanService.timTatCaChiTietLuongTheoThangVaNam(maCongNhan, thang, nam);
								CongNhan congNhan = congNhanService.timKiemBangMaCongNhan(maCongNhan);
								Map<String, Object> dataResponse = new HashMap<>();
								dataResponse.put("congNhan", congNhan);
								dataResponse.put("danhSachChiTietLuong", result);
								// Send Response
								ResponseDTO response = ResponseDTO.builder()
										.data(dataResponse)
										.build();
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "chiTietLuongNhanVien" :{
								Map<String, Object> data = (Map<String, Object>) requestObject.getData();
								int thang = Integer.parseInt(data.get("thang").toString().replace(".0", ""));
								int nam = Integer.parseInt(data.get("nam").toString().replace(".0", ""));
								String maNhanVien = data.get("maNhanVien").toString();
								List<Map<String, Object>> result = this.luongNhanVienService.timTatCaChiTietLuongTheoThangVaNam(maNhanVien, thang, nam);
								NhanVien nhanVien = nhanVienService.timKiemBangMaNhanVien(maNhanVien);
								Map<String, Object> dataResponse = new HashMap<>();
								dataResponse.put("nhanVien", nhanVien);
								dataResponse.put("danhSachChiTietLuong", result);
								// Send Response
								ResponseDTO response = ResponseDTO.builder()
										.data(dataResponse)
										.build();
								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
						}
					}
					case "LuongCongNhanForm": {
						switch (requestObject.getRequest()) {
							case "thongKeLuongCongNhanBangThangVaNam": {
								Map<String, Object> data = (Map<String, Object>) requestObject.getData();
								data = this.luongCongNhanService.thongKeLuongCongNhanBangThangVaNam(Integer.parseInt(data.get("thang").toString().replace(".0", "")), Integer.parseInt(data.get("nam").toString().replace(".0", "")));

								// Send Response
								ResponseDTO response = ResponseDTO.builder()
										.data(data)
										.build();

								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "tinhLuongCongNhan": {
								Map<String, Object> data = (Map<String, Object>) requestObject.getData();

								boolean result = this.luongCongNhanService.tinhLuongCongNhan(Integer.parseInt(data.get("thang").toString().replace(".0", "")), Integer.parseInt(data.get("nam").toString().replace(".0", "")));
								List<Map<String, Object>> luongCongNhans = new ArrayList<>();
								if(result) {
									luongCongNhans = this.luongCongNhanService.timTatCaLuongCongNhanTheoThangVaNam(Integer.parseInt(data.get("thang").toString().replace(".0", "")), Integer.parseInt(data.get("nam").toString().replace(".0", "")));

								}
								Map<String, Object> dataResponse = new HashMap<>();
								dataResponse.put("result", result);
								dataResponse.put("luongCongNhans", luongCongNhans);
								// Send Response
								ResponseDTO response = ResponseDTO.builder()
										.data(dataResponse)
										.build();

								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "timTatCaLuongCongNhanTheoThangVaNam": {
								Map<String, Object> data = (Map<String, Object>) requestObject.getData();
								List<Map<String, Object>> result = this.luongCongNhanService.timTatCaLuongCongNhanTheoThangVaNam(Integer.parseInt(data.get("thang").toString().replace(".0", "")), Integer.parseInt(data.get("nam").toString().replace(".0", "")));

								// Send Response
								ResponseDTO response = ResponseDTO.builder()
										.data(result)
										.build();

								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "capNhatLuongThuong": {
								Map<String, Object> data = (Map<String, Object>) requestObject.getData();
								this.luongCongNhanService.capNhatLuongThuong(data.get("MaLuong").toString(), Double.parseDouble(data.get("LuongThuong").toString()));
								// send response
								ResponseDTO response = ResponseDTO.builder()
										.data(true)
										.build();
								dos.writeBytes(AppUtils.GSON.toJson(response));
								dos.flush();
								break;
							}
						}
					}
					case "LuongNhanVienForm": {
						switch (requestObject.getRequest()) {
							case "thongKeLuongNhanVienBangThangVaNam": {
								Map<String, Object> data = (Map<String, Object>) requestObject.getData();

								data = this.luongNhanVienService.thongKeLuongNhanVienBangThangVaNam(
										Integer.parseInt(data.get("thang").toString().replace(".0", "")),
										Integer.parseInt(data.get("nam").toString().replace(".0", "")));

								// Send Response
								ResponseDTO response = ResponseDTO.builder()
										.data(data)
										.build();

								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "tinhLuongNhanVien": {
								Map<String, Object> data = (Map<String, Object>) requestObject.getData();

								boolean result = this.luongNhanVienService.tinhLuongNhanVien(Integer.parseInt(data.get("thang").toString().replace(".0", "")), Integer.parseInt(data.get("nam").toString().replace(".0", "")));
								List<Map<String, Object>> luongNhanViens = new ArrayList<>();
								if(result) {
									luongNhanViens = this.luongNhanVienService.timKiemTatCaLuongNhanVienTheoThangVaNam(Integer.parseInt(data.get("thang").toString().replace(".0", "")), Integer.parseInt(data.get("nam").toString().replace(".0", "")));

								}
								Map<String, Object> dataResponse = new HashMap<>();
								dataResponse.put("result", result);
								dataResponse.put("luongNhanViens", luongNhanViens);
								// Send Response
								ResponseDTO response = ResponseDTO.builder()
										.data(dataResponse)
										.build();

								json = AppUtils.GSON.toJson(response);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "capNhatLuongThuong": {
								Map<String, Object> data = (Map<String, Object>) requestObject.getData();
								this.luongNhanVienService.capNhatLuongThuong(data.get("MaLuong").toString(), Double.parseDouble(data.get("LuongThuong").toString()));
								// send response
								ResponseDTO response = ResponseDTO.builder()
										.data(true)
										.build();
								dos.writeBytes(AppUtils.GSON.toJson(response));
								dos.flush();
								break;
							}
						}
					}
					case "TongQuatForm": {
						switch (requestObject.getRequest()) {
							case "thucHienChucNangLoadThongKeSoLuong": {

								Map<String, Object> data = new HashMap<>();
								data.put("soLuongSanPham", sanPhamService.tongSoLuongSanPham());
								data.put("soLuongCongNhan", congNhanService.tongSoLuongCongNhan());
								data.put("soLuongNhanVien", nhanVienService.tongSoLuongNhanVien());
								data.put("soLuongHopDong", hopDongService.tongSoLuongHopDong());

								ResponseDTO response = ResponseDTO.builder()
										.data(data)
										.build();

								System.out.println("Response: " + response);
								// Send Response
								json = AppUtils.GSON.toJson(response);
								dos.writeBytes(json);
								dos.flush();
								break;
							}
							case "thongKeLuongTheoNam": {
								Map<String, Map<String, Double>> data = new HashMap<>();
								data.put("luongCongNhans", luongCongNhanService.thongKeLuongCongNhanTheoNam());
								data.put("luongNhanViens", luongNhanVienService.thongKeLuongNhanVienTheoNam());

								ResponseDTO response = ResponseDTO.builder()
										.data(data)
										.build();

								System.out.println("Response: " + response);
								// Send Response
								json = AppUtils.GSON.toJson(response);
								dos.writeBytes(json);
								dos.flush();
								break;
							}

						}
					}
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
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
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
							case "timKiemTatCaSanPham": {
								List<SanPham> sanPhams = sanPhamService.timKiemTatCaSanPham();
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
							case "themSanPham": {
								SanPham sanPham = AppUtils.convert((Map<String, Object>) requestObject.getData(), SanPham.class);
								sanPham = sanPhamService.themSanPham(sanPham);
								ResponseDTO response = ResponseDTO.builder()
										.data(sanPham)
										.build();

								json = AppUtils.GSON.toJson(response);
								System.out.println("Response: " + json);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "capNhatTrangThaiSanPham": {
								Map<String, Object> data = (Map<String, Object>) requestObject.getData();
								String maSanPham = (String) data.get("maSanPham");
								boolean trangThai = (boolean) data.get("trangThai");
								boolean result = sanPhamService.capNhatTrangThaiSanPham(maSanPham, trangThai);

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
							case "capNhatSanPham": {
								SanPham sanPham = AppUtils.convert((Map<String, Object>) requestObject.getData(), SanPham.class);
								sanPham = sanPhamService.capNhatSanPham(sanPham);
								ResponseDTO response = ResponseDTO.builder()
										.data(sanPham)
										.build();

								json = AppUtils.GSON.toJson(response);
								System.out.println("Response: " + json);
								byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
								dos.write(bytes);
								dos.flush();
								break;
							}
							case "timKiemSanPham": {
								Map<String, Object> data = (Map<String, Object>) requestObject.getData();
								SanPham sanPham = AppUtils.convert(data, SanPham.class);
								List<SanPham> sanPhams = sanPhamService.timKiemSanPham(sanPham);
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
