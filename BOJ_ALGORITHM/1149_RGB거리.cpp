#include<cstdio>
#include<algorithm>
using namespace std;
int N;
int color[1002][3], ans[1002][3];
int main() {
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < 3; j++) {
			scanf("%d", &color[i][j]);
		}
	}
	ans[0][0] = color[0][0];
	ans[0][1] = color[0][1];
	ans[0][2] = color[0][2];
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < 3; j++) {
			if (j == 0) {
				ans[i][j] = color[i][j] + min(ans[i - 1][1], ans[i - 1][2]);
			}
			else if (j == 1) {
				ans[i][j] = color[i][j] + min(ans[i - 1][0], ans[i - 1][2]);
			}
			else if (j == 2) {
				ans[i][j] = color[i][j] + min(ans[i - 1][0], ans[i - 1][1]);
			}
		}
	}
	printf("%d\n", *min_element(ans[N - 1], ans[N - 1] + 3));
}