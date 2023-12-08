class Solution {
    
    public static boolean[] visited;
    public static int maxDepth;
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        
        visited = new boolean[dungeons.length];
        
        enterTheDungeons(k,dungeons,0,0);
        
        
        
        return maxDepth;
    }
    
    public void enterTheDungeons(int k,int[][] dungeons,int idx, int depth){
        if(depth > maxDepth)
            maxDepth = depth;
        for(int i = 0; i < dungeons.length; i++){
            if(k >= dungeons[i][0] && !visited[i]){
                visited[i] = true;
                k -= dungeons[i][1];  
                enterTheDungeons(k,dungeons,i+1,depth + 1);
                k += dungeons[i][1];
                visited[i] = false;
            }            
        }
        
    }
}