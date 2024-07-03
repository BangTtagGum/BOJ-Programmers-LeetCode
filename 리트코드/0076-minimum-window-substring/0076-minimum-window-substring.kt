class Solution {
    fun minWindow(s: String, t: String): String {
        var left = 0
        var right = 0
        var start = 0
        var end = 0
        var minLen = Integer.MAX_VALUE
        var missing = t.length

        var need: MutableMap<Char,Int> = mutableMapOf()
        
        for (c in t.toCharArray()){
            need.put(c, need.getOrDefault(c, 0) + 1)
        }
        
        for(c in s.toCharArray()){
            right++;
            if (!need.containsKey(c)) {
                continue
            }
            if(need[c]!! > 0){
                missing--
            }
            need[c] = need[c]!! - 1
            
            if(missing == 0){
                while(left < right){
                    if (!need.containsKey(s[left])) {
                        left++
                        continue
                    }
                    if(need[s[left]]!! < 0){
                        need[s[left]] = need[s[left]]!! + 1
                        left++
                        continue
                    }
                    break
                }
                if(minLen > right - left){
                    minLen = right - left
                    start = left
                    end = right
                }
                need[s[left]] = need[s[left]]!! + 1
                left++
                missing++
            }
        }
        
        return s.substring(start, end)
    }
}