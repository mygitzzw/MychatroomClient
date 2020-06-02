package webclient.model;

import common.Massage;

import java.net.Socket;
import java.util.HashMap;

/**
 * @author 郑志伟
 * @create 2020-05-27 21:02
 * 存放实时更新的用户在线好友信息
 */
public class ManagerFrientUpdate {
    //public static Socket ss;
    //public static HashMap<Socket, Massage> hashMap = new HashMap<>();

    /*public static void addUpdate(Socket s,Massage massage){
        ss = s;
        hashMap.put(s, massage);
    }*/
    public static String updateuser = "";
    public static String[]  updateusers  = new String[30];

   /* public static Massage getMassage(Socket s){
        return hashMap.get(s);
    }*/
}
