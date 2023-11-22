import java.util.Arrays;
import java.util.Collections;
/**
 * 2023.08.18
 * 과일 장수
 */
public class _135808 {
    public static int solution(int k, int m, int[] score) {

        int answer = 0;
        Arrays.sort(score);
        for (int i = score.length-m; i >=0 ; i-=m) {
            answer += score[i] * m;
        }
        return answer;
    }

    public static void main(String[] args) {
        int arr[] = {4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2};
        System.out.println(solution(4, 3, arr));
    }
}
