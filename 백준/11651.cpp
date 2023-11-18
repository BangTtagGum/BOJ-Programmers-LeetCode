/*
    2022.03.22
    11651번 좌표 정렬하기 2
    시간복잡도 O(NlogN)
*/
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
struct Point{
    int x,y;
};
bool compare(Point a, Point b){
    if(a.y == b.y){
        return a.x < b.x;
    }    
    return a.y < b.y;
};


int main(){
    int N;
    cin >> N;
    vector<Point> dot(N);
    for(int i = 0; i < N; i++){
        cin >> dot[i].x >> dot[i].y;
    }
    sort(dot.begin(),dot.end(),compare);
    for(int i = 0; i < N; i++){
        cout << dot[i].x << " " << dot[i].y << '\n';
    }
    return 0;
}