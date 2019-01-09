#include<cstdio>
#include<vector>
#include<algorithm>
using namespace std;

vector <int> net[10001];
int N, M;
bool visited[10001];
int cnt;

void dfs(int v) {
	visited[v] = true;
	for (int i = 0; i < net[v].size(); i++) {
		int next = net[v][i];
		if (!visited[next]) {
			cnt++;
			dfs(next);
		}
	}
}
int main() {
	scanf("%d %d", &N, &M);
	for (int i = 0; i < M; i++) {
		int a, b;
		scanf("%d %d", &a, &b);
		net[b].push_back(a);
	}
	vector<int> res;
	int max = 0;
	for (int i = 1; i <= N; i++) {
		fill(visited+1, visited + N+1, false);
		cnt = 0;		
		dfs(i);
		if (max == cnt)
			res.push_back(i);
		if(max<cnt) {
			max = cnt;
			res.clear();
			res.push_back(i);
		}
	}
	sort(res.begin(), res.end());
	for (int i = 0; i < res.size(); i++) {
		printf("%d ", res[i]);
	}
}