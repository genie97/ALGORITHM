#include<cstdio>
int en[6] = {500, 100, 50, 10, 5, 1 };
int main() {
	int m;
	scanf("%d", &m);
	int c = 1000 - m;
	int sum = 0;
	for (int i = 0; i < 6; i++) {
		while (c - en[i] >= 0) {
			c -= en[i];
			sum++;
			if (c == 0)
				break;
		}
	}
	printf("%d\n", sum);
}