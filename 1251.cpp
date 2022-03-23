/*
    2022.03.21
    1251번 단어 나누기
*/
#include <iostream>
using namespace std;

string a,b,m;
bool turn;

void reverse(int min_1,int min_2);

int main(){
    
    cin >> a;
    m = "{";
    b = a;
    for(int i = 0; i < a.size()-1; i++){
        for(int j = i+1; j < a.size()-1; j++){
            reverse(i,j);;
            if(a < m){
                turn = true;
                m = a;
            }
            a = b;
        }
    }

    cout << m;

    return 0;
}

void reverse(int min_1,int min_2){
    for(int i = 0; i <= min_1/2; i++){
        char tmp = a[i];
        a[i] = a[min_1-i];
        a[min_1-i] = tmp;
    }
    for(int i = min_1+1; i <= (min_2 + min_1)/2; i++){
        char tmp = a[i];
        a[i] = a[min_2-(i-(min_1+1))];
        a[min_2-(i-(min_1+1))] = tmp;
    }
    for(int i = min_2+1; i<= (a.size()-1+min_2)/2; i++){
        char tmp = a[i];
        a[i] = a[a.size()-1-(i-(min_2+1))];
        a[a.size()-1-(i-(min_2+1))] = tmp;    
    }
}