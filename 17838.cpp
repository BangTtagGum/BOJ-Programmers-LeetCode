/*
    2022.03.21
    17838번 커맨드
*/
#include <iostream>
using namespace std;

int main(){
    int T;
    string a,b;
    string str;
    cin >> T;
    for(int i = 0; i < T; i++){
        cin >> str;
        
        if(str.size() != 7){
            cout << 0 << '\n';
            continue;
        }
        a = str.at(0);
        b = str.at(2);
        if(a == b){
            cout << 0 << '\n';
            continue;
        }
        string test = a+a+b+b+a+b+b;
        if(str == test){
            cout << 1 << '\n';
        }
        else{
            cout << 0 << '\n';
        }
    }
    return 0;
}

