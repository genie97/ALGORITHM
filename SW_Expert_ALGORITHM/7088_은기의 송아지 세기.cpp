#include<cstdio>
using namespace std;
int T, N, Q;
int QAcnt[4][100001] = { 0, };
int cow[100001];
int main() {
	scanf("%d", &T);
	for (int tc = 1; tc <= T; tc++) {
		scanf("%d%d", &N, &Q);
		int a = 0, b = 0, c = 0;
		for (int i = 1; i <= N; i++) {
			scanf("%d", &cow[i]);
			if (cow[i] == 1)
				a++;
			if (cow[i] == 2)
				b++;
			if (cow[i] == 3)
				c++;
			QAcnt[1][i] = a, QAcnt[2][i] = b, QAcnt[3][i] = c;
		}
		printf("#%d\n", tc);
		for (int i = 0; i < Q; i++) {
			int L, R;
			scanf("%d%d", &L, &R);
			printf("%d %d %d\n", QAcnt[1][R] - QAcnt[1][L - 1], QAcnt[2][R] - QAcnt[2][L - 1], QAcnt[3][R] - QAcnt[3][L - 1]);
		}
	}
}