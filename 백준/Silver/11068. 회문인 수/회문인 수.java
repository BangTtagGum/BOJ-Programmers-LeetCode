import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            int num = Integer.parseInt(br.readLine());
            boolean chk = false;

            for (int base = 2; base <= 64 && !chk; base++) {
                ArrayList<Integer> conv = new ArrayList<>();
                int n = num;
                while (n != 0) {
                    conv.add(n % base);
                    n /= base;
                }
                boolean isPal = true;
                for (int j = 0; j < conv.size() / 2; j++) {
                    if (conv.get(j) != conv.get(conv.size() - 1 - j)) {
                        isPal = false;
                        break;
                    }
                }
                if (isPal) {
                    chk = true;
                }
            }

            sb.append(chk ? 1 : 0).append('\n');
        }

        System.out.print(sb);
    }
}
