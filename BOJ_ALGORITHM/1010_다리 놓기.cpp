#include<cstdio>
int main() {
	int dp[31];
	int t;
	scanf("%d", &t);
	while (t--) {
		int N, M;
		scanf("%d %d", &N, &M);
		dp[N] = 1;
		for (int i = N + 1; i <= M; i++) {
			dp[i] = dp[i - 1] * i / (i - N);
		}
		printf("%d\n", dp[M]);
	}
}
