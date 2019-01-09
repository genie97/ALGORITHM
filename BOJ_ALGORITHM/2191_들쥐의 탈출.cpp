#include<cstdio>
#include<algorithm>	
#include<vector>
#include<cmath>

using namespace std;
typedef pair <double, double> p;
int N, M, S, V; //N:µÈ¡„ M:∂•±º S:∏≈ V:µÈ¡„
int visited[101], bm[101];
p mouse[101], hole[101];
vector<vector<int>>vt;

bool dfs(int v) {
	if (visited[v])
		return 0;
	visited[v] = 1;
	for (int i : vt[v]) {
		if (!bm[i] || dfs(bm[i])) {
			bm[i] = v;
			return 1;
		}
	}
	return 0;
}

bool in(p a, p b) {
	double dis = sqrt(pow(a.first - b.first, 2) + pow((a.second - b.second), 2));
	if (S*V >= dis)
		return true;
	return false;
}

int main() {
	scanf("%d %d %d %d", &N, &M, &S, &V);
	vector<p> mo, ho;

	for (int i = 1; i <= N; i++) {
		scanf("%lf %lf", &mouse[i].first, &mouse[i].second);
	}
	for (int i = 1; i <= M; i++) {
		scanf("%lf %lf", &hole[i].first, &hole[i].second);
	}
	vt.resize(N + 1);
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			if (in(mouse[i], hole[j]))
				vt[i].push_back(j);
		}
	}
	/*¿Ã∫–∏≈ƒ™*/
	int cnt = 0;
	for (int i = 1; i <= N; i++) {
		fill(visited + 1, visited + N + 1, 0);
		if (dfs(i))
			cnt++;
	}
	printf("%d\n", N - cnt);
}