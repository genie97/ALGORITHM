#include<cstdio>
#include<algorithm>
#include<cstring>
#include<set>
using namespace std;
int T, N;
int main() {
	scanf("%d", &T);
	for (int tc = 1; tc <= T; tc++) {
		int maxV = 0, sc = 0, res = 0, check[10000] = { 0, };
		scanf("%d", &N);
		check[0] = 1;
		for (int i = 0; i < N; i++) {
			scanf("%d", &sc);
			maxV += sc;
			for (int j = maxV; j >= sc; j--)
				check[j] |= check[j - sc];
		}
		for (int i = 0; i <= maxV; i++) {
			res += check[i];
		}
		printf("#%d %d\n", tc, res);
	}
}