/*
#include<cstdio>
#include<algorithm>
using namespace std;
int N, map[25][25], visit[25][25], apt[625], cnt=0;
int dir[4][2] = { {-1,0},{1,0},{0,-1},{0,1} };

void dfs(int x, int y, int num) {
	map[x][y] = num;
	for (int i = 0; i < 4; i++) {
		int dx = x + dir[i][0];
		int dy = y + dir[i][1];
		if ((dx >= 0 && dx < N) && (dy >= 0 && dy < N) && map[dx][dy] == 1) {
			dfs(dx, dy, num);
		}
	}
}
int main() {
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%1d", &map[i][j]);
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (map[i][j] == 1) {
				cnt++;
				dfs(i, j, cnt + 1);
			}
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (map[i][j] > 1) {
				apt[map[i][j] - 2]++;
			}
		}
	}

	sort(apt, apt + cnt);
	printf("%d\n", cnt);
	for (int i = 0; i < cnt; i++) {
		printf("%d\n", apt[i]);
	}
}*/