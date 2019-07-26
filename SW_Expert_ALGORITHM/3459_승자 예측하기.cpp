#include<cstdio>
#include<cmath>
int T;
long long N;
int main() {
	scanf("%d", &T);
	for (int tc = 1; tc <= T; tc++) {
		scanf("%lld", &N);
		int check = 0;
		long long turn = 1;
		long long times = 1;
		while (turn < N) {
			if (!check)
				times *= 4;
			turn += times;
			check = !check;
		}
		printf("#%d %s\n", tc, check ? "Alice" : "Bob");
	}
}