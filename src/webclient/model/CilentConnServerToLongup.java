package webclient.model;

import common.Massage;
import common.MassageType;
import common.UserLogup;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author 郑志伟
 * @create 2020-05-25 22:10
 * 向服务器发送用户注册请求的类
 */
public class CilentConnServerToLongup {
    boolean b = false;
    boolean CheckUserLonup(UserLogup u){
        try {
            //实例化一个socket
            Socket socket = new Socket("127.0.0.1", 8787);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //将用户注册信息发送给服务器
            oos.writeObject(u);
            //实例化对象输入流，接收服务器回送的用户注册信息
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Massage ma = (Massage)ois.readObject();

            if (ma.getMassageType().equals(MassageType.MASSAGE_LOGIN_SUCCESS)){
                System.out.println("注册成功！");
                b = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return b;
    }
}
