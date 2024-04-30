class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        Map<Integer, List<Integer>> bucket = new HashMap<>();
        for (Integer i : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(i);
            List<Integer> elems = bucket.getOrDefault(frequency, new ArrayList<>());
            elems.add(i);
            bucket.put(frequency, elems);
        }
        
        int[] result = new int[k];
        int idx = 0;
        for (int i = nums.length; i >= 0 && idx < k; i--) {
            if (bucket.get(i) != null) {
                for (int num : bucket.get(i)) {
                    result[idx] = num;
                    idx++;
                }
            }
        }

        return result;
    }
}