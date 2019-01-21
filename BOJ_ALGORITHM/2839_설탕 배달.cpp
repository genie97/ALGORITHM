#include<cstdio>
using namespace std;
int N;
int main() {
	scanf("%d", &N);
	int cnt = -1, res = 0;
	if (N % 5 == 0) {
		cnt = N / 5;
	}
	else {
		for (int i = N / 5; i >= 0; i--) {
			if ((N - 5 * i) % 3 == 0) {
				cnt = i;
				res = (N - 5 * i) / 3;
				cnt += res;
				break;
			}
		}
	}
	printf("%d\n", cnt);
}