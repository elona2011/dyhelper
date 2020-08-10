package com.xlcmll.dyhelper;

import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tree {
    public TreeNode root;
    public static HashMap<CharSequence, ArrayList<AccessibilityNodeInfo>> descMap = new HashMap<>();
    public static HashMap<CharSequence, ArrayList<AccessibilityNodeInfo>> textMap = new HashMap<>();
    public static HashMap<CharSequence, ArrayList<AccessibilityNodeInfo>> viewIdMap = new HashMap<>();
    public static HashMap<CharSequence, ArrayList<AccessibilityNodeInfo>> classNameMap = new HashMap<>();

    public Tree(AccessibilityNodeInfo root) {
        this.root = new TreeNode(root);

        for (int i = 0; i < root.getChildCount(); i++) {
            AccessibilityNodeInfo child = root.getChild(i);
            if(child!=null){
                this.root.addTree(new Tree(child));
            }
        }
    }

    public static ArrayList<AccessibilityNodeInfo> getNodesByText(CharSequence s){
        ArrayList<AccessibilityNodeInfo> list = textMap.get(s);
        if(list != null){
            return list;
        }
        return null;
    }

    public static AccessibilityNodeInfo getNodeByDesc(CharSequence s){
        ArrayList<AccessibilityNodeInfo> list = descMap.get(s);
        if(list != null){
            return list.get(0);
        }
        return null;
    }

    public static void addViewIdMap(CharSequence id, AccessibilityNodeInfo node) {
        ArrayList list = viewIdMap.get(id);
        if (list != null) {
            list.add(node);
        } else {
            list = new ArrayList<AccessibilityNodeInfo>();
            list.add(node);
            viewIdMap.put(id, list);
        }
    }

    public static void addDescMap(CharSequence desc, AccessibilityNodeInfo node) {
        ArrayList list = descMap.get(desc);
        if (list != null) {
            list.add(node);
        } else {
            list = new ArrayList<AccessibilityNodeInfo>();
            list.add(node);
            descMap.put(desc, list);
        }
    }

    public static void addTextMap(CharSequence desc, AccessibilityNodeInfo node) {
        ArrayList list = textMap.get(desc);
        if (list != null) {
            list.add(node);
        } else {
            list = new ArrayList<AccessibilityNodeInfo>();
            list.add(node);
            textMap.put(desc, list);
        }
    }

    public static void addClassNameMap(CharSequence className, AccessibilityNodeInfo node) {
        ArrayList list = classNameMap.get(className);
        if (list != null) {
            list.add(node);
        } else {
            list = new ArrayList<AccessibilityNodeInfo>();
            list.add(node);
            classNameMap.put(className, list);
        }
    }

    public static void clearMap(){
        descMap.clear();
        textMap.clear();
        viewIdMap.clear();
        classNameMap.clear();
    }
}
