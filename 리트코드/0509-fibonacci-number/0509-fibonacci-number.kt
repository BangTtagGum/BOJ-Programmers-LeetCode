class Solution {
    var dp:IntArray = IntArray(31)
    fun fib(n: Int): Int {
        if(n <= 1){
            return n
        }
        if(dp[n] != 0){
            return dp[n]
        }
        dp[n] = fib(n - 1) + fib(n - 2)
        return dp[n]
    }
}