package cn.update.com.eventbus30itchen;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import mobile.android.fragment.cycle.LogUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);//设置

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                start(Main2Activity.class);
            }
        });

        Button button = (Button) this.findViewById(R.id.button001);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //发送一个粘性事件
                EventBus.getDefault().postSticky(new MessageEvent("Hello everyone!"));

            }
        });


    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(MsgEvent event) {

        LogUtils.d("itchen---> MainActivity--->" + event.jsonData);



    }

    //@Subscribe(threadMode = ThreadMode.MAIN ,sticky = true)
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEventMaii1From(MessageEvent messageEvent){


        //这个粘性事件很强大 当再次重启应用 依然能够收到
//        10-21 15:27:17.560 12519-12519/cn.update.com.eventbus30itchen D/itchen:MainActivity.onEventMaii1From(L:71): itchen--收到了MessageEvent 来发送过来的消息--->Hello everyone!


                //当它自己点击的时候 他自己也能收到该事件内容
        LogUtils.d("itchen--收到了MessageEvent 来发送过来的消息--->"+messageEvent.getMessage());


//        10-21 03:22:44.307 16986-16986/cn.update.com.eventbus30itchen D/itchen:MainActivity.onEventMaii1From(L:68): itchen--收到了来自Main3发送过来的消息--->HelloMain3Activity发送的粘性事件
//        10-21 03:22:44.307 16986-16986/cn.update.com.eventbus30itchen D/itchen:Main3Activity.onEventMain3(L:101): itchen--Main3Activity 获取到了一个粘性事件-----HelloMain3Activity发送的粘性事件



//        10-21 15:28:08.110 12519-12519/cn.update.com.eventbus30itchen D/itchen:MainActivity.onEventMaii1From(L:71): itchen--收到了MessageEvent 来发送过来的消息--->Hello everyone!
//                10-21 15:28:12.470 12519-12519/cn.update.com.eventbus30itchen D/itchen:Main3Activity.onEventMain3(L:101): itchen--Main3Activity 获取到了一个粘性事件-----Hello everyone!
//                10-21 15:28:18.710 12519-12519/cn.update.com.eventbus30itchen D/itchen:MainActivity.onEventMaii1From(L:71): itchen--收到了MessageEvent 来发送过来的消息--->HelloMain3Activity发送的粘性事件
//        10-21 15:28:18.710 12519-12519/cn.update.com.eventbus30itchen D/itchen:Main3Activity.onEventMain3(L:101): itchen--Main3Activity 获取到了一个粘性事件-----HelloMain3Activity发送的粘性事件



    }


}
