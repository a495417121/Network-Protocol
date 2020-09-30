package UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class client {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = null;
        DatagramPacket rpacket = null;
        DatagramPacket spacket = null;

        System.out.println("以下为客户端的对话显示：");

        while (true) {
            System.out.print("客户端：");
            byte[] buf = new byte[256];
            socket = new DatagramSocket();
            BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
            String strtoserver = cin.readLine();
            InetAddress address = InetAddress.getLocalHost();
            spacket = new DatagramPacket(buf, buf.length, address, 6666);
            socket.send(spacket);

            rpacket = new DatagramPacket(buf, buf.length);
            socket.receive(rpacket);
            String strfromserver = new String(rpacket.getData());
            System.out.println("服务器：" + strfromserver);
            socket.close();
        }

    }
}
