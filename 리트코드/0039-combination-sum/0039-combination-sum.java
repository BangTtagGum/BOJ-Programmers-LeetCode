class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> collect = Arrays.stream(candidates).boxed().collect(Collectors.toList());
        dfs(result, collect, new LinkedList<>(),0, target, collect.size(), 0, 0);

        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> collect,
            LinkedList<Integer> elements,
            int start, int target, int n, int k, int sum) {

        if (sum > target) {
            return;
        }
        if (sum == target) {
            result.add(elements.stream().collect(Collectors.toList()));
            return;
        }
        for (int i = start; i < n; i++) {
            int val = collect.get(i);
            elements.add(val);
            dfs(result, collect, elements,i, target, n, k + 1, sum + val);
            elements.removeLast();
        }
    }
}