import java.util.ArrayDeque
import java.util.PriorityQueue
import java.util.LinkedList

class Solution {
    fun findItinerary(tickets: List<List<String>>): List<String> {

        val fromToMap: MutableMap<String,PriorityQueue<String>> = mutableMapOf()

        for (ticket in tickets) {
            fromToMap.putIfAbsent(ticket[0], PriorityQueue())
            fromToMap[ticket[0]]!!.add(ticket[1])
        }

        val result: LinkedList<String> = LinkedList()
        val stack: Deque<String> = ArrayDeque()

        stack.push("JFK")

        while(!stack.isEmpty()){
            while (fromToMap.containsKey(stack.peek()) && !fromToMap[stack.peek()]!!.isEmpty()) {
                stack.push(fromToMap[stack.peek()]!!.poll())
            }

            result.add(0, stack.pop())
        }
        
        return result
    }
}