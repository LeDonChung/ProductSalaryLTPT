package com.product.salary.application.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.product.salary.application.ProductSalaryApplication;
import com.product.salary.application.entity.NhanVien;
import com.product.salary.application.service.NhanVienService;
import com.product.salary.application.service.impl.NhanVienServiceImpl;
import com.product.salary.application.utils.AppUtils;
import com.product.salary.application.utils.RequestDTO;
import com.product.salary.application.utils.ResponseDTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        try(
                var sever = new ServerSocket(
                        23863
                );
        ) {
            System.out.println("Server is running on port 23863");
            while(true) {
                Socket socket = sever.accept();
                System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());

                Server serverTemp = new Server();
                new Thread(serverTemp.new handlerClient(socket)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private class handlerClient implements Runnable {
        private final Socket socket;
        private final NhanVienService nhanVienService;
        private final Gson gson
                = new Gson();
        public handlerClient(Socket socket) {
            this.socket = socket;
            this.nhanVienService = new NhanVienServiceImpl();
        }

        @Override
        public void run() {
            try(var ois = new ObjectInputStream(socket.getInputStream());
                var oos = new ObjectOutputStream(socket.getOutputStream())) {

                String json = ois.readUTF();
                System.out.println("Server received: " + json);

                RequestDTO request = AppUtils.GSON.fromJson(json, RequestDTO.class);
                System.out.println(request);
                switch (request.getRequestType()) {
                    case "NhanVien": {
                        switch (request.getRequest()) {
                            case "getAll": {

                                List<NhanVien> nhanViens = nhanVienService.timKiemTatCaNhanVien();
                                ResponseDTO response = new ResponseDTO();
                                response.setData(nhanViens);
                                response.setResponseType("");
                                response.setResponse("");

                                String jsonS = AppUtils.GSON.toJson(response);
                                System.out.println("Server response: " + jsonS);
                                oos.writeBytes(jsonS);
                                oos.flush();
                                break;
                            }
                            case "getLuongNhanVienByMaNhanVien": {

                                String maNhanVien = request.getData().toString();
                                NhanVien nhanVien = nhanVienService.timKiemBangMaNhanVien(maNhanVien);


                                ResponseDTO response = new ResponseDTO();
                                response.setData(nhanVien);
                                response.setResponseType("");
                                response.setResponse("");

                                String jsonS = AppUtils.GSON.toJson(response);
                                System.out.println("Server response: " + jsonS);
                                oos.writeBytes(jsonS);
                                oos.flush();
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                        break;

                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            };

        }
    }
}
