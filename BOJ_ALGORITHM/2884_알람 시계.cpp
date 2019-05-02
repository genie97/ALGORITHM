#include<cstdio>
int H, M;
int main() {
	scanf("%d%d", &H, &M);
	if (M < 45) {
		M+= 15;
		H-= 1;
	}
	else
		M=M - 45;
	if (H < 0)
		H = 23;
	printf("%d %d", H, M);
}