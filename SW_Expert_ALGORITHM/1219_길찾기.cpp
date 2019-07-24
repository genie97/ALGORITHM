#include<cstdio>
#include<cstring>
#include<vector>
using namespace std;
int T, N, res;
vector<int> vt[100];
void dfs(int v) {
	if (v == 99) 
		res = 1;
	for (int i = 0; i < vt[v].size(); i++)
		dfs(vt[v][i]);
}
int main() {
	for (int tc = 0; tc < 10; tc++) {
		scanf("%d%d", &T, &N);
		memset(vt, 0, sizeof(vt));
		res = 0;
		for (int i = 0; i < N; i++) {
			int a, b;
			scanf("%d%d", &a, &b);
			vt[a].push_back(b);
		}
		dfs(0);
		printf("#%d %d\n", T, res);
	}
}
