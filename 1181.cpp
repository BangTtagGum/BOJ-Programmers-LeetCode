/*
    2022.03.22
    1181번 단어정렬
*/
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool compare(string a, string b){
    if(a.size() == b.size()){
        return a < b;
    }
    return a.size() < b.size();
}

int main(){
    int N;
    cin >> N;
    vector<string> words(N);
    for(int i = 0; i < N; i++){
        cin >> words[i];
    }
    sort(words.begin(),words.end(),compare);
    cout<< words[0] << '\n';
    for(int i = 1; i < N; i++){
        if(words[i] != words[i-1])
            cout<< words[i] << '\n';
    }
}