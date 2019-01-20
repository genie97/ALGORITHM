#include<cstdio>
#include<cstring>
using namespace std;
int N, M, ans = 0, check = 0;
int map[305][305], temp[305][305];
bool visit[305][305];
int dx[4] = { 0,0,1,-1 }, dy[4] = { 1,-1,0,0 };

void dfs(int x, int y) {
	visit[x][y] = true;
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (0 <= nx && nx < N && 0 <= ny && ny < M) {
			if (!visit[nx][ny] && map[nx][ny] > 0) {
				dfs(nx, ny);
			}
		}
	}
}
int divide() {
	int cnt = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (!visit[i][j] && map[i][j] > 0) {
				cnt++;
				dfs(i, j);
			}
		}
	}
	return cnt;
}
int countYear(int x, int y) {
	int year = 0;
	for (int i = 0; i < 4; i++) {
		int nx = dx[i] + x;
		int ny = dy[i] + y;
		if (0 <= nx && nx < N && 0 <= ny && ny < M) {
			if (map[nx][ny] <= 0 && map[x][y] > 0) {
				year++;
			}
		}
	}
	return year;
}
int main() {
	scanf("%d %d", &N, &M);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%d", &map[i][j]);
		}
	}
	while ((check = divide()) < 2) {
		for (int i = 0; i < N; i++)
			memset(visit[i], false, sizeof(int)*M);
		if (check == 0) {
			ans = 0;
			break;
		}
		ans++;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					temp[i][j] = countYear(i, j);
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] -= temp[i][j];
			}
		}
	}
	printf("%d\n", ans);
}