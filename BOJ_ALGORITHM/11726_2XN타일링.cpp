#include<cstdio>
int dp[1001], n;
int main() {
	scanf("%d", &n);
	dp[1] = 1, dp[2] = 2;
	for (int i = 3; i <= n; i++) {
		dp[i] = dp[i - 1] + dp[i - 2];
		dp[i] %= 10007;
	}
	printf("%d\n", dp[n]);
	//n이 1일 땐, 2*1: 2*1짜리 1개 1*2짜리 0개
	//n이 2일 땐, 2*2 2*1짜리 2개 1*2짜리 2개
	//n이 3일 땐, 2*3 2*1 3개 1*2 
}
