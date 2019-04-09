#include<cstdio>
int E(int x, int y) {
	if (y == 0)
		return x;
	else
		return E(y, x%y);
}
int main() {
	int T, a[100]; 
	scanf("%d", &T);
	while (T--) {
		int N;
		long long sum = 0;
		scanf("%d", &N);
		for (int i = 0; i < N; i++) {
			scanf("%d", &a[i]);
		}
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				sum += E(a[i], a[j]);
			}
		}
		printf("%lld\n", sum);
	}
}