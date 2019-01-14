#include<cstdio>
#include<utility>
#include<vector>
using namespace std;
typedef pair<int, int> p;

vector<bool> visit;
vector <p> vt;
int t, n;

int abs(int x) {
	if (x < 0)
		return -x;
	else
		return x;
}
bool dist(int x1, int y1, int x2, int y2) {
	if (abs(x1 - x2) + abs(y1 - y2) <= 1000)
		return true;
	else
		return false;
}
void dfs(int now) {
	visit[now] = true;
	for (int i = 1; i < n + 2; i++) {
		if (!visit[i] && dist(vt[now].first, vt[now].second, vt[i].first, vt[i].second))
			dfs(i);
	}
}
int main() {
	scanf("%d", &t);
	while (t--) {
		scanf("%d", &n);
		visit = vector<bool>(n + 2, false);
		vt = vector<p>(n + 2, make_pair(0, 0));
		for (int i = 0; i < n+2; i++) {
			scanf("%d %d", &vt[i].first, &vt[i].second);
		}
		dfs(0);
		if (visit[n+1])
			printf("happy\n");
		else
			printf("sad\n");
	}
}