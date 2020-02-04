# include<cstdio>
# include<cmath>
# include<cstring>
using namespace std;

int count[100001];
int main() {
	int T = 0;
	scanf("%d", &T);
	for (int tc = 1; tc <= T; tc++) {
		memset(count, 0, sizeof(count));
		int N = 0;
		scanf("%d", &N);
		int min = 987654321;
		for (int i = 0; i<N; i++) {
			int a;
			scanf("%d", &a);
			count[(int)abs(a)]++;
			if (min >(int)abs(a)) {
				min = (int)abs(a);
			}
		}
		printf("#%d %d %d", tc, min, count[min]);
	}
}
