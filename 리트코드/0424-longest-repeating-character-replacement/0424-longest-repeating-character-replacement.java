class Solution {
    public int characterReplacement(String s, int k) {
        int[] arrCount = new int[26];
        int left = 0;
        int maxCount = 0;
        int result = 0;

        for (int right = 1; right <= s.length(); right++) {
            arrCount[s.charAt(right - 1) - 'A']++;
            maxCount = Math.max(maxCount, arrCount[s.charAt(right - 1) - 'A']);
            if(right - left - maxCount > k){
                arrCount[s.charAt(left) - 'A']--;
                left++;
            }
            result = Math.max(result, right - left);
            
        }
        return result;
    }
}