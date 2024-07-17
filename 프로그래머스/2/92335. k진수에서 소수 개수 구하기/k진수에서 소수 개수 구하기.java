import java.util.*;

class Solution {
    public int solution(int n, int k) {
        
        Deque<Integer> stack = new ArrayDeque<>();
        
        int remainder;
        while(n > 0){
            remainder = n % k;
            n /= k;
            stack.push(remainder);    
        }
        
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        
        int answer = 0;
        for(String s: sb.toString().split("0")){
            
            if(s.equals(""))
                continue;
            
            if(isPrime(Long.parseLong(s))){
                answer++;
            }
        }
        
        
        return answer;
    }
    
    private boolean isPrime(Long num){
        if(num == 1 || (num > 2 && num % 2 == 0)){
            return false;
        }
        
        for(int i = 3; i <= (int) Math.sqrt(num); i++){
            if(num % i == 0){
                return false;
            }
        }   
          
        return true;
    }
}