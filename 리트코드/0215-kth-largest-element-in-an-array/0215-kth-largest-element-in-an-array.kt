class Solution {
    fun findKthLargest(nums: IntArray, k: Int): Int {
        
        val pq:PriorityQueue<Int> = PriorityQueue(Collections.reverseOrder())

        for(n in nums){
            pq.add(n)
        }
        
        for(i in 0 until k-1){
            pq.poll()
        }

        return pq.poll()
    }
}