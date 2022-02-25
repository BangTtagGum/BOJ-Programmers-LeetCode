/*
    2022.02.24
    14889번 스타트와 링크
*/
#include <cstdio>
#include <cmath>

int N;
int sp,lp;
int S[20][20];
int visited[20];
int arr[20];
int result = 99999;

void back(int cnt){
    if(cnt == N/2){
        int k = 0;
        sp = 0;
        lp = 0;
        for(int i = 0; i < N; i++){     //앞에 절반만 백트래킹으로 구하고 안구해진 것들만 다른팀으로 만들어준다.
            if(!visited[i]){
                arr[N/2 + (k++)] = i;
            }
        }
        for(int i = 0; i < N/2; i++){
            for(int j = 0; j < N/2; j++){
                sp += S[arr[i]][arr[j]];
            }
        }
        for(int i = N/2; i < N; i++){
            for(int j = N/2; j < N; j++){
                lp += S[arr[i]][arr[j]];
            }
        }
        if(result > abs(sp - lp)){
            result = abs(sp - lp);
        }
        return;
    }
    for(int i = arr[cnt-1] + 1; i < N; i++){        //i를 1부터 시작했더니   0 1 2 3
        if(!visited[i]){                            //                   0 3 2 1    같은 중복이 발생해서 바로 전 값보다 큰 경우로만 제한해서 중복을 줄임 
            visited[i] = 1;
            arr[cnt] = i;
            back(cnt+1);
            visited[i] = 0;
        }
    }
    
}

int main(){  
    scanf("%d",&N);
    for(int i = 0; i < N; i++){
        for(int j = 0; j < N; j++){
            scanf("%d",&S[i][j]);
        }
    }
    back(0);
    printf("%d", result);
}
