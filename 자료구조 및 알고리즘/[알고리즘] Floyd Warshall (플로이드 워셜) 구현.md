## [알고리즘] Floyd Warshall (플로이드 워셜)

**: 모든 정점에서 모든 정점으로의 최단 경로**

"X~Y로 가는 최소 비용"과 "X에서 노드 i까지의 비용 + 노드 i에서 Y까지의 비용"을 비교

------

**이차원 배열을 이용하여 현재까지 계산된 최소 비용을 저장!**

```c++
#include<cstdio>
int vertex = 4;
int INF = 1000000000; 
int a[6][6]; //현재까지 계산된 최소 비용을 저장하기 위한 array

void floydWarshall(){
    int d[vertex][vertex]; 
    for(int i=0;i<vertex;i++){
        for(int j=0;j<vertex;j++){
            d[i][j]=a[i][j] //그래프 초기화하기
        }
    }
    /**3중 for문이 구현의 포인트**/
    for(int k=0;k<vertex;k++){
        for(int i=0;i<vertex;i++){
            for(int j=0;j<vertex;j++){
                if(d[i][k]+d[k][j] < d[i][j]){
                    d[i][j] = d[i][k] + d[k][j];
                }
            }
        }
    }
}

int main(){
    //...
    /**Floyd Warshall**/
    floydWarshall(); //start => 시작 노드
    //...
}
```
