/*
    2022.03.07
    14891번 톱니바퀴
*/
#include <cstdio>

void spin(char arr[][9],int top,int dir){
    int tmp;
    if(dir == 1){
        tmp = arr[top-1][7];
        for(int i = 6; i >= 0; i--){
            arr[top-1][i+1] = arr[top-1][i];  
        }
        arr[top-1][0] = tmp;
    }
    else{
        tmp =arr[top-1][0];
        for(int i = 0; i < 7; i++){
            arr[top-1][i] = arr[top-1][i+1];
        }
        arr[top-1][7] = tmp;
    }
    
}


void top_turn(char arr[][9],int top,int dir){
    bool a = false,b = false,c = false;
    if(arr[0][2] != arr[1][6]) a = true;
    if(arr[1][2] != arr[2][6]) b = true;
    if(arr[2][2] != arr[3][6]) c = true;
    if(top == 1){ 
        if(a){
            if(b){
                if(c){
                    spin(arr,1,dir);
                    dir *= -1;
                    spin(arr,2,dir);
                    dir *= -1;
                    spin(arr,3,dir);
                    dir *= -1;
                    spin(arr,4,dir);
                }
                else{
                    spin(arr,1,dir);
                    dir *= -1;
                    spin(arr,2,dir);
                    dir *= -1;
                    spin(arr,3,dir);
                }
            }
            else{
                spin(arr,1,dir);
                dir *= -1;
                spin(arr,2,dir);
            }
        }
        else{
            spin(arr,1,dir);
        }
    }
    else if(top == 2){
        if(a){
            if(b){
                if(c){
                    spin(arr,2,dir);
                    dir *= -1;
                    spin(arr,1,dir);
                    spin(arr,3,dir);
                    dir *= -1;
                    spin(arr,4,dir);
                }
                else{
                    spin(arr,2,dir);
                    dir *= -1;
                    spin(arr,1,dir);
                    spin(arr,3,dir);
                }
            }
            else{
                spin(arr,2,dir);
                dir *= -1;
                spin(arr,1,dir);    
            }
        }
        else{
            if(b){
                if(c){
                    spin(arr,2,dir);
                    dir *= -1;
                    spin(arr,3,dir);
                    dir *= -1;
                    spin(arr,4,dir);
                }
                else{
                    spin(arr,2,dir);
                    dir *= -1;
                    spin(arr,3,dir);    
                }
            }
            else{
                spin(arr,2,dir);
            }
        }
    }
    else if(top == 3){
        if(c){
            if(b){
                if(a){
                    spin(arr,3,dir);
                    dir *= -1;
                    spin(arr,4,dir);
                    spin(arr,2,dir);
                    dir *= -1;
                    spin(arr,1,dir);                    
                }
                else{                    
                    spin(arr,3,dir);                    
                    dir *= -1;
                    spin(arr,4,dir);                    
                    spin(arr,2,dir);                                        
                }
            }
            else{
                spin(arr,3,dir);
                dir *= -1;
                spin(arr,4,dir);                        
            }
        }
        else{
            if(b){
                if(a){            
                    spin(arr,3,dir);
                    dir *= -1;
                    spin(arr,2,dir);
                    dir *= -1;
                    spin(arr,1,dir);                                         
                }
                else{                    
                    spin(arr,3,dir);
                    dir *= -1;
                    spin(arr,2,dir);                    
                }
            }
            else{
                spin(arr,3,dir);                    
            }
        }
    }
    else{
        if(c){
            if(b){
                if(a){                
                    spin(arr,4,dir);
                    dir *= -1;
                    spin(arr,3,dir);
                    dir *= -1;
                    spin(arr,2,dir);
                    dir *= -1;
                    spin(arr,1,dir);                                        
                }
                else{                    
                    spin(arr,4,dir);
                    dir *= -1;
                    spin(arr,3,dir);                    
                    dir *= -1;
                    spin(arr,2,dir);                    
                }
            }
            else{
                spin(arr,4,dir);
                dir *= -1;
                spin(arr,3,dir);                    
            }
        }
        else{        
            spin(arr,4,dir);                    
        }
    }
}




int main(){
    char top[4][9];
    int K,t,dir;
    int result;
    for(int i = 0; i < 4;i++){
        scanf("%s",top[i]);
    }
    scanf("%d", &K);
    for(int i = 0; i < K; i++){
        scanf("%d %d",&t,&dir);
        top_turn(top,t,dir);
    }
    result = (top[0][0] -'0')*1 + (top[1][0] -'0')*2 + (top[2][0] -'0')*4 + (top[3][0] -'0')*8;
    printf("%d\n",result);
    return 0;
}

