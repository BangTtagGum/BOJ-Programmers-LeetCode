class Solution {
    fun permute(nums: IntArray): List<List<Int>> {
        val result: MutableList<List<Int>> = mutableListOf()
        val lst = Arrays.stream(nums).boxed().collect(Collectors.toList())
        
        fun dfs(prevElements:MutableList<Int>, elements:MutableList<Int>) {
            if (elements.isEmpty()) {
                result.add(prevElements.stream().collect(Collectors.toList()))
                return
            }
            for (e in elements) {
                val nextElements = ArrayList(elements)
                nextElements.remove(e)

                prevElements.add(e)
                dfs(prevElements, nextElements)
                prevElements.remove(e)
            }
        }
        
        dfs(mutableListOf(),lst)

        return result
    }
}