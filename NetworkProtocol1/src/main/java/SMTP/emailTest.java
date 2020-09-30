package SMTP;

import boundary.SmtpBoundary.SMTPClientFrame;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class emailTest {
    private static String mailFrom = null;// 指明邮件的发件人
    private static String password_mailFrom = null;// 指明邮件的发件人登陆密码
    private static String mailTo = null; // 指明邮件的收件人
    private static String mailTitle = null;// 邮件的标题
    private static String mailText = null; // 邮件的文本内容
    private static String mail_host = null; // 邮件的服务器域名
    private static String photoSrc = null; // 发送图片的路径
    private static List<String> fileList = new ArrayList<String>(); // 发送附件的路径
    public static SMTPClientFrame smtpClientFrame=new SMTPClientFrame();
    public static String path=null;

    public emailTest(){
        smtpClientFrame.initialization();
        overridebtn();
    }

    public static void dirFile(){
        StringBuilder sb=new StringBuilder();
        Scanner sc = new Scanner(System.in);
        //创建Map集合保存文件的编号以及文件的路径
        Map<String, String> map =new HashMap<String, String>();
        System.out.println("---客户端上传文件---");
        System.out.println("文件编号:");
        //1:创建一个File文件
        while(path==null);
        File file = new File(path);
        smtpClientFrame.jTextAreaList.setText(path);
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
        smtpClientFrame.jTextAreaList.setText(sb.toString());
    }

    public static void overridebtn(){
        smtpClientFrame.btnchoosePict.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                final int returnVal = chooser.showOpenDialog(smtpClientFrame.btnchoosePict);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    final File file = chooser.getSelectedFile();
                    path=file.getAbsolutePath();
                    dirFile();
                }
            }
        });

        smtpClientFrame.btnchooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                final int returnVal = chooser.showOpenDialog(smtpClientFrame.btnchooseFile);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    final File file = chooser.getSelectedFile();
                    path=file.getAbsolutePath();
                    dirFile();
                }
            }
        });

        smtpClientFrame.btnsend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sendmail();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    public static void sendmail() throws Exception{

        mailFrom=smtpClientFrame.jTextAreaFrom.getText();
        password_mailFrom= String.valueOf(smtpClientFrame.jPasswordField.getPassword());
        mailTo=smtpClientFrame.jTextAreaTo.getText();
        mailTitle=smtpClientFrame.jTextAreaTitle.getText();
        mailText=smtpClientFrame.jTextAreaContent.getText();
        mail_host="smtp.qq.com";
        photoSrc=smtpClientFrame.jTextAreaPicture.getText();
        fileList.add(0,smtpClientFrame.jTextAreaFile.getText());

        Properties prop = new Properties();
        prop.setProperty("mail.host", mail_host);// 需要修改
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");

        // 使用JavaMail发送邮件的5个步骤
        // 1、创建session
        Session session = Session.getInstance(prop);
        // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        // 2、通过session得到transport对象
        Transport ts = session.getTransport();
        // 3、连上邮件服务器，需要发件人提供邮箱的用户名和密码进行验证
        ts.connect(mail_host, mailFrom, password_mailFrom);// 需要修改
        // 4、创建邮件
        Message message = createMixedMail(session);
        // 5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
        JOptionPane.showMessageDialog(null, "发送成功 ","E-mail" ,JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] args) throws Exception {
        smtpClientFrame.initialization();
        overridebtn();
    }

    /**
     * @Method: createMixedMail
     * @Description: 生成一封带附件和带图片的邮件
     */
    public static MimeMessage createMixedMail(Session session) throws Exception {
        MimeMessage message = new MimeMessage(session);
        // 设置邮件的基本信息
        message.setFrom(new InternetAddress(mailFrom));	// 发件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));// 收件人
        message.setSubject(mailTitle);
        // 正文
        MimeBodyPart text = new MimeBodyPart();
        text.setContent(mailText, "text/html;charset=UTF-8");
        // 图片
        MimeBodyPart image = new MimeBodyPart();
        image.setDataHandler(new DataHandler(new FileDataSource(photoSrc)));
        image.setContentID("1.jpg");
        // 附件1
        MimeBodyPart attach = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource(fileList.get(0)));
        attach.setDataHandler(dh);
        attach.setFileName(dh.getName());
        // 描述关系:正文和图片
        MimeMultipart mp1 = new MimeMultipart();
        mp1.addBodyPart(text);
        mp1.addBodyPart(image);
        mp1.setSubType("related");
        // 描述关系:正文和附件
        MimeMultipart mp2 = new MimeMultipart();
        mp2.addBodyPart(attach);
        // 代表正文的bodypart
        MimeBodyPart content = new MimeBodyPart();
        content.setContent(mp1);
        mp2.addBodyPart(content);
        mp2.setSubType("mixed");
        message.setContent(mp2);
        message.saveChanges();
        // 返回创建好的的邮件
        return message;
    }

}
