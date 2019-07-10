#include<cstdio>
#include<cstring>
#include<algorithm>
#include<vector>
using namespace std;
int dx[4] = { 1,-1,0,0 }, dy[4] = { 0,0,1,-1 };
int visit[105][105] = { 0, }, map[105][105], area[105];
int N, total, area_cnt = 0;
int dfs(int x, int y) {
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx < 0 || nx >= N || ny < 0 || ny >= N)
			continue;
		if (!visit[nx][ny] && map[nx][ny] > 0) {
			visit[nx][ny] = 1;
			dfs(nx, ny);
		}
	}
	return 1;
}
int main() {
	int T, cnt = 0;
	scanf("%d", &T);
	while (T--) {
		cnt++;
		scanf("%d", &N);
		total = N * N;
		vector<pair<int, int>> vt[105];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int a;
				scanf("%d", &a);
				vt[a].push_back({ i,j });
				map[i][j] = a;
			}
		}
		int day = 1;
		while (1) {
			if (total == 0)
				break;
			//dfs를 먼저해야 하는 이유: 하루가 지난 후의 덩어리 개수는 그 다음날의 개수가 되기 때문
			area_cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > 0 && !visit[i][j])
						area_cnt += dfs(i, j);
				}
			}
			area[day] = area_cnt;
			memset(visit, 0, sizeof(visit));
			for (int i = 0; i < vt[day].size(); i++) {
				int x = vt[day][i].first;
				int y = vt[day][i].second;
				map[x][y] = 0;
				total--;
			}
			day++;
		}
		int ans = *max_element(area, area + 101);
		printf("#%d %d\n", cnt, ans);
		memset(map, 0, sizeof(map));
		memset(area, 0, sizeof(area));
	}
}