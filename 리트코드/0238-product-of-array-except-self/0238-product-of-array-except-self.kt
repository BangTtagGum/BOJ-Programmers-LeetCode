class Solution {
    fun productExceptSelf(nums: IntArray): IntArray {
    val answer = IntArray(nums.size)

    var p = 1
    for (i in nums.indices) {
        answer[i] = p
        p *= nums[i]
    }

    p = 1
    for (i in nums.indices.reversed()) {
        answer[i] *= p
        p *= nums[i]
    }
    return answer
}
}