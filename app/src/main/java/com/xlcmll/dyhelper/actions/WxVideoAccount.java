package com.xlcmll.dyhelper.actions;

import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import com.xlcmll.dyhelper.Tree;
import com.xlcmll.dyhelper.location.Location;

import java.util.ArrayList;

public class WxVideoAccount {
    private static String TAG = "yyy";
    public static int thumbNum0;
    public static int thumbNum1;


    public WxVideoAccount() {

    }

    public static void videoAllClick() {
        ArrayList<AccessibilityNodeInfo> list = Tree.textMap.get("视频号");
        if (list != null && !list.isEmpty()) {
            AccessibilityNodeInfo node = list.get(list.size() - 1);
            ActionQueen.addTask(new Action(3, node));
        }else{
            ActionQueen.addTask(new Action(2));
        }
    }

    public static void videoClick(AccessibilityNodeInfo node) {
        String loc = Location.getLocation();
        if (loc == "微信聊天详情页" && node != null) {
            Click.click(node);
            ActionQueen.addTask(0, new Action(4));
        }
    }

    public static void thumbClick(int i) {
        String loc = Location.getLocation();
        if (loc == "视频号详情页") {
            try {
                ArrayList<AccessibilityNodeInfo> list0 = Tree.textMap.get("收藏");
                ArrayList<AccessibilityNodeInfo> list1 = Tree.textMap.get("转发");
                if (list0 == null || list1 == null) return;
                AccessibilityNodeInfo parent0 = list0.get(0).getParent().getParent();
                AccessibilityNodeInfo parent1 = list1.get(0).getParent().getParent();
                if (parent0.getViewIdResourceName() == parent1.getViewIdResourceName()) {
                    AccessibilityNodeInfo child = parent0.getChild(2);
                    child.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    AccessibilityNodeInfo thumbNumNode = child.getChild(0);
                    if (thumbNumNode != null) {
                        CharSequence thumbNum = thumbNumNode.getText();
                        Log.i(TAG, "thumbClick: " + thumbNum);
                        if (i == 0) {
                            WxVideoAccount.thumbNum0 = Integer.parseInt(thumbNum.toString());
                        } else {
                            WxVideoAccount.thumbNum1 = Integer.parseInt(thumbNum.toString());
                        }
                    }
                }
                return;
            } catch (Exception e) {

            }
        }
    }
}
