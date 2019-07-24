#include<cstdio>
#include<cstring>
#include<algorithm>
using namespace std;
int T, N, ans;
int student[100001];
int main() {
	scanf("%d", &T);
	for (int tc = 1; tc <= T; tc++) {
		memset(student, 0, sizeof(student));
		ans = 0;
		scanf("%d", &N);
		for (int i = 0; i < N; i++) {
			int x;
			scanf("%d", &x);
			student[x] = student[x - 1] + 1;
			if (student[x] > ans)
				ans = student[x];
		}
		printf("#%d %d\n", tc, N - ans);
	}
}