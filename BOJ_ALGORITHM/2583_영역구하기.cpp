#include<cstdio>
#include<algorithm>
#include<vector>
using namespace std;

int M, N, K;
int map[100][100] = { 0, }, visit[100][100] = { 0, };

int dx[4] = { 1,-1,0,0 }, dy[4] = { 0,0,1,-1 };

int dfs(int x, int y) {
	int cnt = 1;

	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx < 0 || nx >= M || ny < 0 || ny >= N)
			continue;
		if (visit[nx][ny] || map[nx][ny])
			continue;
		visit[nx][ny]++;
		cnt += dfs(nx, ny);
	}
	return cnt;
}

int main() {
	scanf("%d%d%d", &M, &N, &K);
	for (int i = 0; i < K; i++) {
		int a, b, c, d;
		scanf("%d%d%d%d", &a, &b, &c, &d);
		for (int i = a; i < c; i++)
			for (int j = b; j < d; j++)
				map[j][i]++;
	}
	vector<int> vt;
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			if (!map[i][j] && !visit[i][j]) {
				visit[i][j]++;
				vt.push_back(dfs(i, j));
			}
		}
	}
	printf("%d\n", vt.size());
	sort(vt.begin(), vt.end());
	for (int i = 0; i < vt.size(); i++) {
		printf("%d ", vt[i]);
	}
}