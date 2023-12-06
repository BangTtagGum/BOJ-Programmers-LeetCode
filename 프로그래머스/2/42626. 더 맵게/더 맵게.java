import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : scoville) {
            pq.add(i);
        }

        while (pq.peek() < K) {
            if(pq.size() == 1){
                return -1;
            }
            int mix = 0;
            mix += pq.poll();
            mix += pq.poll()*2;
            pq.add(mix);
            answer++;
        }



        return answer;
    }
}