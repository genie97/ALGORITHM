#include<cstdio>
#include<algorithm>
using namespace std;
int num[100], prime[1001];
int main() {
	int N, cnt = 0;
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		scanf("%d", &num[i]);
	}
	int maxV = *max_element(num, num + N);
	for (int i = 2; i <= maxV; i++) {
		prime[i] = i;
	}
	for (int i = 2; i <= maxV; i++) {
		if (prime[i] == 0) continue;
		for (int j = i + i; j <= maxV; j += i) {
			prime[j] = 0;
		}
	}
	for (int i = 0; i < N; i++) {
		if (prime[num[i]] != 0)
			cnt++;
	}
	printf("%d\n", cnt);
}