/*
    2022.03.22
    1543번 문서 검색
*/
#include <iostream>
#include <vector>
using namespace std;

int main(){
    bool is;
    int result = 0,i,j;
    string a,b;
    getline(cin,a);
    getline(cin,b);
    for(i = 0; i < a.size(); i++){
        if(a.size()-i < b.size())        
            break;

        if(a[i] == b[0]){
            is = true;
            for(j = 0; j < b.size(); j++){
                if(a[i+j] != b[j]){
                    is = false;
                    break;
                }
            }
            if(is){
                result++;
                i += j-1;
            }
        }

    }
    cout << result;
    return 0;
}