/*
    2022.03.11
    2636번 치즈
*/
#include <iostream>
#include <cstring>
#include <queue>

using namespace std;

int map[102][102];
bool visited[102][102];
int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};
queue<pair<int,int> > q;


int main(){ios::sync_with_stdio(false),cin.tie(NULL),cout.tie(NULL);
    int row,column;
    int day= 0,cnt = 0,last = 0;
    cin >> row >> column;
    for (int i = 0; i < row; i++){
        for (int j = 0; j < column; j++){
            cin >> map[i][j];
            if(map[i][j] == 1)
                cnt++;
        }       
    }
    pair<int,int> f;
    f.first = 0;
    f.second = 0;
    while(cnt > 0){
        last = cnt;    //마지막 치즈 개수
        day++;
        q.push(f);
        memset(visited,false,sizeof(visited));
        visited[0][0] = true;
        while(q.size()>0){
            int x = q.front().first;
            int y = q.front().second;
            q.pop();
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < row && ny < column && !visited[nx][ny]){
                    if(map[nx][ny] == 0){
                        f.first = nx;
                        f.second = ny;
                        q.push(f);
                    }
                    else if(map[nx][ny] == 1){                    
                        cnt--;
                        map[nx][ny] = 0;
                    }
                    visited[nx][ny] = true;
                }
            }
        }
    }
    cout << day << '\n' << last;
    
}