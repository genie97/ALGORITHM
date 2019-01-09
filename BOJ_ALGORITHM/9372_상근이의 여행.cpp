#include<cstdio>
#include<utility>
using namespace std;
int T;
int N, M;
int v1, v2;
int main() {
	int cnt = 0;
	scanf("%d", &T);
	while (T--) {
		scanf("%d %d", &N, &M);
		while (M--)
			scanf("%d %d", &v1, &v2);
		printf("%d\n", N - 1);
	}
}