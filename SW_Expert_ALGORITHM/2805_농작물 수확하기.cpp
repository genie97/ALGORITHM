#include<cstdio>
#include<cstring>
using namespace std;
int T, N, sum;
int map[51][51];
int main() {
	scanf("%d", &T);
	for (int tc = 1; tc <= T; tc++) {
		scanf("%d", &N);
		sum = 0;
		memset(map, 0, sizeof(map));
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				scanf("%1d", &map[i][j]);
			}
		}
		int mid = N / 2, k = 1;
		for (int i = 0; i < mid; i++) {
			for (int j = 0; j < k; j++) {
				sum += map[i][mid - i + j];
				sum += map[N - 1 - i][mid - i + j];
			}
			k += 2;
			sum += (map[mid][i] + map[mid][N - 1 - i]);
		}
		printf("#%d %d", tc, sum += map[mid][mid]);
	}
}