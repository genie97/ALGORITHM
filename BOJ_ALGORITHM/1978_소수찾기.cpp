#include<cstdio>
#include<cmath>
int num[100];
bool isPrime(int x) {
	if (x == 1)
		return false;
	int end = (int)sqrt(x);
	for (int i = 2; i <= end; i++) {
		if (x%i == 0)
			return false;
	}
	return true;
}
int main() {
	int N, cnt = 0;
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		scanf("%d", &num[i]);
		if (isPrime(num[i]))
			cnt++;
	}
	printf("%d\n", cnt);
}