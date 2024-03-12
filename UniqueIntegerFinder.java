package com.companyname.stringquestions;

public class UniqueIntegerFinder {
    public static int findUniqueInteger(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 4, 2};
        int uniqueInteger = findUniqueInteger(nums);
        System.out.println("Unique Integer: " + uniqueInteger);
    }
}

