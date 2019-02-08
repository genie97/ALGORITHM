#include<cstdio>
using namespace std;
int n, k;
int coin[101], dp[10001];
int main() {
	scanf("%d %d", &n, &k);
	for (int i = 0; i < n; i++) {
		scanf("%d", &coin[i]);
	}
	dp[0] = 1;
	for (int i = 0; i < n; i++) {
		for (int j = 1; j <= k; j++) {
			if (j - coin[i] >= 0)
				dp[j] += dp[j - coin[i]];
		}
	}
	printf("%d\n",dp[k]);
}