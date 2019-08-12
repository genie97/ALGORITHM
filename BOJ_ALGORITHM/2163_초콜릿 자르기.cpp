#include<cstdio>
int main() {
	int N, M;
	scanf("%d %d", &N, &M);
	//M*N 크기일때, M*1사이즈를 자르는데 M-1번
	// M-1사이즈가 N개 있으므로 M(N-1)
	// M - 1 + MN - M = MN-1
	printf("%d\n", M*N - 1);
}
