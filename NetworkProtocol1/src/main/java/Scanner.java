import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Scanner extends Thread{

    /*使用socket对象的connect方法，输入准备扫描的主机名和端口号，根据方法执行阶段是否抛出异常来判断该端口能否正确访问*/
    private static void  portScan(String domain,int startport,int endport){
        if (startport > endport){
            System.out.println("输入参数错误!");
            return;
        }
        LinkedList<Thread> threadPool = new LinkedList<Thread>();
        for (int i = startport ; i<endport ; i++ ){
            int port = i;
            Socket socket = new Socket();
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        socket.connect(new InetSocketAddress(domain,port));
                        System.out.println("端口"+port+":开放");
                    } catch (IOException e) {
                    }
                }
            };
            Thread th = new Thread(run);
            th.start();
            threadPool.add(th);
        }
    }

    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        portScan("192.168.43.82",100,10000);
        long end=System.currentTimeMillis();
        System.out.println("耗时："+(end-start)+"ms");
    }
}
