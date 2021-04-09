package com.wtd.string;

/**
 * 获取字符串中的最长回文字符串
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        String str = "tattarrattat";
        String result = new LongestPalindrome().longestPalindrome(str);
        System.out.println(result);
    }

    public String longestPalindrome(String s) {
        if(s == null || s.isEmpty()){
            return "";
        }
        char[] charArray = s.toCharArray();
        int i =0;
        int length = charArray.length;
        StringBuilder sb  = new StringBuilder("#");
        for(;i < length;i++){
            sb.append(charArray[i]);
            sb.append("#");
        }
        charArray = sb.toString().toCharArray();
        int centerIndex = 0;
        int charRadius = 1;
        int charRadiusTemp;
        length = charArray.length;
        for(i=0;i < length;i++){
            charRadiusTemp = 1;
            while(i - charRadiusTemp >=0 && i + charRadiusTemp < length){
                if(charArray[i - charRadiusTemp] == charArray[i + charRadiusTemp]){
                    charRadiusTemp++;
                }else{
                    break;
                }
            }
            if(charRadiusTemp > charRadius){
                charRadius = charRadiusTemp;
                centerIndex = i;
            }
        }
        return sb.toString().substring(centerIndex - charRadius + 1,centerIndex + charRadius).replaceAll("#","");
    }
}
