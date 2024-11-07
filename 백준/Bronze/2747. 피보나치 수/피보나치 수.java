import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int x = 0, y = 1;
        for (int i = 0; i < n; i++) {
            int z = x + y;
            x = y;
            y = z;
        }
        System.out.println(x);

        br.close();
    }
}
