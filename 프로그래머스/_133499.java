
/**
 * 2023.08.18
 * 옹알이 (2)
 */
public class _133499 {

    public static int solution(String[] babbling) {
        int answer = 0;

        for (String s : babbling) {
            boolean flag = true;
            s = s.replace("aya", "0");
            s = s.replace("ye", "1");
            s = s.replace("woo", "2");
            s = s.replace("ma", "3");
            s += " ";
            for (int i = 0; i < s.length()-1; i++) {
                if (Character.isAlphabetic(s.charAt(i)) || s.charAt(i) == s.charAt(i+1)) { //중복 검사
                    flag = false;
                    break;
                }
            }
            if (flag) {
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] arr = {"ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"};
        System.out.println(solution(arr));
    }
}
