package com.wtd.tree.treepath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class LoadLeafPath {

    Map<String,List> pathMap=new HashMap();//记录全部从根节点到叶子结点的路径

    //打印出路径
    private void print(List lst) {
        Iterator it=lst.iterator();
        while(it.hasNext())
        {
            TreeNode n=(TreeNode)it.next();
            System.out.print(n.getText()+"-");
        }
        System.out.println();
    }
    public void iteratorTreeNode(TreeNode n,Stack<TreeNode> pathstack) {
        pathstack.push(n);//入栈
        List childlist=n.getChildList();
        if(childlist==null)//没有孩子 说明是叶子结点
        {
            List lst=new ArrayList();
            Iterator stackIt=pathstack.iterator();
            while(stackIt.hasNext())
            {
                lst.add(stackIt.next());

            }
            print(lst);//打印路径
            pathMap.put(n.getText(), lst);//保存路径信息
            return;
        }else
        {
            Iterator it=childlist.iterator();
            while(it.hasNext())
            {
                TreeNode child=(TreeNode)it.next();
                iteratorTreeNode(child,pathstack);//深度优先 进入递归
                pathstack.pop();//回溯时候出栈
            }

        }

    }
    public static void main(String[] args) {
        Stack <TreeNode>pathstack=new Stack();
        TreeNode n=TreeNode.getInitTreeNode();
        LoadLeafPath  tool=new LoadLeafPath();
        tool.iteratorTreeNode(n, pathstack);
    }
}
