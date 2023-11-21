/**
 * 2023.08.18
 * 모의고사 완전탐색
 */
public class _42840 {

    public static int[] solution(int[] answers) {

        int sum[] = new int[3];
        int[] pick1 = {1, 2, 3, 4, 5};
        int[] pick2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] pick3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        for (int i = 0; i < answers.length; i++) {
            sum[0] = pick1[i % 5] == answers[i] ? sum[0] + 1 : sum[0];
            sum[1] = pick2[i % 8] == answers[i] ? sum[1] + 1 : sum[1];
            sum[2] = pick3[i % 10] == answers[i] ? sum[2] + 1 : sum[2];

        }
        int max = Math.max(Math.max(sum[0], sum[1]), sum[2]);

        int cnt = 0;
        for (int i = 1; i <= 3; i++) {
            if (sum[i-1] == max) {
                cnt++;
            }
        }

        int[] answer = new int[cnt];
        int idx = 0;

        for (int i = 1; i <= 3; i++) {
            if (sum[i - 1] == max) {
                answer[idx++] = i;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int[] ans = solution(arr);

        for (int i : ans) {
            System.out.println("i = " + i);
        }

    }
}
