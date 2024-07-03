class Solution {
        public static String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();


        for(char c : t.toCharArray()){
            need.put(c, need.getOrDefault(c,0) + 1);
        }
        int start = 0, end= 0, left = 0, right = 0;
        int minLen = Integer.MAX_VALUE;
        int missing = t.length();

        for(char c : s.toCharArray()){
            right++;
            if(!need.containsKey(c)){
                continue;
            }
            
            if(need.get(c) > 0){
                missing--;
                
                if(missing == 0){
                    
                    while (left < right) {
                        if (!need.containsKey(s.charAt(left))) {
                            left++;
                            continue;
                        }
                        if (need.get(s.charAt(left)) < 0) {
                            need.put(s.charAt(left),need.get(s.charAt(left)) + 1);
                            left++;
                            continue;
                        }
                        break;
                    }
                    if(minLen > right - left ){
                        minLen = right - left ;
                        start = left;
                        end = right;
                    }
                    need.put(s.charAt(left),need.get(s.charAt(left)) + 1);
                    left++;
                    missing++;
                }
            }
            need.put(c, need.get(c) -1);
        }

        return s.substring(start,end);
    }

}