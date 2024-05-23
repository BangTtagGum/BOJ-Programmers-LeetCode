class Solution {
    fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
        val graph: MutableMap<Int, MutableMap<Int,Int>> = mutableMapOf()

        for (flight in flights) {
            graph.putIfAbsent(flight[0], mutableMapOf())
            graph[flight[0]]!![flight[1]] = flight[2]
        }

        val pq: Queue<IntArray> = PriorityQueue{ a, b -> a[1] - b[1] }
        val visited: MutableMap<Int, Int> = mutableMapOf()
        pq.add(intArrayOf(src, 0, 0))

        while (!pq.isEmpty()) {
            val cur = pq.poll()
            val node = cur[0]
            val dist = cur[1]
            var visitCount = cur[2]

            if(node == dst){
                return dist
            }
            visited[node] = visitCount

            // 다음 방문지가 존재할 때
            if(visitCount <= k && graph.containsKey(node)){
                for (entry in graph[node]!!.entries){
                    if (!visited.containsKey(entry.key) || visitCount < visited[entry.key]!!) {
                        pq.add(intArrayOf(entry.key, entry.value + dist, visitCount + 1))
                    }
                }
            }
        }
        
        return -1
    }
}