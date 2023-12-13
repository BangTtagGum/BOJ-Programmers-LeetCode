import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * 2023.12.12
 * 숨바꼭질 4
 */
public class Main {
    static int[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        visited = new int[100001];

        for (int i = 0; i < 100001; i++) {
            visited[i] = -1;
        }

        int n = sc.nextInt();
        int k = sc.nextInt();

        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        visited[n] = 0;

        int time = 1;
        while (!q.isEmpty()) {

            int len = q.size();
            for (int i = 0; i < len; i++) {
                int now = q.poll();

                if (now == k) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(visited[k]).append('\n');
                    Stack<Integer> memory = findRoute(now);
                    int memorySize = memory.size();
                    for (int j = 0; j < memorySize; j++) {
                        sb.append(memory.pop()).append(' ');
                    }
                    System.out.println(sb);
                    return ;
                }

                for (int j = 0; j < 3; j++) {
                    if (j == 0) {
                        int next = now * 2;
                        if (isValid(next)) {
                            visited[next] = time;

                            q.add(next);
                        }
                    } else if (j == 1) {
                        int next = now + 1;
                        if (isValid(next)) {
                            visited[next] = time;
                            q.add(next);
                        }
                    } else {
                        int next = now - 1;
                        if (isValid(next)) {
                            visited[next] = time;
                            q.add(next);
                        }
                    }
                }
            }
            time++;
        }




        sc.close();

    }

    private static Stack<Integer> findRoute(Integer now) {
        int time = visited[now];

        Stack<Integer> stack = new Stack<>();
        stack.add(now);

        while (time > 0) {
            time --;

            for (int j = 0; j < 3; j++) {
                if (j == 0 && now % 2 == 0) {
                    int next = now / 2;
                    if (isValid2(next, time)) {
                        stack.add(next);
                        now = next;
                        break;
                    }
                } else if (j == 1) {
                    int next = now + 1;
                    if (isValid2(next, time)) {
                        stack.add(next);
                        now = next;
                        break;
                    }
                } else {
                    int next = now - 1;
                    if (isValid2(next, time)) {
                        stack.add(next);
                        now = next;
                        break;
                    }
                }
            }
        }

        return stack;
    }

    public static boolean isValid(int location) {
        if (0 <= location && location <= 100000 && visited[location] == -1) {
            return true;
        }
        return false;
    }
    public static boolean isValid2(int location, int prev) {
        if (0 <= location && location <= 100000 && visited[location] == prev) {
            return true;
        }
        return false;
    }


}
