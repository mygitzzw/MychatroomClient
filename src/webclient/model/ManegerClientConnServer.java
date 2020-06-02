package webclient.model;

import java.util.HashMap;

/**
 * @author 郑志伟
 * @create 2020-05-26 22:41
 * 这是管理客户端线程的类
 */
public class ManegerClientConnServer {

    public static HashMap<String, ClientConnServerThread> hashMap = new HashMap<>();

    //把创建好的线程放入hashmap

    public static void addClientConnServerThread(String username,ClientConnServerThread cc){
        hashMap.put(username, cc);
    }

    //获取map存放的线程
    public static ClientConnServerThread getClientConnServerThread(String userName){
        return hashMap.get(userName);

    }
}
