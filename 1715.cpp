/*
    2022.02.18
    1715번 카드 정렬하기
    배열중 합이 가장 작은 두 수를 계속 더한다
*/
#include <iostream>
#include <queue>

using namespace std;

int main(){
    int num , val;
    long long result = 0;
    cin >> num;
    priority_queue<int, vector<int>, greater<int> > pq;
    for( int i = 0; i < num; i++){
        cin >> val;
        pq.push(val);
    }
    while(pq.size()> 1){
        int a = pq.top();       //최소 힙에서 가장 위에 있는 값
        pq.pop();
        a += pq.top();
        pq.pop();
        result += a;
        pq.push(a);
    }
    cout << result;
    return 0;
}


