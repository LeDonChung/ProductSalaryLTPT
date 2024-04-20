package com.product.salary.application;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetInetAddress {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress myIP= InetAddress.getLocalHost();
        System.out.println("My IP Address is:" + myIP.getHostAddress());
    }
}
