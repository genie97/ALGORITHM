#include<cstdio>
#include<queue>
#include<vector>
using namespace std;
typedef pair<int, int> p;
vector<int>student[32001];
queue<int> q;
int N, M, inDegree[32001];
void topology() {
	int result[32001];
	for (int i = 1; i <= N; i++) {
		if (inDegree[i] == 0)
			q.push(i);
	}
	for (int i = 1; i <= N; i++) {
		if (q.empty()) return;
		int x = q.front();
		q.pop();
		result[i] = x;
		for (int i = 0; i < student[x].size(); i++) {
			int y = student[x][i];
			if (--inDegree[y] == 0)
				q.push(y);
		}
	}
	for (int i = 1; i <= N; i++) {
		printf("%d ", result[i]);
	}
}
int main() {
	scanf("%d%d", &N, &M); 
	for (int i = 0; i < M; i++) {
		int a, b;
		scanf("%d%d", &a, &b);
		student[a].push_back(b);
		inDegree[b]++;
	}
	topology();
	printf("\n");
}