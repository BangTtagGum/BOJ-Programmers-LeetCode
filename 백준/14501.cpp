/*
    2022.03.10
    14501번 퇴사
*/
#include <iostream>

using namespace std;

int T[100];
int P[100];
int max_val;




//경우의 수들을 다 보려면 백트래킹으로 해야할 거 같아서 시도
void back(int N,int k,int val){
    if(k >= N){
        if(max_val < val)
            max_val = val;
        return ;
    }
    for(int i = k; i < N; i++){
        if(T[i]+i < N+1)
            back(N,T[i]+i,val+P[i]);
        else{
            back(N,T[i]+i,val);
        }
    }
}

int main(){
    int N;
    cin >> N;
    for(int i = 0; i < N; i++){
        cin >> T[i] >> P[i]; 
    }
    back(N,0,0);
    cout << max_val;
}



