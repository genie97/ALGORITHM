#include<cstdio>
#include<algorithm>
using namespace std;
long long a[300000] = { 0, };

int main() {
	long long N = 0;
	while(1) {
		scanf("%lld", &N);
		if (N == 0)
			break;
		int cnt = 0;
		for (long long i = 2; i <= 2 * N; i++) {
			a[i] = i;
		}
		for (long long i = 2; i <= 2 * N; i++) {
			if (a[i] == 0) continue;
			for (long long j = i + i; j <= 2 * N; j += i) { //에라토스테네스의 체: 자기 이후부터 제거
				a[j] = 0;
			}
		}
		for (long long i = N+1; i <= 2 * N; i++) {
			if (a[i] != 0)
				cnt++;
		}
		printf("%d\n", cnt);
		fill(a, a + 300000, 0);
	}
}