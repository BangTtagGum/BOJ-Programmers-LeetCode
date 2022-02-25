/*
    2022.02.18
    1655번 가운데를 말해요
    ios::sync_with_stdio(false),cin.tie(NULL),cout.tie(NULL);
    이 문장의 의미 알기
*/
/*
#include <iostream>
#include <queue>
using namespace std;


int main(){
    int N;
    int val;
    cin >> N;
    priority_queue<int, vector<int>, greater<int> > pq , ppq;  
    for(int i = 1; i <= N; i++){
        cin >> val;
        pq.push(val);
        while(pq.size() > i/2 +1){
            int a = pq.top();
            pq.pop();
            ppq.push(a);
        } 
        cout << pq.top() << endl;
        while(pq.size() < i){
            pq.push(ppq.top());
            ppq.pop();
        }
    }


    return 0;
}
*/
#include <iostream>
#include <queue>
using namespace std;

int main(){ios::sync_with_stdio(false),cin.tie(NULL),cout.tie(NULL);
    int N;
    int val;
    int mid;
    cin >> N;
    priority_queue<int, vector<int>, greater<int> > maxq; //작은 수 우선순위 큐
    priority_queue<int, vector<int>, less<int> > minq;   //큰 수 우선순위 큐
    for(int i = 1; i <= N; i++){
        cin >> val;
        if(i == 1){
            mid = val;
            cout << mid << '\n';
        }
        else{
            if(val <= mid && i%2 == 0){                 
                minq.push(val);
                maxq.push(mid);
                mid = minq.top();
                minq.pop();
            }
            else if(val > mid && i%2 ==0){         
                maxq.push(val);
            }
            else if(val <= mid && i%2 == 1){
                minq.push(val);
            }
            else if(val > mid && i%2 == 1){
                maxq.push(val);
                minq.push(mid);
                mid = maxq.top();
                maxq.pop();
            }
            cout << mid << '\n';
        }
    }
    return 0;
}

/*     
    짝수번째에 mid 보다 작거나 같음     최대힙  0 / .1   /3 5 최소힙
    val 을 minq에 push 하고 mid 를 maxq에 push 한 뒤 minq의 top을 mid로 저장하고 minq pop

    짝수번째에 mid 보다 큼              1/ .3  /5 6
    val 을 maxq로 push 함            
    
    홀수번째에 mid 보다 작거나 같음       1 2/  .3  /4 5
    val 을 minq로 push 함

    홀수번째에 mid 보다 큼              1 3/  .5 /7  9 
*/