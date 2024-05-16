class Solution {

    public static List<String> findItinerary(List<List<String>> tickets) {
        List<String> result = new LinkedList<>();
        Map<String, PriorityQueue<String>> fromToMap = new HashMap<>();
        for (List<String> ticket : tickets) {
            fromToMap.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            fromToMap.get(ticket.get(0)).add(ticket.get(1));
        }
        dfs(result, fromToMap, "JFK");

        return result;
    }

    public static void dfs(List<String> result, Map<String, PriorityQueue<String>> fromToMap,
            String from) {
        while (fromToMap.containsKey(from) && !fromToMap.get(from).isEmpty()) {
            dfs(result, fromToMap, fromToMap.get(from).poll());
        }
        result.add(0, from);
    }
}