class Solution {
    fun lengthOfLongestSubstring(s: String): Int {
        var used :HashMap<Char,Int> = HashMap()
        var left = 0
        var right = 0
        var maxLength = 0

        for (c in s.toCharArray()) {
            if (left <= used.getOrDefault(c, -1)) {
                left = used.get(c)!! + 1
            } else {
                maxLength = Math.max(maxLength,right - left + 1)
            }
            used.put(c,right)
            right++
        }
        return maxLength
    }
}