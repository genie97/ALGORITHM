/*
#include<cstdio>
#include<algorithm>
#include<utility>

#define MIN 0
using namespace std;
typedef pair<int, int> p;
p day[1000];
int N;
int dp[1000];

int main() {
	int maxPrice = 0;
	scanf("%d", &N);
	for (int i = 1; i <= N; i++) {
		int T, P;
		scanf("%d%d", &T, &P);   // first: 상담 기간 second: 금액
		day[i] = make_pair(T, P);
		dp[i] = day[i].second;
	}
	for (int i = 2; i <= N; i++) {
		for (int j = 1; j < i; j++) {
			if (i - j >= day[j].first) {
				dp[i] = max(day[i].second + dp[j], dp[i]);
			}
		}
	}
	for (int i = 1; i <= N; i++) {
		if (i + day[i].first <= N + 1) {
			if (maxPrice < dp[i]) {
				maxPrice = dp[i];
			}
		}
	}
	printf("%d\n", maxPrice);
}
*/

