#include<cstdio>
#include<cstring>
#include<algorithm>
#include<queue>
#include<vector>
using namespace std;
int T, N, ans;
char map[400][400];
int dx[8] = { -1,-1,-1,0,0,1,1,1 }, dy[8] = { -1,0,1,-1,1,-1,0,1 };
vector<pair<int, int>>vt;
void bfs(int x, int y) {
	queue<pair<int, int>>q;
	q.push({ x,y });
	map[x][y] = 'C';
	while (!q.empty()) {
		int curX = q.front().first;
		int curY = q.front().second;
		q.pop();
		for (int i = 0; i < 8; i++) {
			int nx = curX + dx[i];
			int ny = curY + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;
			if (map[nx][ny] == '*' || map[nx][ny] == 'C')
				continue;
			if (map[nx][ny]=='0')
				q.push({ nx, ny });
			map[nx][ny] = 'C';
		}
	}
}
int main() {
	scanf("%d", &T);
	for (int tc = 0; tc < T; tc++) {
		//ÃÊ±âÈ­
		memset(map, 0, sizeof(map));
		vt.clear();
		ans = 0;
		scanf("%d", &N);
		for (int i = 0; i < N; i++)
			scanf("%s", map[i]);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != '.')
					continue;
				int cnt = 0;
				for (int k = 0; k < 8; k++) {
					int ni = i + dx[k];
					int nj = j + dy[k];
					if (ni < 0 || ni >= N || nj < 0 || nj >= N)
						continue;
					if (map[ni][nj] == '*')
						cnt++;
				}
				if (cnt == 0)
					vt.push_back({ i, j });
				map[i][j] = cnt+'0';
			}
		}
		for (int i = 0; i < vt.size(); i++) {
			int x = vt[i].first;
			int y = vt[i].second;
			if (map[x][y] != 'C') {
				bfs(x, y);
				ans++;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] >= '1'&&map[i][j] <= '8')
					ans++;
			}
		}
		printf("#%d %d\n", tc + 1, ans);
	}
}