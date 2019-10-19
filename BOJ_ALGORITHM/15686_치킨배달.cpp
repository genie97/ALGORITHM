// 2019/10/19 기존 8ms->4ms
#include<cstdio>
#include<vector>
#include<algorithm>
using namespace std;
typedef struct Pos {
	int x, y;
};
int N, M;
int map[55][55];
int num[15];
int ans = 987654321;
vector<Pos> vt;
vector<Pos> home, chicken;
void make_comb(int idx, int cnt) {
	if (cnt == M) {
		int sum = 0;
		for (int i = 0; i < home.size(); i++) {
			int minV = 987654321;
			for (int j = 0; j < chicken.size(); j++) {
				int dx = home[i].x - chicken[j].x;
				int dy = home[i].y - chicken[j].y;
				int dist = abs(dx) + abs(dy);
				minV = min(dist, minV);
			}
			sum += minV;
		}
		ans = min(sum, ans);
		return;
	}
	for (int i = idx; i < vt.size(); i++) {
		if (num[i] == 1) continue;
		num[i] = 1;
		chicken.push_back(vt[i]);
		make_comb(i, cnt + 1);
		num[i] = 0;
		chicken.pop_back();
	}
}
int main() {
	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &map[i][j]);
			if (map[i][j] == 2) {
				vt.push_back({ i,j });
			}
			if (map[i][j] == 1) {
				home.push_back({ i,j });
			}
		}
	}
	make_comb(0, 0);
	printf("%d", ans);
}
/*
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
*/
