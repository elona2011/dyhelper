package com.xlcmll.dyhelper;

import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayList;
import java.util.List;

public class Layout {
    public static Tree tree;

    //完全文字匹配
    public static AccessibilityNodeInfo getNodeByText(String t) {
        AccessibilityNodeInfo nodeInfo = TestService.sSharedInstance.getRootInActiveWindow();
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

    public static ArrayList<AccessibilityNodeInfo> getNodesByText(String t) {
        return Layout.getNodesByText(t);
    }

    public static void refreshLayout(){
        setLayout(TestService.sSharedInstance.getRootInActiveWindow());
    }

    public static void setLayout(AccessibilityNodeInfo root){
        if (root != null) {
            Tree.clearMap();
            Layout.tree = new Tree(root);
        }
    }
}
