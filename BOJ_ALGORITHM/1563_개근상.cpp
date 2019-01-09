//지각 두번 결석 세번 연속//
/*
#include<cstdio>
#include<cstring>
int dp[1001][3][4];
int N;

int at(int o, int l, int a) {
	if (l == 2 || a == 3)
		return 0;
	if (o == N)
		return 1;
	if (dp[o][l][a] != -1)
		return dp[o][l][a] % 1000000;
	dp[o][l][a] = at(o + 1, l, 0) + at(o + 1, l + 1, 0) + at(o + 1, l, a + 1);
	return dp[o][l][a] % 1000000;
}
int main() {
	scanf("%d", &N);
	memset(dp, -1, sizeof(dp));
	printf("%d\n", at(0, 0, 0));
}
*/