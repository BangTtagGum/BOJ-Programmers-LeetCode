/*
    2022.03.22
    14593번 2017 아주대학교 프로그래밍 경시대회(Large)
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
struct People{
    int num;
    int sco;
    int sub;
    int upl;
};

bool compare(People a, People b){
    if(a.sco == b.sco){
        if(a.sub == b.sub){
            return a.upl < b.upl;
        }
        return a.sub < b.sub;
    }
    else{
        return a.sco > b.sco;
    }

};



int main(){
    int N;
    cin >> N;
    vector<People> peo(N);
    for(int i = 0; i < N; i++){
        peo[i].num = i+1;
        cin >> peo[i].sco >> peo[i].sub >> peo[i].upl;
    }
    sort(peo.begin(),peo.end(),compare);
    cout << peo[0].num;
    return 0;
}