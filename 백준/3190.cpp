/*
    2022.03.13
    3190번 뱀
*/
//처음 보고 생각난건 큐
#include <iostream>
#include <queue>
#define UP 0
#define RIGHT 1
#define DOWN 2
#define LEFT 3


using namespace std;
int map[101][101];
bool block[101][101];
queue<pair<int,int> > snake;
pair<int,int> head;
int N,K,L;
int x,y,t,pre_t,cnt,dir = RIGHT;    
char C;

bool is_fin(const int x,const int y);
void SnakeCheck();
void SnakeMV(const int t);

int main(){ios::sync_with_stdio(false),cin.tie(NULL),cout.tie(NULL);
    head.first = 1;
    head.second = 1;
    block[head.second][head.first] = true;
    snake.push(head);

    cin >> N;
    cin >> K;
    for(int i = 0; i < K; i++){
        cin >> x >> y;
        map[x][y] = 1;
    }
    cin >> L;
    for(int i = 0; i < L; i++){
        cin >> t >> C; 
        SnakeMV(t);
    }
    SnakeMV(1000);
    
}

bool is_fin(const int x,const int y){
    if(x == 0 || y == 0 || x == N+1 || y == N+1)
        return true;
    else if(block[y][x] == true)
        return true;
    else
        return false;
}
void SnakeCheck(){
    cnt++;
    if(is_fin(head.first,head.second)){
        cout << cnt;
        exit(0);
    }
    if(map[head.second][head.first] == 1){
        map[head.second][head.first] = 0;
    }
    else{
        block[snake.front().second][snake.front().first] = false;
        snake.pop();
    }
    block[head.second][head.first] = true;
    snake.push(head);
}
void SnakeMV(const int t){
    switch(dir){
        case UP:
            for(int j = cnt; j < t; j++){
                head.second--;
                SnakeCheck();
            }
            break;
        case RIGHT:
            for(int j = cnt; j < t; j++){
                head.first++;
                SnakeCheck();
            }
            break;
        case DOWN:
            for(int j = cnt; j < t; j++){
                head.second++;
                SnakeCheck();
            }
            break;
        case LEFT:
            for(int j = cnt; j < t; j++){
                head.first--;
                SnakeCheck();
            }
            break;
    }
    if(C == 'D'){
        dir = (dir+1)%4;
    }
    else if(C == 'L'){
        dir = (dir+3)%4;
    }
        C = 0;
}
