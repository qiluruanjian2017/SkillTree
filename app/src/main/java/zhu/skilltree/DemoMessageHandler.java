package zhu.skilltree;

import android.content.Context;

import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;

/**
 * Created by dell on 2017/8/20.
 */

//TODO 集成：1.6、自定义消息接收器处理在线消息和离线消息
public class DemoMessageHandler extends BmobIMMessageHandler {
    private Context context;

    public DemoMessageHandler(Context context) {
        this.context = context;
    }

    @Override
    public void onMessageReceive(final MessageEvent event) {
        //在线消息

    }

    @Override
    public void onOfflineReceive(final OfflineMessageEvent event) {
        //离线消息，每次connect的时候会查询离线消息，如果有，此方法会被调用
    }
}