import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, targetR, targetC, ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        targetR = Integer.parseInt(st.nextToken());
        targetC = Integer.parseInt(st.nextToken());
        System.out.println(solve(0, 0, (int) Math.pow(2, n)));
    }

    private static int solve(int r, int c, int size) {
        if (size == 1) {
            return ans;
        }
        int newSize = size / 2;
        if (targetR < r + newSize && targetC < c + newSize) {
            return solve(r, c, newSize);
        } else if (targetR < r + newSize && c + newSize <= targetC) {
            ans += (size * size) / 4;
            return solve(r, c + newSize, newSize);
        } else if (r + newSize <= targetR && targetC < c + newSize) {
            ans += ((size * size) / 4) * 2;
            return solve(r + newSize, c, newSize);
        } else {
            ans += ((size * size) / 4) * 3;
            return solve(r + newSize, c + newSize, newSize);
        }
     
    }
}
