class Solution {
    fun search(nums: IntArray, target: Int): Int {
        fun binarySearch(left:Int, right:Int): Int{
            if(left <= right){
                val mid = (left + right)/2
                if(nums[mid] > target){
                    return binarySearch(left, mid-1)
                }else if(nums[mid] < target){
                    return binarySearch(mid+1, right)
                }else{
                    return mid
                }
            }
            return -1
        }

        return binarySearch(0,nums.size - 1)
    }
}