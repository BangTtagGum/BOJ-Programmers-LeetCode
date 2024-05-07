import java.util.HashMap;
import java.util.Map;
class Solution {
    public String solution(String[] participant, String[] completion) {

        
        Map<String,Integer> map = new HashMap<>();
        for(String name : participant){
            map.put(name, map.getOrDefault(name,0) + 1);
        }
        
        for(String name : completion){
            int left = map.get(name);
            if(left == 1){
                map.remove(name);
            } else{
              map.put(name, left - 1);  
            }
        }
        return map.entrySet().iterator().next().getKey();

    }
}