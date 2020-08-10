package com.xlcmll.dyhelper.actions;

import android.view.accessibility.AccessibilityNodeInfo;

public class Click {
    public static void click(AccessibilityNodeInfo node) {
        while (node != null && !node.isClickable()) {
            node = node.getParent();
        }
        if (node != null) {
            node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        }
    }
}
