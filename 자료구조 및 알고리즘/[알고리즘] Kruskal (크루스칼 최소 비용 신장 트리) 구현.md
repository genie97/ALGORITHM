## [알고리즘] 최소 비용 신장 트리 - Kruskal (크루스칼)

**: Minimum-Spanning Tree (최소 비용 신장 트리)**

- 노드 = 정점 = 도시
- 간선 = 거리 = 비용
- **간선  = 노드 - 1**

------

**cycle이 발생하면 안된다**

구현 순서

1. '거리(비용)'를 기준으로 먼저 오름차순 정렬을 한다
2. 정렬된 순서에 맞게 그래프에 포함시킨다
3. cycle 테이블을 확인해서 포함시킨다
4. cycle을 생성할 경우에는 포함시키지 않는다

**Union Find를 이용하여 구현**

```c++
//BOJ1197. 최소 스패닝 트리
#include<cstdio>
#include<algorithm>
using namespace std;
typedef pair<int, int> p;
pair <int, p> tree[100002];
int V, E;
int parent[10002];

int find(int v) {
	if (parent[v] == v)
		return v;
	else
		return parent[v] = find(parent[v]);
}

void joint(int i) {
	int a = find(tree[i].second.first);
	int b = find(tree[i].second.second);
	if (a > b)
		swap(a, b);
	parent[b] = a;
}

int main() {
	scanf("%d%d", &V, &E);
	for (int i = 0; i < V; i++)
		parent[i] = i;
	
    for (int i = 0; i < E; i++) {
		int a, b, c;
		scanf("%d%d%d", &a, &b, &c);
		tree[i] = make_pair(c, make_pair(a, b));
	}
    
	sort(tree, tree + E); //거리를 기준으로 오름차순 정렬
	long long sum = 0;
	int link = 0;
	for (int i = 0; i < E; i++) {
		if (find(tree[i].second.first) != find(tree[i].second.second)) {
			joint(i);
			link++;
			sum += tree[i].first;
		}
		if (link == V - 1) //모든 정점을 다 확인하면 종료
			break;
	}
	printf("%lld\n", sum);
}
```

