/*
    2022.03.20
    14503번 로봇 청소기
*/  
#include <iostream>

using namespace std;

int map[50][50];
int visited[50][50];
int N, M;
int cnt = 1;
int dr[4] = {-1,0,1,0}; //0 1 2 3 순서대로 위 오 아 왼 방향
int dc[4] = {0,1,0,-1}; 


void dfs(int r, int c, int d){
    for(int i = 0; i<4; i++){ //왼쪽으로 4번 회전      
        int nd = (d+3-i)%4;     //항상 왼쪽으로 돌게
        int nr = r + dr[nd];
        int nc = c + dc[nd];
        
        if(nr<0 || nr>=N || nc<0 || nc>=M || map[nr][nc]==1) {  //범위 넘어가거나 벽이면 다음 방향
            continue;
        }
        
        //청소하지 않은 공간이 존재한다면
        if(visited[nr][nc] == 0 && map[nr][nc] == 0){
               visited[nr][nc] = 1; //현재 위치 청소
               cnt++;
               dfs(nr, nc, nd);
           }

    }
    

    int back_idx = d+2<4 ? d+2 : d-2; //0 1 일때는 2 3, 2 3 일때는 0 1
    int backr = r + dr[back_idx];   //후진할 위오아왼 방향
    int backc = c + dc[back_idx];
    if(0<=backr && backr<=N && 0<=backc && backc<=M){
        if(map[backr][backc]==0){   //뒤쪽 방햑 벽 아니어서 후진할 수 있을 때
            dfs(backr, backc, d);  //한칸 후진
        }
        else{   //뒤쪽 방향 벽이라 후진 못할 때
            cout << cnt <<endl;
            exit(0);
        }
    }
      
}



int main(){
    
    int r, c, d;
    cin >> N >> M;
    cin >> r >> c >> d;
    
    for(int i = 0; i<N; i++){
        for(int j = 0; j<M; j++){
            cin >>map[i][j];
            visited[i][j] = 0;
        }
    }
    
    visited[r][c] = 1;
    dfs(r,c,d);

    return 0;
}