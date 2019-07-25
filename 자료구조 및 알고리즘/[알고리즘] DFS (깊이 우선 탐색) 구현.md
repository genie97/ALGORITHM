## [알고리즘] DFS (깊이 우선 탐색) 구현

구현의 포인트: **재귀적으로 구현하기**

1. 노드를 함수의 파라미터로 넘겨준다
2. 방문 노드를 방문 처리한다
3. 다음 노드를 재귀처리를 한다

```c++
#include<queue>
#include<vector>
int c[7]; //check를 위한 array 선언
vector<int> a[8]; //노드를 저장하기 위한 vector 선언
void DFS(int v){
    if(c[v]) return; //방문한 노드면 재귀 종료
    c[v] = true; //방문처리하기
    for(int i=0;i<a[v].size();i++){
        int nv = a[v][i];
        DFS(nv);//재귀처리하기
    }      
}
int main(){
    //...
    //전체 노드 저장 => a[index].push_back(item1);
    /**DFS 탐색**/
    DFS(vertex); //vertex: 시작 노드
    //...
}

```

