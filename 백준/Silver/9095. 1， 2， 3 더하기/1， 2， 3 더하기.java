import java.util.*;

public class Main {

    static int[] dp = new int[1001];
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        
        for(int i = 0; i < n; i++){
            int num = sc.nextInt();
            System.out.println(f(num));
        }
        sc.close();
    }
        
    public static int f(int n){
        if(dp[n] != 0){
            return dp[n];
        }
        return dp[n] = f(n-3) + f(n-2) + f(n-1);
    }
        
}