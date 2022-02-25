/*
    2022.02.18
    하루 지날때마다 다 익었는지 탐색하면 너무 오랜 시간이 걸릴거같아
    입력받을때 덜 익은 토마토 수를 세어놓고 날이 지나면서 토마토가 익는 경우를
    빼주어 덜익은 토마토 수가 0 이 되면 탈출하는 느낌으로 접근
    여기서 생기는 문제가 다음에 탐색할 토마토가 미리 1로 변해버리면 그 뒤로 쭉
    한번에 변해서 그것을 구분해주기위해 1일에 변한 토마토 2일에 변한 토마토로 구분 지음

*/
/*
#include <iostream>
using namespace std;


int main(){
    int M, N;
    int green = 0, ygreen;
    int day = 0;
    cin >> M >> N;
    int ** box = new int*[N];
    for(int i = 0 ; i < N; i++)
        box[i] = new int[M];
    for(int i = 0 ; i < N ; i++){
        for(int j = 0 ; j < M ; j++){
            cin >> box[i][j];
            if(box[i][j] == 0)
                green++;
        }
    }
    while(green != 0){
        day++;
        ygreen = green;
        for(int i = 0 ; i < N; i++){
            for(int j = 0; j < M ; j++){
                if(box[i][j] == day){      
                    if(i-1 >= 0 && box[i-1][j] == 0){
                        green--;
                        box[i-1][j] = day+1;
                    }
                    if(i+1 < N && box[i+1][j] == 0){
                            green--;
                            box[i+1][j] = day+1;
                    }
                    if(j-1 >= 0 && box[i][j-1] == 0){
                        green--;
                        box[i][j-1] = day+1;
                    }
                    if(j+1 < M && box[i][j+1] == 0){
                        green--;
                        box[i][j+1] = day+1;
                    }
                }
            }
        }
        //토마토 출력
        // for(int i = 0 ; i < N ; i++){
        //     for (int j = 0; j < M ; j++){
        //         cout << box[i][j] << ' ';
        //     }
        //     cout << endl;
        // }
        // cout << endl;

        if(ygreen == green){    // 덜익은 토마토 수가 0이 아닌데 더이상 변할 게 없을경우
            day = -1;
            break;
        }

    }

    cout << day << endl;


    for(int i = 0 ; i < N; i++)
        delete[] box[i];
    delete[] box;
    return 0;
}
*/

/*
    시간초과를 해결하기 위해 생각해본 방법은
    매일마다 전체 맵을 전부 탐색하느라 시간이 너무 걸려서
    토마토가 있는 부분의 좌표만 따로 저장해서 토마토 개수만큼만
    반복하면 해결될거같아서 해봄. 근데 더안됨 알고보니 while 안에 for문
    시작인 yesterday 변수를 while 밖에 선언해놓고 해서 안돼서 성공함
*/

#include <iostream>
using namespace std;

struct Point{
    int i,j;
};
int box[1001][1001];

int main(){
    int M, N;
    int green = 0, ygreen;
    int day = 0;
    int toCnt = 0;
    int yesterday =0, today=0;
    cin >> M >> N;

    Point *to = new Point[M*N+1];     //익은 토마토 좌표 저장배열

    for(int i = 0 ; i < N ; i++){
        for(int j = 0 ; j < M ; j++){
            cin >> box[i][j];
            if(box[i][j] == 0)
                green++;  
            else if(box[i][j] == 1){
                to[toCnt].i = i;
                to[toCnt].j = j;
                toCnt++;
            }
        }
    }

    while(green != 0){
        day++;
        ygreen = green;
        
        today = toCnt;
        for(int i = yesterday; i < today; i++){ 
            if(to[i].i - 1 >= 0 && box[to[i].i-1][to[i].j] == 0){
                green--;
                box[to[i].i-1][to[i].j] = 1;
                to[toCnt].i = to[i].i-1;
                to[toCnt].j = to[i].j;
                toCnt++;                  
            }
            if(to[i].i + 1 < N && box[to[i].i+1][to[i].j] == 0){
                green--;
                box[to[i].i+1][to[i].j] = 1;
                to[toCnt].i = to[i].i+1;
                to[toCnt].j = to[i].j;
                toCnt++;                   
            }
            if(to[i].j - 1 >= 0 && box[to[i].i][to[i].j-1] == 0){
                green--;
                box[to[i].i][to[i].j-1] = 1;
                to[toCnt].i = to[i].i;
                to[toCnt].j = to[i].j-1;
                toCnt++;                   
            }
            if(to[i].j + 1 < M && box[to[i].i][to[i].j+1] == 0){
                green--;
                box[to[i].i][to[i].j+1] = 1;
                to[toCnt].i = to[i].i;
                to[toCnt].j = to[i].j+1;
                toCnt++;                   
            }
        }
        if(ygreen == green){    // 덜익은 토마토 수가 0이 아닌데 더이상 변할 게 없을경우
            day = -1;
            break;
        }
        yesterday = today;
    }
    

    cout << day << endl;


    delete[] to;
    return 0;
}

