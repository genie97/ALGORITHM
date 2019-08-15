#include<cstdio>
#include<cstring>
#include<algorithm>
using namespace std;
int T, N, K;
long long home[100001];
long long dif[100001];

int main() {
	scanf("%d", &T);
	for (int tc = 1; tc <= T; tc++) {
		scanf("%d%d", &N, &K);
		int res = 0;
		memset(home, 0, sizeof(home));
		memset(dif, 0, sizeof(dif));
		for (int i = 0; i < N; i++) {
			scanf("%lld", &home[i]);
			if (i != 0)
				dif[i - 1] = home[i] - home[i - 1];
		}
		sort(dif, dif + N - 1);
		for (int i = 0; i < N - K; i++) {
			res += dif[i];
		}
		printf("#%d %d\n", tc, res);
	}
}

/* MST·Î ±¸Çö(354ms)
#include<cstdio>
#include<cstring>
#include<algorithm>
using namespace std;
typedef pair<int, int> p;
pair<int, p> home[100001];
int T, N, K;
int parent[100001];
int find(int v) {
	if (parent[v] == v) return v;
	else return parent[v] = find(parent[v]);
}
void joint(int a, int b) {
	a = find(a);
	b = find(b);
	if (a > b)
		swap(a, b);
	parent[b] = a;
}
int main() {
	scanf("%d", &T);
	for (int tc = 1; tc <= T; tc++) {
		scanf("%d%d", &N, &K);
		memset(home, sizeof(home), 0);
		int pre, cur;
		scanf("%d", &pre);
		for (int i = 1; i < N; i++) {
			scanf("%d", &cur);
			home[i - 1] = { cur - pre, {i - 1, i } };
			pre = cur;	
		}
		for(int i=0;i<N;i++)
			parent[i] = i;
		if (N <= K) {
			printf("#%d 0\n", tc);
			continue;
		}
		sort(home, home + N - 1);
		int res = 0, link = 0;
		for (int i = 0; i < N; i++) {
			if (find(home[i].second.first)!=find(home[i].second.second)) {
				joint(home[i].second.first, home[i].second.second);
				res += home[i].first;
				if (++link == N - K) break;
			}
		}
		printf("#%d %d\n", tc, res);
	}
}*/
