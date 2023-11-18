/*
    2022.03.19
    15683번 감시
    백트래킹으로 모든 경우의 수 볼건데 하나의 경우가 끝나면
    마지막 cctv만 방향 1 늘려서 다시 지울텐데
    전에 없앴던 사각지대를  스택으로 저장해서
    없앴던 만큼만 다시 돌려주는 방식으로 구현 해보려 생각
*/
#include <iostream>
#include <stack>

using namespace std;

struct CCTV {
    int row;
    int column;
    int type;
};

int map[8][8];
CCTV c_home[8];                     //cctv 배열
int N,M;
int K;
int c_idx;
int result = 100;

void back(int k);
int Count_vacuum();                     //빈 공간 체크하는 함수
void Check_room(int k, int dir); //4번 cctv의 경우 방향이 똑같아서 default 값을 0 으로 지정
void Fill(int row, int column,int dir); //사각지대 없애는 함수
void Trace_back(int desc[][8], int src[][8]);             //지웠던 사각지대 복구 함수

//cctv 좌표, cctv 번호 ,벽 
int main(){ios::sync_with_stdio(false),cin.tie(NULL),cout.tie(NULL);
    cin >> N >> M;
    for(int i = 0; i < N; i++){
        for(int j = 0; j < M; j++){
            cin >> map[i][j];
            if(map[i][j]!= 0 && map[i][j] != 6){
                c_home[c_idx].row = i;
                c_home[c_idx].column = j;
                c_home[c_idx].type = map[i][j];
                c_idx++;
            }
        }
    }
    back(0);
    cout << result;
    return 0;
}
void back(int k){
    if(k == c_idx){ //cctv를 다확인하면
        int vacuum = Count_vacuum();
        if(result > vacuum){
            result = vacuum;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    cout << map[i][j] << ' ';
                }
                cout << '\n';
            }
            cout<< '\n';
        }
        return ;
    }
    int backup[8][8];
    if(c_home[k].type == 1){
        for(int i = 0; i < 4; i++){//cctv의 방향
            Trace_back(backup,map);
            Check_room(k,i);
            back(k+1);
            Trace_back(map,backup);
        }
    }
    else if(c_home[k].type == 2){
        for(int i = 0; i < 2; i++){//cctv의 방향
            Trace_back(backup,map);
            Check_room(k,i);
            back(k+1);
            Trace_back(map,backup);
        }
    }
    else if(c_home[k].type == 3){
        for(int i = 0; i < 4; i++){//cctv의 방향
            Trace_back(backup,map);
            Check_room(k,i);
            back(k+1);
            Trace_back(map,backup);
        }
    }
    else if(c_home[k].type == 4){
        for(int i = 0; i < 4; i++){
            Trace_back(backup,map);
            Check_room(k,i);
            back(k+1);
            Trace_back(map,backup);
        }
    }
    else if(c_home[k].type == 5){
        Trace_back(backup,map);
        Check_room(k,0);
        back(k+1);
        Trace_back(map,backup);
    }
}

//사각지대 count function
int Count_vacuum(){
    int cnt = 0;
    for(int i = 0; i < N; i++){
        for(int j = 0; j < M; j++){
            if(map[i][j] == 0){
                cnt++;
            }
        }
    }
    return cnt;
}

void Check_room(int k, int dir){
    if(c_home[k].type == 1){
        if(dir == 0){
            Fill(c_home[k].row,c_home[k].column,0);
        }
        else if(dir == 1){
            Fill(c_home[k].row,c_home[k].column,1);
        }
        else if(dir == 2){
            Fill(c_home[k].row,c_home[k].column,2);
        }
        else if(dir == 3){
            Fill(c_home[k].row,c_home[k].column,3);
        }
    }
    else if(c_home[k].type == 2){
        if(dir == 0){
            Fill(c_home[k].row,c_home[k].column,0);
            Fill(c_home[k].row,c_home[k].column,2);                       
        }
        else if(dir == 1){
            Fill(c_home[k].row,c_home[k].column,1);
            Fill(c_home[k].row,c_home[k].column,3);                        
        }
    }
    else if(c_home[k].type == 3){
        if(dir == 0){
            Fill(c_home[k].row,c_home[k].column,0);
            Fill(c_home[k].row,c_home[k].column,1);            
        }
        else if(dir == 1){
            Fill(c_home[k].row,c_home[k].column,1);
            Fill(c_home[k].row,c_home[k].column,2);


        }
        else if(dir == 2){
            Fill(c_home[k].row,c_home[k].column,2);
            Fill(c_home[k].row,c_home[k].column,3);


        }
        else if(dir == 3){
            Fill(c_home[k].row,c_home[k].column,3);
            Fill(c_home[k].row,c_home[k].column,0);


        }
    }
    else if(c_home[k].type == 4){
        if(dir == 0){
            Fill(c_home[k].row,c_home[k].column,3); //위 0 오른쪽 1 ...
            Fill(c_home[k].row,c_home[k].column,0);
            Fill(c_home[k].row,c_home[k].column,1);


        }
        else if(dir == 1){
            Fill(c_home[k].row,c_home[k].column,0);
            Fill(c_home[k].row,c_home[k].column,1);
            Fill(c_home[k].row,c_home[k].column,2);


        }
        else if(dir == 2){
            Fill(c_home[k].row,c_home[k].column,1);
            Fill(c_home[k].row,c_home[k].column,2);
            Fill(c_home[k].row,c_home[k].column,3);


        }
        else if(dir == 3){
            Fill(c_home[k].row,c_home[k].column,2);
            Fill(c_home[k].row,c_home[k].column,3);
            Fill(c_home[k].row,c_home[k].column,0);


        }
    }
    else if(c_home[k].type == 5){
        for(int i = 0; i < 4; i++){
            Fill(c_home[k].row,c_home[k].column,i);
        }
    }
}

void Fill(int row, int column,int dir){
    if(dir == 0){
        while(row-1 >=0){
            row--;
            if(map[row][column] == 0){
                map[row][column] = 7;
    
    
            }
            else if(map[row][column] == 6){
                break;
            }
        }
    }
    else if(dir == 1){
        while(column+1 < M){
            column++;
            if(map[row][column] == 0){
                map[row][column] = 7;       
            }
            else if(map[row][column] == 6){
                break;
            }
        }
    }
    else if(dir == 2){
        while(row+1 < N){
            row++;
            if(map[row][column] == 0){
                map[row][column] = 7;        
            }
            else if(map[row][column] == 6){
                break;
            }
        }
    }
    else if(dir == 3){
        while(column-1 >=0){
            column--;
            if(map[row][column] == 0){
                map[row][column] = 7;        
            }
            else if(map[row][column] == 6){
                break;
            }
        }
    }
}

void Trace_back(int desc[8][8], int src[8][8]){
    for (int r = 0; r < N; ++r)
        for (int c = 0; c < M; ++c)
            desc[r][c] = src[r][c];
}

