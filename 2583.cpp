/*
    2022.02.17
    2583번 영역 구하기
*/
#include <iostream>
#include <cstring>
#include <algorithm>
using namespace std;

int K,M,N;
int cnt;
int spaceCnt;
int map[101][101];
int arr[10001];

int dx[4] = {0,1,0,-1};
int dy[4] = {1,0,-1,0};




void dfs(int a,int b){
    if(map[a][b] == 0){
        map[a][b] = 1;
        ++spaceCnt;
        for(int i = 0; i < 4; i++){
            int x = dx[i] + a;
            int y = dy[i] + b;
            if(x >= 0 && y >= 0 && x < M && y < N)
                dfs(x,y);
        }
    }
}



int main(){
    int x1,x2,y1,y2;
    cin >> M >> N >> K;  

    for(int i = 0; i < K; i++){
        cin >> x1 >> y1 >> x2 >> y2;
        for(int i = x1; i < x2; i++)
            for(int j = y1; j < y2; j++)
                map[j][i] = 1;
    }
    for(int i = 0; i < M; i++){
        for(int j = 0; j < N; j++){
            if(map[i][j] == 0){
                spaceCnt = 0;      //방의 개수
                dfs(i,j);
                arr[cnt++] = spaceCnt;
            }
        }
    }
    sort(arr, arr+cnt);

    cout << cnt << endl;
    for(int i = 0 ; i < cnt;i++)
        cout << arr[i] << " ";
    return 0;
}