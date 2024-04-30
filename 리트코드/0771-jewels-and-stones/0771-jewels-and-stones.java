class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> freqs = new HashSet<>();

        for (char c : jewels.toCharArray()) {
            freqs.add(c);
        }

        int answer = 0;
        
        for (char c : stones.toCharArray()) {
            if (freqs.contains(c)) {
                answer++;
            }
        }
        return answer;
    }
}