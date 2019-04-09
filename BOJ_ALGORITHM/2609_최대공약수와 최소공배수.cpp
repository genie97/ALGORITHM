#include<cstdio>
int E(int x, int y) {
	if (y == 0)
		return x;
	else
		return E(y, x%y);
}
int main() {
	int x, y;
	scanf("%d%d", &x, &y);
	int gcd = E(x, y);
	int lcm = x * y / gcd;
	printf("%d\n%d\n", gcd, lcm);
}