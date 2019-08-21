#include<cstdio>
int N, M, T;
long long pow(int x, int cnt) {
	if (cnt == 1) return x;
	else pow(x*N, cnt - 1);
}
int main() {
	for (int tc = 0; tc < 10; tc++) {
		scanf("%d", &T);
		scanf("%d%d", &N, &M);
		printf("#%d %lld\n", T, pow(N, M));
	}
}