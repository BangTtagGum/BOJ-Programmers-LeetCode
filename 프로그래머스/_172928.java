import java.util.StringTokenizer;

/**
 * 2023.08.21
 * 공원 산책
 */
public class _172928 {
    public int[] solution(String[] park, String[] routes) {

        String[][] map = new String[50][50];

        for (int i = 0; i < park.length; i++) {
            map[i] = park[i].split("");
        }
        int r=0,c=0;
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[i].length(); j++) {
                if (map[i][j].equals("S")) {
                    r = i;
                    c = j;
                    System.out.println(r +" "+c);
                }
            }
        }



        for (int i = 0; i < routes.length; i++) {
            StringTokenizer st = new StringTokenizer(routes[i]);
            String dir = st.nextToken();
            int dis = Integer.parseInt(st.nextToken());
            boolean flag = true;
            switch (dir) {

                case "E":
                    if (c + dis >= park[0].length()) {
                        break;
                    }
                    for (int j = 1; j <= dis; j++) {
                        if (map[r][c + j].equals("X") ) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        c = c + dis;

                    }
                    break;
                case "W":
                    if (c - dis < 0) {
                        break;
                    }
                    for (int j = 1; j <= dis; j++) {
                        if (map[r][c - j].equals("X")) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        c = c - dis;
                    }
                    break;
                case "S":
                    if (r + dis >= park.length) {
                        break;
                    }
                    for (int j = 1; j <= dis ; j++) {
                        if (map[r + j][c].equals("X")) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        r = r + dis;

                    }
                    break;
                case "N":
                    if (r - dis < 0) {
                        break;
                    }
                    for (int j = 1; j <= dis; j++) {
                        if (map[r - j][c].equals("X")) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        r = r - dis;
                    }
            }
            System.out.println("dir = " + dir);
            System.out.println("dis = " + dis);
            System.out.println("r = " + r + " " + c);
        }


        int[] answer = {r,c};

        return answer;
    }

    public static void main(String[] args) {

    }

}



