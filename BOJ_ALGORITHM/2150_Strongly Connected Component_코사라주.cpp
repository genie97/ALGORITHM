/*
#include<cstdio>
#include<algorithm>
#include<vector>
#include<stack>
using namespace std;

int V, E, visited[10001], r = 0;
vector<vector<int>> SCC;
vector<vector<int>> vertex; //순방향
vector<vector<int>> Rvertex; //역방향
stack <int> s;

void dfs(int v) {
	visited[v] = true;
	for (int next : vertex[v]) {
		if (visited[next])
			continue;
		dfs(next);
	}
	s.push(v);
}

void Rdfs(int v, int c) {
	visited[v] = true;
	SCC[c].push_back(v);
	for (int next : Rvertex[v]) {
		if (visited[next])
			continue;
		Rdfs(next, c);
	}
}

int main() {
	scanf("%d %d", &V, &E);
	vertex.resize(V + 1);
	Rvertex.resize(V + 1);
	for (int i = 0; i < E; i++) {
		int a, b;
		scanf("%d %d", &a, &b);
		vertex[a].push_back(b);
		Rvertex[b].push_back(a);
	}
	for (int i = 1; i <= V; i++) {
		if (visited[i])
			continue;
		dfs(i);
	}
	fill(visited, visited + V + 1, 0);
	while (s.size()) {
		int cur = s.top();
		s.pop();
		if (visited[cur])
			continue;
		SCC.resize(++r);
		Rdfs(cur, r - 1);
	}
	for (int i = 0; i < r; i++) {
		sort(SCC[i].begin(), SCC[i].end());
	}
	sort(SCC.begin(), SCC.end());
	printf("%d\n", r);
	for (int i = 0; i < r; i++) {
		for (int j : SCC[i])
			printf("%d ", j);
		printf("-1\n");
	}
}
*/