package boundary.HttpBoundary;

import javax.swing.*;
import java.awt.*;

public class HttpDownloadPict {
    public static JFrame jFrame=new JFrame();
    public static JTextArea jTextAreaRecord=new JTextArea();//显示图片的下载情况
    public static JTextArea jTextAreaInputUrl=new JTextArea();//网站输入框
    public static JButton jButtonDownlOad=new JButton("开始下载");//下载按钮
    public void initialization(){
        //窗口
        jFrame.setLayout(null);
        jFrame.setTitle("DownloadPict");
        jFrame.setSize(360,500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font=new Font("宋体",Font.BOLD,11);

        //标签
        JLabel label1=new JLabel("下载记录:");
        JLabel label2=new JLabel("网站链接:");
        label1.setBounds(20,20,65,25);
        label2.setBounds(20,275,65,25);
        label1.setVisible(true);
        label2.setVisible(true);
        jFrame.add(label1);
        jFrame.add(label2);

        //文本框
        jTextAreaRecord.setBounds(95,20,195,245);
        jTextAreaInputUrl.setBounds(95,275,195,25);
        jTextAreaRecord.setVisible(true);
        jTextAreaInputUrl.setVisible(true);
        jFrame.add(jTextAreaRecord);
        jFrame.add(jTextAreaInputUrl);

        //按钮
        jButtonDownlOad.setBounds(125,310,105,25);
        jButtonDownlOad.setVisible(true);
        jFrame.add(jButtonDownlOad);

        jFrame.setVisible(true);
    }
}
