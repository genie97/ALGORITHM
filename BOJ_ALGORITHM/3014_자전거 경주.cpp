#include<cstdio>
#include<vector>
#include<algorithm>
#include<cstring>
using namespace std;
int N, M, cnt;
vector<int> vt[10003];
int visit[10003];
int dfs(int v) {
	visit[v] = 1;
	if (v == 1)
		return cnt;
	for (int i = 0; i < vt[v].size(); i++) {
		int y = vt[v][i];
		if (!visit[y]) {
			cnt++;
			visit[y] = 1;
			dfs(y);
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
	int maxV = 0;
	for (int i = 1; i <= N; i++) {
		cnt = 0;
		memset(visit, 0, sizeof(visit));
		maxV = max(dfs(i), maxV);
	}
	printf("%d", maxV);
}