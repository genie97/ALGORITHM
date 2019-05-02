#include<cstdio>
int visit[31];
int main() {
	for (int i = 0; i < 28; i++) {
		int a;
		scanf("%d",&a);
		visit[a] = 1;
	}
	for (int i = 1; i <= 30; i++) {
		if (visit[i] != 1)
			printf("%d\n", i);
	}
}