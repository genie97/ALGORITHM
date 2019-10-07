#include<cstdio>
#include<queue>
using namespace std;
int main() {
	int N;
	scanf("%d", &N);
	queue<long long> q;
	int cnt = -1;

	if (N <= 10) {
		printf("%d\n", N);
	}
	else if (N >= 1023)
		printf("-1\n");
	else {
		for (int i = 0; i < 10; i++) {
			q.push(i);
			cnt++;
		}
		while (cnt < N) {
			long long num = q.front();
			q.pop();
			long long tmp = num % 10;

			for (int j = 0; j < tmp; j++) {
				long long res = num * 10 + j;
				q.push(res);
				cnt++;
				if (cnt == N) {
					printf("%lld\n", res);
					break;
				}
			}
		}
	}
}