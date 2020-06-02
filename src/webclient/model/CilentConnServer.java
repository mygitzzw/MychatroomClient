package webclient.model;

import common.Massage;
import common.MassageType;
import common.User;
import webclient.view.WebChatFriendlist;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

/**
 * @author 郑志伟
 * @create 2020-05-25 7:44
 * 登录后台，发送用户信息验证合法性
 */
public class CilentConnServer {
    public   Socket socket;
    /**
     *将信息发送到服务器验证用户的合法性
     *@return
     */
    public boolean CheckUser(Object u) {

        boolean b=false;    //布尔类型的变量用于表明用户是否合法
        try {
            //实例化一个Socket
             socket = new Socket("127.0.0.1", 8787);
             //实例化对象输出流
            ObjectOutputStream oos  = new ObjectOutputStream(socket.getOutputStream());
            User user = (User)u;
            //设置信息类型
            user.getMassage().setMassageType(MassageType.MASSAGE_COMM);
            //设置时间
            user.getMassage().setDate(new Date());
            Object o = (User) user;
            //将对象发送到服务器
            oos.writeObject(o);
            System.out.println("user已发送"+user);
            //实例化对象输入流等待服务器回送信息
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("massage：");
            //读取服务器回送的对象信息
            Massage massage = (Massage)ois.readObject();

            //验证用户的登录
            if (massage.getMassageType().equals(MassageType.MASSAGE_COMM)) {
                System.out.println("登录成功！");
                //创建一个该用户与客户端保持的线程
                ClientConnServerThread ccst = new ClientConnServerThread(user.getName(),socket);
                ManagerSocket.addSocket(user.getName(),socket);
                //将线程放入管理线程的类中
                ManegerClientConnServer.addClientConnServerThread(user.getName(), ccst);

                //发送好友信息请求包


                //线程启动
                ccst.start();
                b = true;
            } else {
                System.out.println("登录失败");
                return  b;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  true;
    }

    /*public void SendinfoServer(Object user){
        ObjectOutputStream oos = null;

        try {
            Socket socket = new Socket("127.0.0.1", 9999);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
