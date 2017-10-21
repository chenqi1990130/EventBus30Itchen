package cn.update.com.eventbus30itchen;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.EventLog;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import mobile.android.fragment.cycle.LogUtils;


public class Main3Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                LogUtils.d("itchen---点击Main3Activity发送一个 事件");
                EventBus.getDefault().post(new MsgEvent("From Main3"));

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                LogUtils.d("itchen---点击Main3Activity发送一个---------------粘性--------------事件");
                //先发送一个事件 两秒以后发送一个粘性事件
                EventBus.getDefault().postSticky(new MsgEvent("From Main3 With Sticky"));


            }
        });
    }

    /**
     * threadMode 表示方法在什么线程执行   (Android更新UI只能在主线程, 所以如果需要操作UI, 需要设置ThreadMode.MainThread)
     * sticky     表示是否是一个粘性事件   (如果你使用postSticky发送一个事件,那么需要设置为true才能接受到事件)
     * priority   优先级                 (如果有多个对象同时订阅了相同的事件, 那么优先级越高,会优先被调用.)
     *
     * MsgEvent 就是他们订阅的相同的对象的事件
     *
     * */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true, priority = 100)
    public void onEvent(MsgEvent event){

        // Log.i("-->", "Main3 onEvent");
        //itchen记录
        //priority = 100  的时候 Main3Activity确实是比其他的两个界面 最先收到消息
        // 而且 该界面自己发送的消息 他自己也能收到 ，这是我以前未曾考虑到的
        //这种的话 可以做 自己发消息处理自己的界面的问题 也是可以的

        LogUtils.d("itchen--Main3Activity--->"+event.jsonData);


    }


//10-21 11:44:29.051 18628-18628/cn.update.com.eventbus30itchen D/ViewRootImpl: ViewPostImeInputStage ACTION_DOWN
//10-21 11:44:29.221 18628-18628/cn.update.com.eventbus30itchen D/itchen:Main3Activity$1.onClick(L:38): itchen---点击Main3Activity发送一个 事件
//10-21 11:44:29.221 18628-18628/cn.update.com.eventbus30itchen D/itchen:Main3Activity.onEvent(L:73): itchen--Main3Activity--->From Main3
//10-21 11:44:29.221 18628-18628/cn.update.com.eventbus30itchen D/itchen:MainActivity.onEvent(L:46): itchen---> MainActivity--->From Main3
//10-21 11:44:29.221 18628-18628/cn.update.com.eventbus30itchen D/itchen:BaseActivity.onEventBase(L:67): itchen--收到的消息--1508557469230--cn.update.com.eventbus30itchen.MainActivity-->From Main3
//10-21 11:44:29.221 18628-18628/cn.update.com.eventbus30itchen D/itchen:Main2Activity.onEvent(L:46): itchen--Main2Activity----From Main3
//10-21 11:44:29.221 18628-18628/cn.update.com.eventbus30itchen D/itchen:BaseActivity.onEventBase(L:67): itchen--收到的消息--1508557469232--cn.update.com.eventbus30itchen.Main2Activity-->From Main3
//10-21 11:44:29.221 18628-18628/cn.update.com.eventbus30itchen D/itchen:BaseActivity.onEventBase(L:67): itchen--收到的消息--1508557469232--cn.update.com.eventbus30itchen.Main3Activity-->From Main3
//
//                                                                                                       --------- beginning of system
//10-21 11:44:31.231 18628-18628/cn.update.com.eventbus30itchen D/itchen:Main3Activity$1.onClick(L:47): itchen---点击Main3Activity发送一个---------------粘性--------------事件
//10-21 11:44:31.231 18628-18628/cn.update.com.eventbus30itchen D/itchen:Main3Activity.onEvent(L:73): itchen--Main3Activity--->From Main3 With Sticky
//10-21 11:44:31.231 18628-18628/cn.update.com.eventbus30itchen D/itchen:MainActivity.onEvent(L:46): itchen---> MainActivity--->From Main3 With Sticky
//10-21 11:44:31.231 18628-18628/cn.update.com.eventbus30itchen D/itchen:BaseActivity.onEventBase(L:67): itchen--收到的消息--1508557471236--cn.update.com.eventbus30itchen.MainActivity-->From Main3 With Sticky
//10-21 11:44:31.231 18628-18628/cn.update.com.eventbus30itchen D/itchen:Main2Activity.onEvent(L:46): itchen--Main2Activity----From Main3 With Sticky
//10-21 11:44:31.231 18628-18628/cn.update.com.eventbus30itchen D/itchen:BaseActivity.onEventBase(L:67): itchen--收到的消息--1508557471237--cn.update.com.eventbus30itchen.Main2Activity-->From Main3 With Sticky
//10-21 11:44:31.231 18628-18628/cn.update.com.eventbus30itchen D/itchen:BaseActivity.onEventBase(L:67): itchen--收到的消息--1508557471238--cn.update.com.eventbus30itchen.Main3Activity-->From Main3 With Sticky
//10-21 11:44:31.231 18628-18628/cn.update.com.eventbus30itchen I/Choreographer: Skipped 122 frames!  The application may be doing too much work on its main thread.
//



}
