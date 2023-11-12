/*
    2022.03.23
    11656번 접미사 배열
*/
#include <iostream>
#include <algorithm>
#include <vector> 
using namespace std;


int main(){
    string S;
    cin >> S;
    vector<string> suf(S.size());
    for(int i = 0; i < S.size(); i++){
        for(int j = i; j < S.size(); j++){
            suf[i] += S[j];
        }
    }
    sort(suf.begin(),suf.end());
    for(int i = 0; i < S.size(); i++){
        cout << suf[i] << '\n';
    }
    return 0;
}
