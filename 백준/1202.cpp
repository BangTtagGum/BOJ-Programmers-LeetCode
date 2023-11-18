/*
    2022.02.25
    1202번 보석도둑
*/
#include <cstdio>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;


int main(){
    int N,K;
    long long answer = 0;
    scanf("%d %d", &N, &K);
    vector<pair<int,int> > a(N);
    vector<int> b(K);
    priority_queue<int> pq;    
    for(int i = 0; i < N;i++){
        scanf("%d %d",&a[i].first ,&a[i].second);
    }
    sort(a.begin(),a.end());
    for(int i = 0; i < K; i++){
        scanf("%d",&b[i]);
    }
    sort(b.begin(),b.end());
    int idx = 0;
    for(int i = 0; i < K; i++){
        while(idx < N && a[idx].first <= b[i]){
        pq.push(a[idx++].second);
        }
        if(!pq.empty()){
            answer += (long long)pq.top();
            pq.pop();
        }
    }
    printf("%lld\n",answer);
    return 0;
}
