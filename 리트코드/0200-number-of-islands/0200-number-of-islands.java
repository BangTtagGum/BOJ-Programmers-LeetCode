class Solution {
    int[] dr = {1, -1, 0, 0};
    int[] dc = {0, 0, 1, -1};
    public int numIslands(char[][] grid) {
        int answer = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(i, j, grid);
                    answer++;
                }
            }
        }
        return answer;
    }

    private void dfs(int i, int j, char[][] grid) {
        if ( 0 > i || i >= grid.length || 0 > j || j >= grid[0].length || grid[i][j] == '0') {
            return ;
        }
        grid[i][j] = '0';
        for (int k = 0; k < 4; k++) {
            int nr = i + dr[k];
            int nc = j + dc[k];
            dfs(nr, nc, grid);
        }
    }
}