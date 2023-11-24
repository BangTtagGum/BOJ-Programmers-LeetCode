/**
 * 2023.08.21
 * 문자열 나누기
 */
public class _140108 {
    public static int solution(String s) {
        int answer = 0;
        int idx = 0;
        while (idx < s.length()) {
            int firstAlphaCnt = 1;
            int anotherAlphaCnt = 0;
            char firstAlpha = s.charAt(idx);
            idx++;
            while (true) {
                if (idx == s.length()) {
                    answer++;
                    break;
                }
                if (firstAlpha == s.charAt(idx)) {
                    firstAlphaCnt++;
                } else {
                    anotherAlphaCnt++;
                }
                idx++;
                if (firstAlphaCnt == anotherAlphaCnt) {
                    answer++;
                    break;
                }

            }
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("abracadabra"));
    }
}
