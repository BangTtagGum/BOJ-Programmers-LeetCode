class Solution {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        for (i in numbers.indices) {
            val expected = target - numbers[i]
            val idx = Arrays.binarySearch(numbers, i + 1, numbers.size, expected)
            if (idx >= 0) {
                return intArrayOf(i + 1, idx + 1)
            }
        }
        return intArrayOf()
    }
}