package webclient.model;

import common.Massage;
import common.MassageType;
import common.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

/**
 * @author 郑志伟
 * @create 2020-05-24 9:48
 * 登陆按钮的监听事件类，当用户点击登陆按钮后进入此类。
 * 通过 CilentConnServer()类的CheckUser(User u)方法验证用户是否合法
 */
public class LoginListener implements ActionListener {
    JComboBox<Object> jComboBox;        //账号输入框对象
    JPasswordField jPasswordField;      //密码框对象
    User user;                          //存储用户名和密码的用户类
    JFrame jf;                          //传递进来的登陆界面对象
    String name;                        //用户名

    public LoginListener(JComboBox<Object> jComboBox, JPasswordField jPasswordField, JFrame jf) {
        this.jComboBox = jComboBox;
        this.jPasswordField = jPasswordField;
        this.jf = jf;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //通过输入框获取到用户账号
        String username = (String) jComboBox.getSelectedItem().toString();
        name = username;
        //通过输入框获取到用户密码
        String userpwd = new String(jPasswordField.getPassword()).trim();
        //将用户信息封装到User类中
        user = new User(username,userpwd );
        System.out.println("账号："+jComboBox.getSelectedItem().toString()+",密码："+new String(jPasswordField.getPassword()));
        //验证用户信息的合法性
        boolean chack = new CilentConnServer().CheckUser(user);
        if (chack){   //发送一个在线好友的请求包
            try {
                //通过用户账号获取服务器与客户端通讯的（Socket）套接字
                Socket ss = ManegerClientConnServer.getClientConnServerThread(username).getSocket();
                //实例化一个对象输出流用于发送当前好友的在线信息
                ObjectOutputStream oos = new ObjectOutputStream(ss.getOutputStream());
                //实例化一个信息类用于封装信息
                Massage massage = new Massage();
                //设置信息的类型
                massage.setMassageType(MassageType.MASSAGE_ON_LINE);
                //设置发送者
                massage.setOwner(username);
                //设置接收者
                massage.setFriend("server");
                //避免NollPointerExcetion，填写一些信息
                massage.setMassage("好友在线请求");
                //获取当前时间
                massage.setDate(new Date());
                System.out.println("请求包发送成成功");
                //通过对象流将massage发送到服务器
                oos.writeObject(massage);
                //关闭登陆界面
                jf.dispose();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else{
            //若返回用户不合法则弹窗提醒
            JOptionPane.showMessageDialog(null,"登录失败！！");
        }
    }

}
