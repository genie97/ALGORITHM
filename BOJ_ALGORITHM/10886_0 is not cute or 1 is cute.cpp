#include<cstdio>
int main() {
	int N, cnt=0;
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		int a;
		scanf("%d", &a);
		if (a == 0)
			cnt++;
	}
	if (cnt > N / 2)
		printf("Junhee is not cute!\n");
	else
		printf("Junhee is cute!\n");
}
