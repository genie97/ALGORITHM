#include<cstdio>
int main() {
	long long s;
	int n = 0;
	scanf("%lld", &s);
	for (long long i = 1; i <= s; i++) {
		s -= i;
		n++;
	}
	printf("%d", n);
}