/*
    2022.03.23
    1992번 쿼드트리
    재귀로 풀어봐야할거같은
*/
#include <iostream>
#include <stack>

using namespace std;

int N;
string map[64];

void recursion(int i1,int i2,int j1, int j2);

int main(){
    cin >> N;
    for(int i = 0; i < N; i++){
        cin >> map[i];
    }
    recursion(0,N,0,N);
    return 0;
}

void recursion(int i1,int i2,int j1, int j2){
    char status = map[i1][j1];
    for(int i = i1; i < i2; i++){
        for(int j = j1; j < j2; j++){
            if(status != map[i][j]){
                cout << "(";
                recursion(i1,(i1+i2)/2,j1,(j1+j2)/2); //2사분면
                recursion(i1,(i1+i2)/2,(j1+j2)/2,j2); //1사분면
                recursion((i1+i2)/2,i2,j1,(j1+j2)/2); //3사분면
                recursion((i1+i2)/2,i2,(j1+j2)/2,j2); //4사분면
                cout << ")";
                return ;
            } 
        }     
    }
    cout << status;
}