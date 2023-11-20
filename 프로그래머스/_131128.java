import java.util.ArrayList;
import java.util.Comparator;

/**
 * 2023.08.18
 * 숫자 짝꿍
 */
public class _131128 {
    public static String solution(String X, String Y) {
        StringBuilder answer = new StringBuilder();

        int cntX[] = new int[10];
        int cntY[] = new int[10];

        for (int i = 0; i < X.length(); i++) {
            cntX[X.charAt(i) - 48]++;
        }
        for (int i = 0; i <Y.length() ; i++) {
            cntY[Y.charAt(i) - 48]++;
        }

        for (int i = 9; i >= 0; i--) {
            String num = "" + i;
            answer.append(num.repeat(Math.min(cntX[i], cntY[i])));
        }
        if (answer.toString().equals("")) {
            return "-1";
        }
        if (answer.charAt(0) == '0') {
            return "0";
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        solution("123", "156");
    }
}
