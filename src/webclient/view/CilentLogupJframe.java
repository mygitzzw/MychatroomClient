package webclient.view;

import webclient.model.BackLogin;
import webclient.model.LogupListener;

import javax.swing.*;
import java.awt.*;


/**
 * @author 郑志伟
 * @create 2020-05-24 10:28
 *     注册界面
 */
public class CilentLogupJframe extends JFrame  {

    static int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()-600)/2;
    static int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()-600)/2;
    private static JFrame jf = new JFrame();

    public static void LoginInit() {
        jf.setSize(600, 650);
        System.out.println(242);
        jf.setLocation(width, height);
        jf.setUndecorated(true);
        jf.setBackground(Color.CYAN);
        jf.addMouseListener(new MyListener());
        jf.setResizable(false);

        BorderLayout border_layout = new BorderLayout();
        jf.setLayout(border_layout);

        JPanel panel_north = logupframe.createNorthPanel(jf);
        jf.add(panel_north, BorderLayout.PAGE_START);

        JPanel panel_center = logupframe.CreateCenterPanel(jf);
        jf.add(panel_center, BorderLayout.CENTER);

        JPanel panel_south = logupframe.CreateSouthPanel(jf);
        jf.add(panel_south, BorderLayout.PAGE_END);

        jf.setVisible(true);
    }

    public static void main(String[] args) {


        SwingUtilities.invokeLater(CilentLogupJframe::LoginInit);
    }
}

class logupframe {
    static JTextField jTextFieldcount, jTextFieldname;
    static JPasswordField jpaTextpassword1, jpaTextpassword2;
    public static JPanel createNorthPanel(JFrame jf){
        JPanel panel = new JPanel();
        //取消面板默认布局
        panel.setLayout(null);
        //设置面板尺寸
        panel.setPreferredSize(new Dimension(0,150));
        panel.setBounds(0,0,600,150);
        String path = Thread.currentThread().getContextClassLoader().getResource("myimages/loguppassword.png").getPath().substring(1);
        ImageIcon image = new ImageIcon(path);
        //将图片添加到面板
        JLabel backgroud = new JLabel(image);
        //设置背景图片位置和尺寸
        backgroud.setBounds(0, 0, 600, 150);
        panel.addMouseListener(new MyListener());
        panel.add(backgroud);
        return panel;
    }
    public static JPanel CreateCenterPanel(JFrame jf){
        JPanel panel = new JPanel();
        //取消面板默认布局
        panel.setLayout(null);
        //设置面板尺寸
        panel.setPreferredSize(new Dimension(600,400));
        panel.setBackground(Color.white);
        panel.setBounds(0,0,600,400);
        String path = Thread.currentThread().getContextClassLoader().getResource("myimages/logupcenter.png").getPath().substring(1);

        ImageIcon image = new ImageIcon(path);
        //将图片添加到面板
        JLabel backgroud = new JLabel(image);
        //设置背景图片位置和尺寸
        backgroud.setBounds(0, 0, 300, 400);
        panel.addMouseListener(new MyListener());
        panel.add(backgroud);

        JLabel regeist = new JLabel();
        regeist.setText("          账号:");
        regeist.setForeground(new Color(100,149,238));
        regeist.setBounds(290,33,110,50);
        regeist.setFont(new Font("宋体:",0,18));

        JLabel regetname = new JLabel("     姓名:");
        regetname.setForeground(new Color(100,149,238));
        regetname.setBounds(290,83,110,50);
        regetname.setFont(new Font("宋体",0,18));

        JLabel regetpwd1 = new JLabel("     密码:");
        regetpwd1.setForeground(new Color(100,149,238));
        regetpwd1.setBounds(290,133,110,50);
        regetpwd1.setFont(new Font("宋体",0,18));

        JLabel regetpwd2 = new JLabel(" 确认密码:");
        regetpwd2.setForeground(new Color(100,149,238));
        regetpwd2.setBounds(290,183,110,50);
        regetpwd2.setFont(new Font("宋体",0,18));


         jTextFieldcount = new JTextField();
        //设置密码框为流失布局
        jTextFieldcount.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        jTextFieldcount.setBounds(410,44,170,30);
        jTextFieldcount.setPreferredSize(new Dimension(170, 25));

        String path1 = Thread.currentThread().getContextClassLoader().getResource("myimages/lonupname.png").getPath().substring(1);

        ImageIcon image1 = new ImageIcon(path1);
        JLabel jbu = new JLabel(image1);
        jbu.setPreferredSize(new Dimension(25,25));
        jTextFieldcount.add(jbu);
        panel.add(jTextFieldcount);


         jTextFieldname = new JTextField();
        //设置密码框为流失布局
        jTextFieldname.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        jTextFieldname.setBounds(410,100,170,30);
        jTextFieldname.setPreferredSize(new Dimension(170, 25));
        String path2 = Thread.currentThread().getContextClassLoader().getResource("myimages/lonupcount.png").getPath().substring(1);

        ImageIcon image2 = new ImageIcon(path2);
        JButton jbu2 = new JButton(image2);
        jbu2.setPreferredSize(new Dimension(25,25));

        jTextFieldname.add(jbu2);
        panel.add(jTextFieldname);

        //密码框组件
         jpaTextpassword1 = new JPasswordField();
        //设置密码框为流失布局
        jpaTextpassword1.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        jpaTextpassword1.setBounds(410,144,170,30);
        jpaTextpassword1.setPreferredSize(new Dimension(170, 25));
        String path3 = Thread.currentThread().getContextClassLoader().getResource("myimages/logupsouth.png").getPath().substring(1);

        ImageIcon image3 = new ImageIcon(path3);
        JButton jbu3 = new JButton(image3);
        jbu3.setPreferredSize(new Dimension(25,25));
        jbu3.setBorderPainted(false);
        jpaTextpassword1.add(jbu3);
        panel.add(jpaTextpassword1);

        //密码框组件
        jpaTextpassword2 = new JPasswordField();
        //设置密码框为流失布局
        jpaTextpassword2.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        jpaTextpassword2.setBounds(410,194,170,30);
        jpaTextpassword2.setPreferredSize(new Dimension(170, 25));
        String path4 = Thread.currentThread().getContextClassLoader().getResource("myimages/logupsouth.png").getPath().substring(1);

        ImageIcon image4 = new ImageIcon(path4);
        JButton jbu4 = new JButton(image4);
        jbu4.setPreferredSize(new Dimension(25,25));
        jbu4.setBorderPainted(false);
        jpaTextpassword2.add(jbu4);
        panel.add(jpaTextpassword2);

        panel.add(regeist);
        panel.add(regetname);
        panel.add(regetpwd1);
        panel.add(regetpwd2);
        panel.add(jTextFieldcount);
        panel.add(jTextFieldname);
        panel.add(jpaTextpassword1);
        panel.add(jpaTextpassword2);
        return panel;
    }
    public static JPanel CreateSouthPanel(JFrame jf){
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(0,72));
        panel.setLayout(null);
        String path5 = Thread.currentThread().getContextClassLoader().getResource("myimages/relogup.png").getPath().substring(1);

        ImageIcon image = new ImageIcon(path5);

        JButton jb = new JButton("返回", image);
        jb.setFont(new Font("宋体",1,19));
        jb.setBounds(120,15,133,40);

        jb.setHorizontalTextPosition(SwingConstants.CENTER);
        String path = Thread.currentThread().getContextClassLoader().getResource("myimages/relogup1.png").getPath().substring(1);
        jb.setRolloverIcon(new ImageIcon(path));
        jb.setFocusPainted(false);
        jb.setContentAreaFilled(false);
        jb.setBorderPainted(false);



        ImageIcon imagee = new ImageIcon(path5);

        JButton jbb = new JButton("注册", imagee);
        jbb.setFont(new Font("宋体",1,19));
        jbb.setBounds(320,15,175,40);

        jbb.setHorizontalTextPosition(SwingConstants.CENTER);

        jbb.setRolloverIcon(new ImageIcon(path));
        jbb.setFocusPainted(false);
        jbb.setContentAreaFilled(false);
        jbb.setBorderPainted(false);

        jb.addActionListener(new BackLogin(jf));
        jbb.addActionListener(new LogupListener(jTextFieldcount, jTextFieldname, jpaTextpassword1, jpaTextpassword2,jf));
        panel.add(jb);
        panel.add(jbb);

        return  panel;
    }

}
