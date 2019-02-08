#include<cstdio>
#include<algorithm>
# define INF 987654321
using namespace std;
int n, k;
int coin[101], dp[10001];
int main() {
	scanf("%d %d", &n, &k);
	for (int i = 0; i < n; i++) {
		scanf("%d", &coin[i]);
	}
	for (int i = 1; i <= k; i++) {
		dp[i] = INF;
		for (int j = 0; j <n; j++) {
			int mem = i - coin[j];
			if (mem >= 0 && dp[mem] != INF)
				dp[i] = min(dp[i], dp[mem] + 1);
		}
	}
	if (dp[k] == INF)
		printf("-1\n");
	else
		printf("%d\n", dp[k]);
}