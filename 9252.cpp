/*
    2022.03.07
    9252번 LCS 2
*/
#include <iostream>
#include <cstring>
#include <string>
using namespace std;

string w1;
string w2;
string LCS;
int C[1001][1001];

int max(int a, int b){
    return a > b ? a : b;
}



int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    cin >> w1 >> w2;
    int len1 = w1.length();
    int len2 = w2.length();
    for(int i = 1; i <= len1;i++){
        for(int j = 1; j <= len2; j++){
            if(w1[i-1] == w2[j-1]){
                C[i][j] = C[i-1][j-1] + 1;
            }
            else{
                C[i][j] = max(C[i][j-1],C[i-1][j]);
            }
        }
    }
    int k = len2;
    for(int i = len1; i >= 0; i--){
        if(C[i][k] ==0)
            break;
        for(int j = k; j >= 0; j--){
            if(C[i][j] == C[i - 1][j])
                break;
            if(C[i][j] != C[i][j-1]){
                LCS += w2[j-1];
                k = j - 1;
                break;

            }
        }
    }
    cout << C[len1][len2] << '\n';
    for(int i = LCS.size() -1 ; i >= 0; i--){
        cout << LCS[i];
    }



}