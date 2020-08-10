package com.xlcmll.dyhelper.location;

import android.view.accessibility.AccessibilityNodeInfo;

import com.xlcmll.dyhelper.Tree;

import java.util.ArrayList;

public class WxVideo {

    public static Boolean isVideoTitle(ArrayList<AccessibilityNodeInfo> nodes) {
        for (AccessibilityNodeInfo info : nodes) {
            CharSequence text = info.getText();

            if (text != null && text.toString().equals("视频号")) {
                return true;
            }
        }
        return false;
    }

    public static Boolean is3(){
        ArrayList<AccessibilityNodeInfo> list= Tree.viewIdMap.get("android:id/text1");
        if(list !=null){
            for(AccessibilityNodeInfo node : list){
                CharSequence text = node.getText();
                if(text != null && text.toString().equals("视频号")){
                    return true;
                }
            }
        }
        return false;
    }
}
