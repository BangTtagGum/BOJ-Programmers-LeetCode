class Solution {
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        if(gas.sum() < cost.sum())
            return -1
        
        var start = 0
        var fuel = 0
        for(i in 0 until gas.size){

            fuel += gas[i] - cost[i]
            if(fuel < 0){
                start = i + 1
                fuel = 0
            }
        }
        return start
    }
}