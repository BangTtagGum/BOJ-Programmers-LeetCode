/**
 * 2023.08.18
 * 문자열 내 마음대로 정렬하기
 */
public class _161989 {
    public static int solution(int n, int m, int[] section) {
        int answer = 0;

        boolean dirty[] = new boolean[n+m];
        for (int i : section) {
            dirty[i] = true;
        }
        for (int i = 1; i <= n; i++) {
            if (dirty[i]) {
                answer++;
                for (int j = 0; j < m; j++) {
                    dirty[i + j] = false;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] section = {1,2,3,4};
        System.out.println(solution(4, 1, section));
    }
}
