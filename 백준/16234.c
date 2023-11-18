/*
    2022.02.06
    16234번 인구이동
*/
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

typedef struct GraphNode{
    int vertex;
    struct GraphNode *link;
}GraphNode; 

typedef struct GraphType{
    GraphNode *adj_list[3000];
}GraphType;

int visited[3000];          //방문여부 확인
int uni[3000],idx = 0;      //
int peo[51][51];
int sum,cnt,STOP;
void visited_init(){
    for(int i = 0; i < 3000; i++){
        visited[i] = 0;
    }
}


void dfs_list(GraphType *g, int v,int N){     
    GraphNode *w;
    visited[v] = 1;
    uni[idx++] = v;
    sum += peo[v/N][v%N];
    cnt++;
    for(w = g->adj_list[v]; w ; w = w->link){  
        if(!visited[w->vertex]){
            dfs_list(g,w->vertex,N);
        }
    }
}

void graph_init(GraphType *g){  //그래프 초기화 함수
    for(int i = 0;i < 3000; i++)
        g->adj_list[i] = NULL;
}

void insert_edge(GraphType *g, int u, int v){//간선삽입 함수
    GraphNode *node;
    node = (GraphNode*)malloc(sizeof(GraphNode));
    node -> vertex = v;
    node -> link = g-> adj_list[u];
    g -> adj_list[u] = node;
}


int main(){
    int N,min,max,diff,day = 0;
    GraphType g;
    graph_init(&g);

    scanf("%d %d %d",&N,&min,&max);
    for(int i = 0; i <=50; i++){
        for(int j = 0; j <= 50;j++){
            peo[i][j] = 999;
        }
    }
    for(int i = 0; i < N; i++){
        for(int j = 0; j < N;j++){
            scanf("%d",&peo[i][j]);
        }
    }
    
    while(1){
        sum = 0;
        cnt = 0;
        STOP = 0;
        graph_init(&g);
        visited_init();
        for(int i = 0 ; i < N ; i++){
            for(int j = 0 ; j < N ; j++){
                diff = abs(peo[i][j] - peo[i][j+1]);
                if(min <= diff && diff <= max){
                    insert_edge(&g,(i*N)+j,(i*N)+j+1);
                    insert_edge(&g,(i*N)+j+1,(i*N)+j);
                    STOP = 1;
                }
                diff = abs(peo[i][j] - peo[i+1][j]);
                if(min <= diff && diff <= max){
                    insert_edge(&g,(i*N)+j,(i*N)+j+N);
                    insert_edge(&g,(i*N)+j+N,(i*N)+j);
                    STOP = 1;
                }
            }
        }
        for(int i = 0 ; i < N*N; i++){
            if(!visited[i]){
                dfs_list(&g,i,N);
                for(int j = 0 ; j<idx; j++){
                    peo[uni[j]/N][uni[j]%N] = sum/cnt;    
                    uni[j] = 0;
                }
                idx = 0;
                sum = 0;
                cnt = 0;
            }
        }

        if(STOP == 0){
            break;
        }
        day++;
    }

    printf("%d\n",day);



    return 0;
}