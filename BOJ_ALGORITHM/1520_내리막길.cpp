#include<cstdio>
using namespace std;
int M, N;
int map[501][501];
int dp[501][501];
int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };

int dfs(int y, int x) {
	if (y == M - 1 && x == N - 1)
		return 1;

	if (dp[y][x] == -1) {
		dp[y][x] = 0;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (0 <= nx&&nx < N && 0 <= ny && ny < M) {
				if (map[y][x] > map[ny][nx])
					dp[y][x] += dfs(ny, nx);
			}
		}
	}
	return dp[y][x];
}
int main() {
	scanf("%d %d", &M, &N);
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &map[i][j]);
			dp[i][j] = -1; 
		}
	}
	printf("%d\n", dfs(0, 0));
}
