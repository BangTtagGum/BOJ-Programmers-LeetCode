/**
 * 2023.08.18
 * 카드 뭉치
 */
public class _159994 {
    public static String solution(String[] cards1, String[] cards2, String[] goal) {
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;

        while (true) {
            if (p3 == goal.length) {
                return "Yes";
            }
            if (p1 < cards1.length && cards1[p1].equals(goal[p3])) {
                p1++;
                p3++;
            } else if (p2 < cards2.length && cards2[p2].equals(goal[p3])) {
                p2++;
                p3++;
            } else {
                return "No";
            }
        }
    }

    public static void main(String[] args) {
        String c1[] = {"i", "water", "drink"};
        String c2[] = {"want", "to"};
        String goal[] = {"i", "want", "to", "drink", "water"};
        System.out.println(solution(c1, c2, goal));
    }

}
