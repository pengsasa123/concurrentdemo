package com.test.concurrentdemo.nettyDemo.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 普通socket服务端
 */
public class Server {
    public static void main(String[] args){
        InputStreamReader isr;
        BufferedReader br;
        OutputStreamWriter osw;
        BufferedWriter bw;
        String str;
        Scanner in = new Scanner(System.in);
        try {
            /* 在本机的 8899 端口开放Server */
            ServerSocket server = new ServerSocket(8899);
            /* 只要产生连接，socket便可以代表所连接的那个物体，同时这个server.accept()只有产生了连接才会进行下一步操作。*/
            Socket socket = server.accept();
            /* 输出连接者的IP。*/
            System.out.println(socket.getInetAddress());
            System.out.println("建立了一个连接！");
            while (true) {
                isr = new InputStreamReader(socket.getInputStream());
                br = new BufferedReader(isr);
                System.out.println("客户端回复:" + br.readLine());
                osw = new OutputStreamWriter(socket.getOutputStream());
                bw = new BufferedWriter(osw);
                System.out.print("服务端回复:");
                str = in.nextLine();
                bw.write(str + "\n");
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
