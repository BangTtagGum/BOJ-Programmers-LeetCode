class Solution{
    /**
     * 부루트 포스
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> answer = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            for (int j = i + 1; j < nums.length - 1; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;

                for (int k = j + 1; k < nums.length; k++) {
                    if (k > j + 1 && nums[k] == nums[k - 1])
                        continue;

                    if (nums[i] + nums[j] + nums[k] == 0) {
                        answer.add(new ArrayList<>(Arrays.asList(nums[i],nums[j],nums[k])));
                    }
                }
            }
        }
        return answer;
    }
}