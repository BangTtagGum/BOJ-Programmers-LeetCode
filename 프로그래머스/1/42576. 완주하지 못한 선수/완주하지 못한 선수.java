import java.util.HashMap;
import java.util.Map;

class Solution {
    public String solution(String[] participant, String[] completion) {

        Map<String,Integer> map = new HashMap<>();
        for (int i = 0; i < completion.length; i++) {
            if(map.containsKey(completion[i])){
                int num = map.get(completion[i]);
                map.put(completion[i],num+1);
            }else{
                map.put(completion[i], 1);
            }
        }
        for (int i = 0; i < participant.length; i++) {
            if (!map.containsKey(participant[i])) {
                return participant[i];
            }
            int num = map.get(participant[i]);
            if(num == 1)
                map.remove(participant[i]);
            else
                map.put(participant[i],num -1);
        }
        return null;
    }
}