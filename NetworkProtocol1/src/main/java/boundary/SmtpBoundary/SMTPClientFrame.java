package boundary.SmtpBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SMTPClientFrame {
    public static JFrame jFrame=new JFrame();
    public static JTextArea jTextAreaFrom=new JTextArea();
    public static JPasswordField jPasswordField=new JPasswordField();
    public static JTextArea jTextAreaTo=new JTextArea();
    public static JTextArea jTextAreaContent=new JTextArea();
    public static JTextArea jTextAreaTitle=new JTextArea();
    public static JTextArea jTextAreaPicture=new JTextArea();
    public static JTextArea jTextAreaFile=new JTextArea();
    public static JTextArea jTextAreaList=new JTextArea();
    public static JButton btnsend=new JButton("发送");
    public static JButton btnchoosePict=new JButton("选择图片..");
    public static JButton btnchooseFile=new JButton("选择文件..");

    public void initialization(){
        //画出窗口
        jFrame.setLayout(null);
        jFrame.setTitle("E-mail User");
        jFrame.setSize(390,640);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font=new Font("宋体",Font.BOLD,11);

        //设置按钮
        btnsend.setVisible(true);
        btnsend.setBounds(190,340,70,35);
        btnchoosePict.setBounds(270,270,70,25);
        btnchooseFile.setBounds(270,305,70,25);
        jFrame.add(btnsend);
        jFrame.add(btnchoosePict);
        jFrame.add(btnchooseFile);

        //标签
        JLabel label1 = new JLabel("发送方:");
        JLabel label2 = new JLabel("密码 :");
        JLabel label3 = new JLabel("收件方:");
        JLabel label4 = new JLabel("标题 :");
        JLabel label5 = new JLabel("内容 :");
        JLabel label6 = new JLabel("图片 :");
        JLabel label7 = new JLabel("附件 :");
        JLabel label8 = new JLabel("文件列表 :");
        label1.setBounds(20, 20, 50, 25);
        label2.setBounds(20, 55, 50, 25);
        label3.setBounds(20, 90, 50, 25);
        label4.setBounds(20, 125, 50, 25);
        label5.setBounds(20, 160, 50, 25);
        label6.setBounds(20, 270, 50, 25);
        label7.setBounds(20, 305, 50, 25);
        label8.setBounds(20, 385, 65, 25);
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label4.setVisible(true);
        label5.setVisible(true);
        label6.setVisible(true);
        label7.setVisible(true);
        label8.setVisible(true);
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        label5.setFont(font);
        label6.setFont(font);
        label7.setFont(font);
        label8.setFont(font);
        jFrame.add(label1);
        jFrame.add(label2);
        jFrame.add(label3);
        jFrame.add(label4);
        jFrame.add(label5);
        jFrame.add(label6);
        jFrame.add(label7);
        jFrame.add(label8);

        //文本框
        jTextAreaFrom.setBounds(80,20,180,25);
        jPasswordField.setBounds(80,55,180,25);
        jTextAreaTo.setBounds(80,90,180,25);
        jTextAreaTitle.setBounds(80,125,180,25);
        jTextAreaContent.setBounds(80,160,180,100);
        jTextAreaPicture.setBounds(80,270,180,25);
        jTextAreaFile.setBounds(80,305,180,25);
        jTextAreaList.setBounds(95,385,220,200);
        jTextAreaFrom.setVisible(true);
        jPasswordField.setVisible(true);
        jTextAreaTo.setVisible(true);
        jTextAreaTitle.setVisible(true);
        jTextAreaContent.setVisible(true);
        jTextAreaPicture.setVisible(true);
        jTextAreaFile.setVisible(true);
        jTextAreaList.setVisible(true);
        jFrame.add(jTextAreaFrom);
        jFrame.add(jPasswordField);
        jFrame.add(jTextAreaTo);
        jFrame.add(jTextAreaTitle);
        jFrame.add(jTextAreaContent);
        jFrame.add(jTextAreaPicture);
        jFrame.add(jTextAreaFile);
        jFrame.add(jTextAreaList);

        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        //画出窗口
        jFrame.setLayout(null);
        jFrame.setTitle("E-mail User");
        jFrame.setSize(390,500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font=new Font("宋体",Font.BOLD,11);

        //设置按钮
        btnsend.setVisible(true);
        btnsend.setBounds(190,340,70,35);
        btnchoosePict.setBounds(270,270,70,25);
        btnchooseFile.setBounds(270,305,70,25);
        jFrame.add(btnsend);
        jFrame.add(btnchoosePict);
        jFrame.add(btnchooseFile);

        //文本框
        JLabel label1 = new JLabel("发送方:");
        JLabel label2 = new JLabel("密码 :");
        JLabel label3 = new JLabel("收件方:");
        JLabel label4 = new JLabel("标题 :");
        JLabel label5 = new JLabel("内容 :");
        JLabel label6 = new JLabel("图片 :");
        JLabel label7 = new JLabel("附件 :");
        label1.setBounds(20, 20, 50, 25);
        label2.setBounds(20, 55, 50, 25);
        label3.setBounds(20, 90, 50, 25);
        label4.setBounds(20, 125, 50, 25);
        label5.setBounds(20, 160, 50, 25);
        label6.setBounds(20, 270, 50, 25);
        label7.setBounds(20, 305, 50, 25);
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label4.setVisible(true);
        label5.setVisible(true);
        label6.setVisible(true);
        label7.setVisible(true);
        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);
        label4.setFont(font);
        label5.setFont(font);
        label6.setFont(font);
        label7.setFont(font);
        jFrame.add(label1);
        jFrame.add(label2);
        jFrame.add(label3);
        jFrame.add(label4);
        jFrame.add(label5);
        jFrame.add(label6);
        jFrame.add(label7);

        //标签
        jTextAreaFrom.setBounds(80,20,180,25);
        jPasswordField.setBounds(80,55,180,25);
        jTextAreaTo.setBounds(80,90,180,25);
        jTextAreaTitle.setBounds(80,125,180,25);
        jTextAreaContent.setBounds(80,160,180,100);
        jTextAreaPicture.setBounds(80,270,180,25);
        jTextAreaFile.setBounds(80,305,180,25);
        jTextAreaFrom.setVisible(true);
        jPasswordField.setVisible(true);
        jTextAreaTo.setVisible(true);
        jTextAreaTitle.setVisible(true);
        jTextAreaContent.setVisible(true);
        jTextAreaPicture.setVisible(true);
        jTextAreaFile.setVisible(true);
        jFrame.add(jTextAreaFrom);
        jFrame.add(jPasswordField);
        jFrame.add(jTextAreaTo);
        jFrame.add(jTextAreaTitle);
        jFrame.add(jTextAreaContent);
        jFrame.add(jTextAreaPicture);
        jFrame.add(jTextAreaFile);

        jFrame.setVisible(true);

    }
}
