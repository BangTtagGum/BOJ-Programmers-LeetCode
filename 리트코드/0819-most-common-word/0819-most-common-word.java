class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        String[] words = paragraph.replaceAll("\\W+", " ").toLowerCase().split(" ");
        Set<String> ban = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> count = new HashMap<>();

        for (String word : words) {
            if (!ban.contains(word)) {
                count.put(word, count.getOrDefault(word, 0) + 1);
            }
        }
        return Collections.max(count.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}