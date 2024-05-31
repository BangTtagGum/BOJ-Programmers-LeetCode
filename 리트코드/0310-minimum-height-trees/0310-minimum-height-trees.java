class Solution {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        if (n == 1) {
            return List.of(0);
        }
        
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());

            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (graph.get(i).size() == 1) {
                leaves.add(i);
            }
        }
        while (n > 2) {
            n -= leaves.size();

            List<Integer> newLeaves = new ArrayList<>();
            for (Integer leaf : leaves) {
                // 리프 노드의 이웃 노드 추출
                int neighbor = graph.get(leaf).get(0);
                // 이웃 노드와 리프노드의 연결 삭제
                graph.get(neighbor).remove((Object) leaf);
                graph.get(leaf).remove((Object) neighbor); // 리프노드의 연결은 굳이 삭제하지 않아도 상관 x

                // 이웃노드가 리프노드인 경우 newLeaves에 추가
                if (graph.get(neighbor).size() == 1) {
                    newLeaves.add(neighbor);
                }
            }
            leaves = newLeaves;
        }
        
        return leaves;
    }
}