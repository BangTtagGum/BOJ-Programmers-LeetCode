class Solution {
    fun combine(n: Int, k: Int): List<List<Int>> {
        val result: MutableList<List<Int>> = mutableListOf()
        fun dfs(elements: MutableList<Int>, start: Int, k: Int) {
            if (k == 0) {
                result.add(elements.stream().collect(Collectors.toList()))
                return
            }

            for (i in start..n) {
                elements.add(i)
                dfs(elements, i + 1, k - 1)
                elements.remove(i)
            }
        }
        dfs(LinkedList(), 1, k)
        return result
    }
}