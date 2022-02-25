/*
    2022.02.24
    2512번 예산
*/
#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;


/*
int main(){
    int N,money;
    cin >> N;
    int arr[10001];

    for(int i = 0; i < N; i++){
        cin >> arr[i];
    }
    cin >> money;
    sort(arr,arr+N);
    for(int i = 0; i < N; i++){
        if(money/(N-i) < arr[i]){
            cout << money/(N-i);
            return 0;
        }
        money -= arr[i];
    }
    cout << arr[N-1];


    return 0;
}
*/


int main(){
    int N;
    int val;
    int money;
    int arr[10001];
    priority_queue<int,vector<int>,greater<int> > pq;
    cin >> N;
    for(int i = 0; i < N; i++){
        cin >> val;
        pq.push(val);
    }
    cin >> money;
    for(int i = 0; i < N; i++){
        if(money/pq.size() < pq.top()){
            cout << money/pq.size();
            return 0;
        }
        money -= pq.top();
        val = pq.top();
        pq.pop();
    }
    cout << val;

}