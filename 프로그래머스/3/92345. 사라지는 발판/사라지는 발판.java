class Solution {
    int[][] board;
    int N;
    int M;
    int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
    
    public int dfs(int playerRow, int playerCol, int opponentRow, int opponentCol){
        
        if(board[playerRow][playerCol] == 0){
            return 0;
        }
        int result = 0;
        
        for(int[] direction : directions){
            int playerNextRow = playerRow + direction[0];
            int playerNextCol = playerCol + direction[1];
            
            if(playerNextRow < 0 || playerNextRow >= N || playerNextCol < 0 || playerNextCol >= M || board[playerNextRow][playerNextCol] == 0){
                continue;
            }
            
            board[playerRow][playerCol] = 0;
            int moveCount = dfs(opponentRow, opponentCol, playerNextRow, playerNextCol) + 1;
            board[playerRow][playerCol] = 1;
            
            if(result % 2 == 0 && moveCount %2 == 1){
                result = moveCount;
            } else if (result % 2 == 0 && moveCount % 2 == 0){
                result = Math.max(result, moveCount);
            } else if (result % 2 == 1 && moveCount %2 == 1)
                result = Math.min(result,moveCount);
            
        }
        return result;
    }
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.board = board;
        this.N = board.length;
        this.M = board[0].length;
        
        return dfs(aloc[0],aloc[1],bloc[0],bloc[1]);
    }
    

}