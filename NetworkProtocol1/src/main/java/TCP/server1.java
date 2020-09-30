package TCP;

import boundary.TcpBoundary.serverFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class server1 {

    private StringBuilder record=new StringBuilder();
    DataInputStream dis;
    DataOutputStream dos;
    public static serverFrame serverframe;

    public server1(){
        serverFrame serverframe=new serverFrame();
        overridebtn();
        serverframe.initialization();

    }

    public void overridebtn(){
        // 为按钮添加事件
        serverframe.btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                System.out.println(11);
                String reply=serverframe.textAreaInput.getText();
                record.append("server : "+reply+"\r\n");
                System.out.println(getRecord());
                serverframe.textAreaRecord.setText(getRecord());
                try {
                    dos.writeUTF(reply); //发送给客户端
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public String getRecord() {
        return String.valueOf(record);
    }

    public static void main(String[] args) {
        new server1().startServer();
    }

    public void startServer() {
        try {

            System.out.println("--------------服务器-------------");
            record.append("--------服务器-------\r\n");
            //服务器在9990端口监听客户端的连接
            ServerSocket ss = new ServerSocket(9998);
            System.out.println("服务器正在监听...");
            record.append("服务器正在监听...\r\n");
            serverframe.textAreaRecord.setText(getRecord());
            while (true) {
                //阻塞的accept方法，当一个客户端连接上，才会返回Socket对象
                Socket s = ss.accept();
                System.out.println("客户端已登陆!");
                System.out.println("--------------------------");
                record.append("客户端已登陆!\r\n----------------\r\n");
                serverframe.textAreaRecord.setText(getRecord());
                //开启线程处理通信
                new CommunicateThread(s).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class CommunicateThread extends Thread {

        Socket socket;
//        DataInputStream dis;
//        DataOutputStream dos;

        public CommunicateThread(Socket socket) {
            this.socket = socket;
            try {
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /*创建线程进行监听客户端传来的数据*/
        @Override
        public void run() {
            super.run();
            //读取客户端发过来的消息
            String msg = null;
            try {
                while ((msg = dis.readUTF()) != null) {
                    System.out.println("client  ：" + msg);
//                    System.out.println("server  :");
                    record.append("client  ：" + msg+"\r\n");
                    serverframe.textAreaRecord.setText(getRecord());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
