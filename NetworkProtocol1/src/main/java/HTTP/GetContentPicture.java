package HTTP;

import boundary.HttpBoundary.HttpDownloadPict;
import jdk.nashorn.tools.Shell;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetContentPicture {

    public static HttpDownloadPict httpDownloadPict=new HttpDownloadPict();
    public static StringBuilder sb=new StringBuilder();

    public GetContentPicture(){
        httpDownloadPict.initialization();
        overridebtn();
    }

    public static void overridebtn(){
        httpDownloadPict.jButtonDownlOad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url=httpDownloadPict.jTextAreaInputUrl.getText();
                if(url==null || url.equals("")){
                    JOptionPane.showConfirmDialog(null,"图片下载失败","DownloadPict",JOptionPane.PLAIN_MESSAGE);
                }else {
                    try {
                        get(url);
                        httpDownloadPict.jTextAreaRecord.setText(sb.toString());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    JOptionPane.showConfirmDialog(null, "图片下载成功", "DownloadPict", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }

    public static void getHtmlPicture(String httpUrl) {
        URL url;
        BufferedInputStream in;
        FileOutputStream file;

        try {
            System.out.println("正在爬取网络图片");
            sb.append("正在爬取网络图片\r\n");
            String fileName = httpUrl.substring(httpUrl.lastIndexOf("/"));
            String filePath = "./src/main/java/HTTP/pic/";
//            String filePath = "E:\file\";

            url = new URL(httpUrl);
            //打开连接
            URLConnection con=url.openConnection();
            //设置超时时间
            con.setConnectTimeout(5*1000);
            //输入流
            in = new BufferedInputStream(url.openStream());
            byte[] by=new byte[1024];
            file = new FileOutputStream(new File(filePath+fileName));
            int len;
            while ((len = in.read(by)) != -1) {
                file.write(by,0,len);
            }
            file.close();
            in.close();

            System.out.println("图片获取成功");
            sb.append("图片获取成功\r\n");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*获取网页源码*/
    public static String getHtmlCode(String httpUrl) throws IOException {
        String content ="";
        URL uu = new URL(httpUrl); // 创建URL类对象
        BufferedReader ii = new BufferedReader(new InputStreamReader(uu
                .openStream())); // //使用openStream得到一输入流并由此构造一个BufferedReader对象
        String input;
        while ((input = ii.readLine()) != null) { // 建立读取循环，并判断是否有读取值
            content += input;
        }
        ii.close();
        return content;
    }
    public static Set<String> getImgStr(String htmlStr) {
        Set<String> pics = new HashSet<>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        //     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile
                (regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }
    public static void get(String url) throws IOException {

        String content = getHtmlCode(url);
        System.out.println(content);
        Set<String> srcList=getImgStr(content);
        for(String src:srcList){
            System.out.println(src.replace("//","https://"));
            sb.append(src.replace("//","https://")+"\r\n");
            getHtmlPicture(src.replace("//","https://"));
        }

    }
    public static void main(String[] args) throws IOException {
        httpDownloadPict.initialization();
        overridebtn();
    }
}
