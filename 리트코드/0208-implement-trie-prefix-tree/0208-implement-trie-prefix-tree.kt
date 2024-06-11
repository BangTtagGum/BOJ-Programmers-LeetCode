class TrieNode(){
    var word:Boolean = false

    val children: Array<TrieNode?> = arrayOfNulls(26)
}
class Trie() {
    val root:TrieNode = TrieNode()
    
    fun insert(word: String) {
        var cur:TrieNode = root;
        for(c in word){
            if(cur.children[c -'a'] == null){
                cur.children[c-'a'] = TrieNode()
            }
            cur = cur.children[c-'a']!!
        }
        cur.word = true
    }

    fun search(word: String): Boolean {
        var cur:TrieNode = root;
        for(c in word){
            if(cur.children[c -'a'] == null){
                return false
            }
            cur = cur.children[c-'a']!!
        }
        return cur.word
    }

    fun startsWith(prefix: String): Boolean {
        var cur:TrieNode = root;
        for(c in prefix){
            if(cur.children[c -'a'] == null){
                return false
            }
            cur = cur.children[c-'a']!!
        }
        return true
    }

}

/**
 * Your Trie object will be instantiated and called as such:
 * var obj = Trie()
 * obj.insert(word)
 * var param_2 = obj.search(word)
 * var param_3 = obj.startsWith(prefix)
 */