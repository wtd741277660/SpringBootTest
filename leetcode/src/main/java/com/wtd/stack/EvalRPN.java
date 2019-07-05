package com.wtd.stack;

import java.util.Stack;

/**
 * 逆波兰表达式
 */
public class EvalRPN {

    public static void main(String[] args) {
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(new EvalRPN().evalRPN(tokens));
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        String str = null;
        for(int i = 0;i < tokens.length;i++){
            str = tokens[i];
            if(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")){
                int last = stack.pop();
                int pre_last = stack.pop();
                stack.push(calculate(pre_last,last,str));
            }else{
                stack.push(Integer.parseInt(str));
            }
        }
        System.out.println(stack.size());
        return stack.peek();
    }

    private int calculate(int num1,int num2,String type){
        int result = 0;
        switch(type){
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            default:
                result = num1 / num2;
                break;
        }
        return result;
    }
}
