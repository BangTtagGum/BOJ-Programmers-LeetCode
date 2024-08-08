import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        
        int result = 0;
        Map<Integer, Set<Integer>> roadMap = new HashMap<>();
        
        for(int[] edge : edge_list){
            int a = edge[0];
            int b = edge[1];
            
            roadMap.putIfAbsent(a,new HashSet<>());
            roadMap.putIfAbsent(b,new HashSet<>());
            
            roadMap.get(a).add(b);
            roadMap.get(b).add(a); 
        }
        
        int[][] dp = new int[k][n+1];
        
        for(int i = 0; i < k; i++){
            for(int j = 0; j <= n; j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        dp[0][gps_log[0]] = 0;
        
        for(int i = 0; i < k - 1; i++){
            for(int j = 1; j <= n; j++){
                if(dp[i][j] == Integer.MAX_VALUE){
                    continue;
                }       
                
                for(int next : roadMap.get(j)){
                    if(next == gps_log[i+1]){
                        dp[i+1][next] = Math.min(dp[i+1][next], dp[i][j]);
                    } else{
                        dp[i+1][next] = Math.min(dp[i+1][next], dp[i][j] + 1);
                    }   
                }
            }
        }
        
        if(dp[k-1][gps_log[k-1]] == Integer.MAX_VALUE){
            return -1;
        }
        return dp[k-1][gps_log[k-1]];
    }
}