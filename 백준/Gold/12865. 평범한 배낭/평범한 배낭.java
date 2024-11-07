import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] items = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            items[i][0] = Integer.parseInt(st.nextToken());
            items[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] storage = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            for (int capacity = 0; capacity <= k; capacity++) {
                if (i == 0 || capacity == 0) {
                    storage[i][capacity] = 0;
                } else if (items[i][0] <= capacity) {
                    storage[i][capacity] = Math.max(
                            storage[i - 1][capacity - items[i][0]] + items[i][1],
                            storage[i - 1][capacity]);
                } else {
                    storage[i][capacity] = storage[i - 1][capacity];
                }
            }
        }

        System.out.println(storage[n][k]);
        br.close();
    }
}
