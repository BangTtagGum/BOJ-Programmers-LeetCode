class Solution {
    public int[][] merge(int[][] intervals) {

        List<int[]> merged = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        for (int[] i : intervals) {
            if (!merged.isEmpty() && i[0] <= merged.get(merged.size() - 1)[1]) {
                merged.get(merged.size() - 1)[1] = Math.max(i[1], merged.get(merged.size() - 1)[1]);
            } else {
                merged.add(i);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}