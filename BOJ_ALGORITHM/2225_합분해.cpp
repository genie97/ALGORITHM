#include<cstdio>
int K, N;
int dp[201][201] = { 0, };
int main() {
	scanf("%d%d", &N, &K);
	for (int n = 0; n <= N; n++) {
		dp[1][n] = 1;
	}
	for (int k = 2; k <= K; k++) {
		for (int n = 0; n <= N; n++) {
			for (int i = 0; i <= n; i++) {
				dp[k][n] += dp[k - 1][i];
			}
			dp[k][n] %= 1000000000;
		}
	}
	printf("%d\n", dp[K][N]);
}