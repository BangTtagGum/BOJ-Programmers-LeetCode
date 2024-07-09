class Solution {
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> countMap = new HashMap<>();
        
        int left = 0;
        int right = 0;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            
            right++;
            countMap.put(s.charAt(i), countMap.getOrDefault(s.charAt(i), 0) + 1);
            
            int maxCount = Collections.max(countMap.values());

            if (right - left > k + maxCount) {
                countMap.put(s.charAt(left), countMap.get(s.charAt(left)) - 1);
                left++;
            } else {
                result = Math.max(result, right - left);
            }
        }
        return result;
    }
}