package webclient.view;


import webclient.model.LoginListener;
import webclient.model.ManagerFrientUpdate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * @author 郑志伟
 * @create 2020-05-23 15:36
 *    登录界面
 *
 */
public class ClientLoginJframe {
    private static LoginListener aa = null;  //声明一下按钮监听的类
    static JPasswordField jpaCenter;        //密码框
    static JComboBox<Object> jcoCenter;     //账号输入框
   // private static LogupListener logupListener = null;
    public static JPanel createNorthPanel(JFrame jf){
        JPanel panel = new JPanel();
        //取消面板默认布局
        panel.setLayout(null);
        //设置面板尺寸
        panel.setPreferredSize(new Dimension(0,200));
        String path = Thread.currentThread().getContextClassLoader().getResource("myimages/new1sur.png").getPath().substring(1);
        ImageIcon image = new ImageIcon(path);
        //将图片添加到面板
        JLabel backgroud = new JLabel(image);
        //设置背景图片位置和尺寸
        backgroud.setBounds(0, 0, 428, 200);
        //为面板添加鼠标监听
        panel.addMouseListener(new MyListener());
        panel.add(backgroud);
        //在面板的右上角添加一个退出按钮
        String patha = Thread.currentThread().getContextClassLoader().getResource("myimages/neer1.png").getPath().substring(1);
        JButton out = new JButton(new ImageIcon(patha));
        out.setBounds(400,0,28 ,29);
        //设置移动鼠标到退出按钮时更改图片
        String pathb = Thread.currentThread().getContextClassLoader().getResource("myimages/close_hover.jpg").getPath().substring(1);
        out.setRolloverIcon(new ImageIcon(pathb));
        //取消按钮边框
        out.setBorderPainted(false);

        String pathc = Thread.currentThread().getContextClassLoader().getResource("myimages/neer.png").getPath().substring(1);
        JButton out1 = new JButton(new ImageIcon(pathc));
        out1.setBounds(366,0,28 ,29);
        //设置移动鼠标到退出按钮时更改图片
        String pathd = Thread.currentThread().getContextClassLoader().getResource("myimages/close1_hover.jpg").getPath().substring(1);
        out1.setRolloverIcon(new ImageIcon(pathd));
        //取消按钮边框
        out1.setBorderPainted(false);
        panel.add(out);
        panel.add(out1);
        out.addActionListener(e -> jf.dispose());

        return panel;
    }

    public static JPanel CreateCenterPanel (JFrame jf){

        JPanel panel = new JPanel();
        //设置背景颜色
        panel.setBackground(Color.white);
        //取消默认布局
        panel.setLayout(null);
        //创建一个JcomboBox下拉框组件

        String[] updateusers = ManagerFrientUpdate.updateusers;
        String[] str = {"zzw", "秀儿","阿狸","159115","123456" };
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < updateusers.length; i++) {
            if ("null".equals(updateusers[i])){
                list.add(updateusers[i]);
            }
        }
        String[] ff = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ff[i] = list.get(i);
        }
        if (ff.length!=0) {
            jcoCenter = new JComboBox<>(ff);
        }else{
            jcoCenter = new JComboBox<>(str);
        }

        panel.add(jcoCenter);
        //textSet(jcoCenter);
        //设置下拉框可编辑
        jcoCenter.setEditable(true);
        //设置位置
        jcoCenter.setBounds(124,15,175,30);
        //设置字体
        jcoCenter.setFont(new Font("Calicri",0,13));
        //密码框组件
         jpaCenter = new JPasswordField(25);
        //设置密码框为流失布局
        jpaCenter.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        //设置位置
        jpaCenter.setBounds(124, 44, 175, 30);
        jpaCenter.setPreferredSize(new Dimension(185, 25));
        panel.add(jpaCenter);
        //PasstextSet(jpaCenter);

        //为按钮添加图片
        String path = Thread.currentThread().getContextClassLoader().getResource("myimages/key.png").getPath().substring(1);
        ImageIcon image = new ImageIcon(path);
        //添加按钮
        JButton jbu = new JButton(image);
        jbu.setPreferredSize(new Dimension(22,20));
        //设置无边框
        jbu.setBorderPainted(false);
        jpaCenter.add(jbu);

        JCheckBox jch1 = new JCheckBox("记住密码");

        jch1.setFocusPainted(false);
        jch1.setFont(new Font("宋体",0,13));
        jch1.setBounds(124,85,80,20);
        panel.add(jch1);

        JCheckBox jch2 = new JCheckBox("自动登录");
        jch2.setFocusPainted(false);
        jch2.setFont(new Font("宋体",0,12));
        jch2.setBounds(224,85,80,20);
        panel.add(jch2);

        JLabel regeist = new JLabel();
        regeist.setText("注册账号");
        regeist.setForeground(new Color(100,149,238));
        regeist.setBounds(305,15,60,20);
        regeist.setFont(new Font("宋体",0,12));
        //添加鼠标监听
        regeist.addMouseListener(new MouseListener() {
            @Override //当点击鼠标时
            public void mouseClicked(MouseEvent e) {
               //初始化注册界面
                SwingUtilities.invokeLater(CilentLogupJframe::LoginInit);
                //关闭当前界面
                jf.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            //当鼠标接触到组件时
            @Override
            public void mouseEntered(MouseEvent e) {
                regeist.setForeground(new Color(50,200,150));
            }

            //当鼠标退出组件时
            @Override
            public void mouseExited(MouseEvent e) {
                regeist.setForeground(new Color(100,149,238));
            }
        });

        //添加标签
        JLabel regetpwd = new JLabel("忘记密码");
        regetpwd.setForeground(new Color(100,149,238));
        regetpwd.setBounds(305,45,60,20);
        regetpwd.setFont(new Font("宋体",0,12));
        regetpwd.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "密码都记不住，还能记住啥？？？");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //鼠标按下再抬起
            }

            //当鼠标接触到组件时
            @Override
            public void mouseEntered(MouseEvent e) {
                regetpwd.setForeground(new Color(50,200,150));
            }
            //当鼠标退出组件时
            @Override
            public void mouseExited(MouseEvent e) {
                regetpwd.setForeground(new Color(100,149,238));
            }
        });
        //添加到面板
        panel.add(regeist);
        panel.add(regetpwd);

        return panel;

    }

    /**
     *对面板进行设置
     *@return
     */

    public static JPanel CreateSouthPanel (JFrame jf){
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(0,72));
        panel.setLayout(null);

        String patha = Thread.currentThread().getContextClassLoader().getResource("myimages/single_normal.jpg").getPath().substring(1);
        JButton jble = new JButton(new ImageIcon(patha));
        jble.setPreferredSize(new Dimension(40,40));
        jble.setFocusPainted(false);
        String pathb = Thread.currentThread().getContextClassLoader().getResource("myimages/single_down.jpg").getPath().substring(1);
        jble.setRolloverIcon(new ImageIcon(pathb));
        jble.setBorderPainted(false);

        jble.setContentAreaFilled(false);
        jble.setBounds(0,30,40,40);
        jble.setToolTipText("多账号登录");


        String pathc = Thread.currentThread().getContextClassLoader().getResource("myimages/right_normal.jpg").getPath().substring(1);
        JButton jbri = new JButton(new ImageIcon(pathc));
        jble.setPreferredSize(new Dimension(40,40));
        jbri.setFocusPainted(false);
        jbri.setBorderPainted(false);
        String pathd = Thread.currentThread().getContextClassLoader().getResource("myimages/right_hover.jpg").getPath().substring(1);
        jbri.setRolloverIcon(new ImageIcon(pathd));
        jbri.setContentAreaFilled(false);
        jbri.setBounds(380,28,40,40);
        jbri.setToolTipText("二维码登录");

        String path = Thread.currentThread().getContextClassLoader().getResource("myimages/login_normal.jpg").getPath().substring(1);
        ImageIcon image = new ImageIcon(path);
        JButton jb = new JButton("登     录", image);
        jb.setFont(new Font("宋体",0,13));
        jb.setBounds(130,0,175,40);

        jb.setHorizontalTextPosition(SwingConstants.CENTER);
        jb.setFocusPainted(false);
        jb.setContentAreaFilled(false);
        jb.setBorderPainted(false);
        String pa = Thread.currentThread().getContextClassLoader().getResource("myimages/login_hover.jpg").getPath().substring(1);
        jb.setRolloverIcon(new ImageIcon(pa));

        panel.add(jble);
        panel.add(jb);
        panel.add(jbri);


        /**
         *添加监听，当用户点击登陆时进入
         *@return
         */

        jb.addActionListener(new LoginListener(jcoCenter,jpaCenter,jf));
        return panel;
    }
}
