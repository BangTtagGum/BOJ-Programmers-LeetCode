class Solution {
    public Set<Character> toSortedSet(String s) {
        Set<Character> set = new TreeSet<>((c1, c2) -> {
            if (c1 == c2) {
                return 0;
            } else if (c1 > c2) {
                return 1;
            } else {
                return -1;
            }
        });

        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }
        return set;
    }

    public String removeDuplicateLetters(String s) {
        for (char c : toSortedSet(s)) {

            String suffix = s.substring(s.indexOf(c));

            if (toSortedSet(s).equals(toSortedSet(suffix))) {
                return c + removeDuplicateLetters(suffix.replace(String.valueOf(c), ""));
            }
        }
        return "";
    }
}