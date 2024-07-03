class Solution {
    public int characterReplacement(String s, int k) {
        int maxCount = 0;
        int result = s.length();
        int left = 0, right = 0;

        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            right++;

            map.put(c, map.getOrDefault(c, 0) + 1);
            maxCount = Collections.max(map.values());

            if (right - left - maxCount > k) {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                left++;
            } else if (right - left - maxCount == k) {
                result = right - left;
            }

        }

        return result;
    }
}