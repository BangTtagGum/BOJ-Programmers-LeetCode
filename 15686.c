/*
    2022.02.12
    15686번 치킨배달

    1 2 3 4 5
    1 2 3 , 1 2 4, 1 2 5, 2 3 4
*/
#include <stdio.h>
#include <stdlib.h>

const int on = 1;
const int off = 0;
int total = 0, result = 9999999;

typedef struct House{
    int x;      //x좌표
    int y;      //y좌표
    int h;      //집 치킨집 구분 1 2
    int c_dis;  //치킨거리
    int on_off; //치킨집 활성화
}House;

int abs(int a){
    return a > 0 ? a : a *(-1);
}
int min(int a,int b){
    return a < b ? a : b;
}

void pick(int N, int idx,int idx2,House* chick,House* home ,int M,int k){
    int last,start;
    if(k == 0){ //다 뽑은 경우
        total = 0;
        for(int i = 0; i < idx2; i++){ //치킨거리 구하기
            for(int j = 0 ; j < idx; j++){                                
                if(chick[j].on_off == on){
                    int x_diff = abs(chick[j].x - home[i].x);
                    int y_diff = abs(chick[j].y - home[i].y);
                    home[i].c_dis = min(home[i].c_dis,x_diff + y_diff);
                }                               
            }
            
            total += home[i].c_dis;
            home[i].c_dis = 99999;
        }
        result = result > total ? total : result;
        return;
    }
    last = M-k-1;   //마지막 으로 on 한 치킨집 index  
    start = last+1;
    for(int i = start; i < idx; i++){
        if(chick[i].on_off == on) {             
            k--;
            continue;
        }
        else{
            chick[i].on_off = on;
            pick(N,idx,idx2,chick,home,M,k-1);
            chick[i].on_off = off;
        }
    }

}

int main(){
    int N,M;
    int idx = 0;
    int idx2 = 0;
    int x_diff,y_diff;
    scanf("%d %d",&N,&M);
    House** map = (House**)malloc(sizeof(House*)*N);   //전체 지도 저장 배열
    House* chick = (House*)malloc(sizeof(House)*13);    //치킨칩 저장 배열
    House* home = (House*)malloc(sizeof(House)*2*N);
    for(int i = 0 ; i < N; i++){
        map[i] = (House*)malloc(sizeof(House)*N);
        for(int j = 0; j < N; j++){
            scanf("%d",&map[i][j].h);
            map[i][j].y = i;
            map[i][j].x = j;
            map[i][j].c_dis = 99999;
            map[i][j].on_off = off;
        }
    }
    for(int i = 0; i < N; i++){
        for(int j = 0 ; j < N; j++){
            if(map[i][j].h == 2){
                chick[idx++] = map[i][j];
            }
            else if(map[i][j].h == 1){
                home[idx2++] = map[i][j];
            }
        }
    }
    pick(N,idx,idx2,chick,home,M,M);
    printf("%d\n",result);
    
    for(int i = 0; i < N;i++)
        free(map[i]);
    free(map);
    free(chick);
    free(home);

}