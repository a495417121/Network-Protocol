package boundary.TcpBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class clientFrame extends JFrame {

    public static  clientFrame clientframe = new clientFrame();
    public static JButton btnSend = new JButton("发送");
    public static JTextArea textAreaInput = new JTextArea(); //聊天输入框
    public static JTextArea textAreaRecord = new JTextArea();//聊天记录框

    public static void main(final String[] args) {

        // 画出窗口

        clientframe.setLayout(null);
        clientframe.setTitle("client");
        clientframe.setSize(360, 500);
        clientframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 添加按钮
//        final JButton btnSend = new JButton("发送");
//        final JButton btnConn = new JButton("连接服务器");
        btnSend.setBounds(80, 330, 60, 25);
        btnSend.setVisible(true);
        clientframe.add(btnSend);

        // 设置字体
        final Font font = new Font("宋体", Font.BOLD, 11);
        btnSend.setFont(font);

        // 添加显示框
        final JLabel label1 = new JLabel("请输入:");
        final JLabel label2 = new JLabel("记录 :");
        label1.setBounds(20, 270, 50, 25);
        label2.setBounds(20, 40, 50, 40);
        label1.setVisible(true);
        label2.setVisible(true);
        label1.setFont(font);
        label2.setFont(font);
        clientframe.add(label1);
        clientframe.add(label2);

        // 添加文本框
//        final JTextArea textAreaInput = new JTextArea(); //聊天输入框
//        final JTextArea textAreaRecord = new JTextArea();//聊天记录框
        textAreaRecord.setBounds(75, 40, 220, 200);
        textAreaInput.setBounds(75, 270, 220, 50);
        textAreaInput.setVisible(true);
        textAreaRecord.setVisible(true);
        clientframe.add(textAreaInput);
        clientframe.add(textAreaRecord);
        clientframe.setVisible(true);

        }
    public static void initialization() {
        // 画出窗口

        clientframe.setLayout(null);
        clientframe.setTitle("client");
        clientframe.setSize(360, 500);
        clientframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 添加按钮
//        final JButton btnSend = new JButton("发送");
//        final JButton btnConn = new JButton("连接服务器");
        btnSend.setBounds(80, 330, 60, 25);
        btnSend.setVisible(true);
        clientframe.add(btnSend);

//        // 设置字体
        final Font font = new Font("宋体", Font.BOLD, 11);
        btnSend.setFont(font);

        // 添加显示框
        final JLabel label1 = new JLabel("请输入:");
        final JLabel label2 = new JLabel("记录 :");
        label1.setBounds(20, 270, 50, 25);
        label2.setBounds(20, 40, 50, 40);
        label1.setVisible(true);
        label2.setVisible(true);
        label1.setFont(font);
        label2.setFont(font);
        clientframe.add(label1);
        clientframe.add(label2);

        // 添加文本框
//        final JTextArea textAreaInput = new JTextArea(); //聊天输入框
//        final JTextArea textAreaRecord = new JTextArea();//聊天记录框
        textAreaRecord.setBounds(75, 40, 220, 200);
        textAreaInput.setBounds(75, 270, 220, 50);
        textAreaInput.setVisible(true);
        textAreaRecord.setVisible(true);
        clientframe.add(textAreaInput);
        clientframe.add(textAreaRecord);
        clientframe.setVisible(true);
    }
}
