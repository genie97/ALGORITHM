#include<cstdio>
#include<vector>
#include<queue>
using namespace std;
int N, M;
int q1, q2;
int visit[101];
vector<int> family[101];
int main() {
	scanf("%d\n%d%d\n%d", &N, &q1, &q2, &M);
	for (int i = 0; i < M; i++) {
		int a, b;
		scanf("%d%d", &a, &b);
		family[a].push_back(b);
		family[b].push_back(a);
	}
	queue <int> q;
	int cnt = 0;
	q.push(q1);
	visit[q1] = 1;
	while (!q.empty()) {
		int start = q.front();
		q.pop();
		if (start == q2) {
			printf("%d\n", cnt);
			return 0;
		}

		for (int i = 0; i < family[start].size(); i++) {
			int next = family[start][i];
			if (!visit[next]) {
				visit[next] = 1;
				q.push(next);
				cnt++;
			}
			else
				cnt--;
		}
	}
	printf("-1\n");
}