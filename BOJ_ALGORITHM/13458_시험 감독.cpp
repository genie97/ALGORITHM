#include<cstdio>
#include<cmath>
#include<algorithm>
using namespace std;

long long N, B, C;
long long A[1000002];
long long cnt[1000002];
long long ans = 0;

int main() {
	scanf("%lld", &N);
	for (int i = 1; i <= N; i++) {
		scanf("%lld", &A[i]);
	}
	scanf("%lld%lld", &B, &C);
	sort(A + 1, A + N + 1);
	for (int i = 1; i <= N; i++) {
		cnt[i] = 1;
		if (A[i] - (cnt[i]*B) > 0) {
			cnt[i] += (int)ceil((double)((A[i] - (cnt[i])*B)) / C);
		}
		ans += cnt[i];
	}
	printf("%lld\n", ans);
}