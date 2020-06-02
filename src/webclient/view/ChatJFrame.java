package webclient.view;

import common.Massage;
import webclient.model.*;

import javax.swing.*;
import java.awt.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;


/**
 * @author 郑志伟
 * @create 2020-05-25 15:34
 * 聊天界面
 * 客户端要时刻读取服务器的信息
 */
 public class ChatJFrame extends JFrame  {

    public String userName,friend;
   static  int width = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-810)/2;
    static int height = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-686)/2;
    private static JFrame jf = new JFrame();

    public ChatJFrame(String userName,String friend)  {
        this.friend = friend;
        this.userName = userName;
    }


        public  void LoginInit() {
            String path = Thread.currentThread().getContextClassLoader().getResource("myimages/title.png").getPath().substring(1);
            ImageIcon imageIcon = new ImageIcon(path);
            jf.setIconImage(imageIcon.getImage());
            jf.setLayout(null);
            jf.setTitle("WebChat聊天室:  "+userName);
            jf.setLayout(new BorderLayout());
            jf.setSize(810,686);
            jf.setBackground(Color.ORANGE);
            //jf.setLocation(width,height);
            jf.setBounds(width,height,830,686);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //设置退出方式
            jf.setResizable(false);

            //设置窗口的布局
            BorderLayout border_layout = new BorderLayout();
            jf.setLayout(border_layout);


            CilentChatJframe c = new CilentChatJframe(userName, friend);
            //更新hashmap中的CilentChatJframe对象
            ManagerChatJFrame.addCilentChatJframe(ManagerSocket.getSocket(userName),c);


            //设置面板的布局
            JPanel panel_north = c.createNorthPanel(jf);
            jf.add(panel_north,BorderLayout.LINE_START);

            JPanel panel_center = c.CreateEastPanel(jf);
            jf.add(panel_center, BorderLayout.LINE_END);
            /*ClientConnServerThread mcc = ManegerClientConnServer.getClientConnServerThread(userName);
            Socket ss = mcc.getSocket();
            CilentChatJframe mmc = ManagerChatJFrame.getCilentChatJframe(ss);*/
            c.jTextArea_up.append("系统消息："+userName+",欢迎您来到WebChat"+"\r\n");
            if (!friend.equals("多人聊天室")) {
                c.jTextArea_up.append("系统消息：您正在与" + friend + "聊天！" + "\r\n");
            }else{
                c.jTextArea_up.append("系统消息：欢迎加入多人聊天室！" + "\r\n");
            }
            jf.setVisible(true);
        }

        public static void main(String[] args) {

        //new ChatJFrame("d").LoginInit();
            //SwingUtilities.invokeLater(webclient.view.ChatJFrame::LoginInit);
        }
    }


    /*
class clientJFrameThread extends Thread {
        @Override
        public void run() {
            while (true){
                try {
                    ObjectInputStream ois = new ObjectInputStream(CilentConnServer.socket.getInputStream());
                    Massage m = (Massage) ois.readObject();
                    String mm = m.getOwner() + ":" +  m.getMassage()+"  ["+m.getDate()+"]";
                    CilentChatJframe.jTextArea_up.append(mm+"\r\n");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }

}
*/
