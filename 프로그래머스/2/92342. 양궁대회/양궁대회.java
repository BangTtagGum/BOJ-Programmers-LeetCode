import java.util.*;
class Solution {
    int maxScore = 0;
    int[] info;
    int[] answer = new int[11];
    
    public int[] solution(int n, int[] info) {
        this.info = info;
        dfs(0, new int[11], n);
        
        
        if(maxScore == 0){
            return new int[]{-1};
        }
        
        return answer;
    }
    
    
    public void dfs(int index, int[] lion, int arrow){
        if(index == 11){
            lion[10] = arrow;
            
            int score = calculateScore(lion);
            
            if(score > maxScore){
                maxScore = score;
                answer = Arrays.copyOf(lion, lion.length);
            } else if(score == maxScore){
                for(int i = 10; i >= 0; i--){
                    if(answer[i] == lion[i]){
                        continue;
                    }
                    if(answer[i] < lion[i]){
                        answer = Arrays.copyOf(lion,lion.length);
                    }
                    break;
                }
            }
            return ;
        }
        
        if(info[index] < arrow){
            lion[index] = info[index] + 1;
            dfs(index + 1, lion, arrow - lion[index]);
            lion[index] = 0;
        }
        dfs(index + 1, lion, arrow);
    }
    
    public int calculateScore(int[] lion){
        int diff = 0;
        for(int i = 0; i <= 9; i++){
            if(lion[i] == 0 && info[i] == 0){
                continue;
            }
            diff = lion[i] > info[i] ? diff + (10 - i) : diff - (10 - i);
        }
        
        return diff;
    }
}