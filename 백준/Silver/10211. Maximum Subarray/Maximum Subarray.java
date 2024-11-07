import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] nums = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            ArrayList<Integer> sums = new ArrayList<>(List.of(nums[0]));
            for (int i = 1; i < n; i++) {
                sums.add(nums[i] + (sums.get(i - 1) > 0 ? sums.get(i - 1) : 0));
            }

            System.out.println(Collections.max(sums));
        }

        br.close();
    }
}
