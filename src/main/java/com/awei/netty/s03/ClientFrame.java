package com.awei.netty.s03;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientFrame extends Frame {

    //用于Client使用实例调用TextArea回显数据
    public static final ClientFrame INSTANCE = new ClientFrame();

    //用于连接
    Client client = null;

    public TextArea ta = new TextArea();
    public TextField tf = new TextField();

    public ClientFrame() {
        this.setSize(600,400);
        this.setLocation(100,20);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.closeConnect();
                System.exit(0);
            }
        });

        this.add(ta,BorderLayout.CENTER);
        this.add(tf,BorderLayout.SOUTH);

        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendMsg(tf.getText());
                tf.setText("");
            }
        });
    }

    public void updateText(String msgAccepted) {
        this.ta.setText(ta.getText() + System.getProperty("line.separator") + msgAccepted);
    }

    private void connectToServer() {
        client = new Client();
        client.connect();
    }

    public static void main(String[] args) {
        ClientFrame frame = ClientFrame.INSTANCE;
        frame.setVisible(true);
        frame.connectToServer();
    }
}
