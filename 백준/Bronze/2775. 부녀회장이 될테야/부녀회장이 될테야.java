import java.util.*;

public class Main{
    
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        int[][] dp = new int[15][15];
        for(int i = 0; i < 15; i++){
            dp[0][i] = i;
        }
        for(int i = 1; i < 15; i++){
            for(int j = 1; j < 15; j++){
                for(int k = 1; k <= j; k++){
                    dp[i][j] += dp[i-1][k];
                }
            }
        }
        
        int t = sc.nextInt();
        
        for(int i = 0; i < t; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println(dp[a][b]);
        }
        
        
        
        sc.close();
    }
}