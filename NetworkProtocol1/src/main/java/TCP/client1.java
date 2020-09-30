package TCP;

import boundary.TcpBoundary.clientFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class client1 {

    public clientFrame clientframe;
    private StringBuilder sb=new StringBuilder();
    public  DataInputStream dis;
    public  DataOutputStream dos;

    public client1(){
        clientFrame clientframe=new clientFrame();
        clientframe.btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String line=clientframe.textAreaInput.getText();
                try {
                    sb.append("clinet : "+line+"\r\n");
                    clientframe.textAreaRecord.setText(getSb());
                    dos.writeUTF(line);//发给服务端
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        clientFrame.initialization();
    }

    public String getSb() {
        return String.valueOf(sb);
    }

    public static void main(String[] args) {
        new client1().startClient();
    }

    public void startClient(){
        try {
            System.out.println("--------------客户端--------------");
            //连接到服务器
            System.out.println("正在连接服务器..");
            sb.append("---------客户端--------\r\n正在连接服务器..\r\n");
            clientframe.textAreaRecord.setText(getSb());
            Socket socket = new Socket("localhost", 9998);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            String line = null;
            System.out.println("服务器连接成功，开始对话");
            System.out.println("--------------------------");
            sb.append("服务器连接成功，开始对话\r\n-----------------\r\n");
            clientframe.textAreaRecord.setText(getSb());
            listenServerReply(dis);
//            System.out.println("client  :");
//            sb.append("client  :\r\n");
//            while((line = scanner.nextLine()) != null){//读取从键盘输入的一行
//                dos.writeUTF(line);//发给服务端
//                System.out.println("client  :");
////                System.out.println("client  : " + line);
//            }
        } catch (Exception e) {
            System.out.println("服务器连接失败!");
            e.printStackTrace();
        }
    }

    //监听服务端回复的消息
    public void listenServerReply(final DataInputStream dis){
        new Thread(){
            @Override
            public void run() {
                super.run();
                String line = null;
                try {
                    while((line = dis.readUTF()) != null){
                        System.out.println("server : " + line);
                        sb.append("server : " + line+"\r\n");
                        clientframe.textAreaRecord.setText(getSb());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
