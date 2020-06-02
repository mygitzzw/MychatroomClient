package webclient.model;

import webclient.view.ClientLoginBulidJFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 郑志伟
 * @create 2020-05-22 16:31
 * 监听注册界面的返回按钮，当用户点击按钮后即刻回到登陆界面，并删除当前界面
 */
public class BackLogin implements ActionListener {
    JFrame frame;  //定义一个JFrame类接受传进来的对象


    public BackLogin(JFrame frame ) { //构造函数
        this.frame = frame;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(ClientLoginBulidJFrame::LoginInit); //回到登录界面
        frame.dispose();     //删除当前的窗口
    }
}
