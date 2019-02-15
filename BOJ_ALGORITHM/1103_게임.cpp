#include <cstdio>
#include <algorithm>
using namespace std;
char map[51][51];
int dp[51][51], visit[51][51], temp[51][51];
int N, M;
int dx[4] = { 1,-1,0,0 }, dy[4] = { 0,0,1,-1 };
int dfs(int x, int y) {
	if (x < 0 || x >= N || y < 0 || y >= M || map[x][y] == 'H')
		return 0;
	if (temp[x][y]) {
		printf("-1\n");
		exit(0);
	}
	int &ans = dp[x][y];
	if (visit[x][y])
		return ans;
	temp[x][y] = visit[x][y] = 1;
	int move = map[x][y] - '0';
	for (int i = 0; i < 4; i++) {
		ans = max(ans, dfs(x + (move * dx[i]), y + (move * dy[i])));
	}
	temp[x][y] = 0;
	return ++ans;
}
int main() {
	scanf("%d %d", &N, &M);
	for (int i = 0; i < N; i++) {
		scanf("%s", map[i]);

	}
	printf("%d\n", dfs(0, 0));
}