#include<cstdio>
int T, D, H, M, cnt = 0;
int main() {
	scanf("%d", &T);
	while (T--) {
		cnt++;
		scanf("%d%d%d", &D, &H, &M);
		int first = 11 * 24 * 60 + 11 * 60 + 11; //기준 오전 11일 11시 11분
		int total = D * 24 * 60 + H * 60 + M;
		if (total- first >= 0)
			printf("#%d %d\n", cnt, total-first);
		else
			printf("#%d -1\n", cnt);
	}
}