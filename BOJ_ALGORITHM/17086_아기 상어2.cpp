#include<cstdio>
#include<algorithm>
#include<cstring>
#include<queue>
using namespace std;
int N, M;
int dx[8] = { -1,-1,-1,0,0,1,1,1 };
int dy[8] = { -1,0,1,-1,1,-1,0,1 };
int map[51][51] = { 0, }, visit[51][51] = { 0, };
int maxV = 0, cnt = 0;
queue<pair<int, int>> q;
void bfs() {
	while (!q.empty()) {
		int curX = q.front().first;
		int curY = q.front().second;
		q.pop();

		for (int i = 0; i < 8; i++) {
			int nx = curX + dx[i];
			int ny = curY + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M)
				continue;
			if (!visit[nx][ny] && !map[nx][ny]) {
				q.push({ nx, ny });
				visit[nx][ny] = 1;
				map[nx][ny] = map[curX][curY] + 1;
			}
		}
	}
}
int main() {
	scanf("%d %d", &N, &M);
	for (int r = 0; r < N; r++) {
		for (int c = 0; c < M; c++) {
			scanf("%d", &map[r][c]);
			if (map[r][c])
				q.push({ r,c });
		}
	}
	bfs();
	for (int r = 0; r < N; r++) {
		for (int c = 0; c < M; c++) {
			maxV = max(maxV, map[r][c]);
		}
	}
	printf("%d\n", maxV-1);
}
