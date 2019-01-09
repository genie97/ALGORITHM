#include<cstdio>
#include<algorithm>
#include <vector>
using namespace std;

int N, M;
int notebook[101];
bool visited[101];
vector <int> adj[101];

bool dfs(int v) {
	if (visited[v])
		return false;
	visited[v] = true;
	for (int i : adj[v]) {
		if (!notebook[i] || dfs(notebook[i])) {
			notebook[i] = v;
			return true;
		}
	}
	return 0;
}
int main() {
	scanf("%d %d", &N, &M);
	for (int i = 0; i < M; i++) {
		int a, b;
		scanf("%d %d", &a, &b);
		adj[a].push_back(b);
	}
	int cnt = 0;
	for (int i = 1; i <= N; i++) {
		fill(visited + 1, visited + N + 1, false);
		if (dfs(i))
			cnt++;
	}
	printf("%d\n", cnt);
}