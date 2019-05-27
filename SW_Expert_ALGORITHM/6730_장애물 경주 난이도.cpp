#include<cstdio>
#include<algorithm>
using namespace std;
int T, N, cnt = 0;
int main() {
	scanf("%d", &T);
	while (T--) {
		int UM = 0, DM = 0, check = 0;
		int block[101];
		scanf("%d", &N);
		for (int i = 0; i < N; i++) {
			scanf("%d", &block[i]);
		}
		for (int i = 0; i < N - 1; i++) {
			if (block[i] < block[i + 1]) {
				UM = max(UM, block[i + 1] - block[i]);
			}
			else if (block[i] > block[i + 1]) {
				DM = max(DM, block[i] - block[i + 1]);
			}
		}
		cnt++;
		printf("#%d %d %d\n", cnt, UM, DM);
	}
}