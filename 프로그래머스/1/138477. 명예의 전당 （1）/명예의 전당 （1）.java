import java.util.Arrays;
import java.util.Comparator;
class Solution {
    public int[] solution(int k, int[] score) {
        Integer[] result = new Integer[score.length];
        int answer[] = new int[score.length];
        int idx = 0;
        for (int i = 0; i < score.length; i++) {
            result[i] = score[i];
            Arrays.sort(result,0,i+1, Comparator.reverseOrder());
            System.out.println();
            if (i < k) {
                answer[idx++] = result[i];
            }else {
                answer[idx++] = result[k - 1];
            }
        }
        return answer;
    }
}