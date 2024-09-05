import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static int[] population;
    static int totalPopulation;
    static int minDiff = Integer.MAX_VALUE;
    static int n;
    static Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        population = new int[n + 1];
        for (int i = 1; i <= n; i++) {

            population[i] = Integer.parseInt(st.nextToken());
            totalPopulation += population[i];
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            graph.putIfAbsent(i, new ArrayList<>());
            int size = Integer.parseInt(st.nextToken());
            for (int j = 0; j < size; j++) {
                graph.get(i).add(Integer.valueOf(st.nextToken()));
            }
        }
        int[] arr = new int[n+1];
        for (int i = 1; i < n + 1 ; i++) {
            arr[i] = i;
        }

        for (int i = 1; i < n; i++) {
            combination(arr, new ArrayList<>(), 1, i);
        }

        if (minDiff == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minDiff);
        }

    }

    static void combination(int[] arr, List<Integer> comb, int start, int r) {
        // 조합 확정
        if (r == 0) {
            int currentPopulation = 0;
            for (Integer i : comb) {
                currentPopulation += population[i];
            }
            int populationDiff = Math.abs((totalPopulation - currentPopulation) - currentPopulation);

            if(!isLinked(comb)){
                return;
            }
            ArrayList<Integer> area = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if (!comb.contains(i)) {
                    area.add(i);
                }
            }
            if (!isLinked(area)) {
                return;
            }

            if(minDiff > populationDiff) {
                minDiff = populationDiff;
            }

            return;
        }

        for (int i = start; i <= n; i++) {
            comb.add(arr[i]);
            combination(arr, comb, i + 1,  r - 1);
            comb.remove(comb.size() - 1);
        }
    }

    private static boolean isLinked(List<Integer> area) {
        boolean[] isVisited = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();
        Integer first = area.get(0);
        isVisited[first] = true;
        q.add(first);
        while (!q.isEmpty()) {
            Integer cur = q.poll();

            for (Integer i : graph.get(cur)) {
                if (area.contains(i) && !isVisited[i]) {
                    isVisited[i] = true;
                    q.add(i);
                }
            }
        }
        for (Integer i : area) {
            if (!isVisited[i]) {
                return false;
            }
        }

        return true;
    }
}