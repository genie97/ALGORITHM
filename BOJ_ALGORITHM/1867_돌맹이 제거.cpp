#include<cstdio>
#include<vector>
#include<string.h>
using namespace std;
int n, k, bm[502];
bool visit[502];
vector <vector<int>> vt;
bool dfs(int v){
	if (visit[v])
		return false;
	visit[v] = true;
	for (int next : vt[v]) {
		if (!bm[next] || dfs(bm[next])) {
			bm[next] = v;
			return true;
		}
	}
	return false;
}
int main() {
	scanf("%d %d", &n, &k);
	vt.resize(n + 1);
	for (int i = 1; i <= k; i++) {
		int a, b;
		scanf("%d %d", &a, &b);
		vt[a].push_back(b);
	}
	int res = 0;
	for (int i = 1; i <= n; i++) {
		memset(visit, false, sizeof(visit));
		if (dfs(i))
			res++;
	}
	printf("%d\n", res);
}
