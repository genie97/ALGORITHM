#include<cstdio>
int E(int x, int y) {
	if (y == 0)
		return x;
	else
		return E(y, x%y);
}
int main() {
	int T;
	scanf("%d", &T);
	while (T--) {
		int a, b;
		scanf("%d%d", &a, &b);
		int gcd = E(a, b);
		printf("%d\n", a*b / gcd);
	}
}