import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static char[] dir = {'N', 'W', 'S', 'E', 'U', 'D'};
    static int L,R,C;
    static char[][][] building;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        while (L != 0 || R != 0 || C != 0) {

            building = new char[R][C][L];

            SangBum sb = new SangBum();

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String lineInput = br.readLine();
                    for (int k = 0; k < C; k++) {
                        building[j][k][i] = lineInput.charAt(k);
                        if (building[j][k][i] == 'S') {
                            sb.row = j;
                            sb.column = k;
                            sb.floor = i;
                        }
                    }
                }
                br.readLine();
            }

            bfs(sb);

            st = new StringTokenizer(br.readLine());

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
        }

    }

    static void bfs(SangBum sb) {
        int time = 1;

        Queue<SangBum> q = new LinkedList<>();

        q.add(sb);
        while (!q.isEmpty()) {
            SangBum poll = q.poll();

            int r = poll.row;
            int c = poll.column;
            int f = poll.floor;

            int nr;
            int nc;
            int nf;

            for (int i = 0; i < 6; i++) {
                switch (dir[i]) {
                    case 'N':
                        nr = r - 1;
                        nc = c;
                        nf = f;

                        // validCheck
                        if (!isValid(nr, nc, nf)) {
                            break;
                        }

                        // endCheck
                        if (building[nr][nc][nf] == 'E') {
                            System.out.println("Escaped in " + poll.time + " minute(s).");
                            return;
                        }

                        q.add(new SangBum(nr,nc,nf,poll.time+1));
                        building[nr][nc][nf] = '#';
                        break;
                    case 'W':
                        nr = r;
                        nc = c -1;
                        nf = f;

                        // validCheck
                        if (!isValid(nr, nc, nf)) {
                            break;
                        }

                        // endCheck
                        if (building[nr][nc][nf] == 'E') {
                            System.out.println("Escaped in " + poll.time + " minute(s).");
                            return;
                        }

                        q.add(new SangBum(nr,nc,nf,poll.time+1));

                        building[nr][nc][nf] = '#';
                        break;
                    case 'S':
                        nr = r + 1;
                        nc = c;
                        nf = f;

                        // validCheck
                        if (!isValid(nr, nc, nf)) {
                            break;
                        }

                        // endCheck
                        if (building[nr][nc][nf] == 'E') {
                            System.out.println("Escaped in " + poll.time + " minute(s).");
                            return;
                        }

                        q.add(new SangBum(nr,nc,nf,poll.time+1));

                        building[nr][nc][nf] = '#';
                        break;
                    case 'E':
                        nr = r;
                        nc = c + 1;
                        nf = f;

                        // validCheck
                        if (!isValid(nr, nc, nf)) {
                            break;
                        }

                        // endCheck
                        if (building[nr][nc][nf] == 'E') {
                            System.out.println("Escaped in " + poll.time + " minute(s).");
                            return;
                        }

                        q.add(new SangBum(nr,nc,nf,poll.time+1));

                        building[nr][nc][nf] = '#';
                        break;
                    case 'U':
                        nr = r;
                        nc = c;
                        nf = f - 1;

                        // validCheck
                        if (!isValid(nr, nc, nf)) {
                            break;
                        }

                        // endCheck
                        if (building[nr][nc][nf] == 'E') {
                            System.out.println("Escaped in " + poll.time + " minute(s).");
                            return;
                        }

                        q.add(new SangBum(nr,nc,nf,poll.time+1));

                        building[nr][nc][nf] = '#';
                        break;
                    case 'D':
                        nr = r;
                        nc = c;
                        nf = f + 1;

                        // validCheck
                        if (!isValid(nr, nc, nf)) {
                            break;
                        }

                        // endCheck
                        if (building[nr][nc][nf] == 'E') {
                            System.out.println("Escaped in " + poll.time + " minute(s).");
                            return;
                        }

                        q.add(new SangBum(nr,nc,nf,poll.time+1));

                        building[nr][nc][nf] = '#';
                        break;
                }
            }
        }
        System.out.println("Trapped!");
    }

    static boolean isValid(int r, int c, int f) {
        if (0 <= r && r < R && 0 <= c && c < C && 0 <= f && f < L) {
            if (building[r][c][f] != '#') {
                return true;
            }
            return false;
        }
        return false;
    }

    static class SangBum {
        int row;
        int column;
        int floor;

        int time = 1;

        public SangBum() {
        }

        public SangBum(int row, int column, int floor, int time) {
            this.row = row;
            this.column = column;
            this.floor = floor;
            this.time = time;
        }
    }

}