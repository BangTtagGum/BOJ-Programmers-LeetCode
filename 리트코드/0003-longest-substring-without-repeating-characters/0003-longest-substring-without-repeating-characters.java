class Solution {
        public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> used = new HashMap<>();
        int maxLen = 0;
        int left = 0, right = 0;

        for (char c : s.toCharArray()) {

            if (used.containsKey(c) && left <= used.get(c)) {
                // 마지막으로 저장되었던 위치 다음으로 이동
                left = used.get(c) + 1;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
            used.put(c,right);
            right++;
            
        }
        return maxLen;
    }

}