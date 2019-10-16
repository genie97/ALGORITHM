#include<cstdio>
#include<vector>
using namespace std;
int N;
int dx[4] = { 0,-1,0,1 }, dy[4] = { 1,0,-1,0 }; // 0: 우 1: 상 2: 좌 3: 하
int map[102][102] = {0,};

int main() {
	scanf("%d", &N);

	for (int i = 0; i < N; i++) {
		int x, y, d, g;
		//중요한 부분, x가 가로로 움직이는 부분 / y가 세로로 움직이는 부분
		scanf("%d%d%d%d", &y, &x, &d, &g);

		vector<int> dir;
		dir.push_back(d);

		for (int gen = 1; gen <= g; gen++) {
			for (int s = dir.size() - 1; s >= 0; s--) {
				dir.push_back((dir[s] + 1) % 4);
			}
		}
		map[x][y] = 1;
		for (int j = 0; j < dir.size(); j++) {
			int nd = dir[j];
			int nx = x + dx[nd];
			int ny = y + dy[nd];
			map[nx][ny] = 1;
			x = nx;
			y = ny;
		}
	}
	int ans = 0;
	for (int i = 0; i < 100; i++) {
		for (int j = 0; j < 100; j++) {
			if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1])
				ans++;
		}
	}
	printf("%d", ans);
}