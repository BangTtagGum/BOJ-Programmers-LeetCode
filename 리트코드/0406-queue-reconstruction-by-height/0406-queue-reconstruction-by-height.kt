class Solution {
    fun reconstructQueue(people: Array<IntArray>): Array<IntArray> {
        val pq:Queue<IntArray> = PriorityQueue { a: IntArray, b:IntArray ->
            if(a[0] != b[0]){
                b[0] - a[0]
            } else {
                a[1] - b[1]
            }
        }

        for(i in people){
            pq.add(i)
        }

        val result: MutableList<IntArray> = mutableListOf()
        while (!pq.isEmpty()) {
            val person = pq.poll()
            result.add(person[1], person)
        }

        return result.toTypedArray()
    }
}