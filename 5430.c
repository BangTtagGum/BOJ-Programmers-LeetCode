/*
    2022.02.12
    5430번 AC
    문자열 입력시 버퍼 초기화 문제 잊지말자. 투포인터 할때 느낌
*/
#include <stdio.h>

char p[100001];
int x[100001];
int len;
int back,error;
int start, end;

void R(){
    back = (back)? 0 : 1;       //back 이 1이면 다시 0으로 0이면 1로 저장
} 

void D(){
    if(end == start){
        error = 1;
        return ;
    }
    if(back == 1){
        x[end-1] = 0;
        end--;
    }
    else{
        x[start] = 0;
        start++;
    }
}


int main(){
    int T;
    scanf("%d*c",&T);
    char xn,link;

    for(int i = 0; i < T;i++){
        scanf("%s",p);  //수행할 함수 p 입력
        scanf("%d",&len);   //배열에 들어있는 수의 개수 n
        back = 0;       //R로 뒤집기를 표현하는 변수 초기화
        error = 0;      //error 변수 초기화
        start = 0;
        end = len;      //나중에 활용하기 위해 len 길이를 복사

        if(end == 0){   //길이가 0일 경우 []입력을 받아준다
            scanf("%c%*c",&xn);     //입력 버퍼 문제 해결
            scanf("%c%*c",&xn);
        }
        for(int j = 0 ; j < len || link !=0;){      //배열 저장
            scanf("%1c",&xn);
            if('0' <= xn && xn <= '9'){
                if(link == 1){          //전에 입력받은 숫자가 아직 끝나지 않았을 경우
                    j--;
                    x[j] *= 10;
                    x[j] += xn - '0';
                }
                else{
                    x[j] = xn - '0';
                    link = 1;       
                }
                j++;
            }
            else{
                link = 0;
            }
        }
        for(int j = 0;p[j];j++){    //함수부분
            if(p[j] == 'R'){
                R();
            }
            else{
                D();
            }
            p[j] = 0;       //다음 테스트케이스를 위해 초기화
        }
        if(back == 0){
            if(error == 1){
                printf("error\n");
                continue;
            }
            printf("[");
            for(int j = start ; j < end; j++){
                if(j+1 < end){
                    printf("%d,",x[j]);
                }
                else{
                    printf("%d",x[j]);
                }
            }
            printf("]\n");
        }
        else{
            if(error == 1){
                printf("error\n");
                continue;
            }
            printf("[");
            for(int j = end-1 ; j >= start; j--){
                if(j > start){
                    printf("%d,",x[j]);
                    x[j] = 0;
                }
                else{
                    printf("%d",x[j]);
                    x[j] = 0;
                }
            }
            printf("]\n");
        }
    }
}