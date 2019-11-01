#include<cstdio>
#include<queue>
#include<cstring>

typedef struct Pos {
	int x, y;
};
using namespace std;
int T, N, M, R, C, L;
int map[55][55], visit[55][55];
vector<Pos> vt;
void bfs() {
	queue<Pos> q;
	q.push({ R, C });
	vt.push_back(Pos{ R,C });
	int cnt = 0;
	while (!q.empty()) {
		if (cnt == L - 1) return;
		int size = q.size();
		for (int sz = 0; sz < size; sz++) {
			int x = q.front().x;
			int y = q.front().y;
			visit[x][y] = 1;
			q.pop();

			if (map[x][y] == 1) {
				int dx[4] = { 0,0,1,-1 }, dy[4] = { 1,-1,0,0 };
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (i == 0)
						if (map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 5) continue;
					if (i == 1)
						if (map[nx][ny] == 2 || map[nx][ny] == 6 || map[nx][ny] == 7) continue;
					if (i == 2)
						if (map[nx][ny] == 3 || map[nx][ny] == 5 || map[nx][ny] == 6) continue;
					if (i == 3)
						if (map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 7) continue;
					if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
					if (!visit[nx][ny] && map[nx][ny]) {
						visit[nx][ny] = 1;
						q.push(Pos{ nx, ny });
						vt.push_back(Pos{ nx, ny });
					}
				}
			}
			else if (map[x][y] == 2) {
				int dx[2] = { 1,-1 }, dy[2] = { 0,0 };
				for (int i = 0; i < 2; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (i == 0)
						if (map[nx][ny] == 3 || map[nx][ny] == 5 || map[nx][ny] == 6) continue;
					if (i == 1)
						if (map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 7) continue;
					if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
					if (!visit[nx][ny] && map[nx][ny]) {
						visit[nx][ny] = 1;
						q.push(Pos{ nx, ny });
						vt.push_back(Pos{ nx, ny });
					}
				}
			}
			else if (map[x][y] == 3) {
				int dx[2] = { 0, 0 }, dy[2] = { 1,-1 };
				for (int i = 0; i < 2; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (i == 0)
						if (map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 5) continue;
					if (i == 1)
						if (map[nx][ny] == 2 || map[nx][ny] == 6 || map[nx][ny] == 7) continue;
					if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
					if (!visit[nx][ny] && map[nx][ny]) {
						visit[nx][ny] = 1;
						q.push(Pos{ nx, ny });
						vt.push_back(Pos{ nx, ny });
					}
				}
			}
			else if (map[x][y] == 4) {
				int dx[2] = { -1,0 }, dy[2] = { 0,1 };
				for (int i = 0; i < 2; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (i == 0)
						if (map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 7) continue;
					if (i == 1)
						if (map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 5) continue;

					if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
					if (!visit[nx][ny] && map[nx][ny]) {
						visit[nx][ny] = 1;
						q.push(Pos{ nx, ny });
						vt.push_back(Pos{ nx, ny });
					}
				}
			}
			else if (map[x][y] == 5) {
				int dx[2] = { 1,0 }, dy[2] = { 0,1 };
				for (int i = 0; i < 2; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (i == 0)
						if (map[nx][ny] == 3 || map[nx][ny] == 5 || map[nx][ny] == 6) continue;
					if (i == 1)
						if (map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 5) continue;

					if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
					if (!visit[nx][ny] && map[nx][ny]) {
						visit[nx][ny] = 1;
						q.push(Pos{ nx, ny });
						vt.push_back(Pos{ nx, ny });
					}
				}
			}
			else if (map[x][y] == 6) {
				int dx[2] = { 1,0 }, dy[2] = { 0,-1 };
				for (int i = 0; i < 2; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (i == 0)
						if (map[nx][ny] == 3 || map[nx][ny] == 5 || map[nx][ny] == 6) continue;
					if (i == 1)
						if (map[nx][ny] == 2 || map[nx][ny] == 6 || map[nx][ny] == 7) continue;

					if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
					if (!visit[nx][ny] && map[nx][ny]) {
						visit[nx][ny] = 1;
						q.push(Pos{ nx, ny });
						vt.push_back(Pos{ nx, ny });
					}
				}
			}
			else if (map[x][y] == 7) {
				int dx[2] = { -1,0 }, dy[2] = { 0,-1 };
				for (int i = 0; i < 2; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (i == 0)
						if (map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 7) continue;
					if (i == 1)
						if (map[nx][ny] == 2 || map[nx][ny] == 6 || map[nx][ny] == 7) continue;
					if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
					if (!visit[nx][ny] && map[nx][ny]) {
						visit[nx][ny] = 1;
						q.push(Pos{ nx, ny });
						vt.push_back(Pos{ nx, ny });
					}
				}
			}
		}
		cnt++;
	}
}
int main() {
	scanf("%d", &T);
	for (int tc = 1; tc <= T; tc++) {
		vt.clear();
		memset(map, 0, sizeof(map));
		memset(visit, 0, sizeof(visit));

		scanf("%d%d%d%d%d", &N, &M, &R, &C, &L);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				scanf("%d", &map[i][j]);
			}
		}
		bfs();
		printf("#%d %d\n", tc, vt.size());
	}
}