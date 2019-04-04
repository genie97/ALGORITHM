#include<cstdio>
#include<algorithm>
#include<cmath>
using namespace std;
int L, A, B, C, D;
int main() {
	scanf("%d%d%d%d%d", &L, &A, &B, &C, &D);
	printf("%d\n", L - max((int)ceil((double) A / C), (int)ceil((double) B / D)));
}