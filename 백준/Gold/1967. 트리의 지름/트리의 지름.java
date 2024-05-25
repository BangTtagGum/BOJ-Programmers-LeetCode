import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {


    static class TreeNode {

        // 노드 번호
        int val;

        // 자식 노드들
        ArrayList<TreeNode> childs = new ArrayList<>();

        TreeNode(int val) {
            this.val = val;
        }
    }

    static StringTokenizer st;
    static int n;
    static int longest;
    static Map<Integer, Map<Integer, Integer>> edgeValueMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        edgeValueMap = new HashMap<>();

        TreeNode[] trees = new TreeNode[n + 1];
        for (int i = 1; i <= n; i++) {
            trees[i] = new TreeNode(i);
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            edgeValueMap.putIfAbsent(start, new HashMap<>());
            edgeValueMap.get(start).put(end, value);
            trees[start].childs.add(trees[end]);
        }

        dfs(trees[1]);

        System.out.println(longest);
    }

    static int dfs(TreeNode node) {
        // 자식이 없으면 상태값 0 반환
        if (node.childs.isEmpty()) {
            return 0;
        }

        // (자식 노드의 상태값(리프노드에서 자식노드까지의 최대 거리) + 현재 노드와 자식노드 사이 거리) 저장 큐
        // 내림차순 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        // 현재 노드의 자식들 탐색
        for (TreeNode child : node.childs) {
            // 자식의 상태값
            int childStatusValue = dfs(child);

            // node -> child 거리
            int distanceToChild = edgeValueMap.get(node.val).get(child.val);

            // 우선순위 큐에 내림차순으로 저장
            pq.add(childStatusValue + distanceToChild);
        }

        // 자식 노드 중 최대 거리
        int longestChildDistance = pq.poll();

        // 자식 노드 중두번쨰로 긴 거리
        int secondLongestChildDistance = 0;
        if (!pq.isEmpty()) {
            secondLongestChildDistance = pq.poll();
        }

        // 현재 노드의 지름과 최대값 비교
        longest = Math.max(longest, longestChildDistance + secondLongestChildDistance);

        // 리프노드에서 현재 노드까지 가장 긴 거리(상태값) 반환
        return longestChildDistance;
    }
}
