import java.util.ArrayList;
import java.util.Stack;

/**
 * 2023.08.21
 * 햄버거 만들기
 */
public class _133502 {
    public static int solution(int[] ingredient) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i : ingredient) {
            stack.push(i);

            if (stack.size() >= 4) {

                if (stack.get(stack.size() - 4) == 1 &&
                        stack.get(stack.size() - 3) == 2 &&
                        stack.get(stack.size() - 2) == 3 &&
                        stack.get(stack.size() - 1) == 1) {
                    answer++;
                    stack.pop();
                    stack.pop();
                    stack.pop();
                    stack.pop();
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int arr[] = {2, 1, 1, 2, 3, 1, 2, 3, 1};
        System.out.println(solution(arr));

    }
}
