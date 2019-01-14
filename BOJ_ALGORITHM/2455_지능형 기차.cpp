#include<cstdio>
#include<algorithm>
using namespace std;
int train[4][2];
int dp[4];
int main() {
	for (int i = 0; i < 4; i++) {
		scanf("%d %d", &train[i][0], &train[i][1]);
	}
	dp[0] = train[0][1];
	for (int i = 1; i < 3; i++) {
		dp[i] = dp[i - 1] - train[i][0] + train[i][1];
	}
	printf("%d", *max_element(dp, dp + 4));

}