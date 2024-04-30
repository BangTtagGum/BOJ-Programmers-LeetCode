class Solution {
    fun numJewelsInStones(jewels: String, stones: String): Int {
    var answer = 0
    var freqs: MutableSet<Char> = HashSet()

    for (j in jewels.toCharArray()) {
        freqs.add(j)
    }
    for (s in stones.toCharArray()) {
        if(freqs.contains(s)) answer ++
    }
    return answer
}
}