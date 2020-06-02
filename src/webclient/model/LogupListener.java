package webclient.model;

import common.UserLogup;
import webclient.view.ClientLoginBulidJFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 郑志伟
 * @create 2020-05-24 15:52
 * 注册按钮监控实现
 */
public class LogupListener implements ActionListener {


    JTextField count,name = null;
    JPasswordField Password1,Password2 = null;
    JFrame jf;
    UserLogup u;

    public LogupListener(JTextField count, JTextField name, JPasswordField password1, JPasswordField password2, JFrame jf) {
        this.count = count;
        this.name = name;
        Password1 = password1;
        Password2 = password2;
        this.jf = jf;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取用户注册内容
        String cc = count.getText().trim();
        String nn = name.getText().trim();
        String p1 = new String(Password1.getPassword());
        String p2 = new String(Password2.getPassword());

        //将信息封装到UserLogup类中
        u = new UserLogup(cc,nn,p1,p2);
        System.out.println("count："+cc+"，name"+nn+",pass1"+p1+",pass2"+p2);

        //判断用户信息的合法性
        if (cc.length()==0||nn.length()==0
                ||p1.length()==0||p2.length()==0){
            JOptionPane.showMessageDialog(null,"信息不完整！！！");
        }else if(!p1.equals(p2)){
            JOptionPane.showMessageDialog(null,"密码不一致！！！");
        }else{
            boolean chack = new CilentConnServerToLongup().CheckUserLonup(u);

            //获取服务器回送的用户注册相关信息
            if (chack){
                JOptionPane.showMessageDialog(null,"恭喜您！注册成功！");
                SwingUtilities.invokeLater(ClientLoginBulidJFrame::LoginInit); //回到登录界面
                jf.dispose();
            }else{
                JOptionPane.showMessageDialog(null,"注册失败！请重试！");
            }
        }
    }
}
