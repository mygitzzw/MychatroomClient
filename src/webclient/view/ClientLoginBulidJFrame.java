package webclient.view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * @author 郑志伟
 * @create 2020-05-23 10:36
 * 登陆界面的起点，初始化登陆界面
 */
public class ClientLoginBulidJFrame {
    /**
     * @author 郑志伟
     * @create 2020-05-23 10:15
     */
    private static JFrame jf = new JFrame();  //创建一个JFrame，用于登陆界面

    public static void LoginInit() {
            jf.setSize(426, 377);   //设置窗口的大小
            int width = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-426)/2; //定位窗口的宽度
            int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 377 / 2); //定位窗口的高度
            jf.setLocation(width, 242);     //设置窗口的位置
            jf.setUndecorated(true);           //设置窗口取消边框
            jf.addMouseListener(new MyListener());  //为窗口添加鼠标监听
            jf.setResizable(false);            //设置不可改变大小

            BorderLayout border_layout = new BorderLayout();
            jf.setLayout(border_layout);//设置边框布局

            JPanel panel_north = ClientLoginJframe.createNorthPanel(jf);
            jf.add(panel_north, BorderLayout.PAGE_START);//添加JPanel到窗口

            JPanel panel_center = ClientLoginJframe.CreateCenterPanel(jf);
            jf.add(panel_center, BorderLayout.CENTER);//添加JPanel到窗口

            JPanel panel_south = ClientLoginJframe.CreateSouthPanel(jf);
            jf.add(panel_south, BorderLayout.PAGE_END);//添加JPanel到窗口
            jf.setVisible(true);        //设置窗口可见
        }

        public static void main(String[] args) {


            SwingUtilities.invokeLater(ClientLoginBulidJFrame::LoginInit); //初始化登陆界面
        }
    }
    /**
     *此处忽略，本想为窗口添加监听，让登陆界面可以拖动，但好像没什么用
     *@return
     */

class MyListener extends MouseAdapter {
    //这两组x和y为鼠标点下时在屏幕的位置和拖动时所在的位置
    int newX,newY,oldX,oldY;
    //这两个坐标为组件当前的坐标
    int startX,startY;

    @Override
    public void mousePressed(MouseEvent e) {
        //此为得到事件源组件
        Component cp = (Component)e.getSource();
        //当鼠标点下的时候记录组件当前的坐标与鼠标当前在屏幕的位置
        startX = cp.getX();
        startY = cp.getY();
        oldX = e.getXOnScreen();
        oldY = e.getYOnScreen();
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        Component cp = (Component)e.getSource();
        //拖动的时候记录新坐标
        newX = e.getXOnScreen();
        newY = e.getYOnScreen();
        //设置bounds,将点下时记录的组件开始坐标与鼠标拖动的距离相加
        cp.setBounds(startX+(newX - oldX), startY+(newY - oldY), cp.getWidth(), cp.getHeight());
    }

}

