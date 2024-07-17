import java.util.*;
import java.util.Map.Entry;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> reportedMap = new HashMap<>();
        Map<String, Integer> mailCountMap = new HashMap<>();
        
        // init
        for(String id : id_list){
            reportedMap.put(id,new HashSet<>());
            mailCountMap.put(id,0);
        }
        
        for(String reportContents : report){
            String[] ids = reportContents.split(" ");
            String reportId = ids[0];
            String reportedId = ids[1];
            reportedMap.get(reportedId).add(reportId);
        }
        
        for(Entry<String,Set<String>> e : reportedMap.entrySet()){
            if(e.getValue().size() >= k){
                for(String id : e.getValue()){
                    mailCountMap.put(id,mailCountMap.get(id)+1);
                }
            }
        }
        
        return Arrays.stream(id_list).map(x -> mailCountMap.get(x))
                .mapToInt(Integer::valueOf).toArray();

    }
}