class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();

        int maxCount = 0;
        int result = 0;
        for(int num : nums){
            int count = map.getOrDefault(num,0) + 1;
            map.put(num, count);
            if(maxCount < count){
                result = num;
                maxCount = count;
            }
        
        }
        
        return result;

    }
}