package ChatRoom;

import javafx.scene.control.Alert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientView extends JFrame implements ActionListener, KeyListener {

    private JTextArea ChatList;   // 聊天内容区
    private JTextField MessageInput;   // 聊天输入框
    private JTextField NameInput;      // 用户名输入框
    private JButton btnSend;        // 发送按钮
    private JLabel labelNick;
    private JPanel jp1, jp2;

    private JScrollPane scrollPane;
    private JLabel labelHost;
    private JLabel labelPort;
    private JTextField  ServerHost;      // 服务器地址输入框
    private JTextField  ServerPort;      // 服务器端口输入框
    private JButton btnConnect;     // 连接/断开服务器按钮

    private NetworkService networkService;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSend) {
            // 发送按钮
            String name = NameInput.getText();
            String msg = MessageInput.getText();
            // 检查参数合法性
            if (name == null || msg == null || "".equals(name) || "".equals(msg)) {
                JOptionPane.showMessageDialog(this, "message", "title", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            // 发送消息
            networkService.sendMessage(name, msg);
        } else if (e.getSource() == btnConnect) {
            // 连接/断开按钮
            if(!networkService.isConnect()){
                // 未连接状态下，执行连接服务器操作
                String host=ServerHost.getText();
                int port=Integer.valueOf(ServerPort.getText());
                networkService.connect(host,port,NameInput.getText());
            }else{
                String name=NameInput.getText();
                String host=(ServerHost.getText().equals("localhost"))?"127.0.0.1":ServerHost.getText();
                System.out.println(host);
                networkService.disconnect(name,host);
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // 发送聊天消息
            String name = NameInput.getText();
            String msg = MessageInput.getText();
            // 检查参数合法性
            if (name == null || msg == null || "".equals(name) || "".equals(msg)) {
                return;
            }
            // 发送消息
            networkService.sendMessage(name, msg);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void initNetworkService(){
        networkService=new NetworkService();
        networkService.setCallback(new NetworkService.Callback() {
            @Override
            public void onConnected(String host, int port) {
                alert("连接", "成功连接到[" + host + ":" + port + "]");
                btnConnect.setText("断开");
            }

            @Override
            public void onConnectFailed(String host, int port) {
                alert("连接", "无法连接到[" + host + ":" + port + "]");
                btnConnect.setText("连接");
            }

            @Override
            public void onDisconnected() {
                alert("连接", "连接已断开");
                btnConnect.setText("连接");
            }

            @Override
            public void onMessageSent(String name, String msg) {
                MessageInput.setText("");
                ChatList.append("我("+ name + "):\r\n" + msg + "\r\n");
            }

            @Override
            public void onMessageReceived(String name, String msg) {
                ChatList.append(name + ":\r\n" + msg + "\r\n");
            }
        });
    }

    // 显示标题为title，内容为message的对话框
    private void alert(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void initView() {
        ChatList = new JTextArea(20, 20);
        ChatList.setEditable(false);

        scrollPane = new JScrollPane(ChatList);
        MessageInput = new JTextField(15);
        btnSend = new JButton("发送");

        jp1 = new JPanel();
        labelHost = new JLabel("主机地址");
        ServerHost = new JTextField(15);
        ServerHost.setText("localhost");
        labelPort = new JLabel("端口号");
        ServerPort = new JTextField(4);
        ServerPort.setText("8765");
        btnConnect = new JButton("连接");

        jp1.add(labelHost);
        jp1.add(ServerHost);
        jp1.add(labelPort);
        jp1.add(ServerPort);
        jp1.add(btnConnect);

        labelNick = new JLabel("昵称：");
        NameInput = new JTextField(8);
        jp2 = new JPanel();
        jp2.add(labelNick);
        jp2.add(NameInput);
        NameInput.setText("用户0");
        jp1.setLayout(new FlowLayout(FlowLayout.CENTER));
        jp2.add(MessageInput);
        jp2.add(btnSend);
        jp2.setLayout(new FlowLayout(FlowLayout.CENTER));

        add(jp1, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(jp2, BorderLayout.SOUTH);
        setTitle("聊天室");
        setSize(500, 500);
        setLocation(450, 150);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 当光标定位在聊天输入框时监听回车键按下事件
        MessageInput.addKeyListener(this);
        // 为发送按钮增加鼠标点击事件监听
        btnSend.addActionListener(this);
        // 为连接按钮增加鼠标点击事件监听
        btnConnect.addActionListener(this);
        // 当窗口关闭时触发
        addWindowListener(new WindowAdapter() { // 窗口关闭后断开连接
            @Override
            public void windowClosing(WindowEvent e) {
                networkService.disconnect(NameInput.getText(),ServerHost.getText()=="localhost"?"127.0.0.1":ServerHost.getText());
            }
        });
    }
    public ClientView() {
        initView();
        initNetworkService();
    }
    public static void main(String[] args) {
        ClientView view = new ClientView();
    }
}
