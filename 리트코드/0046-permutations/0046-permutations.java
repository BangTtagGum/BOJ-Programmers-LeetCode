class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> lst = Arrays.stream(nums).boxed().toList();
        dfs(result, new ArrayList<>(), lst);
        return result;
    }

    public void dfs(List<List<Integer>> result, List<Integer> prevElements,
            List<Integer> elements) {
        if (elements.isEmpty()) {
            result.add(new ArrayList<>(prevElements));
        }
        for (Integer e : elements) {
            List<Integer> nextElements = new ArrayList<>(elements);
            nextElements.remove(e);

            prevElements.add(e);
            dfs(result, prevElements, nextElements);
            prevElements.remove(e);
        }
    }
}