#include<cstdio>
#include<queue>
#include<vector>
#include<algorithm>
using namespace std;
int N;
int inDegree[501], time[501]; //time은 원래 가지고 있는 처음 시간
vector<int> vt[501];
void topologySort() {
	queue<int> q;
	int res[501] = { 0, }; //res는 꼭 초기화를 해줄 것!
	for (int i = 1; i <= N; i++) {
		if (inDegree[i] == 0) {
			q.push(i);
			res[i] = time[i];
		}
	}
	while (!q.empty()) {
		int  x = q.front();
		q.pop();
		for (int j = 0; j < vt[x].size(); j++) {
			int y = vt[x][j];
			res[y] = max(res[y], res[x] + time[y]);
			if (--inDegree[y] == 0) q.push(y);
		}

	}
	for (int i = 1; i <= N; i++)
		printf("%d\n", res[i]);
}
int main() {
	scanf("%d", &N);
	for (int i = 1; i <= N; i++) {
		scanf("%d", &time[i]);
		int a;
		while (1) {
			scanf("%d", &a);
			if (a == -1)
				break;
			vt[a].push_back(i);
			inDegree[i]++;
		}
	}
	topologySort();
}