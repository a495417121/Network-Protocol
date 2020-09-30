package FTP;

import boundary.FtpBoundary.FTPClientFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class client {

    public static FTPClientFrame ftpClientFrame=new FTPClientFrame();
    public static StringBuilder sb=new StringBuilder();
    public static String path=null;

    public client(){
        ftpClientFrame.FTPclientFrame();
        overrbtn();
    }

    public static void overrbtn(){
        ftpClientFrame.btnchoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                final int returnVal = chooser.showOpenDialog(ftpClientFrame.btnchoose);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    final File file = chooser.getSelectedFile();
                    ftpClientFrame.path = file.getAbsolutePath();
                    dirFile();
//                    ftpClientFrame.textAreaInput.setText(ftpClientFrame.path);
                }
            }
        });

        ftpClientFrame.btnsend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    upload();
                    JOptionPane.showConfirmDialog(null,"文件上传成功","FtpUpload",JOptionPane.PLAIN_MESSAGE);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
    /**
     * 遍历需要上传的文件
     */
    public static void dirFile(){
        StringBuilder sb=new StringBuilder();
        Scanner sc = new Scanner(System.in);
        //创建Map集合保存文件的编号以及文件的路径
        Map<String, String> map =new HashMap<String, String>();
        System.out.println("---客户端上传文件---");
        System.out.println("文件编号:");
        //1:创建一个File文件
//        File file = new File("D:/工具");
        while(ftpClientFrame.path==null);
        File file = new File(ftpClientFrame.path);
        ftpClientFrame.textAreaList.setText(ftpClientFrame.path);
        //2:遍历File的目录
        File[] listFiles = file.listFiles();
        int count = 1;
        for (File file2 : listFiles) {
            if(file2.isFile()){
                System.out.println((count)+":"+file2.getAbsolutePath());
                //保存编号和文件名
                map.put(String.valueOf(count), file2.getAbsolutePath());
                sb.append((count)+":"+file2.getAbsolutePath()+"\r\n");
                count++;
            }
        }
        ftpClientFrame.textAreaList.setText(sb.toString());
    }


    public static void upload() throws IOException {
        //1:获取文件的路径
//        String path ="E:/网络协议工程/test/要求.txt";
        path=ftpClientFrame.textAreaInput.getText();
        String newpath=path.replace("\\","/");
        System.out.println(newpath);
//        String newpath="E:/网络协议工程/test/要求.txt";

        //2:创建字节缓冲输入流
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(newpath));
        //3:创建客户端的套接字
        Socket s = new Socket("127.0.0.1", 10086);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream())) ;
        bw.write("client");
        bw.flush();
        //4:通过套接字获取输出流
        BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());
        //5:一边读取，一边写入
        byte [] by = new byte[1024];
        int num = 0;
        while((num=bis.read(by))!=-1){
            //把文件写入服务器
            bos.write(by, 0, num);
            bos.flush();
        }
        //通知服务器客户端写入完毕
        s.shutdownOutput();
        //获取一个输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String info = br.readLine();
        System.out.println(info);

        //6:关闭流和套接字
        bis.close();
        s.close();
    }


    public static void  main(String[] args) {
        ftpClientFrame.FTPclientFrame();
        overrbtn();
    }
}
