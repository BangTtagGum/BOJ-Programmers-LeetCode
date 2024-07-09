class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        
        // 전체 방문 불가
        if(Arrays.stream(gas).sum() < Arrays.stream(cost).sum())
            return -1;
        
        int start = 0;
        int fuel = 0;
        for(int i = 0; i < gas.length; i++){

            fuel += gas[i] - cost[i];
            if(fuel < 0){
                start = i + 1;
                fuel = 0;
            }

        }
        return start;
    }
}