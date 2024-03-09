class Solution {
    /**
     * 문자열 직접 비교
     */
    public static boolean isPalindrome(String s) {
        String s_filtered = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String s_reversed = new StringBuilder(s_filtered).reverse().toString();
        return s_filtered.equals(s_reversed);
    }
}