/*
    2022.02.05
    11058번 크리보드
    풀이 방법 : i부터 N까지 인덱스 별로 붙여넣기를 할 경우와 
    원래 DP[i]의 값을 비교해서 더 큰값들을 저장해나가면 DP값이 갱신되며 최고값을 저장한다.
*/
#include <stdio.h>
#include <stdlib.h>

long long max_(long long a,long long b){
    return a > b ? a : b;
}

int main(){
    int N;
    long long DP[101];
    long long buf = 1;
    long long max = 0;
    scanf("%d",&N);
    for(int i = 0 ; i <= N; i ++){
        DP[i] = i;
        if(DP[i] > max){
            max = DP[i];
        }
    }

    for(int i = 1; i <= N-3; i++){
        buf = DP[i];
        DP[i+3] = max_(DP[i+3],DP[i] + buf);
        for(int j = i+4; j <= N; j++){
            DP[j] = max_(DP[j],DP[i] + buf*(j-i-2));
        }
    }

    printf("%lld\n",DP[N]);

    return 0;
}