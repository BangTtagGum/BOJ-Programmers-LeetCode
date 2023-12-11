import java.util.*;

class Solution {
    public String solution(String number, int k) {
        
        Stack<Character> stack = new Stack<>();
        int drop = k;

        for(int i = 0; i < number.length(); i++){
            if(!stack.isEmpty() && drop > 0){
                if(stack.peek() < number.charAt(i)){
                    stack.pop();
                    drop--;    
                    i--;
                } else{
                    stack.push(number.charAt(i));
                }
                
            } else{                    
                stack.push(number.charAt(i));
            }
            
        }
        
        
        
        StringBuilder sb = new StringBuilder();
        
        Stack<Character> out = new Stack<>();
        
        int len = stack.size();
        
        for(int i =0; i < len; i++){
            out.push(stack.pop());
        }
            
        
        
        for(int i = 0; i < number.length() - k; i++){
            sb.append(out.pop());
        }
        return sb.toString();
    }
}