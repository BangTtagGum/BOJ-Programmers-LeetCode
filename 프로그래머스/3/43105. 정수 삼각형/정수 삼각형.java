class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int r = triangle.length;
        int c = triangle[triangle.length - 1].length;
        int[][] dp = new int[r][c];
        
        dp[0][0] = triangle[0][0];
        for(int i = 0; i < r-1; i++){
            for(int j = 0 ; j < triangle[i].length; j++){
                dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j] + triangle[i + 1][j]);
                dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j] + triangle[i + 1][j + 1]);
            }
        }
        
        int max = 0;
        for(int i = 0; i < triangle[r-1].length; i++){
            max = Math.max(dp[r-1][i], max);
        }
        return max;
    }
}