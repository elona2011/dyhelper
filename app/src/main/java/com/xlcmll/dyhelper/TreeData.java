package com.xlcmll.dyhelper;

import android.view.accessibility.AccessibilityNodeInfo;

public class TreeData {
    public AccessibilityNodeInfo node;
    public CharSequence text;
    public CharSequence className;
    public CharSequence contentDes;
    public CharSequence viewId;

    public TreeData(AccessibilityNodeInfo node){
        this.node = node;
        this.text = node.getText();
        this.className = node.getClassName();
        this.contentDes = node.getContentDescription();
        this.viewId = node.getViewIdResourceName();
        if(this.contentDes!=null && this.contentDes.length()>0){
            Tree.addDescMap(this.contentDes,node);
        }
        if(this.text!=null && this.text.length()>0){
            Tree.addTextMap(this.text,node);
        }
        if(this.viewId!=null && this.viewId.length()>0){
            Tree.addViewIdMap(this.viewId,node);
        }
        if(this.className!=null && this.className.length()>0){
            Tree.addClassNameMap(this.className,node);
        }
    }
}
