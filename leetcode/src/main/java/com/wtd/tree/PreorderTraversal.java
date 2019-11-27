package com.wtd.tree;

import jdk.nashorn.internal.ir.BinaryNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.BinaryOperator;

/**
 * 二叉树的前序遍历算法
 */
public class PreorderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.left.right = new TreeNode(7);
        new PreorderTraversal().preorderTraversal(root);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if(root == null){
            return result;
        }
        preorderIteration(root,result);

        for(Integer i:result){
            System.out.print(i + ",");
        }
        return result;
    }


    /**
     * 通过递归的方式获取二叉树的前序算法
     * @param node
     * @param list
     */
    private void preorderRecursion(TreeNode node,List<Integer> list){
        if(node != null){
            list.add(node.val);
            preorderRecursion(node.left,list);
            preorderRecursion(node.right,list);
        }
    }

    /**
     * 通过迭代的方式获取二叉树前序算法
     * @param node
     * @param list
     */
    private void preorderIteration(TreeNode node,List<Integer> list){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode next = node;
        stack.push(node);
        while (!stack.isEmpty()){
            TreeNode curr = stack.pop();
            list.add(curr.val);
            TreeNode right = curr.right;
            TreeNode left = curr.left;
            if(right != null){
                stack.push(right);
            }
            if(left != null){
                stack.push(left);
            }
        }
    }

}
