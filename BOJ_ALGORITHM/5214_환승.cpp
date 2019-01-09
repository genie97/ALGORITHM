#include<cstdio>
#include<vector>
#include<queue>
using namespace std;
int N, K, M;
vector<vector<int>>vt;
bool visit[101001];
int dist[101001];

int main() {
	scanf("%d %d %d", &N, &K, &M);
	vt.resize(N + M + 1);
	for (int i = N+1; i < M+N+1; i++) {
		for (int j = 0; j < K; j++) {
			int a;
			scanf("%d", &a);
			vt[i].push_back(a);
			vt[a].push_back(i);
		}
	}
	queue<int> q;
	q.push(1);
	visit[1] = true;
	while (!q.empty()) {
		int cur = q.front();
		q.pop();
		if (cur == N)
			break;
		for (int next : vt[cur]) {
			if (!visit[next]) {
				visit[next] = true;
				dist[next] = dist[cur] + 1;
				q.push(next);
			}
		}
	}
	if (N != 1 && dist[N] == 0)
		printf("-1");
	else
		printf("%d\n", dist[N] / 2 + 1);  //하이퍼튜브는 연결하는 역할만!
}