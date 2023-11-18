/*
    2022.02.06
    16946번 벽 부수고 이동하기 4
*/
#include <stdio.h>
#include <stdlib.h>
int visited[1000000];   //방문 유무 확인
int val[1000000];       //입력받을 배열
int room = 0;           //연결된 방 개수를 저장할 변수

typedef struct GraphNode{
    int vertex;
    struct GraphNode *link;
}GraphNode; 

typedef struct GraphType{
    GraphNode *adj_list[1000000];
}GraphType;

void dfs_list(GraphType *g, int v){     //인접리스트를 이용한 dfs함수
    GraphNode *w;
    visited[v] = 1;
    room++;     //방문한 방 개수 추가
    for(w = g->adj_list[v]; w ; w = w->link){   //인접리스트 따라가면서
        if(!visited[w->vertex] && val[w->vertex] == 0){ //방문하지 않았고 입력받은 배열이 이동가능한 값인 0일경우
            dfs_list(g,w->vertex);
        }
    }
    visited[v] = 0;             //재탐색을 위한 방문 철회
}


void graph_init(GraphType *g){  //그래프 초기화 함수
    int v;
    for(v = 0;v<1000000;v++)
        g->adj_list[v] = NULL;
}

void insert_edge(GraphType *g, int u, int v){//간선삽입 함수
    GraphNode *node;
    node = (GraphNode*)malloc(sizeof(GraphNode));
    node -> vertex = v;
    node -> link = g-> adj_list[u];
    g -> adj_list[u] = node;
}

int main(){
    int N,M;
    scanf("%d %d",&N,&M);
    GraphType g;
    graph_init(&g);
    /* <인접 리스트 만들기> <시작> */ //소요되는 시간 O(max(M,N))
    if(N == 1 && M == 1){}
    else if(N == 1 || M == 1){
        for(int i = 0; i < N*M;i++){
            if(i == 0)
                insert_edge(&g,0,1);
            else if(i == N*M-1)
                insert_edge(&g,N*M-1,(N*M-1)-1);
            else{
                insert_edge(&g,i,i-1);
                insert_edge(&g,i,i+1);
            }
        }
    }
    //N 과 M이 1이 아닌 경우        //소요되는 시간 O(N*M))
    else{
        for(int i = 0; i < N*M;i++){    
            if(i == 0){
                insert_edge(&g,i,i+1);
                insert_edge(&g,i,i+M);
            }
            else if(i == M-1){
                insert_edge(&g,i,i-1);
                insert_edge(&g,i,i+M);
            }
            else if(i == (N-1)*M){
                insert_edge(&g,i,i+1);
                insert_edge(&g,i,i-M);
            }
            else if(i == N*M-1){
                insert_edge(&g,i,i-1);
                insert_edge(&g,i,i-M);
            }
            else if(i < M){
                insert_edge(&g,i,i-1);
                insert_edge(&g,i,i+1);
                insert_edge(&g,i,i+M);
            }
            else if(i % M == 0){
                insert_edge(&g,i,i-M);
                insert_edge(&g,i,i+1);
                insert_edge(&g,i,i+M);
            }
            else if((i+1)%M == 0){
                insert_edge(&g,i,i-M);
                insert_edge(&g,i,i-1);
                insert_edge(&g,i,i+M);
            }
            else if((N-1)*M <i && i < N*M-1){
                insert_edge(&g,i,i-1);
                insert_edge(&g,i,i-M);
                insert_edge(&g,i,i+1);
            }
            else{
                insert_edge(&g,i,i-M);
                insert_edge(&g,i,i-1);
                insert_edge(&g,i,i+1);
                insert_edge(&g,i,i+M);
            }
        }
    }
    /* <인접리스트 만들기> <끝> */

    for(int i = 0; i < N*M; i++)
        scanf("%1d",&val[i]);       //맵 입력받기

    for(int i = 0; i < N*M; i++){   
        room = 0;                   //연결된 방 개수
        if(val[i] == 1){
            val[i] = 0;             //벽을 뚫고 
            dfs_list(&g,i);         //연결된 방 탐색
            val[i] = room;          //방 개수 저장
        }
    }
    //출력
    for(int i = 0; i < N;i++){
        for(int j = 0; j < M;j++){
            printf("%d",val[(i*M)+j]);
        }
        printf("\n");
    }



    return 0;
}