class Solution {
    fun letterCombinations(digits: String): List<String> {
        val result: MutableList<String> = mutableListOf()
        val dic: MutableMap<Char, List<Char>> = mutableMapOf(
            '0' to mutableListOf(),
            '1' to mutableListOf(),
            '2' to mutableListOf('a', 'b', 'c'),
            '3' to mutableListOf('d', 'e', 'f'),
            '4' to mutableListOf('g', 'h', 'i'),
            '5' to mutableListOf('j', 'k', 'l'),
            '6' to mutableListOf('m', 'n', 'o'),
            '7' to mutableListOf('p', 'q', 'r', 's'),
            '8' to mutableListOf('t', 'u', 'v'),
            '9' to mutableListOf('w', 'x', 'y', 'z')
        )
        fun dfs(index:Int, path: StringBuilder){
            if (digits.length == path.length) {
                result.add(path.toString())
                return 
            }
            for (c in dic[digits[index]]!!) {
                dfs(index + 1, StringBuilder(path).append(c))
            }
        }
        if(digits.isEmpty()) return result
        
        dfs(0, StringBuilder())
        return result
        
    }
}