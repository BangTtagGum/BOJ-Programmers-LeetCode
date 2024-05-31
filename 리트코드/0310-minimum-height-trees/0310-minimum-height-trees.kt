class Solution {
    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {

        if (n == 1) {
            return listOf(0)
        }

        val graph: MutableMap<Int, MutableList<Int>> = mutableMapOf()

        for (edge in edges) {
            graph.putIfAbsent(edge[0], mutableListOf())
            graph.putIfAbsent(edge[1], mutableListOf())

            graph[edge[0]]!!.add(edge[1])
            graph[edge[1]]!!.add(edge[0])
        }

        var leaves: MutableList<Int> = mutableListOf()

        for (i in 0 until n) {
            if (graph[i]!!.size == 1)
                leaves.add(i)
        }
        var left = n
        while (left > 2) {
            left -= leaves.size

            val newLeaves: MutableList<Int> = mutableListOf()
            for (leaf in leaves) {
                val neighbor = graph[leaf]!![0]
                graph[neighbor]!!.remove(leaf)
                if (graph[neighbor]!!.size == 1) {
                    newLeaves.add(neighbor)
                }
            }
            leaves = newLeaves
        }

        return leaves
    }
}