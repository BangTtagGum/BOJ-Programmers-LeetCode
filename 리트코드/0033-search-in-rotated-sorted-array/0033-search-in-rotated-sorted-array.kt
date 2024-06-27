class Solution {
    fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1
        while(left < right){
            val mid = left + (right - left) / 2
            when{
                nums[mid] < nums[right] -> right = mid
                else -> left = mid + 1
            }
        }
        val pivot = left

        left = 0
        right = nums.size - 1
        while(left <= right){
            val mid = left + (right - left) / 2
            val midPivot = (mid + pivot) % nums.size

            when{
                nums[midPivot] < target -> left = mid + 1
                nums[midPivot] > target -> right = mid -1
                else -> return midPivot
            }
        }
        return -1
    }
}