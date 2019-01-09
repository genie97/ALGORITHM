#include<cstdio>
#include<queue>
using namespace std;

int A, B, N, M, cnt = 0;
int visit[100001];
queue<int> q;

int main() {
	scanf("%d%d%d%d", &A, &B, &N, &M);
	q.push(N);
	visit[N] = 1;
	while (!q.empty()) {
		int size = q.size();
		while (size--) {
			int pos = q.front();
			q.pop();
			int moveCase[8] = { 1,-1,A,-A,B,-B,pos*A,pos*B };
			if (pos == M) {
				printf("%d\n", cnt);
				return 0;
			}
			for (int i = 0; i < 8; i++) {
				int next;
				if (i < 6)
					next = pos + moveCase[i];
				else
					next = moveCase[i];
				if (next >= 0 && next <= 100000 && !visit[next]) {
					visit[next] = 1;
					q.push(next);
				}
			}
		}
		cnt++;
	}
}