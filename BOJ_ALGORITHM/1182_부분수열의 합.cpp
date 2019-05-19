#include<cstdio>
using namespace std;
int N, S, ans = 0;
int num[20];
void dfs(int index, int sum) {
	sum += num[index];
	if (index >= N)
		return;
	if (sum == S)
		ans++;

	dfs(index + 1, sum - num[index]);
	dfs(index + 1, sum);
}
int main() {
	scanf("%d%d", &N, &S);
	for (int i = 0; i < N; i++) {
		scanf("%d", &num[i]);
	}
	dfs(0, 0);
	printf("%d", ans);
}
