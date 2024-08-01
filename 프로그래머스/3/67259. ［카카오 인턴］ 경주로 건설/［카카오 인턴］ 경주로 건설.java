import java.util.PriorityQueue;
import java.util.Queue;
class Solution {
    int[] dr = {0,1,0,-1};
    int[] dc = {1,0,-1,0};
    public int solution(int[][] board) {
        
        int size = board.length;
        int[][][] minCost = new int[size][size][4];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                for(int k = 0; k < 4; k++){
                minCost[i][j][k] = Integer.MAX_VALUE;    
                }
            }
        }
        
        Queue<int[]> pq = new PriorityQueue<>((a,b) -> (a[2] * 100 + a[3] * 600) - (b[2] * 100 + b[3] * 600));
        
        
        for(int i = 0; i < 4; i++){
            pq.add(new int[]{0,0,0,0,i});    
        }
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int r = cur[0];
            int c = cur[1];
            int zic = cur[2];
            int con = cur[3];
            int dir = cur[4]; // 0:동 1:남 2:서 3:북
            int cost = zic * 100 + con * 500;
            
            if(minCost[r][c][dir] < cost){
                continue;
            }
            
            minCost[r][c][dir] = cost;
            
            // 보고있는 방향에서 왼, 앞, 오 만 탐색
            for(int i = 0; i < 4; i++){
                // 뒤로 도는경우 스킵
                if((i + 2) % 4 == dir){
                   continue; 
                }
                
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(isValid(nr,nc,size,board)){
                    // 방향이 같으면 직선도로 100원
                    if(i == dir){
                        pq.add(new int[]{nr,nc,zic + 1,con, i});
                        
                    // 방향을 꺾으면 코너 500원
                    } else{
                        pq.add(new int[]{nr,nc,zic + 1, con + 1, i});
                    }
                }
            }
        }
        int answer = Integer.MAX_VALUE;
        for(int i = 0; i <4; i++){
            answer = Math.min(minCost[size-1][size-1][i], answer);
        }
        return answer;
    }
    
    private boolean isValid(int r,int c, int size,int[][] board){
        return 0<= r && r <size && 0<= c && c < size && board[r][c] != 1; 
    }
}