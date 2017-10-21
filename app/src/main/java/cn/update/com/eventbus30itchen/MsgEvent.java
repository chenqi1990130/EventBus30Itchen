package cn.update.com.eventbus30itchen;

/**
 * Created by itchenqi on 2017/10/21 0021.
 * description：
 */

class MsgEvent {

    public String jsonData;
    //其实可以不必这样写 为了方便

    public MsgEvent(String jsonData) {
        this.jsonData = jsonData;
    }

}
