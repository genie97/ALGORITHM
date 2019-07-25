## [알고리즘] BFS (너비 우선 탐색) 구현

구현의 포인트: **큐(Queue) 사용하기**

1. 시작 노드를 큐에 삽입한다
2. 시작 노드를 방문 처리를 한다
3. 큐에서 하나의 노드를 꺼낸다
4. 해당 노드와 연결된 다른 노드를 큐에 삽입한다
5. 큐가 빌 때까지, 3~4를 반복한다

```c++
#include<queue>
#include<vector>
int c[7]; //check를 위한 array 선언
vector<int> a[8]; //노드를 저장하기 위한 vector 선언

int main(){
    //...
    //전체 노드 저장 => a[index].push_back(item1);
    //시작 노드 큐에 삽입 => q.push(root_node); 
    /**BFS 탐색**/
    while(!q.empty()){
        int x = q.front();
        q.pop();
        for(int i=0;i<a[x].size();i++){
            int y = a[x][i];
            if(!c[y]){
                q.push(y);
                c[y]=true;
            }
        } 
    }
    //...
}

```

