package com.wtd.stack;

import java.util.Stack;

/**
 * 校验()[]{}括号的合法性
 */
public class ValidString {

    public static void main(String[] args) {
        boolean result = new ValidString().isValid("([])");
        System.out.println(result);
    }

    public boolean isValid(String s) {
        Stack<Character> result = new Stack<>();
        if(s.length() == 0){
            return true;
        }
        char[] charArray = s.toCharArray();
        for(int i = 0;i < charArray.length;i++){
            if(result.isEmpty()){
                result.push(charArray[i]);
            }else if((result.peek() == '(' && charArray[i] == ')')
                    || (result.peek() == '[' && charArray[i] == ']')
                    || (result.peek() == '{' && charArray[i] == '}')){
                result.pop();
            }else{
                result.push(charArray[i]);
            }
        }
        return result.isEmpty();
    }

}
