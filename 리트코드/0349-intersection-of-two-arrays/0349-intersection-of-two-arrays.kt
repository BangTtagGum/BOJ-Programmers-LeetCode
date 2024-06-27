class Solution {
    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        
        val result:MutableSet<Int> = HashSet()
        Arrays.sort(nums2);
        for(n1 in nums1){

            val idx = Arrays.binarySearch(nums2, n1)
            if(idx >= 0){
                result.add(n1)
            }
        }
       return result.toIntArray()
    }
}