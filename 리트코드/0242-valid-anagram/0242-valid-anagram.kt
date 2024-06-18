class Solution {
    fun isAnagram(s: String, t: String): Boolean {
        fun sort(s: String) : String{
            val chars = s.toCharArray()
            Arrays.sort(chars)
            return String(chars)
        }

        return sort(s) == sort(t)
    }
}