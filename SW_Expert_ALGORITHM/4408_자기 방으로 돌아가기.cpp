#include<cstdio>
#include<cstring>
#include<algorithm>
using namespace std;
int T, N;
int room[201];
int main() {
	scanf("%d", &T);
	for (int tc = 1; tc <= T; tc++) {
		scanf("%d", &N);
		memset(room, 0, sizeof(room));
		for (int i = 0; i < N; i++) {
			int a, b;
			scanf("%d%d", &a, &b);
			if (a > b) swap(a, b);
			if (a % 2) a += 1;
			if (b % 2) b += 1;
			a /= 2; //방번호
			b /= 2; //방번호
			for (int i = a; i <= b; i++) {
				room[i]++;
			}
		}
		int ans = *max_element(room, room + 201);
		printf("#%d %d\n", tc, ans);
	}
}