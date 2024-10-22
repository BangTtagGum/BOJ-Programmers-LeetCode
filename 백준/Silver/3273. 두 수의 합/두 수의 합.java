import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public int count(int N, int[] arr, int X) {
        int result = 0;
        int sum = 0;
        
        int l = 0;
        int r = N - 1;

        Arrays.sort(arr);

        while (l < r) {
            sum = arr[r] + arr[l];

            if (sum == X) {
                result++;
            }

            if (sum < X) {
                l++;
            } else {
                r--;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        Main num = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int X = Integer.parseInt(br.readLine());

        System.out.println(num.count(N, arr, X));
    }
}