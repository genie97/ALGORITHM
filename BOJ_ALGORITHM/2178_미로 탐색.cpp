#include<cstdio>
#include<queue>
#include<cstring>
#include<algorithm>
using namespace std;
int N, M, map[103][103], dp[103][103];
bool visit[103][103];
typedef pair<int, int> pr;
queue<pr> q;
int dx[4] = { 1,-1,0,0 }, dy[4] = { 0,0,1,-1 };

int main() {
	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%1d", &map[i][j]);
			dp[i][j] = 1;
		}
	}
	q.push(make_pair(0, 0));

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		visit[x][y] = true;
		q.pop();
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M)
				continue;
			if (map[nx][ny] == 1 && !visit[nx][ny]) {
				visit[nx][ny] = true;
				q.push(make_pair(nx, ny));
				dp[nx][ny] += dp[x][y];
			}
			if (nx == N - 1 && ny == M - 1)
				break;
		}
	}
	printf("%d\n", dp[N - 1][M - 1]);
}
