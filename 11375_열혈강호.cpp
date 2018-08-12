#include<cstdio>
#include<algorithm>
#include<vector>
using namespace std;

int N, M;
int p[1001], w[1001];
int visited[1001];
vector<int> adj[1001];

int dfs(int v) {
	visited[v] = 1;
	for (int i : adj[v]) {
		if (w[i] == -1 || (!visited[w[i]] && dfs(w[i]))) {
			p[v] = i;
			w[i] = v;
			return 1;
		}
	}
	return 0;
}
int main() {
	scanf("%d %d", &N, &M);
	for (int i = 1; i <= N; i++) {
		int S;
		scanf("%d", &S);
		for (int j = 0; j < S; j++) {
			int num;
			scanf("%d", &num);
			adj[i].push_back(num);
		}
	}
	fill(p + 1, p + N + 1, -1);
	fill(w + 1, w + M + 1, -1);
	int cnt = 0;
	for (int i = 1; i <= N; i++) {
		if (p[i] == -1) {
			fill(visited + 1, visited + N + 1, 0);
			if (dfs(i))
				cnt++;
		}
	}
	printf("%d\n", cnt);
}