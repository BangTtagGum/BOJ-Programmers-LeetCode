/*
    2022.03.15
    2606번 바이러스
*/
#include <iostream>

using namespace std;

bool visited[101];
bool adj[101][101];
int N;
int link;
int cnt;

void dfs(int k){
    visited[k] = true;
    cnt++;
    for(int i = 1; i <= N; i++){
        if(adj[k][i] == true && visited[i] == false){
            dfs(i);
        }
    }


}





int main(){ios::sync_with_stdio(false),cin.tie(NULL),cout.tie(NULL);

    int c1,c2;
    cin >> N;
    cin >> link;
    for(int i = 0; i <link; i++){
        cin >> c1 >> c2;
        adj[c1][c2] = true;
        adj[c2][c1] = true;
    }
    dfs(1);
    cout << cnt - 1;


    return 0;
}