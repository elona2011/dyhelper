package com.xlcmll.dyhelper.location;

import android.view.accessibility.AccessibilityNodeInfo;

import com.xlcmll.dyhelper.Tree;

import java.util.ArrayList;

public class Wx {
    /**
     * 根据微信TITLE上“微信(10)”来判断
     * @return
     */
    public static Boolean is1(){
        ArrayList<AccessibilityNodeInfo> list= Tree.viewIdMap.get("android:id/text1");
        if(list !=null){
            for(AccessibilityNodeInfo node : list){
                CharSequence text = node.getText();
                if(text != null && text.toString().indexOf("微信")>-1){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 根据按钮上的DESC判断
     * @return
     */
    public static Boolean is2(){
        ArrayList<AccessibilityNodeInfo> list1= Tree.descMap.get("切换到按住说话");
        ArrayList<AccessibilityNodeInfo> list2= Tree.descMap.get("表情");
        ArrayList<AccessibilityNodeInfo> list3= Tree.descMap.get("更多功能按钮，已折叠");

        if(list1 !=null && list2 != null && list3 != null){
            return true;
        }
        return false;
    }
}
