/*#include<cstdio>
#include<cstring>
using namespace std;
int T, N, D;
int city[300001];
int main() {
	scanf("%d", &T);
	for (int tc = 1; tc <= T; tc++) {
		scanf("%d%d", &N, &D);
		memset(city, 0, sizeof(city));
		for (int i = 0; i < N; i++)
			scanf("%d", &city[i]);
		int curD=0, res=0;
		for (int i = 0; i < N; i++) {
			curD++;
			if (city[i] == 1) curD = 0;
			if (curD == D) if (city[i] == 0) curD = 0, city[i] = 1, res++;
		}
		printf("#%d %d\n", tc, res);
	}
}
*/