#include<cstdio>
#include<algorithm>
using namespace std;
int T, N, B;
int emp[21];
int minV = 987654321;
void find(int cur, int sum) {
	if (cur > N) return;
	if (sum >= B)
		minV = min(minV, sum);
	find(cur + 1, sum + emp[cur]);
	find(cur + 1, sum);
}
int main() {
	scanf("%d", &T);
	for (int tc = 1; tc <= T; tc++) {
		scanf("%d%d", &N, &B);
		minV = 987654321;
		for (int i = 0; i < N; i++) {
			scanf("%d", &emp[i]);
		}
		find(0, 0);
		printf("#%d %d\n", tc, minV - B);
	}
}