package webclient.model;

import common.Massage;
import common.MassageType;
import webclient.view.CilentChatJframe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

/**
 * @author 郑志伟
 * @create 2020-05-26 12:18
 * 用户聊天界面中发送按钮的监听类
 */
public class JbActionlistner implements ActionListener {
    JTextArea jTextArea_down,jTextArea_up;
    String userName,friend;
    CilentChatJframe ccc;


    public JbActionlistner(JTextArea jTextArea,JTextArea jTextArea1_up,String usaerName,String friend,CilentChatJframe cc ) {
        this.jTextArea_down = jTextArea;
        this.jTextArea_up = jTextArea1_up;
        this.friend = friend;
        this.userName = usaerName;
        this.ccc = cc;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
            Massage massage = new Massage();
            //通过用户账号获取对应客户端的聊天界面
      /*  Socket socket = ManagerSocket.getSocket(userName);
        CilentChatJframe ccj = ManagerChatJFrame.getCilentChatJframe(socket);*/
        //获取用户输入的聊天内容
            String mass = ccc.jTextArea_down.getText();
        //判断内容是否为空
            if (mass.length()==0) {
                    JOptionPane.showMessageDialog(null,"消息不能为空！");
            }else{
                //将信息同步到自己的聊天窗口
                ccc.jTextArea_up.append("我："+mass +"\n");

            try {
                //实例化对象输出流，并将聊天信息发送给服务器
                ObjectOutputStream oos = new ObjectOutputStream(
                        ManegerClientConnServer.getClientConnServerThread(userName)
                                .getSocket().getOutputStream());
                //设置信息的内容
                massage.setMassageType(MassageType.MASSAGE_COMM_AVG);
                massage.setOwner(userName);
                massage.setFriend(friend);
                massage.setMassage(mass);
                massage.setDate(new Date());
                oos.writeObject(massage);
                System.out.println(massage.getMassage());
                System.out.println("消息已发送");
                //清空用户的消息输入框
                ccc.jTextArea_down.setText("");
            } catch (IOException ex) {
                ex.printStackTrace();
        }
        }
    }
}
