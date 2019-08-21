#include<cstdio>
#include<cstring>
int N, flag, cnt;
char sen[1000], sea[10];
int main() {
	for (int tc = 1; tc <= 10; tc++) {
		scanf("%d", &N);
		scanf("%s", sea);
		scanf("%s", sen);
		cnt = 0;
		for (int i = 0; i < strlen(sen); i++) {
			flag = 1;
			for (int j = 0; j < strlen(sea); j++) {
				if (sen[i + j] != sea[j])
					flag = 0;
			}
			if (flag) cnt++;
		}
		printf("#%d %d\n", N, cnt);
	}
}