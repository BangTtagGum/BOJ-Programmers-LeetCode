import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Comparator;




class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<Job> pq = new PriorityQueue<>();


        int time = 0;
        int totalTime = 0;

        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]- o2[0];
            }
        });
        
        
        for (int i = 0; i < jobs.length; i++) {
            // 작업 수행하는동안 들어온 작업들 집어넣기
            if (time >= jobs[i][0]) {
                pq.add(new Job(jobs[i][0], jobs[i][1]));
                continue;
            }
            
            if(pq.isEmpty()){
                time++;
                i--;
                continue;
            }
            i--;

            // 가능한 작업중 가장 빠른 작업 수행
            Job currentJob = pq.poll();
            

            time += currentJob.taskTime;
            totalTime += time - currentJob.requestTime; // 총 소요시간 = 현재 시간 - 요청 시간
        }

        //모든 작업이 힙에 다 들어가면 순서대로 작업 수행
        while (!pq.isEmpty()) {
            Job currentJob = pq.poll();
            time += currentJob.taskTime;
            totalTime += time - currentJob.requestTime;
        }

        
        return totalTime / jobs.length;
    }
    class Job implements Comparable<Job> {

        int taskTime;
        int requestTime;

        public Job(int requestTime, int taskTime) {
            this.requestTime = requestTime;
            this.taskTime = taskTime;
        }

        @Override
        public int compareTo(Job o) {
            return this.taskTime - o.taskTime;
        }
    }
}