import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());	

        while (T --> 0) {

            HashMap<String, Integer> map = new HashMap<>();	

            int N = Integer.parseInt(br.readLine());	

            while (N-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");

                st.nextToken(); 

                String s = st.nextToken(); 

                if (map.containsKey(s)) {
                    map.put(s, map.get(s) + 1);
                }
                else {
                    map.put(s, 1);
                }
            }

            int result = 1;

            for (int val : map.values()) {
                result *= (val + 1);
            }
            sb.append(result - 1).append('\n');

        }
        System.out.println(sb);
    }

}