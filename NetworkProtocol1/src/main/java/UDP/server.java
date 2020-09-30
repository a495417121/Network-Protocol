package UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class server {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket=null;
        DatagramPacket rpacket=null;
        DatagramPacket spacket=null;

        System.out.println("等待接收数据包");
        System.out.println("以下为服务器端的对话显示：");
        while (true){
            byte[] buf=new byte[256];
            socket =new DatagramSocket(6666);
            rpacket=new DatagramPacket(buf,buf.length);
            socket.receive(rpacket);
            String strFromClient=new String(rpacket.getData());//buf1,0,rpacket.getLength()
            System.out.println("客户端："+strFromClient);

            System.out.print("服务器端：");
            BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
            String strtoclient=sin.readLine();
            buf=strtoclient.getBytes();
            InetAddress address=rpacket.getAddress();
            int port=rpacket.getPort();
            spacket=new DatagramPacket(buf,buf.length,address,port);
            socket.send(spacket);
            socket.close();
        }

    }
}
