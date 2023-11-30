/**
 * 2023.08.21
 * 둘만의 암호
 */
public class _155652 {
    public String solution(String s, String skip, int index) {
        String answer = "";

        for (int i = 0; i < s.length(); i++) {
            char changeChar = s.charAt(i);
            for (int j = 0; j < index; j++) {
                if (changeChar == 'z') {
                    changeChar = 'a';
                } else {
                    changeChar++;
                }
                while (skip.contains(String.valueOf(changeChar))) {
                    if (changeChar == 'z') {
                        changeChar = 'a';
                    } else {
                        changeChar++;
                    }
                }
            }
            answer += changeChar;
        }
        return answer;
    }

    public static void main(String[] args) {

    }

}
