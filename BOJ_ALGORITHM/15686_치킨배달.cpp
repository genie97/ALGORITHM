#include<cstdio>
#include<algorithm>
#include<vector>
using namespace std;
typedef pair<int, int> p;
int N, M, map[51][51], visit[15], ans=1e9;
vector<p> home, chicken;
void dfs(int location, int cnt) {
	if (location > chicken.size())
		return;
	if (cnt == M) {
		int sum = 0;
		for (int i = 0; i < home.size(); i++) {
			int minV = 1e9;
			for (int j = 0; j < chicken.size(); j++) {
				if (visit[j]) {
					int dx = home[i].first - chicken[j].first;
					int dy = home[i].second - chicken[j].second;
					int dist = abs(dx) + abs(dy);
					minV = min(dist, minV);
				}
			}
			sum += minV;
		}
		ans = min(sum, ans);
	}
	visit[location] = true;
	dfs(location + 1, cnt + 1);
	visit[location] = false;
	dfs(location + 1, cnt);
}
int main() {
	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &map[i][j]);
			if (map[i][j] == 1)
				home.push_back({ i,j });
			if (map[i][j] == 2)
				chicken.push_back({ i,j });
		}
	}
	dfs(0, 0);
	printf("%d\n", ans);
}