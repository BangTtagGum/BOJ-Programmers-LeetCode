import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;

    static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int k = Integer.parseInt(br.readLine());

            Queue<Integer> minQueue = new PriorityQueue<>();
            Queue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());

            map = new HashMap<>();

            for (int i = 0; i < k; i++) {
                String instruction = br.readLine();
                String[] op = instruction.split(" ");
                if (op[0].equals("I")) {
                    int num = Integer.parseInt(op[1]);
                    minQueue.add(num);
                    maxQueue.add(num);
                    map.put(num, map.getOrDefault(num, 0) + 1);

                } else if (op[0].equals("D")) {
                    if (map.size() == 0) {
                        continue;
                    }
                    if (op[1].equals("1")) {
                            remove(maxQueue);
                    } else {
                            remove(minQueue);
                    }
                }
            }

            if (map.size() == 0) {
                sb.append("EMPTY").append("\n");
            } else if (map.size() == 1) {
                int min = remove(minQueue);
                sb.append(min).append(" ").append(min).append("\n");
            } else {
                sb.append(remove(maxQueue)).append(" ").append(remove(minQueue)).append("\n");
            }

        }

        System.out.println(sb);
    }

    private static int remove(Queue<Integer> q) {
        int num = 0;
        while (true) {
            num = q.poll();

            int cnt = map.getOrDefault(num, 0);
            if (cnt == 0) {
                continue;
            }
            if (cnt == 1) {
                map.remove(num);
            } else {
                map.put(num, cnt - 1);
            }
            break;
        }
        return num;
    }
}