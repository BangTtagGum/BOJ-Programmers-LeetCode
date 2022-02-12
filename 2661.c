/*
    2022.02.12
    2661번 좋은 수열
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char a[100];
char b[100];

void back(char * arr,int k,int n){
    for(int i = 1; i <= k/2; i++){
        for(int j = i; j < k && arr[j] != '\0'; j++){
            strcpy(a,&arr[j-i]);
            a[i] = '\0';
            strcpy(b,&arr[j]);
            b[i] = '\0';
            if(strcmp(a,b) == 0)
                return;
        }
    }
    if(k == n){
        for(int i = 0; i < n; i++){
            printf("%c",arr[i]);
        }
        printf("\n");
        exit(0);
    }
    for(int i = 1; i <=3 ; i++){
        arr[k] = i + '0';
        arr[k+1] = '\0';    //문자열 끝 처리 안해줘서 자꾸 틀림
        back(arr,k+1,n);
    }
}

int main(){
    int n;
    scanf("%d",&n);
    char arr[100];
    for(int i = 0 ; i <= n; i++){
        arr[i] = '4';
    }
    back(arr,0,n);

}