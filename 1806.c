/*
	2022.02.05
	1806번 부분합
	풀이 방법 : 투 포인터 알고리즘을 사용하여 품. arr[1]에서 arr[i] 까지의 합을 sum[i]에 저장하고
	sum[end] 에서 sum[start-1]의 값을 빼면 arr[start]에서 arr[end]까지의 부분 합을 구할 수 있고,
	그중 S 이상이면서 가장 짧은 길이를 len에 저장해 출력한다.
*/
#include <stdio.h>
#include <stdlib.h>

int main(){
	int N,S;
	int len = 999999;
	int start = 1 ,end = 1;
	scanf("%d %d",&N,&S);
	int* arr = (int*)malloc(sizeof(int)*(N+1));
	int* sum = (int*)malloc(sizeof(int)*(N+1));
	sum[0] = 0;

	for(int i = 1; i <= N; i++){
		scanf("%d",&arr[i]);
		sum[i] = 0;
	}
	for(int i = 1; i <= N; i++){
		sum[i] += sum[i-1] + arr[i];
	}

	for(end = 1; end <= N; end++){
		//start 에서 end 까지의 부분합이 S보다 작을경우와 start가 end를 넘어갈 경우 while문 무시
		while(start <= end && (sum[end] - sum[start-1]) >= S){
			if(len > end - start + 1){
				len = end - start + 1;
			}
			start++;
		}
	}
	if(len == 999999){
		printf("%d\n",0);
	}
	else{
		printf("%d\n",len);
	}
	return 0;
}
