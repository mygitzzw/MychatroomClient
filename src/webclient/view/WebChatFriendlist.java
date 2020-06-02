package webclient.view;

import common.Massage;
import common.MassageType;
import webclient.model.ManagerChatJFrame;
import webclient.model.ManagerSocket;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

/**
 * @author 郑志伟
 * @create 2020-05-26 16:09
 */
public class WebChatFriendlist extends JFrame implements ActionListener, MouseListener {
    JPanel jPanel,jPanel_list;  // 底层面板，列表面板
    JButton jButton;            //
    JScrollPane jScrollPane;    //滚动框
    String userName, friend;    // 用户信息
    JLabel[] jLabels;           //存放30个用户的标签
    String[] friendlist;        //实时好友在线情况

    public static void main(String[] args) {
           // new WebChatFriendlist();
    }

    /**
     *对好友列表的在线信息进行实时更新
     *@return
     */
    public void updateFriend(String mass){
        if (!"null".equals(mass)){
            //对服务器发来的信息进行拆分
         friendlist = mass.split("@");
        for (int i = 0; i < friendlist.length; i++) {

                String List = friendlist[i].replaceFirst("@", "");
                friendlist[i] = List.trim();
        }

            //对列表进行更新
        for (int i = 0; i < friendlist.length ; i++) {
            for (int j = 1; j < jLabels.length; j++) {
                if (friendlist[i].equals(jLabels[j].getText())) {
                    //对标签初始化
                    jLabels[j].setEnabled(true);

                }
            }
        }
        }


    }
    public WebChatFriendlist(String username,String[] friends) {
        this.userName = username;
        jPanel = new JPanel(new BorderLayout());
        //假定有30个好友
        int a = 0;
        //给jPanel_list初始化30个好友
        for (int i = 0; i < friends.length; i++) {
            if (!("null".equals(friends[i]))&&friends[i]!=""){
                a +=1;
            }
        }
        System.out.println("a:"+a);
        jPanel_list = new JPanel(new GridLayout(a+1, 1, 4, 4));
        jLabels = new JLabel[a+1];

        String path = Thread.currentThread().getContextClassLoader().getResource("myimages/title.png").getPath().substring(1);
        jLabels[0] = new JLabel("多人聊天室", new ImageIcon(path), JLabel.LEFT);
        jLabels[0].setEnabled(true);
        jLabels[0].addMouseListener(this);
        jPanel_list.add(jLabels[0]);

        for (int i =1; i < jLabels.length; i++) {

                        jLabels[i] = new JLabel(friends[i-1], new ImageIcon(path), JLabel.LEFT);

                        if (username.equals(jLabels[i].getText())) {
                            jLabels[i].setEnabled(true);
                        }else{
                            jLabels[i].setEnabled(false);
                        }

                jLabels[i].addMouseListener(this);
                jPanel_list.add(jLabels[i]);

        }
        String pathb = Thread.currentThread().getContextClassLoader().getResource("myimages/friendList.jpg").getPath().substring(1);
        jButton = new JButton("我的好友",new ImageIcon(pathb));
        jButton.setBounds(0,0,210,50);
        jButton.setFont(new Font("宋体",0,20));
        jButton.setBorderPainted(false);

        jScrollPane = new JScrollPane(jPanel_list);
        jPanel.add(jButton, "North");
        jPanel.add(jScrollPane, "Center");

        this.add(jPanel);
        String pathc = Thread.currentThread().getContextClassLoader().getResource("myimages/listtitle.jpg").getPath().substring(1);
        this.setIconImage(new ImageIcon(pathc).getImage());
        this.setBounds(200,100,210,500);
        this.setResizable(false);
        this.setTitle("好友");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //当用户双击时
        if (e.getClickCount()==2){
            //获取到被双击的好友信息
          friend = ((JLabel) e.getSource()).getText();
//            SwingUtilities.invokeLater(webclient.view.ChatJFrame::LoginInit);
            //实例化一个聊天界面
            ChatJFrame chatJFrame = new ChatJFrame(userName,friend);

           // System.out.println("909090+user"+friend);

                //通过用户名获取用户的socket
            Socket socket = ManagerSocket.getSocket(userName);
            try {
                //设置消息并发送给服务器
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                Massage mas = new Massage();

                //判断用户的聊天对象
                if ("多人聊天室".equals(friend)){
                    mas.setOwner(userName);
                    mas.setFriend(friend);
                    mas.setDate(new Date());
                    mas.setMassage(userName+"加入聊天室");
                }else{
                    mas.setOwner(userName);
                    mas.setFriend(friend);
                    mas.setDate(new Date());
                    mas.setMassage("我上线啦!!!");
                }
                mas.setMassageType(MassageType.MASSAGE_COMM_AVG);
                mas.setDate(new Date());
                oos.writeObject(mas);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            //初始化此聊天界面
            chatJFrame.LoginInit();
            //关闭当前好友列表界面
            this.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel j1 = (JLabel)e.getSource();
        j1.setForeground(Color.pink);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel j1 = (JLabel)e.getSource();
        j1.setForeground(Color.BLACK);
    }
}
