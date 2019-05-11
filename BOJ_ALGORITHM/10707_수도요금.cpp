#include<cstdio>
#include<algorithm>
using namespace std;
int A, B, C, D, P;
int main() {
	int ans;
	scanf("%d%d%d%d%d", &A, &B, &C, &D, &P);
	if (P > C)
		B = B + (P - C)*D;
	ans = min(A*P, B);
	printf("%d", ans);
}