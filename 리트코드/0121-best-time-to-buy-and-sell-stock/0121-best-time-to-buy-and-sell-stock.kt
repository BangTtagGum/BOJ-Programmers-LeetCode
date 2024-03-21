class Solution {
    fun maxProfit(prices: IntArray): Int {
        var minPrice = prices[0]
    var maxPrice = 0

    for (price in prices) {

        minPrice = minPrice.coerceAtMost(price)
        maxPrice = maxPrice.coerceAtLeast(price - minPrice)
    }
    return maxPrice
    }
}