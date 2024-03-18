class Solution {
    fun threeSum(nums: IntArray): List<List<Int>> {
    var answer: MutableList<List<Int>> = mutableListOf()
    
    nums.sort()

    for (i in 0 until nums.size - 2) {
        if (i > 0 && nums[i] == nums[i - 1]) 
            continue

        var left = i + 1
        var right = nums.size - 1   

        while (left < right) {
            var sum = nums[i] + nums[left] + nums[right]

            if (sum < 0) {
                left++
            } else if (sum > 0) {
                right--
            } else {
                answer.add(listOf(nums[i],nums[left],nums[right]))

                while (left < right && nums[left] == nums[left + 1]) left++
                while (left < right && nums[right] == nums[right - 1]) right--
                    
                left++
                right--
            }
        }
    }
    return answer
}
}