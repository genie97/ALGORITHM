#include<cstdio>
#include<algorithm>
#include<utility>
using namespace std;

int N;
int tree[501][501];
int dp[501][501];
int dy[4] = { 0,0,1,-1 }, dx[4] = { 1,-1,0,0 };

int LIS(int y, int x) {
	
	if (dp[y][x] != 0)
		return dp[y][x];

	dp[y][x] = 1;
	for (int i = 0; i < 4; i++) {
		int ry = y + dy[i];
		int rx = x + dx[i];
		if (ry == -1 || rx == -1)
			continue;
		if (tree[y][x] <tree[ry][rx]) {
			dp[y][x] = max(dp[y][x], LIS(ry, rx)+1);
		}
	}
	return dp[y][x];
}

int main() {
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &tree[i][j]);
			dp[i][j] = 0;
		}
	}
	
	int k = 0;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			k = max(k, LIS(i, j));
		}
	}
	printf("%d\n", k);
}
