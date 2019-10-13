#include<cstdio>
#include<algorithm>
using namespace std;
int N, M, R;
int map[305][305], temp[305][305];
int out_line;
bool visit[305][305];

void dfs(int x, int y, int dir, int r, int range, int orig_map) {
	if (r == R) { // 아웃라인을 다 보는 경우
		temp[x][y] = orig_map;
		return;
	}
	if (dir == 0) {
		if (x + 1 < N - range) dfs(x + 1, y, dir, r + 1, range, orig_map);
		else dfs(x, y + 1, 1, r + 1, range, orig_map);
	}
	else if (dir == 1) {
		if (y + 1 < M - range) dfs(x, y + 1, dir, r + 1, range, orig_map);
		else dfs(x - 1, y, 2, r + 1, range, orig_map);
	}
	else if (dir == 2) {
		if (x-1 >=range) dfs(x-1, y, dir, r + 1, range, orig_map);
		else dfs(x, y-1, 3, r + 1, range, orig_map);
	}
	else if (dir == 3) {
		if (y - 1 >= range) dfs(x, y-1, dir, r + 1, range, orig_map);
		else dfs(x+1, y, 0, r + 1, range, orig_map);
	}
}
void rotate_array() {
	for (int line = 0; line < out_line; line++) {
		int x = line;
		int y = line;
		for (int i = x; i < N - line; i++) { //밑으로 이동하는 라인 0
			if (visit[i][y] == false) {
				dfs(i, y, 0, 0, line, map[i][y]);
				visit[i][y] = true;
			}
		}
		for (int i = y; i < M - line; i++) { //오른쪽으로 이동하는 라인 1
			if (visit[N - 1 - line][i] == false) {
				dfs(N - 1 - line, i, 1, 0, line, map[N - 1 - line][i]);
				visit[N - 1 - line][i] = true;
			}
		}
		for (int i = N - 1 - line; i >= line; i--) { //위로 이동하는 라인 2
			if (visit[i][M - 1 - line] == false) {
				dfs(i, M - 1 - line, 2, 0, line, map[i][M - 1 - line]);
				visit[i][M - 1 - line] = true;
			}
		}
		for (int i = M - 1 - line; i >= line; i--) { //왼쪽으로 이동하는 라인 3
			if (visit[x][i] == false) {
				dfs(x, i, 3, 0, line, map[x][i]);
				visit[x][i] = true;
			}
		}
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			printf("%d ", temp[i][j]);
		}
		printf("\n");
	}
}
int main() {
	scanf("%d%d%d", &N, &M, &R);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%d", &map[i][j]);
		}
	}
	out_line = min(N, M) / 2;
	rotate_array();
}