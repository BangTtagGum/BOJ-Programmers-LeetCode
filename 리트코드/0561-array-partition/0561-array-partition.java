class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        List<Integer> pair = new ArrayList<>();
        int answer = 0;
        for (int num : nums) {
            pair.add(num);
            if (pair.size() == 2) {
                answer += Collections.min(pair);
                pair.clear();
            }
        }
        return answer;
    }
}