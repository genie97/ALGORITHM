#include<cstdio>
#include<algorithm>
using namespace std;
int N;
int line[1000001];
int main() {
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		int a;
		scanf("%d", &a);
		line[a] = line[a - 1] + 1;
	}
	int ans = *max_element(line, line + N);
	printf("%d\n", N - ans);
}