#include<cstdio>
int s;
int main() {
	scanf("%d", &s);
	if (90 <= s && s <= 100)
		printf("A");
	else if (80 <= s && s < 90)
		printf("B");
	else if (70 <= s && s < 80)
		printf("C");
	else if (60 <= s && s < 70)
		printf("D");
	else
		printf("F");
}