#include<cstdio>
#include<queue>
using namespace std;
int N, M, tomato[1001][1001], visit[1001][1001];
int dir[4][2] = { {-1,0},{1,0},{0,-1},{0,1} };

int main() {
	queue <pair<int, int>>q;
	scanf("%d %d", &M, &N);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%d", &tomato[i][j]);
			visit[i][j] = -1;
			if (tomato[i][j] == 1) {
				visit[i][j] = 0;
				q.push({ i,j });
			}
		}
	}
	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();
		for (int i = 0; i < 4; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];
			if (tomato[nx][ny] == 0 && visit[nx][ny] == -1) {
				if ((nx >= 0 && nx < N) && (ny >= 0 && ny < M)) {
					visit[nx][ny] = visit[x][y] + 1;
					q.push({ nx,ny });
				}
			}
		}
	}
	int maxV = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (maxV < visit[i][j])
				maxV = visit[i][j];
		}
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (tomato[i][j] == 0 && visit[i][j] == -1)
				maxV = -1;
		}
	}
	printf("%d\n", maxV);
}
