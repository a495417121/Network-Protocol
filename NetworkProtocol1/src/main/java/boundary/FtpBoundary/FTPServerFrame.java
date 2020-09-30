package boundary.FtpBoundary;

import boundary.TcpBoundary.serverFrame;

import javax.swing.*;
import java.awt.*;

public class FTPServerFrame {
    public static serverFrame serverframe = new serverFrame();
//    public static JTextField textAreaInput = new JTextField(); //聊天输入框
    public static JTextArea textAreaRecord = new JTextArea();//聊天记录框

    public static void FTPserverFrame(){
        //画出窗口
        serverframe.setLayout(null);
        serverframe.setTitle("FTPServer");
        serverframe.setSize(550,500);
        serverframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置字体
        final Font font = new Font("宋体", Font.BOLD, 11);

        JLabel label1=new JLabel("记录：");
        label1.setBounds(20,40,50,40);
        label1.setVisible(true);
        label1.setFont(font);
        serverframe.add(label1);

        //设置文本框
        textAreaRecord.setBounds(75, 40, 660, 350);
        textAreaRecord.setVisible(true);
        serverframe.add(textAreaRecord);

        serverframe.setVisible(true);
    }
}
