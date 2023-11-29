/**
 * 2023.08.18
 * 로또의 최고 순위와 최저 순위
 */
public class _77484 {
    public static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];

        int zeroNum = 0;
        int cnt = 0;
        for (int i = 0; i < 6; i++) {
            if (lottos[i] == 0) {
                zeroNum++;
                continue;
            }
            for (int j = 0; j < 6; j++) {
                if (lottos[i] == win_nums[j]) {
                    cnt++;
                }
            }
        }

        switch (cnt + zeroNum) {
            case 6:
                answer[0] = 1;
                break;
            case 5:
                answer[0] = 2;
                break;
            case 4:
                answer[0] = 3;
                break;
            case 3:
                answer[0] = 4;
                break;
            case 2:
                answer[0] = 5;
                break;
            default :
                answer[0] = 6;
        }
        switch (cnt) {
            case 6:
                answer[1] = 1;
                break;
            case 5:
                answer[1] = 2;
                break;
            case 4:
                answer[1] = 3;
                break;
            case 3:
                answer[1] = 4;
                break;
            case 2:
                answer[1] = 5;
                break;
            default :
                answer[1] = 6;
        }
        return answer;
    }

    public static void main(String[] args) {

    }

}
