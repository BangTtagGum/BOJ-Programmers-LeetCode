/*
    2022.02.24
    1927번 최소 힙
*/

#include <iostream>
#include <queue>

using namespace std;

priority_queue<int,vector<int>,greater<int> > pq;

int main(){ios::sync_with_stdio(false),cout.tie(NULL),cin.tie(NULL);
    int N;
    int val;
    cin >> N;

    for(int i = 0; i < N; i++){
        cin >> val;
        if(val == 0){
            if(pq.size() == 0){
                cout << 0 << '\n';
                continue;
            }
            cout << pq.top() << '\n';
            pq.pop();
        }
        else{
            pq.push(val);
        }
    }
    return 0;
}