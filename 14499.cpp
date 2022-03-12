/*
    2022.03.13
    14499번 주사위 굴리기
*/
#include <iostream>

using namespace std;
int dice[6];
int map[20][20];
void DiceTurn(const int cmd);
void DiceMV(const int cmd,const int x, const int y);



int main(){ios::sync_with_stdio(false),cin.tie(NULL),cout.tie(NULL);
    
    int N,M;
    int x,y,K;
    int cmd;
    cin >> N >> M >> y >> x >> K;
    for(int i = 0; i < N; i++){
        for(int j = 0; j < M; j++){
            cin >> map[i][j];
        }
    }
    for(int i = 0; i < K; i++){
        cin >> cmd;
        switch(cmd){
            case 1:
                if(x == M-1){
                    continue;
                }
                x++;
                DiceMV(cmd,x,y);
                break;
            case 2:
                if(x == 0){
                    continue;
                }
                x--;
                DiceMV(cmd,x,y);
                break;
            case 3:
                if(y == 0){
                    continue;
                }
                y--;
                DiceMV(cmd,x,y);
                break;
            case 4:
                if(y == N-1){
                    continue;
                }
                y++;
                DiceMV(cmd,x,y);
                break;
        }
    }
    
    return 0;
}

void DiceTurn(const int cmd){
    int tmp;
    switch(cmd){
        case 1:
            tmp = dice[0];
            dice[0] = dice[4];
            dice[4] = dice[5];
            dice[5] = dice[2];
            dice[2] = tmp;
            break;
        case 2:
            tmp = dice[0];
            dice[0] = dice[2];
            dice[2] = dice[5];
            dice[5] = dice[4];
            dice[4] = tmp;
            break;
        case 3:
            tmp = dice[0];
            dice[0] = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[3];
            dice[3] = tmp;
            break;
        case 4:
            tmp = dice[0];
            dice[0] = dice[3];
            dice[3] = dice[5];
            dice[5] = dice[1];
            dice[1] = tmp;
            break;
    }
}

void DiceMV(const int cmd,const int x, const int y){
    DiceTurn(cmd);
    if(map[y][x] == 0){
        map[y][x] = dice[0];
        cout << dice[5] << '\n';
    }
    else{
        dice[0] = map[y][x];
        map[y][x] = 0;
        cout << dice[5] << '\n';
    }
}