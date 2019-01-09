/*
#include<cstdio>
#include<algorithm>
#include<vector>
using namespace std;
int N, card[102], visited[102];
void dfs(int v) {
	if (visited[v] == 2)
		return;
	visited[v]++;
	dfs(card[v]);
}
void init() {
	for (int i = 1; i <= N; i++)
		if (visited[i] < 2)
			visited[i] = 0;
}
int main() {
	int cnt = 0;
	scanf("%d", &N);
	for (int i = 1; i <= N; i++)
		scanf("%d", &card[i]);
	for (int i = 1; i <= N; i++) {
		dfs(i);
		init();
	}
	for (int i = 1; i <= N; i++)
		if (visited[i] == 2)
			cnt++;
	printf("%d\n", cnt);
	for (int i = 1; i <= N; i++)
		if (visited[i] == 2)
			printf("%d\n", i);
}
*/