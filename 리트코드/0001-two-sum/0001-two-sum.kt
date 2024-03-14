class Solution {
    /**
    * 조회 구조 개선
    */
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val numsMap: MutableMap<Int, Int> = mutableMapOf()

        for ((i,num) in nums.withIndex()) {

            if (numsMap.containsKey(target - num)) {
                return intArrayOf(numsMap[target - num] ?: 0, i)
            }
            numsMap[num] = i
        }

        return intArrayOf(0, 0)
    }
}