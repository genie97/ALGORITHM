#include<cstdio>
#include<algorithm>
using namespace std;
int N, a[1001], dp[1001];

int main() {
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		scanf("%d", &a[i]);
	}
	for (int i = 0; i < N; i++) {
		dp[i] = 1;
		for (int j = 0; j < i; j++) {
			if (a[i] < a[j] && dp[i] < dp[j] + 1)
				dp[i] = dp[j]+ 1;
		}
	}
	int res = *max_element(dp, dp + N);
	printf("%d\n", res);
}
