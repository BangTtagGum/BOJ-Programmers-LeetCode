import java.util.ArrayList;
import java.util.*;

/**
 * 2023.08.21
 * 신고 결과 받기
 */
public class _92334 {
    public static int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Player> players = new HashMap<>(); // 플레이어 저장 리스트
        for (String s : id_list) {
            players.put(s,new Player(s));
        }

        for (String s : report) {
            StringTokenizer st = new StringTokenizer(s);
            Player sender = players.get(st.nextToken());
            Player receiver = players.get(st.nextToken());

            sender.report(receiver);
        }

        int[] answer = new int[id_list.length];
        int idx = 0;
        for (String s : id_list) {
            Player player = players.get(s);
            answer[idx++] = player.getMailCount(k);
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] ids = {"muzi", "frodo", "apeach", "neo"};
        String[] reports = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int[] ans = solution(ids, reports, 2);
        for (int an : ans) {
            System.out.println("an = " + an);
        }
    }
}

class Player{

    String name;

    int reportedNum = 0;

    Set<Player> reportPlayers = new HashSet<>();

    public Player(String name) {
        this.name = name;
    }

    public void report(Player player) {
        if (!this.reportPlayers.contains(player)) {
            player.reported();
        }
        this.reportPlayers.add(player);
    }
    public void reported() {
        this.reportedNum++;
    }

    public int getMailCount(int k) {
        int cnt = 0;
        for (Player reportPlayer : reportPlayers) {
            if (reportPlayer.reportedNum >= k) {
                cnt++;
            }
        }
        return cnt;
    }

}
