import java.util.AbstractMap.SimpleEntry;
class Solution {
    fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {

        val graph: MutableMap<Int, MutableMap<Int, Int>> = mutableMapOf()
        val pq: Queue<Map.Entry<Int, Int>> = PriorityQueue(compareBy{(node,time) -> time})
        val dist: MutableMap<Int, Int> = mutableMapOf()

        for (time in times) {
            graph.putIfAbsent(time[0], mutableMapOf())
            graph[time[0]]!![time[1]] = time[2]
        }

        pq.add(SimpleEntry(k, 0))

        while (!pq.isEmpty()) {
            val cur = pq.poll()
            val u = cur.key
            val distToU = cur.value

            if (!dist.containsKey(u)) {
                dist[u] = distToU
                if(graph.containsKey(u)){
                    for (entry in graph[u]!!) {
                        val alt = distToU + entry.value
                        pq.add(SimpleEntry(entry.key,alt))
                    }
                }
            }
        }

        if (dist.size == n) {
            return Collections.max(dist.values)
        }
        return -1
    }
}