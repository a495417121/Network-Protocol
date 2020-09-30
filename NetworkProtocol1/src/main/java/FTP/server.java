package FTP;

import boundary.FtpBoundary.FTPServerFrame;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class server {

    public static StringBuilder sb;
    public static FTPServerFrame ftpServerFrame=new FTPServerFrame();

    public static String getSb() {
        return sb.toString();
    }

    /*
     * 服务器接受客户端上传的文件
     *  保存地址：当先项目下的file文件下
     *
     */
//    public server() throws IOException{
//        sb=new StringBuilder();
//        FTPServerFrame.FTPserverFrame();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss\r\n");
//        //1创建服务器端的套接字
//        ServerSocket ss = new ServerSocket(10086);
//        System.out.println("等待文件上传...");
//        sb.append("等待文件上传...\r\n");
//        ftpServerFrame.textAreaRecord.setText(getSb());
//        while(true){
//            //2:监听客户端的套接字，并且获取套接字
//            Socket s = ss.accept();
//            //3:通过客户端的套接字获取一个输入流
//            BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
//            //4:创建一个输出流
//            UUID uuid = UUID.randomUUID();
//            System.out.println(uuid);
//            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("E:\\file\\"+uuid.toString()+".txt"));
//            //5:读取流中内容
//            byte[] by = new byte[1024];
//            int num = 0;
//            while((num=bis.read(by))!=-1){
//                bos.write(by, 0, num);
//                bos.flush();
//            }
//
//            System.out.println("---------------");
//            System.out.println("上传成功");
//            sb.append("client upload a file："+uuid+" in "+df.format(new Date()));
//            ftpServerFrame.textAreaRecord.setText(getSb());
//            //给客户端一个响应 :上传成功
//            //获取一个输出流
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream())) ;
//            bw.write("上传成功");
//            bw.flush();
//            //5:关闭流
//            s.close();
//        }
//    }
    public static void main(String[] args) throws IOException {

            sb=new StringBuilder();
            FTPServerFrame.FTPserverFrame();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss\r\n");
            //1创建服务器端的套接字
            ServerSocket ss = new ServerSocket(10086);
            System.out.println("等待文件上传...");
            sb.append("等待文件上传...\r\n");
            ftpServerFrame.textAreaRecord.setText(getSb());
            while(true){
                //2:监听客户端的套接字，并且获取套接字
                Socket s = ss.accept();
                //3:通过客户端的套接字获取一个输入流
                BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
                //4:创建一个输出流
                UUID uuid = UUID.randomUUID();
                System.out.println(uuid);
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("./src/main/java/FTP/file/"+uuid.toString()+".txt"));
                //5:读取流中内容
                byte[] by = new byte[1024];
                int num = 0;
                while((num=bis.read(by))!=-1){
                    bos.write(by, 0, num);
                    bos.flush();
                }

                System.out.println("---------------");
                System.out.println("上传成功");
                sb.append("client upload a file："+uuid+" in "+df.format(new Date()));
                ftpServerFrame.textAreaRecord.setText(getSb());
                //给客户端一个响应 :上传成功
                //获取一个输出流
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream())) ;
                bw.write("上传成功");
                bw.flush();
                //5:关闭流
                s.close();
            }


    }
}
