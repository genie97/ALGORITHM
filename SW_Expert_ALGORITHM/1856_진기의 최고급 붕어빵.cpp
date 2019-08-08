#include<cstdio>
#include<algorithm>
#include<queue>
using namespace std;
int T, N, M, K;
priority_queue<int> pq;

bool solve() {
	int nowT = 0, finNum = 0;
	while (!pq.empty()) {
		while (-pq.top() < nowT + M) {
			if (finNum == 0)
				return false;
			finNum -= 1;
			pq.pop();
			if (pq.empty())
				return true;
		}
		nowT += M;
		finNum += K;
	}
}
int main() {
	scanf("%d", &T);
	for (int tc = 1; tc <= T; tc++) {
		priority_queue<int> temp;
		pq = temp;
		scanf("%d%d%d", &N, &M, &K);
		for (int i = 0; i < N; i++) {
			int a;
			scanf("%d", &a);
			pq.push(-a);
		}
		printf("#%d %s\n", tc, solve()?"Possible":"Impossible");
	}
}