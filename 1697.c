/*
    2022.02.08
    1697번 숨바꼭질
*/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int min(int a,int b){
    return a < b ? a : b;
}
int X(int N,int K){
    if(N >= K)
        return N - K;
    else if(K == 1)
        return 1;
    else if(K % 2)
        return 1 + min(X(N,K - 1),X(N,K + 1));
    else
        return min(1 + X(N,K / 2),K - N);
}

int main(){
    int N,K;

    scanf("%d %d",&N,&K);
    printf("%d",X(N,K));
}
