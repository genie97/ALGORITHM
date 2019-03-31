#include<cstdio>
using namespace std;
int zero_cnt = 0;
char ans[5] = { 'E', 'A','B','C','D'};
char res[3];
int main() {
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 4; j++) {
			int a = 0;
			scanf("%d", &a);
			if (a == 0)
				zero_cnt++;
		}
		res[i] = ans[zero_cnt];
		zero_cnt = 0;
	}
	for (int i = 0; i < 3; i++) {
		printf("%c\n", res[i]);
	}
}