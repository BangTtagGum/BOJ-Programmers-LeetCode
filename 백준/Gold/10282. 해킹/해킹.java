import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int t, n, d,c;
    static StringTokenizer st;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {

            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            Map<Integer, Map<Integer, Integer>> virusMap = new HashMap<>();


            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                virusMap.putIfAbsent(b, new HashMap<>());
                virusMap.get(b).put(a, s);
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            Map<Integer, Integer> infectionMap = new HashMap<>();

            int infectionCount = 0;
            int totalTimeCost = 0;

            pq.add(new int[]{c, 0});
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int computerNum = cur[0];
                int time = cur[1];


                if (!infectionMap.containsKey(computerNum)) {
                    infectionMap.put(computerNum, time);

                    totalTimeCost = time;
                    infectionCount++;

                    if (virusMap.containsKey(computerNum)) {
                        for (Entry<Integer, Integer> e : virusMap.get(computerNum).entrySet()) {
                            pq.add(new int[]{e.getKey(), time + e.getValue()});
                        }
                    }
                }
            }

            System.out.println(infectionCount + " " + totalTimeCost);
        }

    }


}
