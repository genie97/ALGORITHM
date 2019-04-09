#include<cstdio>
int E, S, M;
int main() {
	scanf("%d%d%d", &E, &S, &M);
	int ans;
	for (int x = 0; x <= 28 * 19; x++) {
		for (int y = 0; y < 15 * 19; y++) {
			for (int z = 0; z < 15 * 28; z++) {
				if (15 * x + E == 28 * y + S && 28 * y + S == 19 * z + M) {
					ans = 15 * x + E;
					break;
				}
			}
		}
	}
	printf("%d", ans);
}