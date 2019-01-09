#include<cstdio>
#include<algorithm>
#include <vector>
using namespace std;

int N, M;
int cow[201], cage[201];
vector <int> adj[201];
bool visited[201];

bool dfs(int v) {
	if (visited[v])
		return false;
	visited[v] = true;
	for (int i : adj[v]) {
		if (!cage[i] || dfs(cage[i])) {
			cow[v] = i;
			cage[i] = v;
			return true;
		}
	}
	return false;
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
	fill(cow + 1, cow + N + 1, false);
	fill(cage + 1, cage + N + 1, false);
	int cnt = 0;
	for (int i = 1; i <= N; i++) {
		fill(visited + 1, visited + N + 1, false);
		if (dfs(i))
			cnt++;
	}
	printf("%d\n", cnt);
}