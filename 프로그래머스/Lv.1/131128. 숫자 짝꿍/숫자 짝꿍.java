import java.util.ArrayList;
import java.util.Comparator;
class Solution {
    public String solution(String X, String Y) {
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
            answer.append(-1);
        }
        if (answer.charAt(0) == '0') {
            answer.delete(1, answer.length());
        }
        return answer.toString();
    }
}