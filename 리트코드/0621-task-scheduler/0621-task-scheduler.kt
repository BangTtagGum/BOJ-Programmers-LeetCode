class Solution {
    fun leastInterval(tasks: CharArray, n: Int): Int {
    var result = 0

    val freqs: IntArray = IntArray(26)


    for (i in tasks){
        freqs[i - 'A']++
    }

    val pq:Queue<Int> = PriorityQueue{ a : Int , b : Int ->
        b - a
    }

    for(i in freqs){
        if (i > 0) {
            pq.add(i)
        }
    }
    while(true){
        var intervals = 0
        val list: MutableList<Int> = mutableListOf()
        while (!pq.isEmpty()) {
            val freq = pq.poll()
            if (intervals < n + 1) {
                intervals++
                result++
                if (freq > 1) {
                    list.add(freq - 1)
                }
            } else {
                list.add(freq)
            }
        }
        if (list.isEmpty()) {
            break
        }
        result += n + 1 - intervals
        pq.addAll(list)
    }
    return result
}
}