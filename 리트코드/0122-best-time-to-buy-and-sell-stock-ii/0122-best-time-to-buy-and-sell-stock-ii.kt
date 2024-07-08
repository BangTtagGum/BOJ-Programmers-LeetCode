class Solution {
    fun maxProfit(prices: IntArray): Int {
        var result = 0
        for(i in 0 until prices.size - 1){
            if(prices[i + 1] > prices[i]){
                result += prices[i + 1] - prices[i]
            }
        }
        return result
    }
}