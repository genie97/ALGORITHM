#include<cstdio>
int X, N, A[10000], ans[10000];
int main() {
	scanf("%d%d", &N, &X);
	for (int i = 0; i < N; i++)
		scanf("%d", &A[i]);
	int j = 0;
	for (int i = 0; i < N; i++) {
		if (A[i] < X) {
			ans[j] = A[i];
			j++;
		}
	}
	for (int i = 0; i < j; i++) {
		printf("%d ", ans[i]);
	}
}
