/*
    2022.03.23
    2631번 줄세우기
*/
#include <iostream>
#include <vector>
using namespace std;

int c[200];
int dp[200];

int main(){
    int N;
    int max;
    cin >> N;
    
    for(int i = 0; i < N; i++){
        cin >> c[i];
    }
    max = 0;
    dp[0] = 1;
    for(int i = 1; i < N; i++){
        dp[i] = 1;
        for(int j = 0; j < i; j++){
            if(c[i] > c[j] && dp[j]+1 > dp[i])
                dp[i] = dp[j] + 1;
        }
        if (max < dp[i])
            max = dp[i];
    }
    cout << N - max;




    return 0;
}


