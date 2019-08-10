#include<cstdio>
#include<algorithm>
using namespace std;
int N, res;
int arr[1001], dp[1001] = { 0, };
int main() {
	scanf("%d", &N);
	for (int i = 0; i < N; i++)
		scanf("%d", &arr[i]);
	for (int i = 0; i < N; i++) {
		dp[i] = 1;
		for (int j = 0; j < i; j++) {
			if (arr[j] < arr[i] && dp[i] < dp[j] + 1)
				dp[i] = dp[j] + 1;
		}
	}
	int  res = *max_element(dp, dp + N);
	printf("%d\n", res);
}
