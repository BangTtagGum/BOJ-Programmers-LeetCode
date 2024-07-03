class Solution {
    public int characterReplacement(String s, int k) {
        
        int left = 0;
        Map<Character, Integer> counts = new HashMap<>();

        for (int right = 1; right <= s.length(); right++) {
            counts.put(s.charAt(right - 1), counts.getOrDefault(s.charAt(right - 1), 0) + 1);
            int maxCharCount = Collections.max(counts.values());
            
            if(right - left - maxCharCount > k){
                counts.put(s.charAt(left), counts.get(s.charAt(left)) - 1);
                left++;
            }
        }
        return s.length() - left;
    }
}