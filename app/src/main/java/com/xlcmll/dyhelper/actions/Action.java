package com.xlcmll.dyhelper.actions;

import android.os.SystemClock;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.xlcmll.dyhelper.Layout;
import com.xlcmll.dyhelper.location.Location;

public class Action {
    /**
     * 见doTask
     */
    public int type;
    public CharSequence text;
    public CharSequence desc;
    public AccessibilityNodeInfo node;
    public int sleepTime = 2000;
    private String TAG = "yyy";

    public Action(int type) {
        this.type = type;
    }

    public Action(int type, AccessibilityNodeInfo node) {
        this.type = type;
        this.node = node;
    }

    public Action(int type, CharSequence desc) {
        this.type = type;
//        switch (type){
//            case 1:
//                this.text = "视频号";
//        }
        this.desc = desc;
    }

    public void doTask() {
        Log.i(TAG, "doTask: " + this.type);
        Layout.refreshLayout();
        switch (this.type) {
            case 1:
                WxPageChange.enterDetail(6);
                break;

            case 2:
                WxPageChange.exitDetail();
                break;

            case 3:
                if(this.node!=null){
                    WxVideoAccount.videoClick(this.node);
                    SystemClock.sleep(2000);
                    WxPageChange.exitDetail();
                }

            case 4:
                WxVideoAccount.thumbClick(0);
                SystemClock.sleep(2000);
                WxVideoAccount.thumbClick(1);
                if(WxVideoAccount.thumbNum0<WxVideoAccount.thumbNum1){
                    //才点
                    Log.i(TAG, "doTask: 才点");
                    SystemClock.sleep(2000);
                    WxVideoAccount.thumbClick(1);
                    SystemClock.sleep(2000);
                    WxPageChange.exitDetail();
                }
//            case 5:
//                WxVideoAccount.getThumbNum();

            default:
                break;
        }
    }
}
