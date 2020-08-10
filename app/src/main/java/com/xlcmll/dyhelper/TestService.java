package com.xlcmll.dyhelper;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import android.os.Handler;

import com.xlcmll.dyhelper.actions.ActionQueen;
import com.xlcmll.dyhelper.actions.Action;
import com.xlcmll.dyhelper.actions.WxVideoAccount;
import com.xlcmll.dyhelper.location.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TestService extends AccessibilityService {
    public static TestService sSharedInstance;
    private String TAG = "yyy";
    private boolean isStart = false;
    private Map<String, Boolean> videoMap = new HashMap<>();
    private Tree tree;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.i(TAG, event.toString());
        ActionQueen.initActionQueen();
//        Random rand = new Random();
//        int delayM = 4000 + rand.nextInt(4000);
//        if (isStart == false) {
//            isStart = true;
//            final Handler handler0 = new Handler();
//            handler0.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    isStart = false;
//                }
//            }, delayM);
//
        int eventType = event.getEventType();
        Log.i(TAG, event.toString());

        switch (eventType) {
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                String loc = Location.getLocation();
                Log.i(TAG, "location:" + loc);
                if (loc == "微信聊天列表页"&& Location.isNewLoc) {
                    Location.isNewLoc = false;
                    ActionQueen.addTask(new Action(1));
                }
                if (loc == "微信聊天详情页" && Location.isNewLoc) {
                    Location.isNewLoc = false;
                    WxVideoAccount.videoAllClick();
//                    ActionQueen.addTask(new Action(3));
//                    action.addTask(new Action(2));
                }
                if (loc == "视频号详情页" && Location.isNewLoc) {
                    Location.isNewLoc = false;
                    ActionQueen.addTask(new Action(4));
                    ActionQueen.addTask(new Action(2));
                }
//                    clickVideoMini();
//                    int level = Location.getLocation();
//                    Log.i(TAG + "level", Integer.toString(level));
//                    toLocation(1);

//                    getThumb();
                //.......
        }
//        }
    }


//    private void getThumb() {
//        AccessibilityNodeInfo nodeInfo = getNodeByText("收藏");
//        if (nodeInfo != null) {
//            nodeInfo.getParent().getParent().getChild(2).performAction(AccessibilityNodeInfo.ACTION_CLICK);
//        }
//    }

    /**
     * 获取视频简介
     *
     * @return
     */
    private String getVideoContent(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo != null) {
            CharSequence t = nodeInfo.getChild(1).getText();
            if (t != null) {
                return t.toString();
            }
        }
        return "";
    }

    /**
     * 获取聊天窗口中的视频号下框
     * 实际结构与appium获取的不一样
     */
//    private void clickVideoMini() {
//        List<AccessibilityNodeInfo> nodes = Tree.getNodeByText("视频号");
//        if(nodes!=null){
//            for (AccessibilityNodeInfo info : nodes) {
//                click.clickNode(info);
//            }
//        }
//    }
    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
        sSharedInstance = this;
        Toast.makeText(this, "服务开启", Toast.LENGTH_SHORT).show();
        super.onServiceConnected();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        sSharedInstance = null;
        Toast.makeText(this, "服务关闭", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }
}
