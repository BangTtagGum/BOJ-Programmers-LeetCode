class Solution {
    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        dfs(result, nums, new ArrayDeque<>(), 0);
        
        return result;
    }

    private void dfs(List<List<Integer>> result,int[] nums, Deque<Integer> path, int index) {
        result.add(path.stream().collect(Collectors.toList()));

        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(result, nums, path, i + 1);
            path.removeLast();
        }
    }
}