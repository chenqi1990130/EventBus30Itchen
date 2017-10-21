package cn.update.com.eventbus30itchen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import mobile.android.fragment.cycle.LogUtils;

//简单的父类
public  abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtils.customTagPrefix = "itchen";
        LogUtils.allowD = true;

        //避免重复注册的注册保护内容
        /*if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);//添加注册
        }*/

        EventBus.getDefault().register(this);
        //test();

       // setContentView(R.layout.activity_base);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        LogUtils.d("itchen--onDestroy--"+this.getClass().getName());

        EventBus.getDefault().unregister(this);//去掉注册内容

        //test();

    }

    //这些添加标注
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBase(MsgEvent event){

        String action = event.jsonData;
        LogUtils.d("itchen--收到的消息--"+System.currentTimeMillis()+"--"+this.getClass().getName()+"-->"+action);

        //从日志上看 ，优先级越高的那个最先收到消息之后 父类 反而最后才收到消息，高于父类

        //test();


//        10-21 11:34:59.211 17498-17498/cn.update.com.eventbus30itchen D/ViewRootImpl: ViewPostImeInputStage ACTION_DOWN
//        10-21 11:34:59.341 17498-17498/cn.update.com.eventbus30itchen D/itchen:Main3Activity$1.onClick(L:38): itchen---点击Main3Activity发送一个 事件
//        10-21 11:34:59.341 17498-17498/cn.update.com.eventbus30itchen D/itchen:Main3Activity.onEvent(L:71): itchen--Main3Activity 收到了事件消息 onEvent
//
//
//        10-21 11:34:59.341 17498-17498/cn.update.com.eventbus30itchen D/itchen:MainActivity.onEvent(L:46): itchen---> MainActivity 收到了来自外界的消息 Main onEvent
//        10-21 11:34:59.341 17498-17498/cn.update.com.eventbus30itchen D/itchen:BaseActivity.onEventBase(L:67): itchen--收到的消息--1508556899350--cn.update.com.eventbus30itchen.MainActivity-->From Main3
//        10-21 11:34:59.341 17498-17498/cn.update.com.eventbus30itchen D/itchen:Main2Activity.onEvent(L:48): itchen--Main2Activity收到了onEvent
//        10-21 11:34:59.341 17498-17498/cn.update.com.eventbus30itchen D/itchen:BaseActivity.onEventBase(L:67): itchen--收到的消息--1508556899351--cn.update.com.eventbus30itchen.Main2Activity-->From Main3
//        10-21 11:34:59.341 17498-17498/cn.update.com.eventbus30itchen D/itchen:BaseActivity.onEventBase(L:67): itchen--收到的消息--1508556899352--cn.update.com.eventbus30itchen.Main3Activity-->From Main3



    }

    protected  void test(){

        Exception exception = new Exception();

        final StackTraceElement[] stackTrace = exception.getStackTrace();
        final StackTraceElement stackTraceElement = stackTrace[1];
        final String className = stackTraceElement.getClassName();

        final String classNamePre = stackTrace[2].getClassName();
        final String methodName = stackTraceElement.getMethodName();

        final int lineNumber = stackTraceElement.getLineNumber();

        LogUtils.d("itchen---->"+System.currentTimeMillis() + "-->"+String.format("class:%s %s method:%s:%d", classNamePre, className, methodName, lineNumber));


    }

    protected void start(Class activity) {
        final Intent intent = new Intent(this, activity);
        startActivity(intent);
    }


}
