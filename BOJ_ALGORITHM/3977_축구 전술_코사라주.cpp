/*
#include<cstdio>
#include<algorithm>
#include<vector>
#include<stack>
using namespace std;
int SCCnum[100001]; // SCC의 인덱스 번호

int N, M, visited[100001], indegree[100001], r = 0;
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
	int test;
	scanf("%d", &test);
	for (int i = 0; i < test; i++) {
		scanf("%d %d", &N, &M);
		vertex.resize(N + 1);
		Rvertex.resize(N + 1);
		for (int i = 0; i < M; i++) {
			int a, b;
			scanf("%d %d", &a, &b);
			vertex[a].push_back(b);
			Rvertex[b].push_back(a);
		}
		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			dfs(i);
		}
		fill(visited, visited + N + 1, 0);
		while (s.size()) {
			int cur = s.top();
			s.pop();
			if (visited[cur])
				continue;
			SCC.resize(++r);
			Rdfs(cur, r - 1);
		}
		for (int i = 0; i < r; i++) {
			for (int j : SCC[i])
				SCCnum[j] = i;
		}
		for (int i = 0; i < N; i++)
			for (int j : vertex[i])
				if (SCCnum[i] != SCCnum[j])
					indegree[SCCnum[j]]++;

		int cnt = 0;
		int pos = 0;
		for (int i = 0; i < r; i++) {
			if (indegree[i] == 0) {
				pos = i;
				cnt++;
			}
		}

		if (cnt > 1)
			printf("Confused\n");

		else
			for (int i = 0; i < N; i++)
				if (SCCnum[i] == pos)
					printf("%d\n", i);

		printf("\n");

		SCC.clear();
		vertex.clear();
		Rvertex.clear();
		fill(visited, visited + N + 1, 0);
		fill(SCCnum, SCCnum + N + 1, 0);
		fill(indegree, indegree + N + 1, 0);
		r = 0;
	}
}
*/