package com.product.salary.application.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.product.salary.application.entity.NhanVien;
import com.product.salary.application.utils.AppUtils;
import com.product.salary.application.utils.RequestDTO;
import com.product.salary.application.utils.ResponseDTO;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (var socket = new Socket("localhost", 23863);
             var scanner = new Scanner(System.in);
             var out = new ObjectOutputStream(socket.getOutputStream());
             var in = new ObjectInputStream(socket.getInputStream())) {
            int choice = 0;
            System.out.println("Connected to server");
            RequestDTO request = null;
            while (true) {
                System.out.println("1. Get All Nhan Vien.");
                System.out.println("2. Get All Cong Nhan.");
                System.out.println("3. Get All LuongNhanVien bang ma nhan vien.");

                System.out.println("Enter your option: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        request = new RequestDTO();
                        request.setRequestType("NhanVien");
                        request.setRequest("getAll");
                        request.setData(new Object());

                        out.writeUTF(AppUtils.GSON.toJson(request));
                        out.flush();

                        String json = new String(in.readAllBytes());
                        System.out.println(json);
                        ResponseDTO response = AppUtils.GSON.fromJson(json, ResponseDTO.class);

                        System.out.println(response);
                        break;
                    case 3: {
                        String maNhanVien = scanner.nextLine();
                        request = new RequestDTO();
                        request.setRequestType("NhanVien");
                        request.setRequest("getLuongNhanVienByMaNhanVien");
                        request.setData(maNhanVien);


                        out.writeUTF(AppUtils.GSON.toJson(request));
                        out.flush();


                        json = new String(in.readAllBytes());
                        System.out.println(json);
                        response = AppUtils.GSON.fromJson(json, ResponseDTO.class);
                        System.out.println(response);
                        break;
                    }
                    default:
                        break;
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
