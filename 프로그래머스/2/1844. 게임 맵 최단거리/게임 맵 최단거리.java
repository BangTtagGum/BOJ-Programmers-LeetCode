import java.util.*;

class Solution {
    
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static int n;
    static int m;
    
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        
        Queue<Robot> q = new LinkedList<>();
        q.add(new Robot(maps,0,0,1));
        
        while(!q.isEmpty()){
            Robot robot = q.poll();
            int r = robot.r;
            int c = robot.c;
            
            if(robot.r == n-1 && robot.c == m-1){
                return robot.roomCnt;
            }
            
            for(int i = 0; i <4; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(isValid(maps,nr,nc)){
                    int cnt = robot.roomCnt + 1;
                    maps[nr][nc] = cnt;
                    q.add(new Robot(maps,nr,nc,cnt));
                }
            }
        }
    
        return -1;
    }
    
    public boolean isValid(int[][] maps, int nr, int nc){
        if(0<=nr && nr< n && 0<= nc && nc < m && maps[nr][nc] == 1){
            return true;
        }
        return false;
    }
    
    static class Robot{
        
        int[][] maps;
        int r;
        int c;
        int roomCnt;
        
        public Robot(int[][] maps,int r, int c,int roomCnt){
            this.maps = maps;
            this.r = r;
            this.c = c;
            this.roomCnt = roomCnt;
        }
    }
}