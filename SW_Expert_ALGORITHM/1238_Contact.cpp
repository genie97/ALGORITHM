#include<cstdio>
#include<algorithm>
#include<vector>
#include<queue>
#include<cstring>
using namespace std;
int data_len, start;
vector<int> graph[101];
bool c[101];
int bfs() {
	queue<int> q;
	queue<int> last;
	q.push(start);
	c[start] = true;
	while (q.size()) {
		int q_size = q.size();
		while (!last.empty())
			last.pop();
		for (int i = 0; i < q_size; i++) {
			int cur = q.front();
			q.pop();
			last.push(cur);
			for (int j = 0; j < graph[cur].size(); j++) {
				if (!c[graph[cur][j]]) {
					c[graph[cur][j]] = true;
					q.push(graph[cur][j]);
				}
			}
		}
	}
	int maxV = 0;
	while (!last.empty()) {
		int tmp = last.front();
		last.pop();
		maxV = max(maxV, tmp);
	}
	return maxV;
}
int main() {
	for (int tc = 1; tc <= 10; tc++) {
		memset(graph, 0, sizeof(graph));
		memset(c, false, sizeof(c));
		scanf("%d%d", &data_len, &start);
		for (int i = 0; i < data_len / 2; i++) {
			int a, b;
			scanf("%d%d", &a, &b);
			graph[a].push_back(b);
		}
		printf("#%d %d\n", tc, bfs());
	}
}