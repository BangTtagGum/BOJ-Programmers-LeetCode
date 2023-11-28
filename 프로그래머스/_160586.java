/**
 * 2023.08.21
 * 대충 만든 자판
 */
public class _160586 {
    public static int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        int idx = 0;
        for (String target : targets) {
            int sum = 0;
            int min = 9999;
            for (int i = 0; i < target.length(); i++) {
                min = 9999;
                for (String s : keymap) {
                    for (int j = 0; j < s.length(); j++) {
                        if (target.charAt(i) == s.charAt(j)) {
                            min = Math.min(min, j+1);
                        }
                    }
                }
                if (min == 9999) {
                    answer[idx++] = -1;
                    break;
                } else {
                    sum += min;
                }
            }
            if (min != 9999) {
                answer[idx++] = sum;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] a = {"ABACD", "BCEFD"};
        String[] b = {
                "ABCD", "AABB"
        };
        int ans[] = solution(a, b);
        for (int an : ans) {
            System.out.println(
                    an
            );
        }
    }
}
