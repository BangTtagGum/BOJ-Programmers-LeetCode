class Solution {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortWith(Comparator{a: IntArray, b:IntArray -> a[0] - b[0]})

        val merged: MutableList<IntArray> = mutableListOf()
        
        for (i in intervals) {
            if (merged.isNotEmpty() && i[0] <= merged[merged.size - 1][1]) {
                merged[merged.size - 1][1] = max(i[1], merged[merged.size - 1][1])
            } else {
                merged.add(i)
            }
        }
        return merged.toTypedArray()
    }
}