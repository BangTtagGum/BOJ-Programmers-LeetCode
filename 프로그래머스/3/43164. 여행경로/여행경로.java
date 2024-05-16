import java.util.*;

class Solution {
    public String[] solution(String[][] tickets) {
        Map<String, PriorityQueue<String>> fromToMap = new HashMap();
        
        for(String[] ticket : tickets){
            fromToMap.putIfAbsent(ticket[0], new PriorityQueue<>());
            fromToMap.get(ticket[0]).add(ticket[1]);
        }
        
        List<String> result = new LinkedList<>();
        Deque<String> stack = new ArrayDeque<>();
        
        stack.push("ICN");
        while(!stack.isEmpty()){
            while(fromToMap.containsKey(stack.peek()) && 
                  !fromToMap.get(stack.peek()).isEmpty()){
                stack.push(fromToMap.get(stack.peek()).poll());
            }
            
            result.add(0,stack.pop());
        }
        
        return result.toArray(new String[0]);
    }
}