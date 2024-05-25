import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String num = br.readLine();

        int oddCount = 0;
        int evenCount = 0;

        for (int i = 0; i < n; i++) {
            if ((num.charAt(i) - '0') % 2 == 1) {
                oddCount++;
            } else {
                evenCount++;
            }
        }

        if (oddCount > evenCount) {
            System.out.println(1);
        } else if (oddCount < evenCount) {
            System.out.println(0);
        } else {
            System.out.println(-1);
        } 
    }
}