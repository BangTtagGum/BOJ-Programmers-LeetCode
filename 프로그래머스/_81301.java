import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 2023.08.18
 * 숫자 문자열과 영단어
 */
public class _81301 {
    public static int solution(String s) {
        int answer = 0;

        Map<String,String> map= new HashMap<>();
        map.put("zero","0");
        map.put("one","1");
        map.put("two","2");
        map.put("three","3");
        map.put("four","4");
        map.put("five","5");
        map.put("six","6");
        map.put("seven","7");
        map.put("eight","8");
        map.put("nine","9");
        for (String key : map.keySet()) {
            s = s.replace(key, map.get(key));
        }

        answer = Integer.parseInt(s);
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int ans = solution(s);

        System.out.println("ans = " + ans);
    }
}
