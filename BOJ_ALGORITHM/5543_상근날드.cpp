#include<cstdio>
#include<algorithm>
using namespace std;
int main() {
	int burger[3];
	int drink[2];
	for (int i = 0; i < 3; i++) {
		scanf("%d", &burger[i]);
	}
	for (int i = 0; i < 2; i++) {
		scanf("%d", &drink[i]);
	}
	printf("%d\n", *min_element(burger, burger + 3) + *min_element(drink, drink + 2) - 50);
}
