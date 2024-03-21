class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0, minPrice = prices[0];
        for (int price : prices) {

            minPrice = Math.min(minPrice, price);

            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        return maxProfit;
    }
}