class Solution {
    fun subsets(nums: IntArray): List<List<Int>> {
        val result: MutableList<List<Int>> = mutableListOf()
        fun dfs(index:Int, path:ArrayDeque<Int>) {
            result.add(path.stream().collect(Collectors.toList()))

            for (i in index until nums.size) {
                path.add(nums[i])
                dfs(i + 1, path)
                path.removeLast()
            }
        }

        dfs(0, ArrayDeque())
        return result
    }
}