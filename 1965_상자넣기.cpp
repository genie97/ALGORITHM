#include<cstdio>
#include<algorithm>	
using namespace std;

int main() {
	int n;
	int box[1001];
	int dp[1001] = { 0, };
	int maxV=0;
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &box[i]);
	}
	for (int i = 0; i < n; i++) {
		dp[i] = 1;
		for (int j = 0; j < i; j++) {
			if (box[i] > box[j] && dp[j] + 1 > dp[i])
				dp[i] = dp[j] + 1;
		}
		maxV=max(maxV, dp[i]);
	}
	printf("%d\n", maxV);
}