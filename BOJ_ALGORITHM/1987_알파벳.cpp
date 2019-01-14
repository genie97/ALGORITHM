#include<cstdio>
#include<algorithm>
using namespace std;
int R, C;
int map[22][22];
bool visit[26] = { false, };
int dx[4] = { 0,0,1,-1 };
int dy[4] = { 1,-1,0,0 };
int ans = 1, cnt = 1;

void dfs(int x, int y) {
	int now = map[x][y] - 'A';
	visit[now] = true;
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (0 < nx && nx <= R && 0 < ny && ny <= C) {
			int next = map[nx][ny]-'A';
			if (!visit[next]) {
				ans = max(++cnt, ans);
				dfs(nx, ny);
			}
		}
	}
	cnt--;
	visit[now] = false;
}
int main() {
	scanf("%d %d", &R, &C);
	for (int i = 1; i <= R; i++) {
		for (int j = 1; j <= C; j++) {
			scanf(" %c", &map[i][j]);
		}
	}
	dfs(1, 1);
	printf("%d\n", ans);
}