package webclient.model;

import java.net.Socket;
import java.util.HashMap;

/**
 * @author 郑志伟
 * @create 2020-05-27 22:14
 */
public class ManagerSocket {
    public static HashMap<String, Socket> hashMap = new HashMap<>();

    public static void addSocket(String name,Socket ss ){
        hashMap.put(name, ss);
    }
    public static Socket getSocket(String username){
        return hashMap.get(username);
    }
}
