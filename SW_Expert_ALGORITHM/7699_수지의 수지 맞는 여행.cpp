#include<cstdio>
#include<algorithm>
#include<cstring>
using namespace std;
int T, R, C, maxV;
char map[21][21];
int check[26];
int dx[4] = { 0,0,1,-1 }, dy[4] = { 1,-1,0,0 };
void dfs(int x, int y, int cnt) {
	check[map[x][y] - 'A'] = 1;
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx < 0 || nx >= R || ny < 0 || ny >= C)
			continue;
		if (!check[map[nx][ny] - 'A']) {
			dfs(nx, ny, cnt + 1);
		}
	}
	check[map[x][y] - 'A'] = 0;
	maxV = max(maxV, cnt);
}
int main() {
	scanf("%d", &T);
	for (int tc = 1; tc <= T; tc++) {
		memset(map, 0, sizeof(map));
		memset(check, 0, sizeof(check));
		maxV = 0;
		scanf("%d %d", &R, &C);
		for (int i = 0; i < R; i++) {
			scanf("%s", map[i]);
		}
		dfs(0, 0, 1);
		printf("#%d %d\n", tc, maxV);
	}
}