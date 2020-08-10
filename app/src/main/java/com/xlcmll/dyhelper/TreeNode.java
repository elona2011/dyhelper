package com.xlcmll.dyhelper;

import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    public TreeData data;
    public TreeNode parent;
    public List<TreeNode> children;

    public TreeNode(AccessibilityNodeInfo node){
        this.data = new TreeData(node);
        this.children = new ArrayList<TreeNode>();
    }

    public void addTree(Tree tree){
        this.children.add(tree.root);
        tree.root.parent = this;
    }
}
