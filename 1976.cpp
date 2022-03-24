/*
    2022.03.24
    1976번 여행 가자
*/
#include <iostream>
using namespace std;

int N,M;
int adj_list[1000][1000];
int travel[1000];
bool can =true;

int main(){ios::sync_with_stdio(false),cin.tie(NULL),cout.tie(NULL);
    
    cin >> N;
    cin >> M;
    for(int i = 0; i < N; i++){
        for(int j = 0; j < N; j++){
            cin >> adj_list[i][j];
            if(i == j){
                adj_list[i][j] = 1;
            }
        }
    }
    for(int i = 0; i < M; i++){
        cin >> travel[i];
    }
    for(int k = 0; k < N; k++){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(adj_list[i][j] == 0 && adj_list[i][k] == 1 && adj_list[k][j] == 1){
                    adj_list[i][j] = 1;                
                }        
            }    
        }        
    }   

    for(int i = 0; i < M-1; i++){
        if(adj_list[travel[i]-1][travel[i+1]-1] == 0){
            can = false;
        }
    }
    if(can)
        cout << "YES";
    else
        cout << "NO";
    return 0;
}
