package ChatRoom;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


/*客户端网络服务*/
public class NetworkService {
    public interface Callback {
        void onConnected(String host, int port);        //连接成功
        void onConnectFailed(String host, int port);    //连接失败
        void onDisconnected();                          //已经断开连接
        void onMessageSent(String name, String msg);    //消息已经发出
        void onMessageReceived(String name, String msg);//收到消息
    }

    private  Callback callback;
    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    // 套接字对象
    private  Socket socket = null;
    // 套接字输入流对象，读取收到的消息
    private  DataInputStream inputStream = null;
    // 套接字输出流对象，发送聊天消息
    private  DataOutputStream outputStream = null;
    // 当前连接状态
    private  boolean isConnected = false;

    /*连接到服务器*/
    public void connect(String host,int port,String name){
        try {
            //创建套接字，和服务器建立连接
            socket=new Socket(host,port);
            isConnected=true;
            outputStream=new DataOutputStream(socket.getOutputStream());
            outputStream.writeUTF(name);
            outputStream.flush();
            //通知外界已建立连接
            if(callback!=null){
                callback.onConnected(host,port);
            }
            //创建线程负责监听是否有消息到来
            beginListening();
        } catch (IOException e) {
            isConnected=false;
            if(callback!=null){
                callback.onConnectFailed(host,port);
            }
            e.printStackTrace();
        }
    }
    /*监听其他客户端传来的数据*/
    private void beginListening(){
        Runnable listening=new Runnable() {
            @Override
            public void run() {
                try {
                    inputStream=new DataInputStream(socket.getInputStream());
                    while(true){
                        String[] s=inputStream.readUTF().split("#");
                        if(callback!=null){
                            callback.onMessageReceived(s[0],s[1]);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
        new Thread(listening).start();
    }

    /*断开与服务器的连接*/
    public void disconnect(String name,String host){
        try {
            outputStream=new DataOutputStream(socket.getOutputStream());
            outputStream.writeUTF(name+"#ClOSE_CLIENT#"+host);
            outputStream.flush();
            if(socket!=null) socket.close();
            if(inputStream!=null) inputStream.close();
            if(outputStream!=null) outputStream.close();
            isConnected=false;
            //通知外界断开连接
            if(callback!=null) callback.onDisconnected();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*发送信息*/
    public void sendMessage(String name,String content){
        if(name==null || content==null || name.equals("") || content.equals("")){
            return ;
        }
        if(socket==null){
            return ;
        }
        try {
            //将消息写入socket的输出流
            outputStream=new DataOutputStream(socket.getOutputStream());
            outputStream.writeUTF(name+"#"+content);
            outputStream.flush();
            System.out.println(name+"#"+content);
            if(callback!=null){
                callback.onMessageSent(name,content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*检测是否已连接到服务器，true为已连接,false为未连接*/
    public boolean isConnect(){
        return isConnected;
    }


}
