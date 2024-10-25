import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] levels = new int[n];
        for (int i = 0; i < n; i++) {
            levels[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(levels);

        int result = 0;
        int left = levels[0];
        int right = levels[0] + k; 
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (levels[i] < mid) {
                    sum += mid - levels[i];
                }
            }
            if (sum <= k) {
                result = Math.max(result,mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(result);
    }
}