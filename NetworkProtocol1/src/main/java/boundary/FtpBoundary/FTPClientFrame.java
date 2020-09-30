package boundary.FtpBoundary;

import boundary.TcpBoundary.clientFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FTPClientFrame extends JFrame {

    public static String path = null;
    public static clientFrame clientframe = new clientFrame();
    public static JTextArea textAreaInput = new JTextArea(); //文件路径框
    public static JTextArea textAreaList = new JTextArea(); //文件列表框
    public static JButton btnchoose=new JButton("选择文件");//选择文件按钮
    public static JButton btnsend=new JButton("发送");//发送按钮

    public void FTPclientFrame(){
        //窗口
        clientframe.setLayout(null);
        clientframe.setVisible(true);
        clientframe.setTitle("Client");
        clientframe.setSize(360,500);
        clientframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置字体
        final Font font = new Font("宋体", Font.BOLD, 11);

        //按钮
        btnchoose.setBounds(80, 290, 100, 25);
        btnsend.setBounds(200,290,60,25);
        btnsend.setVisible(true);
        btnchoose.setVisible(true);
        clientframe.add(btnsend);
        clientframe.add(btnchoose);

        //添加显示框
        JLabel label1=new JLabel("文件路径：");
        JLabel label2=new JLabel("文件列表");
        label1.setBounds(20,240,80,30);
        label2.setBounds(20,20,80,30);
        label1.setVisible(true);
        label2.setVisible(true);
        clientframe.add(label1);
        clientframe.add(label2);
        label1.setFont(font);

        //添加文本框
        textAreaInput.setBounds(80,240,210,35);
        textAreaList.setBounds(80,20,210,200);
        textAreaInput.setVisible(true);
        textAreaInput.setVisible(true);
        clientframe.add(textAreaInput);
        clientframe.add(textAreaList);

        clientframe.setVisible(true);
    }

    public static void main(String[] args) {

    }
}
