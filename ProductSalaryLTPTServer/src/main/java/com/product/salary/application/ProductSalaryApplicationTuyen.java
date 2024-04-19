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

public class ProductSalaryApplicationTuyen {
    private final static ResourceBundle BUNDLE = ResourceBundle.getBundle("app");


    public static void main(String[] args) throws IOException {
        try (
                var sever = new ServerSocket(
                        Integer.parseInt(BUNDLE.getString("server.port"))
                );
        ) {
            System.out.println("Server is running on port 23862");
            while (true) {
                Socket socket = sever.accept();
                System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());

                ProductSalaryApplicationTuyen serverTemp = new ProductSalaryApplicationTuyen();
                new Thread(serverTemp.new handlerClient(socket)).start();
            }
        }
    }

    private class handlerClient implements Runnable {
        private Socket socket;
        private final AccountService accountService;
        private final HopDongService hopDongService;
        private final PhongBanService phongBanService;
        private final ChucVuService chucVuService;
        private final TrinhDoService trinhDoService;
        private final NhanVienService nhanVienService;

        public handlerClient(Socket socket) {
            this.socket = socket;
            this.trinhDoService = new TrinhDoServiceImpl();
            this.accountService = new AccountServiceImpl();
            this.hopDongService = new HopDongServiceImpl();
            this.phongBanService = new PhongBanServiceImpl();
            this.chucVuService = new ChucVuServiceImpl();
            this.nhanVienService = new NhanVienServiceImpl();
        }


        @Override
        public void run() {
            try {
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

//                                System.out.println("Response: " + response);
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
