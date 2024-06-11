class Solution {

    class TrieNode() {
        var wordId: Int = -1
        val children: Array<TrieNode?> = arrayOfNulls(26)
        val palindromeIds: MutableList<Int> = mutableListOf()
    }

    class Trie() {
        val root = TrieNode()

        fun isPalindrome(word: String, start: Int, end: Int): Boolean {
            var s = start
            var e = end
            while (s < e) {
                if (word[s++] != word[e--]) {
                    return false
                }
            }
            return true
        }

        fun insert(word: String, index: Int) {
            var cur: TrieNode = root

            for (i in word.length - 1 downTo 0) {
                val c: Char = word[i]
                if (isPalindrome(word, 0, i)) {
                    cur.palindromeIds.add(index)
                }

                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = TrieNode()
                }

                cur = cur.children[c - 'a']!!
            }
            cur.wordId = index
        }

        fun search(word:String, index:Int): MutableList<List<Int>> {
            var cur: TrieNode = root
             
            val result: MutableList<List<Int>> = mutableListOf()

            for (i in word.indices) {
                val c: Char = word[i]
                if (cur.wordId >= 0 && isPalindrome(word, i, word.length - 1)) {
                    result.add(listOf(index, cur.wordId))
                }
                if (cur.children[c - 'a'] == null) {
                    return result
                }

                cur = cur.children[c - 'a']!!
            }
            if (cur.wordId >= 0 && index != cur.wordId) {
                result.add(listOf(index, cur.wordId))
            }
            for (id in cur.palindromeIds) {
                result.add(listOf(index, id))
            }

            return result
        }

    }

    fun palindromePairs(words: Array<String>): List<List<Int>> {
        
        val results: MutableList<List<Int>> = mutableListOf()

        val t: Trie = Trie()
        
        for (i in words.indices) {
            t.insert(words[i], i)
        }

        for (i in words.indices) {
            results.addAll(t.search(words[i], i))
        }
        
        return results
    }
}