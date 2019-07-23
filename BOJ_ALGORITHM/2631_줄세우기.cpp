#include<cstdio>
#include<cstring>
#include<algorithm>
using namespace std;
int N, maxV;
int student[201];
int dp[201];
int main() {
	maxV = 0;
	memset(student, 0, sizeof(student));
	memset(dp, 0, sizeof(dp));
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		scanf("%d", &student[i]);
	}
	for (int i = 0; i < N; i++) {
		dp[i] = 1;
		for (int j = 0; j < i; j++) {
			if (student[j] < student[i] && dp[i] < dp[j] + 1)
				dp[i] = dp[j] + 1;
		}
		maxV = max(maxV, dp[i]);
	}
	printf("%d", N - maxV);
}