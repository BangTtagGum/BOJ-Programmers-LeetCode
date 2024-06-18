class Solution {
    public boolean isAnagram(String s, String t) {
        return sort(s).equals(sort(t));
    }

    public String sort(String s){
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}