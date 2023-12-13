class Solution {
    
    static int answer;
    public int solution(int[] numbers, int target) {
        
        dfs(numbers,0,target,0);
        return answer;
    }
    // 1 2 3 4 5 6
    
    static void dfs(int[] numbers,int sum,int target,int idx){
        if(idx == numbers.length){
            if(sum == target){
                answer += 1;
            }
            return ;
        }
        
        for(int i = 0; i <2; i++){
            if(i == 0){
                sum += numbers[idx];
                dfs(numbers,sum,target,idx+1);
                sum -= numbers[idx];
            } else{
                sum -= numbers[idx];
                dfs(numbers,sum,target,idx+1);
                sum += numbers[idx];
            } 
        }
    }
}