#include<cstdio>
#include<vector>
#include<algorithm>
using namespace std;
int N, s[25][25];
bool visit[25] = { false, };
int sum = 0;
int ans = 1e9;
void dfs(int cnt, int location) {
	if (cnt == (N / 2)) {
		vector<int> start, link;
		for (int i = 0; i < N; i++) {
			if (visit[i]) {
				start.push_back(i);
			}
			else
				link.push_back(i);
		}
		int start_sum = 0, link_sum = 0;
		for (int i = 0; i < start.size(); i++) {
			for (int j = i + 1; j < start.size(); j++) {
				int sx = start[i], sy = start[j];
				int lx = link[i], ly = link[j];
				start_sum += s[sx][sy] + s[sy][sx];
				link_sum += s[lx][ly] + s[ly][lx];
			}
		}
		ans = min(ans, abs(start_sum - link_sum));
		return;
	}
	for (int i = location + 1; i < N; i++) {
		if (visit[i] == false) {
			visit[i] = true;
			dfs(cnt + 1, i);
			visit[i] = false;
		}
	}
}
int main() {
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &s[i][j]);
		}
	}
	dfs(0, 0);
	printf("%d\n", ans);
}