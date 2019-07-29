#include<cstdio>
#include<queue>
using namespace std;
int N, K;
int visit[100001];
int main() {
	scanf("%d%d", &N, &K);
	queue<int> q;
	int maxV = 0, cnt = 0;
	q.push(N);
	visit[N] = 1;
	while (1) {
		int size = q.size();
		for (int i = 0; i < size; i++) {
			int cur = q.front();
			q.pop();
			if (cur == K) {
				printf("%d\n", cnt);
				return 0;
			}
			if (cur > 0 && !visit[cur - 1]) {
				q.push(cur - 1);
				visit[cur - 1] = 1;
			}
			if (cur < 100000 && !visit[cur + 1]) {
				q.push(cur + 1);
				visit[cur + 1] = 1;
			}
			if (cur * 2 <= 100000 && !visit[cur * 2]) {
				q.push(cur * 2);
				visit[cur * 2] = 1;
			}
		}
		cnt++;
	}
}