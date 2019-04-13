#include<cstdio>
#include<algorithm>
#include<queue>
#include<vector>
using namespace std;
int N, M, V;
vector<int> node[1001];
bool visit[1001];
queue <int> q;
void dfs(int v) {
	visit[v] = true;
	printf("%d ", v);
	for (int j = 0; j < node[v].size(); j++) {
		if (!visit[node[v][j]]) {
			visit[node[v][j]] = true;
			dfs(node[v][j]);
		}
	}
}
void bfs(int v) {
	visit[v] = true;
	q.push(v);

	while (!q.empty()) {
		int nv = q.front();
		q.pop();
		printf("%d ", nv);
		for (int i = 0; i < node[nv].size(); i++) {
			if (!visit[node[nv][i]]) {
				visit[node[nv][i]] = true;
				q.push(node[nv][i]);
			}
		}
	}
}
int main() {
	scanf("%d%d%d", &N, &M, &V);
	for (int i = 0; i < M; i++) {
		int a, b;
		scanf("%d%d", &a, &b);
		node[a].push_back(b);
		node[b].push_back(a);
	}
	for (int i = 1; i <= N; i++)
		sort(node[i].begin(), node[i].end());
	dfs(V);
	printf("\n");
	fill(visit + 1, visit + 1 + N, 0);
	bfs(V);
	printf("\n");
}