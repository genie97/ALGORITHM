#include<cstdio>
#include<cstring>
using namespace std;
int T, N, cnt;
int pt[1005], visit[1005], finish[1005];

void dfs(int v) {
	visit[v] = true;
	int nv = pt[v];
	if (!visit[nv])
		dfs(nv);
	else {
		if (!finish[nv]) {
			for (int vt = nv; v != vt; vt = pt[vt])
				continue;
			cnt++;
		}
	}
	finish[v] = true;
}

int main() {
	scanf("%d", &T);
	while (T--) {
		scanf("%d", &N);
		memset(pt, 0, sizeof(pt));
		memset(visit, 0, sizeof(visit));
		memset(finish, 0, sizeof(finish));
		cnt = 0;

		for (int i = 1; i <= N; i++) {
			scanf("%d", &pt[i]);
		}
		for (int i = 1; i <= N; i++) {
			if (!visit[i])
				dfs(i);
		}
		printf("%d\n", cnt);
	}
}