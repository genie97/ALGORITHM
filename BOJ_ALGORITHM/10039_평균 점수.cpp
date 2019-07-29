#include<cstdio>
using namespace std;
int sum = 0;
int a;
int main() {
	for (int i = 0; i < 5; i++) {
		scanf("%d", &a);
		if (a < 40)
			a = 40;
		sum += a;
	}
	printf("%d\n", sum / 5);
}