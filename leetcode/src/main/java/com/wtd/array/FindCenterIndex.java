package com.wtd.array;

/**
 * 查找数组的中心索引
 */
public class FindCenterIndex {

    public static void main(String[] args) {
        int[] nums = {-1,-1,0,-1,-1,-1};
        int result = new FindCenterIndex().pivotIndex(nums);
        System.out.println(result);
    }

    public int pivotIndex(int[] nums) {
        if(nums.length == 0){
            return -1;
        }
        if(nums.length == 1){
            return 0;
        }
        int startIndex = 0;
        int endIndex = nums.length - 1;
        int leftCount = 0;
        int rightCount = 0;
        int length = nums.length;
        //判断第一个数字之后的所有数字之和是否为0
        for(int i = 1;i < length;i++){
            rightCount += nums[i];
        }
        if(rightCount == 0){
            return 0;
        }
        //从索引1开始向右推，判断左右两侧的数字之和是否相等
        for(int i = 1;i < length;i++){
            rightCount -= nums[i];
            leftCount += nums[i - 1];
            if(leftCount == rightCount){
                return i;
            }
        }
        return -1;
    }
}
