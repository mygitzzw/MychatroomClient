package webclient.view;

import common.Massage;
import webclient.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class CilentChatJframe {
     public  JTextArea jTextArea_up,jTextArea_down;
     String userName,friend;
     JButton jb;

     public CilentChatJframe(String userName, String friend) {
         this.userName = userName;
         this.friend = friend;
     }

     public  JPanel createNorthPanel(JFrame jf){
        JPanel panel = new JPanel();
        //取消面板默认布局
        panel.setLayout(null);
        //设置面板尺寸
        panel.setPreferredSize(new Dimension(440,470));
        panel.setBounds(5,5,447,676);
        panel.setBackground(Color.orange);

         jTextArea_up = new JTextArea(90,60);
        Font x = new Font("宋体",0,16);
        jTextArea_up.setFont(x);
        jTextArea_up.setForeground(Color.ORANGE);
        jTextArea_up.setDisabledTextColor(Color.black);
        jTextArea_up.setBounds(0,0,430,460);
        jTextArea_up.setEnabled(false);
        JScrollPane jScrollPane = new JScrollPane(jTextArea_up);

        //取消文本域的横向滚动条
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setBounds(5,5,430,460);
        jScrollPane.setAutoscrolls(true);
        jScrollPane.setOpaque(false);
        panel.add(jScrollPane);

         jTextArea_down = new JTextArea(20, 60);
         jTextArea_down.setFont(x);
        jTextArea_down.setBounds(0,0,430,125);
        JScrollPane jScrollPane1 = new JScrollPane(jTextArea_down);
        //取消文本域的横向滚动条
        jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setBounds(5,477,430,125);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setOpaque(false);
        panel.add(jScrollPane1);


        String path5 = Thread.currentThread().getContextClassLoader().getResource("myimages/button3.jpg").getPath().substring(1);

        ImageIcon imagef = new ImageIcon(path5);
        JButton jb = new JButton("发送",imagef);
        jb.setFont(new Font("楷体",0,22));
        jb.setForeground(Color.black);
        jb.setBounds(355,605,80,40);
        jb.addActionListener(new JbActionlistner(jTextArea_down,jTextArea_up,userName,friend,this));

        jb.setHorizontalTextPosition(SwingConstants.CENTER);
        String pathh = Thread.currentThread().getContextClassLoader().getResource("myimages/button1.png").getPath().substring(1);
        jb.setRolloverIcon(new ImageIcon(pathh));
        jb.setFocusPainted(false);
        jb.setContentAreaFilled(false);
        jb.setBorderPainted(false);
        panel.add(jb);

         String pathe = Thread.currentThread().getContextClassLoader().getResource("myimages/button-2.jpg").getPath().substring(1);

         ImageIcon imagefg = new ImageIcon(pathe);
         JButton jbf = new JButton("返回",imagefg);
         jbf.setFont(new Font("楷体",0,22));
         jbf.setForeground(Color.black);
         jbf.setBounds(5,605,80,40);

         //对返回按钮进行监听
         jbf.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
//                 Socket socket = ManagerSocket.getSocket(userName);
//                 WebChatFriendlist ww = ManagerFirendList.getWebChatFriendlist(socket);
                 String friends[] = ManagerFrientUpdate.updateusers;
                 String oline = ManagerFrientUpdate.updateuser;

                 WebChatFriendlist listfeame = new WebChatFriendlist(userName,friends);
                 ManagerChatJFrame.addCilentChatJframe(ManagerSocket.getSocket(userName),null);
                 ManagerFirendList.addWebChatFriendlist(ManagerSocket.getSocket(userName),null);
                 ManagerFirendList.addWebChatFriendlist(ManagerSocket.getSocket(userName),listfeame);
//                 用此刻的好友在线列表信息对界面进行更新
                 listfeame.updateFriend(oline);

                 jf.dispose();
             }
         });

         jbf.setHorizontalTextPosition(SwingConstants.CENTER);
         String pathhf = Thread.currentThread().getContextClassLoader().getResource("myimages/button1.png").getPath().substring(1);
         jbf.setRolloverIcon(new ImageIcon(pathhf));
         jbf.setFocusPainted(false);
         jbf.setContentAreaFilled(false);
         jbf.setBorderPainted(false);
         panel.add(jbf);
        return panel;
    }
    public  JPanel CreateEastPanel(JFrame jf){
        JPanel panel = new JPanel();
        //取消面板默认布局
        panel.setLayout(null);
        //设置面板尺寸
        panel.setPreferredSize(new Dimension(353,686));
        panel.setBackground(Color.white);
        panel.setBounds(0,0,353,686);
        String path = Thread.currentThread().getContextClassLoader().getResource("myimages/chating_right.jpg").getPath().substring(1);

        ImageIcon image = new ImageIcon(path);
        //将图片添加到面板
        JLabel backgroud = new JLabel(image);
        //设置背景图片位置和尺寸
        backgroud.setBounds(0, 0, 353, 686);
        panel.add(backgroud);


        return panel;
    }


     }
