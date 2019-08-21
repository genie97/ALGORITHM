#include<cstdio>
#include<algorithm>
using namespace std;
int T, N;
int main() {
	for (int tc = 1; tc <= 10; tc++) {
		scanf("%d", &N);
		int map[100][100], maxV = 0;
		for (int i = 0; i < 100; i++) {
			int sum = 0;
			for (int j = 0; j < 100; j++) {
				scanf("%d", &map[i][j]);
				sum += map[i][j];
			}
			maxV = max(maxV, sum);
		}
		int sum_crossL = 0, sum_crossR = 0;
		for (int i = 0; i < 100; i++) {
			int sum = 0;
			for (int j = 0; j < 100; j++) {
				if (i == j) {
					sum_crossL += map[i][j];
				}
				if (i == 100 - j) {
					sum_crossR += map[i][j];
				}
				sum += map[j][i];
			}
			maxV = max(maxV, sum);
		}
		maxV = max(maxV, max(sum_crossL, sum_crossR));
		printf("#%d %d\n", N, maxV);
	}
}