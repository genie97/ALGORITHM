#include<cstdio>
#include<algorithm>
using namespace std;

int N;
int wine[10001], dp[10001];
int main() {
	scanf("%d", &N);
	for (int i = 1; i <= N; i++) {
		scanf("%d", &wine[i]);
	}
	dp[1] = wine[1];
	dp[2] = wine[1]+wine[2];
	for (int i = 3; i <= N; i++) {
		dp[i] += max(dp[i-1] , max(dp[i-2]+wine[i], dp[i - 3] + wine[i - 1] + wine[i]));
	}
	printf("%d\n", dp[N]);
}