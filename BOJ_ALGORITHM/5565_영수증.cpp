#include<cstdio>
int sum = 0;
int main() {
	scanf("%d", &sum);
	for (int i = 0; i < 9; i++) {
		int a;
		scanf("%d", &a);
		sum -= a;
	}
	printf("%d\n", sum);
}