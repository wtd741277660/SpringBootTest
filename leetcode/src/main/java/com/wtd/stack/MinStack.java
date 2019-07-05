package com.wtd.stack;

import java.util.Stack;

/**
 * 在常数时间内获取栈的最小值
 */
public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minIndexStack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        minIndexStack = new Stack<>();
    }

    public void push(int x) {
        if(stack.isEmpty()){
            stack.push(x);
            minIndexStack.push(0);
        }else{
            if(x > stack.get(minIndexStack.peek())){
                stack.push(x);
            }else{
                stack.push(x);
                minIndexStack.push(stack.size() - 1);
            }
        }
    }

    public void pop() {
        if(stack.peek() == stack.get(minIndexStack.peek())){
            stack.pop();
            minIndexStack.pop();
        }else{
            stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return stack.get(minIndexStack.peek());
    }

    public Stack getStack(){
        return stack;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(0);
        minStack.push(3);
        minStack.push(0);
//        System.out.println(minStack.getStack().size());
        minStack.pop();
        minStack.pop();
        minStack.pop();
//        System.out.println(minStack.getStack().size());
        System.out.println(minStack.getMin());
    }
}
