/*
    2022.03.07
    3079번 입국심사
*/
#include <iostream>
#include <algorithm>
using namespace std;

int T[100000];


void Parametric_Search(int *T,int start, int end);


int main(){ios::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);
    int N,M;
    scanf("%d %d", &N,&M);
    for(int i = 0; i <N; i++){
        cin >> T[i];
    }
    sort(T,T+N);
}

int Parametric_Search(int *T,int start, int end,int target,int max){
    while(start<=end){
        int mid = (start+end)/2;
        if(T[mid] < target){
            
        }
    }
}