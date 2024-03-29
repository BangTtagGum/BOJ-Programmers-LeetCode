
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * 2023.12.12
 * 숨바꼭질 4
 *
 * 첫번째 풀이 - 가는 위치마다 배열을 저장해두고 더해가는 방식으로 풀이 -> 계속 배열을 복사해 줘야해서 시간초과
 * 두번째 풀이 - 한번 최단거리를 다 구해놓고, 방문한 위치는 해당 위치까지의 최단시간만 저장.
 *             도착 지점부터 (최단시간 -1) 해가며 지나왔던 경로 거꾸로 저장 후출력
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
        int target = sc.nextInt();

        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        visited[n] = 0;

        int time = 1;
        while (!q.isEmpty()) {

            int len = q.size();
            for (int i = 0; i < len; i++) {
                int subin = q.poll();

                if (subin == target) {
                    StringBuilder builder = new StringBuilder();
                    builder.append(visited[target]).append('\n');
                    Stack<Integer> memory = findRoute(subin);
                    int memorySize = memory.size();
                    for (int j = 0; j < memorySize; j++) {
                        builder.append(memory.pop()).append(' ');
                    }
                    System.out.println(builder);
                    return ;
                }

                for (int j = 0; j < 3; j++) {
                    if (j == 0) {
                        int next = subin * 2;
                        if (isValid(next)) {
                            visited[next] = time;

                            q.add(next);
                        }
                    } else if (j == 1) {
                        int next = subin + 1;
                        if (isValid(next)) {
                            visited[next] = time;
                            q.add(next);
                        }
                    } else {
                        int next = subin - 1;
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
                    if (isPrev(next, time)) {
                        stack.add(next);
                        now = next;
                        break;
                    }
                } else if (j == 1) {
                    int next = now + 1;
                    if (isPrev(next, time)) {
                        stack.add(next);
                        now = next;
                        break;
                    }
                } else {
                    int next = now - 1;
                    if (isPrev(next, time)) {
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
    public static boolean isPrev(int location, int prev) {
        if (0 <= location && location <= 100000 && visited[location] == prev) {
            return true;
        }
        return false;
    }


}
