package com.xhh.demo.http.other;

import java.net.*;

/**
 * Box Builder test
 *
 * @author tiger
 * @version 1.0.0 createTime: 15/5/6 下午3:53
 * @since 1.6
 */
public class BoxTest {

    public static void main(String[] args) {
        Box box = Box.builder().color("blue").name("box").build();

        int a = 10, b = 20;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);

        int c = 10, d = 20;
        c = c + d;
        d = c - d;
        c = c - d;
        System.out.println("c: " + c);
        System.out.println("d: " + d);

        try {
            System.out.println(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            Socket socket = new Socket();
            try {
                SocketAddress addr = new InetSocketAddress("192.178.106.224", 2181);
                socket.connect(addr, 1000);
                System.out.println(socket.getLocalAddress().getHostAddress());
            } finally {
                try {
                    socket.close();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
