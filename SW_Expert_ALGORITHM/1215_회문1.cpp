#include<cstdio>
using namespace std;
char map[8][8];
int Xline(int len, int x, int y) {
	for (int i = 0; i < len / 2; i++)
		if (map[x][y + i] != map[x][y + len - 1 - i])
			return 0;
	return 1;
}

int Yline(int len, int x, int y) {
	for (int i = 0; i < len / 2; i++)
		if (map[x + i][y] != map[x + len - 1 - i][y])
			return 0;
	return 1;
}

int main() {
	for (int tc = 1; tc <= 10; tc++) {
		int len, cnt=0;
		scanf("%d", &len);
		for (int i = 0; i < 8; i++) {
			scanf("%s", map[i]);
		}
		for (int i = 0; i <= 8 - len; i++) {
			for (int j = 0; j < 8; j++) {
				cnt += Yline(len, i, j);
			}
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j <= 8 - len; j++) {
				cnt += Xline(len, i, j);
			}
		}
		printf("#%d %d\n", tc, cnt);
	}
}