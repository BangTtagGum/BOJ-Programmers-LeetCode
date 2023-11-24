/**
 * 2023.08.21
 * 성격 유형 검사하기
 */
public class _118666 {

    public static String solution(String[] survey, int[] choices) {
        String answer = "";
        int score[] = new int[200];
        for (int i = 0; i < survey.length; i++) {
            switch (choices[i]) {
                case 1:
                    score[survey[i].charAt(0)] += 3;
                    break;
                case 2:
                    score[survey[i].charAt(0)] += 2;
                    break;
                case 3:
                    score[survey[i].charAt(0)] += 1;
                    break;
                case 4:
                    break;
                case 5:
                    score[survey[i].charAt(1)] += 1;
                    break;
                case 6:
                    score[survey[i].charAt(1)] += 2;
                    break;
                case 7:
                    score[survey[i].charAt(1)] += 3;
                    break;
            }
        }

        if (score['R'] >= score['T']) {
            answer += "R";
        } else {
            answer += "T";
        }
        if (score['C'] >= score['F']) {
            answer += "C";
        } else {
            answer += "F";
        }
        if (score['J'] >= score['M']) {
            answer += "J";
        } else {
            answer += "M";
        }
        if (score['A'] >= score['N']) {
            answer += "A";
        } else {
            answer += "N";
        }

        return answer;
    }

    public static void main(String[] args) {

    }
}

