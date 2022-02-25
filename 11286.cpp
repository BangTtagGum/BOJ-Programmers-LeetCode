/*
    2022.02.24
    11286번 절댓값 힙
*/
#include <iostream>
#include <queue>
#include <cmath>
using namespace std;

struct cmp{
    bool operator()(int a, int b){
        if(abs(a) == abs(b)){
            return a > b;
        }
        else{
            return abs(a) > abs(b);
        }
    }
};

int main(){
    ios::sync_with_stdio(false),cin.tie(NULL),cout.tie(NULL);
    int N;
    int val;
    cin >> N;
    priority_queue<int,vector<int>,cmp> pq;
    for(int i = 0; i < N; i++){
        cin >> val;
        if(val == 0){
            if(pq.size() == 0){
                cout << 0 << '\n';
                continue;
            }
            cout << pq.top() << '\n';
            pq.pop();
            continue;
        }
        pq.push(val);
    }

    return 0;
}