#include<cstdio>
#include<cstring>
#include<queue>
#include<algorithm>
using namespace std;
int dx[4] = { 1,-1,0,0 };
int dy[4] = { 0,0,1,-1 };
int map[51][51] = { 0, };
bool visit[51][51] = { false };
int T, M, N, K;
queue<pair<int, int>> q;
int cnt = 0;
void bfs(int i, int j) {
	visit[i][j] = true;
	q.push({ i,j });
	while (!q.empty()) {
		int y = q.front().first;
		int x = q.front().second;
		q.pop();
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny < 0 || ny >= N || nx < 0 || nx >= M)
				continue;
			if (!visit[ny][nx] && map[ny][nx] == 1) {
				q.push({ ny, nx });
				visit[ny][nx] = true;
			}
		}
	}
	cnt++;
}
int main() {
	scanf("%d", &T);
	while (T--) {
		scanf("%d%d%d", &M, &N, &K);
		for (int i = 0; i < K; i++) {
			int a, b;
			scanf("%d%d", &a, &b);
			map[b][a] = 1;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visit[i][j] && map[i][j] == 1)
					bfs(i, j);
			}
		}
		printf("%d\n", cnt);
		/*초기화*/
		queue<pair<int, int>> empty;
		swap(q, empty);
		memset(visit, 0, sizeof(visit));
		memset(map, 0, sizeof(map));
		cnt = 0;
	}
}
