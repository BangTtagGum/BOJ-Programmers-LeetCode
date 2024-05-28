import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static int t,n,m;
    static int[] a,b;


    public static void main(String[] args) throws IOException {
        input();

        PriorityQueue<Integer> subA = new PriorityQueue<>((a1, a2) -> a1 - a2);
        PriorityQueue<Integer> subB = new PriorityQueue<>((b1, b2) -> b2 - b1);


        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += a[j];
                subA.add(sum);
            }
        }

        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = i; j < m; j++) {
                sum += b[j];
                subB.add(sum);
            }
        }

        long answer = 0;

        while (!subA.isEmpty() && !subB.isEmpty()) {
            int aSum = subA.peek();
            int bSum = subB.peek();

            if (aSum + bSum == t) {
                subA.poll();
                subB.poll();

                int sameACount = 1;
                int sameBCount = 1;
                while (!subA.isEmpty() && aSum == subA.peek()) {
                    subA.poll();
                    sameACount++;
                }
                while (!subB.isEmpty() && bSum == subB.peek()) {
                    subB.poll();
                    sameBCount++;
                }
                answer += (long) sameACount * sameBCount;
            }
            else if (aSum + bSum < t) {
                while (!subA.isEmpty() && aSum == subA.peek()) {
                    subA.poll();
                }
            } else if(aSum + bSum > t) {
                while (!subB.isEmpty() && bSum == subB.peek()) {
                    subB.poll();
                }
            }
        }
        System.out.println(answer);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        n = Integer.parseInt(br.readLine());
        a = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        b = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }
}