import java.util.*

class Solution {
    fun solution(tickets: Array<Array<String>>): Array<String> {
        val fromToMap: MutableMap<String, PriorityQueue<String>> = mutableMapOf()
        
        for(ticket in tickets){
            fromToMap.putIfAbsent(ticket[0], PriorityQueue())
            fromToMap[ticket[0]]!!.add(ticket[1])
        }
        
        val stack: Deque<String> = ArrayDeque()
        var answer:LinkedList<String> = LinkedList()
        
        stack.push("ICN")
        while(!stack.isEmpty()){
            while(fromToMap.containsKey(stack.peek()) && 
                    !fromToMap[stack.peek()]!!.isEmpty()){
                stack.push(fromToMap[stack.peek()]!!.poll())
            }
            answer.add(0,stack.pop())
        }
        
        return answer.toTypedArray()
    }
}