/**
 * 2023.08.18
 * 2016년
 */
public class _12901 {
    public static String solution(int a, int b) {
        int days[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int nowDay = 1;

        int diffDays = 0;
        for (int i = 0; i < a-1; i++) {
            diffDays += days[i];
        }
        diffDays += b - nowDay;
        diffDays %= 7;
        String answer = "";
        switch (diffDays) {
            case 0:
                answer = "FRI";
                break;
            case 1:
                answer = "SAT";
                break;
            case 2:
                answer = "SUN";
                break;
            case 3:
                answer = "MON";
                break;
            case 4:
                answer = "TUE";
                break;
            case 5:
                answer = "WED";
                break;
            case 6:
                answer = "THU";
                break;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(5, 24));
    }
}
