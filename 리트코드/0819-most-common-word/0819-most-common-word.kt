class Solution {
    fun mostCommonWord(paragraph: String, banned: Array<String>): String {
        val counts: MutableMap<String, Int> = mutableMapOf()
        
        val words = paragraph.replace("\\W+".toRegex(), " ").lowercase().trim().split(" ")

        for (word in words) {
            if (!banned.contains(word)) {
                counts[word] = counts.getOrDefault(word, 0) + 1
            }
        }
        return counts.maxBy { it.value }!!.key
    }
}