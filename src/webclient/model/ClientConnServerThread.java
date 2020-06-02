package webclient.model;

import common.Massage;
import common.MassageType;
import webclient.view.CilentChatJframe;
import webclient.view.WebChatFriendlist;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Iterator;

/**
 * @author 郑志伟
 * @create 2020-05-26 22:36
 * 这是客户端和服务器端获取链接的线程
 */
public class ClientConnServerThread extends Thread {
    private Socket s;
    private String username;

    public ClientConnServerThread(String username,Socket s) {
        this.username = username;
        this.s = s;
    }


    public Socket getSocket() {
        return s;
    }

    @Override
    public void run() {
        while (true) {
            //不停的读取从服务器发来的消息

            while (true) {
                try {
                    //实例化对象输入流
                    ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                    Massage m = (Massage) ois.readObject();
                    System.out.println(m.getMassage());

                    //根据服务器回送的信息进行对应的操作

                    if (m.getMassageType().equals(MassageType.MASSAGE_COMM_AVG)) {
                        String mm = m.getOwner() + ":" + m.getMassage() + "  [" + m.getDate() + "]";
                        //通过socket获取用户的聊天界面对象

                        if (ManagerChatJFrame.hashMap.containsKey(s)) {
                            CilentChatJframe cjf = ManagerChatJFrame.getCilentChatJframe(s);
                            if (null != cjf) {
                                //将信息显示到对应的界面
                                cjf.jTextArea_up.append(mm + "\r\n");
                            }
                        }
                    }else if(m.getMassageType().equals(MassageType.MASSAGE_FRIEND)){
                        String fff = m.getFriend();
                        String[] split = fff.split("&");
                        for (int i = 0; i < split.length; i++) {
                            String substring = split[i].substring(0);
                            if (substring.length()!=0) {
                                split[i] = substring;
                            }
                        }
                        if (!ManagerFirendList.hashMap.containsKey(s)){
                        //创建好友列表界面
                        WebChatFriendlist wc = new WebChatFriendlist(username,split);
                        //将界面存放到hashmap中
                        ManagerFirendList.addWebChatFriendlist(s,wc);}
                        //修改相应的好友列表

                          //将实时的好友在线情况更新到好友界面上
                        Iterator<Socket> it = ManagerFirendList.hashMap.keySet().iterator();
                        String updateuser  = updateuser = m.getMassage();
                        while (it.hasNext()){
                            WebChatFriendlist webChatFriendlist = ManagerFirendList.getWebChatFriendlist(it.next());
                            //调用方法进行更新
                            webChatFriendlist.updateFriend(updateuser);
                            //将实时的在线信息存入ManagerFrientUpdate
                        }
                        ManagerFrientUpdate.updateuser = updateuser;
                      //   for (int i = 0; i < split.length; i++) {
                        ManagerFrientUpdate.updateusers = split;
                      //   }
                    }
                } catch (IOException e) {
                    try {
                        s.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null,"连接已中断！");
                    System.exit(0);
                    e.printStackTrace();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
