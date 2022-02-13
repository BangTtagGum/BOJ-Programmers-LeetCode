/*
    2022.02.13
    9466번 텀 프로젝트
    선택 과정을 temp 배열에 저장 다음에 선택되는 수가 이미 선택 되었다면 탐색
    1 -> 2 -> 3 -> 4 -> 5 -> 3
           idx = 3         count = 6;
    버려진 인원 = count-1 - idx

*/

#include <stdio.h>
#include <stdbool.h>
#include <string.h>
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