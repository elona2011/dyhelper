package com.xlcmll.dyhelper.actions;

import android.view.accessibility.AccessibilityNodeInfo;

import com.xlcmll.dyhelper.Tree;
import com.xlcmll.dyhelper.location.Location;

import java.util.ArrayList;

public class WxPageChange {
    /**
     * 从0开始
     *
     * @param i
     */
    public static void enterDetail(int i) {
        String loc = Location.getLocation();
        if(loc == "微信聊天列表页"){
            ArrayList<AccessibilityNodeInfo> list = Tree.classNameMap.get("android.widget.ListView");
            if (list != null && !list.isEmpty()) {
                AccessibilityNodeInfo chatListView = list.get(0);
                if (chatListView.getChildCount() > i + 11) {
                    AccessibilityNodeInfo child = list.get(0).getChild(i + 11);
                    child.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            }
        }
    }

    public static void exitDetail() {
        String loc = Location.getLocation();
        if(loc == "微信聊天详情页" || loc == "视频号详情页") {
            ArrayList<AccessibilityNodeInfo> list = Tree.descMap.get("返回");
            if (list != null && !list.isEmpty()) {
                AccessibilityNodeInfo parent = list.get(0).getParent();
                parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }
    }
}
