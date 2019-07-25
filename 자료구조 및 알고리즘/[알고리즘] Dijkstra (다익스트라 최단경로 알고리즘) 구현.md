## [알고리즘] 최단경로 - Dijkstra

**: 최단 경로 탐색 알고리즘**

음의 간선을 포함할 수 없다!

------

**구현의 순서**

1. 출발 노드를 설정한다
2. 출발 노드를 기준으로 각 노드의 최소 비용을 저장한다
3. 방문하지 않은 노드 중에서 가장 비용이 적은 노드를 선택한다
4. 해당 노드를 거쳐서 특정한 노드로 가는 경우를 고려하여 최소 비용을 갱신한다
5. 3~4과정을 반복한다

**기본적인 구현: 시간 복잡도 : O(N^2)**

```c++
#include<cstdio>
int vertex = 6;
int INF = 1000000000;
bool v[6];//방문 check를 위한 array
int d[6]; //최단 거리(비용)를 저장하는 array
int a[6][6]; //초기에 연결된 노드들의 거리(비용)를 저장하는 array

/**최소거리를 가지는 정점 반환하는 함수**/
int getSmallIndex(){
    int min = INF;
    int index=0;
    for(int i=0;i<vertex;i++){
        if(d[i] < min && !v[i]){ //min값 체크 + 방문 여부!
            min = d[i];
            index = i;
        }
    }
    return index;
}

void dijkstra(int start){
    for(int i=0;i<vertex;i++){
        d[i] = a[start][i]; //모든 경로의 비용 저장
    }
    v[start] = true; //시작점 방문처리
    for(int i=0;i<vertex-2;i++){
        int cur = getSmallIndex();
        v[cur] = true; //최소 거리를 가지는 노드를 방문처리
        for(int j=0; j<vertex;j++){
            if(!v[j]){
                if(d[cur]+a[cur][j]<d[j]){
                    d[j] = d[cur] + a[cur][j];
                }
            }
        }
    }
}

int main(){
    //...
    /**Dijkstra**/
    dijkstra(start); //start => 시작 노드
    //...
}
```

------

**힙을 통한 시간 복잡도 줄이기:  O(NlogN)**

```c++
#include<vector>
#include<queue> //우선순위 큐 사용
int v = 6;
int INF = 10000000000;
vector<pair<int, int>> a[7]; //간선 정보를 저장하기 위한 vecotor
int d[7]; //최단 거리(비용)를 저장하는 array 

void dijkstra(int start){
    d[start]=0;
    priority_queue<pair<int, int>> pq; //가까운 순서대로 처리
    while(!pq.empty()){
        int cur = pq.top().first;
        int distance = -pq.top().second; //거리가 짧은 것이 먼저 오도록 음수화!
        pq.top();
        if(d[cur]<distance) continue;
        for(int i=0;i<a[cur].size();i++){
            int next = a[cur][i].first;
            int nextDistance = distance + a[cur][i].second;
            if(nextDistance < d[next]){
                d[next] = nextDistance;
                //큰 값이 먼저나오는 priority queue의 특징을 반영하여
                //-nextDistance로 저장
                pq.push(make_pair(next, -nextDistance)); 
            }
        }
    }
}

int main(){
    //...
    /**Dijkstra**/
    dijkstra(start); //start => 시작 노드
    //...
}
```

