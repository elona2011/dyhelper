package com.xlcmll.dyhelper;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import android.os.Handler;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TestService extends AccessibilityService {
    private String TAG = "yyy";
    private boolean isClick = false;
    private Map<String, Boolean> videoMap = new HashMap<>();

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.i(TAG, event.toString());
        Random rand = new Random();
        int delayM = 4000 + rand.nextInt(4000);
        if (isClick == false) {
            isClick = true;
            final Handler handler0 = new Handler();
            handler0.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isClick = false;
                }
            }, delayM);

            int eventType = event.getEventType();
            Log.i(TAG, event.toString());

            switch (eventType) {
                case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                    AccessibilityNodeInfo node = getVideoMini();
                    String txt = getVideoContent(node);
                    Boolean txtStatus = videoMap.get(txt);
                    if (txtStatus == null || txtStatus == false) {
                        clickShiPingHao(node);
                    }
                    int level = getLocation();
                    Log.i(TAG + "level", Integer.toString(level));


//                    getThumb();
                    //.......
            }
        }

    }

    private void clickShiPingHao(AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo != null) {
            clickNode(nodeInfo);
        }
    }

    private void getThumb() {
        AccessibilityNodeInfo nodeInfo = getNodeByText("收藏");
        if (nodeInfo != null) {
            nodeInfo.getParent().getParent().getChild(2).performAction(AccessibilityNodeInfo.ACTION_CLICK);
        }
    }

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
    private AccessibilityNodeInfo getVideoMini() {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo != null) {
            List<AccessibilityNodeInfo> nodes = nodeInfo.findAccessibilityNodeInfosByText("视频号");
            for (AccessibilityNodeInfo info : nodes) {
                CharSequence text = info.getText();
                if (text != null && text.toString().equals("视频号")) {
                    AccessibilityNodeInfo parent = info.getParent();
                    int c = parent.getChildCount();
                    try {
                        Log.i(TAG, parent.toString());
                        Log.i(TAG, parent.getChild(0).toString());
                        Log.i(TAG, parent.getChild(1).toString());
                        Log.i(TAG, parent.getChild(2).toString());

                        Log.i(TAG, Integer.toString(c));
                    } catch (Exception e) {

                    }

                    if (parent.getChildCount() != 3) continue;

                    Log.i(TAG, parent.getClassName().toString());
//                    if(parent.getClassName().toString().equals(""))
                    String txt = getVideoContent(parent);
                    if (videoMap.get(txt) == null) {
                        videoMap.put(getVideoContent(parent), false);
                    }
                    return parent;
                }
            }
        }
        return null;
    }

    //完全文字匹配
    private AccessibilityNodeInfo getNodeByText(String t) {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo != null) {
            List<AccessibilityNodeInfo> nodes = nodeInfo.findAccessibilityNodeInfosByText(t);
            for (AccessibilityNodeInfo info : nodes) {
                CharSequence text = info.getText();

                if (text != null && text.toString().equals(t)) {
                    return info;
                }
            }
        }
        return null;
    }

    private void toLocation(int level){

    }

    /**
     * @param info
     */
    private int getLocation() {
        AccessibilityNodeInfo node = getNodeByText("视频号");
        if (isVideoTitle()) {
            return 2;
        }
        return 0;
    }

    private Boolean isVideoTitle() {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo != null) {
            List<AccessibilityNodeInfo> nodes = nodeInfo.findAccessibilityNodeInfosByViewId("android:id/text1");
            for (AccessibilityNodeInfo info : nodes) {
                CharSequence text = info.getText();

                if (text != null && text.toString().equals("视频号")) {
                    return true;
                }
            }
        }
        return false;
    }

    private void clickNode(AccessibilityNodeInfo info) {
        boolean isClickable = false;
        while (!isClickable) {
            isClickable = info.isClickable();
            if (isClickable) {
                info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            } else {
                info = info.getParent();
            }
        }
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
        Toast.makeText(this, "服务开启", Toast.LENGTH_SHORT).show();
        super.onServiceConnected();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "服务关闭", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }
}
