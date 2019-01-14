#include <cstdio>
using namespace std;
int ind[402][402];
int n, s, k;

int main() {
	scanf("%d %d", &n, &k);
	for (int i = 1; i <= k; i++) {
		int a, b;
		scanf("%d %d", &a, &b);
		ind[a][b] = -1;
	}
	for (int l = 1; l <= n; l++) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (ind[i][l] == -1 && ind[l][j] == -1)
					ind[i][j] = -1;
			}
		}
	}
	scanf("%d", &s);
	for (int i = 1; i <= s; i++) {
		int a, b;
		scanf("%d %d", &a, &b);
		if (ind[a][b] == -1)
			printf("-1\n");
		else if (ind[b][a] == -1)
			printf("1\n");
		else
			printf("0\n");
	}
}