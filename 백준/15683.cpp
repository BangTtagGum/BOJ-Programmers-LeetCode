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

stack<pair<int,int> > tmp_vacuum; //없앤 사각지대 임시 저장
pair <int,int> tmp;     //없어진 사각지대 배열 좌표 임시 저장
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
void Trace_back();                      //지웠던 사각지대 복구 함수
bool Is_in(int i, int j);               //지정범위 넘어나는지 확인하는 함수

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
        }
        return ;
    }
    if(c_home[k].type == 1){
        for(int i = 0; i < 4; i++){//cctv의 방향
            Check_room(k,i);
            back(k+1);
            Trace_back();
        }
    }
    else if(c_home[k].type == 2){
        for(int i = 0; i < 2; i++){//cctv의 방향
            Check_room(k,i);
            back(k+1);
            Trace_back();
        }
    }
    else if(c_home[k].type == 3){
        for(int i = 0; i < 4; i++){//cctv의 방향
            Check_room(k,i);
            back(k+1);
            Trace_back();
        }
    }
    else if(c_home[k].type == 4){
        for(int i = 0; i < 4; i++){
            Check_room(k,i);
            back(k+1);
            Trace_back();
        }
    }
    else if(c_home[k].type == 5){
        Check_room(k,0);
        back(k+1);
        Trace_back();
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
            tmp.first = -1,tmp.second = -1;
            tmp_vacuum.push(tmp);   //이번 cctv 가 없앤 사각지대가 여기까지인 경계 표시로 하나 넣어줌
        }
        else if(dir == 1){
            Fill(c_home[k].row,c_home[k].column,1);
            tmp.first = -1,tmp.second = -1;
            tmp_vacuum.push(tmp);   //이번 cctv 가 없앤 사각지대가 여기까지인 경계 표시로 하나 넣어줌
        }
        else if(dir == 2){
            Fill(c_home[k].row,c_home[k].column,2);
            tmp.first = -1,tmp.second = -1;
            tmp_vacuum.push(tmp);   //이번 cctv 가 없앤 사각지대가 여기까지인 경계 표시로 하나 넣어줌
        }
        else if(dir == 3){
            Fill(c_home[k].row,c_home[k].column,3);
            tmp.first = -1,tmp.second = -1;
            tmp_vacuum.push(tmp);   //이번 cctv 가 없앤 사각지대가 여기까지인 경계 표시로 하나 넣어줌
        }
    }
    else if(c_home[k].type == 2){
        if(dir == 0){
            Fill(c_home[k].row,c_home[k].column,0);
            Fill(c_home[k].row,c_home[k].column,2);
            tmp.first = -1,tmp.second = -1;
            tmp_vacuum.push(tmp);   //이번 cctv 가 없앤 사각지대가 여기까지인 경계 표시로 하나 넣어줌
        }
        else if(dir == 1){
            Fill(c_home[k].row,c_home[k].column,1);
            Fill(c_home[k].row,c_home[k].column,3);
            tmp.first = -1,tmp.second = -1;
            tmp_vacuum.push(tmp);   //이번 cctv 가 없앤 사각지대가 여기까지인 경계 표시로 하나 넣어줌
        }
    }
    else if(c_home[k].type == 3){
        if(dir == 0){
            Fill(c_home[k].row,c_home[k].column,0);
            Fill(c_home[k].row,c_home[k].column,1);
            tmp.first = -1,tmp.second = -1;
            tmp_vacuum.push(tmp);   //이번 cctv 가 없앤 사각지대가 여기까지인 경계 표시로 하나 넣어줌
        }
        else if(dir == 1){
            Fill(c_home[k].row,c_home[k].column,1);
            Fill(c_home[k].row,c_home[k].column,2);
            tmp.first = -1,tmp.second = -1;
            tmp_vacuum.push(tmp);   //이번 cctv 가 없앤 사각지대가 여기까지인 경계 표시로 하나 넣어줌
        }
        else if(dir == 2){
            Fill(c_home[k].row,c_home[k].column,2);
            Fill(c_home[k].row,c_home[k].column,3);
            tmp.first = -1,tmp.second = -1;
            tmp_vacuum.push(tmp);   //이번 cctv 가 없앤 사각지대가 여기까지인 경계 표시로 하나 넣어줌
        }
        else if(dir == 3){
            Fill(c_home[k].row,c_home[k].column,3);
            Fill(c_home[k].row,c_home[k].column,0);
            tmp.first = -1,tmp.second = -1;
            tmp_vacuum.push(tmp);   //이번 cctv 가 없앤 사각지대가 여기까지인 경계 표시로 하나 넣어줌
        }
    }
    else if(c_home[k].type == 4){
        if(dir == 0){
            Fill(c_home[k].row,c_home[k].column,3); //위 0 오른쪽 1 ...
            Fill(c_home[k].row,c_home[k].column,0);
            Fill(c_home[k].row,c_home[k].column,1);
            tmp.first = -1,tmp.second = -1;
            tmp_vacuum.push(tmp);   //이번 cctv 가 없앤 사각지대가 여기까지인 경계 표시로 하나 넣어줌
        }
        else if(dir == 1){
            Fill(c_home[k].row,c_home[k].column,0);
            Fill(c_home[k].row,c_home[k].column,1);
            Fill(c_home[k].row,c_home[k].column,2);
            tmp.first = -1,tmp.second = -1;
            tmp_vacuum.push(tmp);   //이번 cctv 가 없앤 사각지대가 여기까지인 경계 표시로 하나 넣어줌
        }
        else if(dir == 2){
            Fill(c_home[k].row,c_home[k].column,1);
            Fill(c_home[k].row,c_home[k].column,2);
            Fill(c_home[k].row,c_home[k].column,3);
            tmp.first = -1,tmp.second = -1;
            tmp_vacuum.push(tmp);   //이번 cctv 가 없앤 사각지대가 여기까지인 경계 표시로 하나 넣어줌
        }
        else if(dir == 3){
            Fill(c_home[k].row,c_home[k].column,2);
            Fill(c_home[k].row,c_home[k].column,3);
            Fill(c_home[k].row,c_home[k].column,0);
            tmp.first = -1,tmp.second = -1;
            tmp_vacuum.push(tmp);   //이번 cctv 가 없앤 사각지대가 여기까지인 경계 표시로 하나 넣어줌
        }
    }
    else if(c_home[k].type == 5){
        for(int i = 0; i < 4; i++){
            Fill(c_home[k].row,c_home[k].column,i);
        }
        tmp.first = -1,tmp.second = -1;
        tmp_vacuum.push(tmp);   //이번 cctv 가 없앤 사각지대가 여기까지인 경계 표시로 하나 넣어줌   
    }
}

void Fill(int row, int column,int dir){
    if(dir == 0){
        while(Is_in(row-1,column)){
            row--;
            if(map[row][column] == 0){
                map[row][column] = 7;
                tmp.first = row,tmp.second = column;    
                tmp_vacuum.push(tmp);   //이번에 없앤 사각지대들을 스택에 저장
            }
            else if(map[row][column] == 6){
                break;
            }
        }
    }
    else if(dir == 1){
        while(Is_in(row,column+1)){
            column++;
            if(map[row][column] == 0){
                map[row][column] = 7;
                tmp.first = row,tmp.second = column;
                tmp_vacuum.push(tmp);
            }
            else if(map[row][column] == 6){
                break;
            }
        }
    }
    else if(dir == 2){
        while(Is_in(row+1,column)){
            row++;
            if(map[row][column] == 0){
                map[row][column] = 7;
                tmp.first = row,tmp.second = column;
                tmp_vacuum.push(tmp);
            }
            else if(map[row][column] == 6){
                break;
            }
        }
    }
    else if(dir == 3){
        while(Is_in(row,column-1)){ // 범인 ㅋㅋ
            column--;
            if(map[row][column] == 0){
                map[row][column] = 7;
                tmp.first = row,tmp.second = column;
                tmp_vacuum.push(tmp);
            }
            else if(map[row][column] == 6){
                break;
            }
        }
    }
}

void Trace_back(){
    if(!tmp_vacuum.empty()){
        tmp_vacuum.pop();   //처음 -1 -1 저장되어있는 스택 하나 팝
        while(tmp_vacuum.size() != 0 && tmp_vacuum.top().first != -1){ //second 도 있지만 어차피 -1 는 하나만 있어도 경계 확인 가능
            map[tmp_vacuum.top().first][tmp_vacuum.top().second] = 0; //0으로 돌려주기
            tmp_vacuum.pop();
        }
    }
}

bool Is_in(int i, int j){
    return i < N && i >= 0 && j < M && j >= 0; 
}