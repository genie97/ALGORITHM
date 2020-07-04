#include<cstdio>
#include<cstring>
#include<iostream>
using namespace std;
int dx[4] = { 0,0,1,-1 }, dy[4] = { 1,-1,0,0 };
char map[101][101], c_map[101][101];
int visit[101][101] = { 0, };
int N;
int n_area = 0, a_area = 0;
void dfs(int x, int y, char color) {
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx < 0 || nx >= N || ny < 0 || ny >= N)
			continue;
		if (visit[nx][ny]) continue;
		if (map[nx][ny] != color) continue;
		visit[nx][ny] = 1;
		dfs(nx, ny, color);
	}
}

int main() {
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		scanf("%s", &map[i]);
	}
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (map[i][j] == 'G') {
				c_map[i][j] = 'R';
			}
			else {
				c_map[i][j] = map[i][j];
			}
		}
	}


	//일반인
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (!visit[i][j]) {
				visit[i][j] = 1;
				dfs(i, j, map[i][j]);
				n_area++;
			}
		}
	} 
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			map[i][j] = c_map[i][j];
		}
	}
	memset(visit, 0, sizeof(visit));

	//적록색약 (빨강 초록이 같은 것으로 판단)
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (!visit[i][j]) {
				visit[i][j] = 1;
				dfs(i, j, map[i][j]);
				a_area++;
			}
		}
	}

	cout << n_area << ' ' << a_area << endl;
}
