#include<cstdio>
#include<utility>
#include<vector>
#include<algorithm>

using namespace std;
int N, M;
int parent[1001];
typedef pair<int, pair<int, int>> p; // first: cost,  second.first: v1, second.second: v2
p vertex[100001];
vector <p> vertices;
bool comp(p a, p b) {
	if (a.first == b.first)
		return a.second.first < b.second.first;
	else
		return a.first < b.first;
}
void MakeSet(int v) {
	parent[v] = v;
}
int Find(int v) {
	if (parent[v] == v)
		return v;
	return Find(parent[v]);
}
void Union(int v1, int v2) {
	int p1 = Find(v1);
	int p2 = Find(v2);
	parent[p1] = p2;
}

int main() {
	int minCost = 0;
	scanf("%d", &N);
	scanf("%d", &M);
	for (int i = 0; i < M; i++) {
		scanf("%d %d %d", &vertex[i].second.first, &vertex[i].second.second, &vertex[i].first);
	}

	for (int i = 0; i < M; i++) {
		vertices.push_back(p(vertex[i].first, make_pair(vertex[i].second.first, vertex[i].second.second)));
	}

	sort(vertices.begin(), vertices.end(), comp);

	for (int i = 1; i <= N; i++) {
		MakeSet(i);
	}

	for (int i = 0; i < M; i++) {
		if (Find(vertices[i].second.first) != Find(vertices[i].second.second)) {
			Union(vertices[i].second.first, vertices[i].second.second);
			minCost = minCost + vertices[i].first;
		}
	}

	printf("%d\n", minCost);
}