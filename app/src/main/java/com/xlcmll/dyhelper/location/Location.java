package com.xlcmll.dyhelper.location;

import android.view.accessibility.AccessibilityNodeInfo;

import com.xlcmll.dyhelper.Layout;

import java.util.ArrayList;

import static com.xlcmll.dyhelper.location.Wx.is1;
import static com.xlcmll.dyhelper.location.Wx.is2;
import static com.xlcmll.dyhelper.location.WxVideo.is3;
import static com.xlcmll.dyhelper.location.WxVideo.isVideoTitle;

public class Location {
    public static String nowLoc;
    public static Boolean DoneAction4 = false;
    public static Boolean isNewLoc = true;
    /**
     * 1 微信聊天列表页
     * 2 微信聊天详情页
     * 3 视频号详情页
     */
    public static String getLocation() {
//        ArrayList<AccessibilityNodeInfo> nodes = Layout.getNodesByText("视频号");
//        if (isVideoTitle(nodes)) {
//            return 2;
//        }
        Layout.refreshLayout();
        if(is1()){
            setNowLoc("微信聊天列表页");
            return nowLoc;
        }
        if(is2()){
            setNowLoc("微信聊天详情页");
            return nowLoc;
        }
        if(is3()){
            setNowLoc("视频号详情页");
            return nowLoc;
        }
        setNowLoc("unknown");
        return nowLoc;
    }

    public static void setNowLoc(String nowLoc) {
        if(Location.nowLoc!=nowLoc){
            Location.isNewLoc = true;
            Location.nowLoc = nowLoc;
            Location.DoneAction4 = false;
        }
    }
}
