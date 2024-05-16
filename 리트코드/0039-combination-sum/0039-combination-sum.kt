class Solution {
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {
        val result: MutableList<List<Int>> = mutableListOf()
        fun dfs(path: ArrayDeque<Int>, index: Int, target: Int) {

            if (target < 0) {
                return
            }
            if (target == 0) {
                result.add(path.stream().collect(Collectors.toList()))
                return
            }

            for (i in index until candidates.size) {
                path.add(candidates[i])
                dfs(path, i, target - candidates[i])
                path.removeLast()
            }
        }

        dfs(ArrayDeque(), 0, target)
        return result
    }
}