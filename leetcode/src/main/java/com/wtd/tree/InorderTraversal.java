package com.wtd.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树中序遍历算法
 */
public class InorderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.left.right = new TreeNode(7);
        new InorderTraversal().inorderTraversal(root);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }

        for(Integer i:result){
            System.out.print(i + ",");
        }
        return result;
    }
}
