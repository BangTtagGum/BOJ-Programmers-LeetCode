class Solution {
    public static int leastInterval(char[] tasks, int n) {

        Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        int[] freqs = new int[26];
        for (char task : tasks) {
            freqs[task - 'A']++;
        }

        for (int i : freqs) {
            if (i > 0) {
                pq.add(i);
            }
        }

        int result = 0;

        while (true) {
            int intervals = 0;
            List<Integer> list = new ArrayList<>();
            while (!pq.isEmpty()) {
                int freq = pq.poll();
                if (intervals < n + 1) {
                    intervals++;
                    result++;
                    if (freq > 1) {
                        list.add(freq - 1);
                    }
                } else {
                    list.add(freq);
                }
            }
            if (list.isEmpty()) {
                break;
            }
            result += n + 1 - intervals;

            pq.addAll(list);
        }

        return result;
    }
}