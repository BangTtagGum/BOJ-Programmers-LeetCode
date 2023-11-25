import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class _150370 {
    public int[] solution(String today, String[] terms, String[] privacies) {
        StringTokenizer st = new StringTokenizer(today,".");
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int days= Integer.parseInt(st.nextToken());
        ArrayList<Integer> save = new ArrayList<>();


        Map<String, Integer> map = new HashMap<>();
        for (String term : terms) {
            st = new StringTokenizer(term," ");
            map.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < privacies.length; i++) {

            st = new StringTokenizer(privacies[i]," .");
            int pyear = Integer.parseInt(st.nextToken());
            int pmonth = Integer.parseInt(st.nextToken());
            int pdays = Integer.parseInt(st.nextToken());
            String type = st.nextToken();
            System.out.println("pmonth = " + pmonth);
            pmonth += map.get(type);
            System.out.println("pmonth = " + pmonth);

            if (pmonth > 12) {
                pmonth = (+ 11) % 12 + 1;
                pyear++;
            }

            if (year > pyear) {
                save.add(i+1);
            } else if (year == pyear){
                if (month > pmonth) {
                    save.add(i+1);
                } else if (month == pmonth) {
                    if (days >= pdays) {
                        save.add(i + 1);
                    }
                }
            }
        }
        int[] answer = new int[save.size()];
        int idx = 0;
        for (Integer integer : save) {
            answer[idx++] = integer;
        }
        return answer;
    }

    public static void main(String[] args) throws ParseException {
        for (int i = 1; i < 100; i++) {
            System.out.println((i+ 11) % 12 + 1);
        }
    }
}
