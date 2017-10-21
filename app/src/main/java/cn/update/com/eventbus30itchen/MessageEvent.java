package cn.update.com.eventbus30itchen;

/**
 * Created by itchenqi on 2017/10/21 0021.
 * description：
 */

class MessageEvent {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageEvent(String message) {
        this.message = message;
    }
}
