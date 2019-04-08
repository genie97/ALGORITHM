#include<cstdio>
int N, K, prime[1002];
int main() {
	scanf("%d%d", &N, &K);
	int cnt = 0;
	for (int i = 2; i <= N; i++)
		prime[i] = i;
	for (int i = 2; i <= N; i++) {
		if (prime[i] == 0) continue;
		for (int j = i; j <= N; j += i) {
			if (prime[j] != 0) {
				prime[j] = 0;
				cnt++;
				if (cnt == K) {
					printf("%d\n", j);
					return 0;
				}
			}
		}
	}
}