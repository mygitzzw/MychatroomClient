package webclient.model;

import webclient.view.ChatJFrame;
import webclient.view.CilentChatJframe;

import java.net.Socket;
import java.util.HashMap;

/**
 * @author 郑志伟
 * @create 2020-05-27 7:10
 * 这是一个管理聊天界面的类
 */
public class ManagerChatJFrame {
    public static HashMap<Socket, CilentChatJframe> hashMap = new HashMap<>();

    //把创建好的界面入hashmap

    public static void addCilentChatJframe(Socket id,CilentChatJframe cc){
        hashMap.put(id, cc);
    }

    //获取map存放的界面
    public static CilentChatJframe getCilentChatJframe(Socket id){
        return hashMap.get(id);

    }
}
