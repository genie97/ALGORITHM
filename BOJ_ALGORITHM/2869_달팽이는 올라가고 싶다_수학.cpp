#include<cstdio>
#include<cmath>
int main() {
	int A, B, V;
	scanf("%d%d%d", &A, &B, &V);
	int ans = ceil((double)(V - A) / (double)(A - B)) + 1;
	printf("%d\n",ans);
}
