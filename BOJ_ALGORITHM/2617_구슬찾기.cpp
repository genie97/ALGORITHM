#include<cstdio>
#include<cmath>
using namespace std;
int inDegree[100], outDegree[100];
int N, M, cnt=0;
int main() {
	scanf("%d%d", &N, &M);
	for (int i = 0; i < M; i++) {
		int a, b;
		scanf("%d%d", &a, &b);
		outDegree[a]++;
		inDegree[b]++;
	}
	for (int i = 1; i <= N; i++) {
		//if (abs(inDegree[i] - outDegree[i]) > (int)N / 2+1)
			//cnt++;
		printf("in: %d out: %d\n", inDegree[i], outDegree[i]);
	}
	printf("%d\n", cnt);
}