#include<stdio.h>
#define INF 987654321
#define minimum(a,b) (a<b ? a : b)

int V, E;
int v1, v2, dis;
int min = INF;
int D[403][403];
void floyd() {
	for (int i = 1; i <= V; i++) {
		for (int j = 1; j <= V; j++) {
			for (int k = 1; k <= V; k++) {
				if (D[i][j] > D[i][k] + D[k][j]) {
					D[i][j] = D[i][k] + D[k][j];
					if (D[j][i] != INF)
						min = minimum(min, D[i][j] + D[j][i]);
				}
			}
		}
	}
}
int main() {
	scanf("%d %d", &V, &E);
	for (int i = 1; i <= V; i++) {
		for (int j = 1; j <= V; j++) {
			if (i == j) {
				D[i][j] = 0;
			}
			else {
				D[i][j] = INF;
			}
		}
	}
	for (int i = 1; i <= E; i++) {
		scanf("%d %d %d", &v1, &v2, &dis);
		D[v1][v2] = dis;
		if (D[v2][v1] != INF)
			min = minimum(min, D[v1][v2] + D[v2][v1]);
	}
	floyd();
	if (min == INF)
		printf("-1");
	else
		printf("%d\n", min);
}