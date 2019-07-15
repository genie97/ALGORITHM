#include<cstdio>
#include<cstring>
#include<algorithm>
using namespace std;
int N, ans;
int map[16][16] = { 0, }, visit[16][16] = { 0, };
int dx[4] = { 1,-1,0,0 }, dy[4] = { 0,0,1,-1 };

int dfs(int x, int y) {
	visit[x][y] = 1;
	if (map[x][y] == 3) {
		ans = 1;
		return 0;
	}
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx < 0 || nx >= 16 || ny < 0 || ny >= 16)
			continue;
		if (!visit[nx][ny] && map[nx][ny] == 0 || map[nx][ny] == 3)
			dfs(nx, ny);
	}
}
int main() {
	for (int i = 0; i < 10; i++) {
		scanf("%d", &N);
		ans = 0;
		memset(map, 0, sizeof(map));
		memset(visit, 0, sizeof(visit));
		int x = 0, y = 0;
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				scanf("%1d", &map[i][j]);
				if (map[i][j] == 2) {
					x = i;
					y = j;
				}
			}
		}
		dfs(x, y);
		printf("#%d %d\n", N, ans);
	}
}
