#include<cstdio>
int main() {
	int T;
	scanf("%d", &T);
	while (T--) {
		int r, e, c;
		scanf("%d%d%d", &r, &e, &c);
		if (e - c > r)
			printf("advertise\n");
		else if (e - c == r)
			printf("does not matter\n");
		else
			printf("do not advertise\n");
	}
}