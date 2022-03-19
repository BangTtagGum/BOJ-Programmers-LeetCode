/*
    2022.03.20
    14500번 테트로미노
*/
#include <iostream>
#include <algorithm>

using namespace std;

int N,M;
int map[500][500];
int result;
int sum;
int com1[8][2] ={{0,1},{1,2},{3,4},{4,5},{0,2},{0,5},{3,2},{3,5}};
int com2[8][2] ={{0,2},{1,3},{2,4},{3,5},{0,5},{1,4},{0,4},{1,5}};

int main(){ios::sync_with_stdio(false),cin.tie(NULL),cout.tie(NULL);
    cin >> N >> M;
    for(int i = 0; i < N; i++)
        for(int j = 0; j < M; j++)
            cin >> map[i][j];
        

    //정사각형
    for(int i = 0; i < N-1; i++){
        for(int j = 0; j < M-1; j++){
            sum = map[i][j] + map[i+1][j] + map[i][j+1] + map[i+1][j+1];
            if(sum > result)
                result = sum;
        }
    }
    //막대기 가로
    for(int i = 0; i < N; i++){
        for(int j = 0; j < M-3; j++){
            sum = map[i][j] + map[i][j+1] + map[i][j+2] + map[i][j+3];
            if(sum > result)
                result = sum;
        }
    }
    //막대기 세로
    for(int i = 0; i < N-3; i++){
        for(int j = 0; j < M; j++){
            sum = map[i][j] + map[i+1][j] + map[i+2][j] + map[i+3][j];
            if(sum > result)
                result = sum;
        }
    }
    //2행 3열
    for(int i = 0; i < N-1; i++){
        for(int j = 0; j < M-2; j++){
            int arr[6] ={map[i][j], map[i][j+1], map[i][j+2], map[i+1][j], map[i+1][j+1], map[i+1][j+2]};
            int min = 99999999;
            for(int k = 0 ; k < 8;k++){
                if(min > arr[com1[k][0]] + arr[com1[k][1]])
                    min = arr[com1[k][0]] + arr[com1[k][1]];
            }
            sum = arr[5] + arr[4] + arr[3] + arr[2] + arr[1] + arr[0] - min;
            if(sum > result)
                result = sum;
        }
    }
    //3행 2열
    for(int i = 0; i < N-2; i++){
        for(int j = 0; j < M-1; j++){
            int arr[6] ={map[i][j], map[i][j+1], map[i+1][j], map[i+1][j+1], map[i+2][j], map[i+2][j+1]};
            int min = 99999999;
            for(int k = 0 ; k < 8;k++){
                if(min > arr[com2[k][0]] + arr[com2[k][1]])
                    min = arr[com2[k][0]] + arr[com2[k][1]];
            }
            sum = arr[5] + arr[4] + arr[3] + arr[2] + arr[1] + arr[0] - min;
            if(sum > result)
                result = sum;
        }
    }
    cout << result ;

            
        

    return 0;
}