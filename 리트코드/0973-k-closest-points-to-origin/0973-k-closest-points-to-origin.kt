class Solution {
    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
        val pq = PriorityQueue<Point>{p1,p2 ->
            val distance1 = p1.x *p1.x + p1.y * p1.y
            val distance2 = p2.x *p2.x + p2.y * p2.y
            distance1 - distance2
        }
        for (point in points){
            pq.add(Point(point[0],point[1]))
        }
        val results: Array<IntArray> = Array(k) { IntArray(2){0} }

        for (i in 0 until k) {
            val point = pq.poll()
            results[i][0] = point.x
            results[i][1] = point.y
        }
        return results
    }
    class Point(var x:Int, var y: Int){

    }
}