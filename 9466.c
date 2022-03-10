/*
    2022.02.13
    9466번 텀 프로젝트
    재귀함수 다시 복습해야할 문제 memset 함수 기억하기

    1 -> 3 -> 5 -> 2 -> 3
                  idx  next
*/  

#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#define MAX_SIZE 100001



int cnt;
int sel[MAX_SIZE];
bool visited[MAX_SIZE]; 
bool fin[MAX_SIZE]; //아예 끝

void select(int idx){
    visited[idx] = true;
    int next = sel[idx];    //현재 수가 가르키는 다음 수
    if(!visited[next])
        select(next);

    else if(!fin[next]){    //현재 탐색중이지만 방문한 수
        for(int i = next; i != idx; i = sel[i])
            cnt++;      //지나온 모든 팀원 선택
        cnt++;  //자신까지 카운트
    }
    fin[idx] = true;    //아예 볼일없는 수 표현
}


int main(){
    int T;
    int n; 
    scanf("%d",&T);
    while(T--){
        scanf("%d",&n);
        memset(visited,false,sizeof(visited));      //배열 초기화 함수 string.h 헤더파일에 있음
        memset(fin,false,sizeof(fin));              
        
        for(int i = 1; i <= n; i++)
            scanf("%d",&sel[i]);

        cnt = 0;
        for(int i = 1;i <= n; i++)
            if(!visited[i])
                select(i);

        printf("%d\n",n - cnt);

        
    }

    
    return 0;
}