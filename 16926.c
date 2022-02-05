/*
    2022.02.06
    16926번 배열 돌리기 1
*/

#include <stdio.h>
int min(int a, int b){
    return a < b ? a : b;
}

int main(){
    int A[301][301];
    int N,M,R;
    int i,j,box;
    scanf("%d %d %d",&N,&M,&R);
    for(i = 0; i < N;i++){
        for(j = 0; j < M; j++){
            scanf("%d",&A[i][j]);
        }
    }
    for(i = 0; i < R; i++){
        box = min(N,M)/2;
        for(j = 0; j < box; j++){
            int n = N - j - 1;
            int m = M - j - 1;

            int tmp = A[j][j];
            for(int k = j; k < m;k++){
                A[j][k] = A[j][k+1];
            }
            for(int k = j; k < n;k++){
                A[k][m] = A[k+1][m];
            }
            for(int k = m; k > j;k--){
                A[n][k] = A[n][k-1];
            }
            for(int k = n; k > j;k--){
                A[k][j] = A[k-1][j];
            }
            A[j+1][j] = tmp;
        }
    }
    for(i = 0; i < N;i++){
        for(j = 0; j < M; j++){
            printf("%d ",A[i][j]);
        }
        printf("\n");
    }



    return 0;
}