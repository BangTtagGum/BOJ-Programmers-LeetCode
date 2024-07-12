class Solution {
    public int maxSubArray(int[] nums) {

        for (int i = 1; i < nums.length ; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
        }
        return Arrays.stream(nums).max().getAsInt();
    }    
}