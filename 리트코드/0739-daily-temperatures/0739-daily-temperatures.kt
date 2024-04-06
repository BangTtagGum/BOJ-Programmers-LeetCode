import java.util.ArrayDeque


class Solution {
    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val stack: Deque<Int> = ArrayDeque()
        val answer = IntArray(temperatures.size)

        for (i in 0 until temperatures.size) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                val pop = stack.pop()
                answer[pop] = i - pop
            }
            stack.push(i)
        }

        return answer
    }
}