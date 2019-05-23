#include<cstdio>
#include<vector>
using namespace std;
vector<int> vt[1001];
int visit[1001];
int cnt = 0, line = 0;
int N, M; // 정점, 간선
void dfs(int v) {
	visit[v] = 1;
	for (int i = 0; i < vt[v].size(); i++) {
		if (!visit[vt[v][i]]) {
			visit[vt[v][i]] = 1;
			dfs(vt[v][i]);
		}
	}
}
int main() {
	scanf("%d%d", &N, &M);
	for (int i = 0; i < M; i++) {
		int a, b;
		scanf("%d%d", &a, &b);
		vt[a].push_back(b);
		vt[b].push_back(a);
	}
	for (int i = 1; i <= N; i++) {
		if (!visit[i]) {
			dfs(i);
			cnt++;
		}
	}
	printf("%d\n", cnt);
}