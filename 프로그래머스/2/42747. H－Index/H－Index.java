import java.util.*;
class Solution {
    public int solution(int[] citations) {
        
        Arrays.sort(citations);
        
        int len = citations.length;
        int h = 0; 
        for(int i = 0; i < len; i++){
            h = len - i;
            if(citations[i] >= h){
                return h;
            }
        }
        return 0;
    }
}