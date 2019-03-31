#include<cstdio>
long long facto(int s) {
	if (s == 0)
		return 1;
	else
		return facto(s - 1) * s;
}
int main() {
	int N;
	scanf("%d", &N);
	printf("%lld\n", facto(N));
}