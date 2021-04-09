package com.wtd.tree.treepath;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    private String text;
    private List<TreeNode> childList;
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public List<TreeNode> getChildList() {
        return childList;
    }
    public void setChildList(List<TreeNode> childList) {
        this.childList = childList;
    }
    public static TreeNode getInitTreeNode()
    {
        TreeNode TreeNodeA=new TreeNode();
        TreeNodeA.setText("A");
        TreeNode TreeNodeB=new TreeNode();
        TreeNodeB.setText("B");
        TreeNode TreeNodeC=new TreeNode();
        TreeNodeC.setText("C");
        TreeNode TreeNodeD=new TreeNode();
        TreeNodeD.setText("D");
        TreeNode TreeNodeE=new TreeNode();
        TreeNodeE.setText("E");

        List<TreeNode>lstB=new ArrayList();
        lstB.add(TreeNodeC);
        lstB.add(TreeNodeD);
        TreeNodeB.setChildList(lstB);

        List<TreeNode>lstA=new ArrayList();
        lstA.add(TreeNodeB);
        lstA.add(TreeNodeE);
        TreeNodeA.setChildList(lstA);
        return TreeNodeA;

    }
}
