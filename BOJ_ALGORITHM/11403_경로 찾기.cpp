#include<cstdio>
#include<algorithm>
using namespace std;
int K, N;
int G[102][102] = { 0, };
int visit[102] = { 0, };
void dfs(int v) {
	for (int i = 1; i <= N; i++) {
		if (G[v][i] == 1 && !visit[i]) {
			visit[i] = 1;
			dfs(i);
		}
	}
}
int main() {
	scanf("%d", &N);
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			scanf("%d", &G[i][j]);
		}
	}
	for (int i = 1; i <= N; i++) {
		fill(visit + 1, visit + 1 + N, 0);
		dfs(i);
		for (int i = 1; i <= N; i++) {
			if (visit[i])
				printf("1 ");
			else
				printf("0 ");
		}
		printf("\n");
	}
}