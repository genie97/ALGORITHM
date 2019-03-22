#include<cstdio>
int main() {
	int n;
	scanf("%d", &n);
	int dp[1002];
	dp[0] = 0, dp[1] = 1, dp[2] = 3;
	for (int i = 0; i < n; i++) {
		int res = (dp[n - 1] + 2 * dp[n - 2]) % 10007;
		printf("%d", res);
	}
}