class Solution {
    fun isValid(s: String): Boolean {
        val stack: Deque<Char> = ArrayDeque()
        val table: MutableMap<Char,Char> = mutableMapOf(
            ')' to '(',
            '}' to '{',
            ']' to '['
        )

        for (i in s.indices) {
            if (!table.containsKey(s[i])) {
                stack.push(s[i])
            } else if (stack.isEmpty() || table[s[i]] !== stack.pop()) {
                return false
            }
        }
        return stack.size == 0
    }
}