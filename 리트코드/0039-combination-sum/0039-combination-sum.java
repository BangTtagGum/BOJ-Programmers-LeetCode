class Solution {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();
        dfs(result, candidates, new LinkedList<>(), 0, target);

        return result;
    }

    private static void dfs(List<List<Integer>> result, int[] candidates,
            LinkedList<Integer> elements, int index, int target) {

        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(elements.stream().collect(Collectors.toList()));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            elements.add(candidates[i]);
            dfs(result, candidates, elements, i, target - candidates[i]);
            elements.removeLast();
        }
    }
}