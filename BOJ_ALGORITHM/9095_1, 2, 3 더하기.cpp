#include<cstdio>
int dp[11];
int main() {
	int T;
	dp[0] = 1;
	for (int i = 1; i <= 10; i++) {
		if (i - 1 >= 0)
			dp[i] += dp[i - 1];
		if (i - 2 >= 0)
			dp[i] += dp[i - 2];
		if (i - 3 >= 0)
			dp[i] += dp[i - 3];
	}
	scanf("%d", &T);
	while (T--) {
		int N;
		scanf("%d", &N);
		printf("%d\n", dp[N]);
	}
}