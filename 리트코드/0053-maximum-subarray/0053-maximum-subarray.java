class Solution {
    public int maxSubArray(int[] nums) {

        int cur = 0;
        int bestSum = Integer.MIN_VALUE;
        for (int num : nums) {
            cur = Math.max(num, cur + num);
            bestSum = Math.max(bestSum, cur);
        }
        return bestSum;
    } 
}