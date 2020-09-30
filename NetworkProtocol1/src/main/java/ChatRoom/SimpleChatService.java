package ChatRoom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
/*服务器端网络服务*/
public class SimpleChatService {
	
	public interface OnSocketAcceptListener {
		void onSocketAccept(Socket socket,String name);
	}
	
	private  ClientManager clientManager = null;
	private  OnSocketAcceptListener onAcceptListener = null;
	private  ServerSocket serverSocket = null;
	private ChatSocket.ServerLog log=null;
	
	public SimpleChatService(ClientManager clientManager) {
		this.clientManager = clientManager;
	}
	
	public void setOnAcceptListener(OnSocketAcceptListener listener) {
		onAcceptListener = listener;
	}

	public void setLog(ChatSocket.ServerLog log) {
		this.log = log;
	}

	public void startup() {
		// TODO Auto-generated method stub
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				runStartup();
			}

		});
        thread.start();
	}
	
	public void shutdown() {
		try {
			clientManager.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	/*获取新连接的客户端，并添加到CLientManager中进行管理*/
	private void runStartup() {
		try {
			serverSocket = new ServerSocket(8765);
			while (true) {
				Socket socket = serverSocket.accept();
				DataInputStream inputStream =new DataInputStream(socket.getInputStream());
				String name= inputStream.readUTF();
				if (onAcceptListener != null) {
					onAcceptListener.onSocketAccept(socket,name);
				}
				
				clientManager.addClientSocket(socket,log);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
