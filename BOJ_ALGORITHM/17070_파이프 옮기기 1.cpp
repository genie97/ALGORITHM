#include<cstdio>
int N, cnt = 0;
int map[17][17];
int dx[3] = { 0,1,1 }, dy[3] = { 1,1,0 };
int visit[17][17] = { 0, };
void dfs(int x1, int y1, int x2, int y2, int dir) { //dir 0:���� 1:�밢�� 2:����
	if (x2 == N&&y2 == N)
		cnt++;
	switch (dir) {
	case 0:
		for (int i = 0; i < 2; i++) {
			int nx1 = x2;
			int ny1 = y2;
			int nx2 = x2 + dx[i];
			int ny2 = y2 + dy[i];
			int nDir = i;
			if (nx2 > N || ny2 > N)
				continue;
			if (i == 1 && (map[x2][y2 + 1] == 1 || map[x2 + 1][y2] == 1))
				continue;
			if (map[nx2][ny2] == 0 && !visit[nx1][ny1]) {
				visit[nx1][ny1] = 1;
				dfs(nx1, ny1, nx2, ny2, nDir);
				visit[nx1][ny1] = 0;
			}
		}
		break;
	case 1:
		for (int i = 0; i < 3; i++) {
			int nx1 = x2;
			int ny1 = y2;
			int nx2 = x2 + dx[i];
			int ny2 = y2 + dy[i];
			int nDir = i;
			if (nx2 > N || ny2 > N)
				continue;
			if (i == 1 && (map[x2][y2 + 1] == 1 || map[x2 + 1][y2] == 1))
				continue;
			if (map[nx2][ny2] == 0 && !visit[nx1][ny1]) {
				visit[nx1][ny1] = 1;
				dfs(nx1, ny1, nx2, ny2, nDir);
				visit[nx1][ny1] = 0;
			}
		}
		break;
	case 2:
		for (int i = 1; i < 3; i++) {
			int nx1 = x2;
			int ny1 = y2;
			int nx2 = x2 + dx[i];
			int ny2 = y2 + dy[i];
			int nDir = i;
			if (nx2 > N || ny2 > N)
				continue;
			if (i == 1 && (map[x2][y2 + 1] == 1 || map[x2 + 1][y2] == 1))
				continue;
			if (map[nx2][ny2] == 0 && !visit[nx1][ny1]) {
				visit[nx1][ny1] = 1;
				dfs(nx1, ny1, nx2, ny2, nDir);
				visit[nx1][ny1] = 0;
			}
		}
		break;
	}
}
int main() {
	scanf("%d", &N);
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			scanf("%d", &map[i][j]);
		}
	}
	dfs(1, 1, 1, 2, 0); // ���� �������� (1,1) / (1,2)
	printf("%d", cnt);
}