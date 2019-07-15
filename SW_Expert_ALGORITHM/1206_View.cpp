#include<cstdio>
#include<cstring>
#include<algorithm>
using namespace std;
int N, ans;
int build[1003] = { 0, };

int main() {
	for (int tc = 0; tc < 10; tc++) {
		scanf("%d", &N);
		memset(build, 0, sizeof(build));
		ans = 0;
		for (int i = 3; i < N + 3; i++) {
			scanf("%d", &build[i]);
		}
		for (int i = 3; i < N + 3; i++) {
			if (build[i - 1] < build[i] && build[i + 1] < build[i]) {
				int minV = min(min(build[i] - build[i - 2], build[i] - build[i + 2]), min(build[i] - build[i - 1], build[i] - build[i + 1]));
				if (minV > 0)
					ans += minV;
			}
		}
		printf("#%d %d\n", tc + 1, ans);
	}
}