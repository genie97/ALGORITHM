#include<cstdio>
#include<cstring>
using namespace std;

int T, n, cnt;
int st[100005];
int visit[100005], finish[100005];

void dfs(int v) {
	visit[v] = true;
	int nv = st[v];
	if (!visit[nv])
		dfs(nv);
	else {
		if (!finish[nv]) {
			for (int vt = nv; v != vt; vt = st[vt])
				cnt++;
			cnt++;
		}
	}
	finish[v] = true;
}
int main() {
	scanf("%d", &T);
	while (T--) {
		scanf("%d", &n);

		cnt = 0;
		memset(st, 0, sizeof(st));
		memset(visit, 0, sizeof(visit));
		memset(finish, 0, sizeof(finish));
		
		for (int i = 1; i <= n; i++) {
			scanf("%d", &st[i]);
		}
		for (int i = 1; i <= n; i++) {
			if (!visit[i])
				dfs(i);
		}
		printf("%d\n", n - cnt);
	}
}