/**
 * 2023.08.18
 * 가장 가까운 같은 글자
 */
public class _142086 {

    public static int[] solution(String s) {
        int[] answer = new int[s.length()];

        int[] alpha = new int[123]; // idx는 알파벳, 값은 문자열 중 가장 최근 인덱스 (0 구분을 위해 첫 인덱스는 1로 시작)
                                    // ex) alpha['s'] = 1  => s 문자가 0번째에 나온게 가장 처음임
        int idx =0;
        for (int i = 0; i < s.length(); i++) {
            if (alpha[s.charAt(i)] == 0) { // 전에 나온적 없다면
                alpha[s.charAt(i)] = i + 1; // 가장 최근에 나온 인덱스 저장
                answer[idx++] = -1;
            } else {
                answer[idx++] = i + 1 - alpha[s.charAt(i)];
                alpha[s.charAt(i)] = i + 1; // 알파벳 최근 인덱스 갱신
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int answer[] = solution("banana");
        for (int i : answer) {
            System.out.println("i = " + i);
        }

    }
}
