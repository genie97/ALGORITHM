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
	//n�� 1�� ��, 2*1: 2*1¥�� 1�� 1*2¥�� 0��
	//n�� 2�� ��, 2*2 2*1¥�� 2�� 1*2¥�� 2��
	//n�� 3�� ��, 2*3 2*1 3�� 1*2 
}