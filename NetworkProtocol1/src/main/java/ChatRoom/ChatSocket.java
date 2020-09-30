package ChatRoom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatSocket{
	public interface Callback {
		void onReadSocket(ChatSocket chatSocket, String msg);
		void onError(ChatSocket chatSocket, String error);
	}

	public interface ServerLog{
		void onLog(String msg);
	}
	private  DataInputStream inputStream = null;
	private  DataOutputStream outputStream = null;
	private  Callback callback = null;
	private ServerLog log=null;

	public DataInputStream getInputStream() {
		return inputStream;
	}

	public DataOutputStream getOutputStream() {
		return outputStream;
	}

	public ChatSocket(Socket socket, Callback callback,ServerLog log) {
		try {
			inputStream = new DataInputStream(socket.getInputStream());
			outputStream = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.callback = callback;
		this.log=log;
	}

	public void send(String send) { // 向客户端发送数据
		try {
			outputStream.writeUTF(send);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*和客户端相对应，负责监控指定客户端的输入流*/
	public void start() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				String accept = null;
				while (true) {
					try {
						accept = inputStream.readUTF();
						String[] s=accept.split("#");
						if(s[1].equals("ClOSE_CLIENT")){ //客户端发送关闭信号，服务器也关闭对应的socket
							log.onLog("客户端  "+s[0]+"//"+s[2]+"已断开连接");
							continue;
						}
						if (callback != null) {
							callback.onReadSocket(ChatSocket.this, accept);
							log.onLog(accept);
						}
					} catch (IOException e) {
						if (callback != null) {
							callback.onError(ChatSocket.this, e.getMessage());
						}
					}
				}
			}
		});
		thread.start();
	}

	public void close() throws IOException {
		if (inputStream != null) {
			inputStream.close();
		}
		if (outputStream != null) {
			outputStream.close();
		}
	}

}
