import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int rows, cols;
    static int[] moveY = {-1, 0, 1, 0};
    static int[] moveX = {0, 1, 0, -1};
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());
        map = new char[rows][cols];
        visited = new boolean[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            String line = reader.readLine();
            for (int j = 0; j < cols; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        
        int maxDistance = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] == 'L') {
                    visited = new boolean[rows][cols];
                    int distance = bfs(i, j);
                    maxDistance = Math.max(maxDistance, distance);
                }
            }
        }

        System.out.println(maxDistance);
    }

    private static int bfs(int startY, int startX) {
        Queue<Point> queue = new LinkedList<>();
        int maxDistance = 0;
        visited[startY][startX] = true;
        queue.add(new Point(startX, startY, 0));
        
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            for (int d = 0; d < 4; d++) {
                int newX = current.x + moveX[d];
                int newY = current.y + moveY[d];
                if (0 <= newX && newX < cols && 0 <= newY && newY < rows && !visited[newY][newX] && map[newY][newX] == 'L') {
                    visited[newY][newX] = true;
                    queue.add(new Point(newX, newY, current.distance + 1));
                    maxDistance = Math.max(maxDistance, current.distance + 1);
                }
            }
        }
        return maxDistance;
    }

    public static class Point {
        int x;
        int y;
        int distance;

        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
