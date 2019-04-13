#include<cstdio>
#include<algorithm>
#include<vector>
#include<queue>
using namespace std;
typedef pair<int, int> p;
int N, M, maxV=0;
int map[9][9], temp[9][9];
int dx[4] = { 1,-1,0,0 }, dy[4] = { 0,0,1,-1 };

void copyMap(int (*a)[9], int (*b)[9]) {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			a[i][j] = b[i][j];
		}
	}
}
void bfs() {
	int new_map[9][9];
	copyMap(new_map, temp);
	queue<p> virus;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (new_map[i][j] == 2) {
				virus.push({ i,j });
			}
		}
	}
	while (!virus.empty()) {
		int x = virus.front().first;
		int y = virus.front().second;
		virus.pop();
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M)
				continue;
			if (new_map[nx][ny] == 0) {
				new_map[nx][ny] = 2;
				virus.push({ nx, ny });
			}
		}
	}
	 int area=0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (new_map[i][j] == 0)
				area++;
		}
	}
	maxV = max(maxV, area);
}
void make_wall(int cnt) {
	if (cnt == 3) {
		bfs();
		return;
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (temp[i][j] == 0) {
				temp[i][j] = 1;
				make_wall(cnt + 1);
				temp[i][j] = 0;
			}
		}
	}
}
//0은 빈칸 1은 벽 2는 바이러스 벽은 3개를 세워야함
int main() {
	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%d", &map[i][j]);
		}
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (map[i][j] == 0) {
				copyMap(temp, map);
				temp[i][j] = 1;
				make_wall(1);
				temp[i][j] = 0;
			}
		}
	}
	printf("%d\n", maxV);
}