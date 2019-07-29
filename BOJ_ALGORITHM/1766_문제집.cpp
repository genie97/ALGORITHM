#include<cstdio>
#include<queue>
#include<vector>
#include<functional>
using namespace std;

int N, M, inDegree[32001];
vector<int> prob[32001];

void topologySort() {
	int result[100001];
	priority_queue<int, vector<int>, greater<int>> q;
	for (int i = 1; i <= N; i++)
		if (inDegree[i] == 0) q.push(i);
	for (int i = 1; i <= N; i++) {
		if (q.empty())
			return;
		int x = q.top();
		q.pop();
		result[i] = x;
		for (int j = 0; j < prob[x].size(); j++) {
			int y = prob[x][j];
			if (--inDegree[y] == 0)
				q.push(y);
		}
	}
	for (int i = 1; i <= N; i++)
		printf("%d ", result[i]);
}
int main() {
	scanf("%d%d", &N, &M);
	for (int i = 0; i < M; i++) {
		int a, b;
		scanf("%d%d", &a, &b);
		prob[a].push_back(b);
		inDegree[b]++;
	}
	topologySort();
}