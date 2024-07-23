class Solution {
    public int solution(int[][] board, int[][] skill) {
        
        int n = board.length;
        int m = board[0].length;
        int[][] map = new int[n + 1][m + 1];
        for(int[] s : skill){
            int degree = s[0] == 1 ? s[5] * -1 : s[5];
        
            map[s[1]][s[2]] += degree;
            map[s[1]][s[4] + 1] += (degree * -1);
            map[s[3] + 1][s[2]] += (degree * -1);
            map[s[3]+ 1][s[4] + 1] += degree;
        }
        
        for(int i = 0; i < n; i++){
            for(int j = 1; j < m; j++){
                map[i][j] += map[i][j - 1];
            }
        }
        for(int j = 0; j < m; j++){
            for(int i = 1; i < n; i++){
                map[i][j] += map[i - 1][j];
            }
        }
        
        int answer = 0;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                board[i][j] += map[i][j];
                if(board[i][j] > 0){
                    answer++;
                }
            }
        }
        
        
        return answer;
    }
}