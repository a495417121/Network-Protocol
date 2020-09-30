package boundary.TcpBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class serverFrame extends JFrame {


    public static serverFrame serverframe = new serverFrame();
    public static JTextArea textAreaInput = new JTextArea(); //聊天输入框
    public static JTextArea textAreaRecord = new JTextArea();//聊天记录框
    public static JButton btnSend = new JButton("发送");

    public static void main(final String[] args) {
        // 画出窗口
        serverframe.setLayout(null);
        serverframe.setTitle("Server");
        serverframe.setSize(360, 500);
        serverframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 添加按钮
        btnSend.setBounds(80, 330, 60, 25);
        btnSend.setVisible(true);
        serverframe.add(btnSend);

//        // 设置字体
        final Font font = new Font("宋体", Font.BOLD, 12);
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
        serverframe.add(label1);
        serverframe.add(label2);

        // 添加文本框
//        final JTextField textAreaInput = new JTextField(); //聊天输入框
//        final JTextField textAreaRecord = new JTextField();//聊天记录框
        textAreaRecord.setBounds(75, 40, 220, 200);
        textAreaInput.setBounds(75, 270, 220, 50);
        textAreaInput.setVisible(true);
        textAreaRecord.setVisible(true);
        serverframe.add(textAreaInput);
        serverframe.add(textAreaRecord);
        serverframe.setVisible(true);

    }

    public  void initialization(){
        // 画出窗口
        serverframe.setLayout(null);
        serverframe.setTitle("Server");
        serverframe.setSize(360, 500);
        serverframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 添加按钮
        btnSend.setBounds(80, 330, 60, 25);
        btnSend.setVisible(true);
        serverframe.add(btnSend);

//        // 设置字体
        final Font font = new Font("宋体", Font.BOLD, 12);
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
        serverframe.add(label1);
        serverframe.add(label2);

        // 添加文本框
//        final JTextField textAreaInput = new JTextField(); //聊天输入框
//        final JTextField textAreaRecord = new JTextField();//聊天记录框
        textAreaRecord.setBounds(75, 40, 220, 200);
        textAreaInput.setBounds(75, 270, 220, 50);
        textAreaInput.setVisible(true);
        textAreaRecord.setVisible(true);
        serverframe.add(textAreaInput);
        serverframe.add(textAreaRecord);
        serverframe.setVisible(true);

    }
}
