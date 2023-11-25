import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 2023.08.18
 * K번째수 정렬
 */

public class _42748 {

    public static int[] solution(int[] array, int[][] commands) {

        int answer[] = new int[commands.length];
        int idx =0;
        for (int[] command : commands) {
            int[] cloneArr = Arrays.copyOfRange(array, command[0] - 1, command[1]);
            Arrays.sort(cloneArr);
            answer[idx++] = cloneArr[command[2] - 1];
        }
        return answer;
    }
    public static void main(String[] args) {
        int arr[] = {1, 5, 2, 6, 3, 7, 4};
        int commands[][] = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        int[] ans = solution(arr, commands);
        for (int i : ans) {
            System.out.println("i = " + i);
        }
    }
}
