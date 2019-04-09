#include<cstdio>
#include<cmath>
int N;
int isPrime(int x) {
	int end = (int)sqrt(x);
	for (int i = 2; i <= end; i++) {
		if (x%i == 0)return 0;
	}
	return 1;
}
int main() {
	while (1) {
		scanf("%d", &N);
		if (N == 0)
			break;
		int x = 2;
		while (x % 2 == 0 || !isPrime(x) || N - x % 2 == 0 || !isPrime(N - x)) {
			x++;
		}
		if (x == 0 || N - x <= 1)
			printf("Goldbach's conjecture is wrong.\n");
		else
			printf("%d = %d + %d\n", N, x, N - x);
	}
}