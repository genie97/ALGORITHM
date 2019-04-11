#include<cstdio>
#include<vector>
using namespace std;
int map[101][101] = { 0, };
int N, link, cnt=0;
int main() {
	scanf("%d", &N);
	scanf("%d", &link);
	for (int i = 0; i < link; i++) {
		int a, b;
		scanf("%d%d", &a, &b);
		map[a][b] = 1;
		map[b][a] = 1;
	}
	for (int k = 1; k <= N; k++) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				if (map[i][k] == 1 && map[k][j] == 1)
					map[i][j] = 1;
			}
		}
	}
	for (int i = 1; i <= N; i++) {
		if (map[1][i] == 1)
			cnt++;
	}
	printf("%d\n", cnt);
}