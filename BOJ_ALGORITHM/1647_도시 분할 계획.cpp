#include<cstdio>
#include<algorithm>
using namespace std;
int N, M;
int parent[100002];
typedef pair<int, int> p;
pair<int, p> home[1000002];
int find(int v) {
	if (v == parent[v])
		return v;
	else
		return parent[v] = find(parent[v]);
}
void joint(int i) {
	int a = find(home[i].second.first);
	int b = find(home[i].second.second);
	if (a > b)
		swap(a, b);
	parent[b] = a;
}
int main() {
	int cnt = 0;
	scanf("%d%d", &N, &M);
	for (int i = 0; i < N; i++)
		parent[i] = i;
	for (int i = 0; i < M; i++) {
		int a, b, c;
		scanf("%d%d%d", &a, &b, &c);
		home[i] = make_pair(c, make_pair(a, b));
	}
	sort(home, home + M);
	int sum = 0, link = 0;
	for (int i = 0; i < M; i++) {
		if (find(home[i].second.first) != find(home[i].second.second)) {
			joint(i);
			sum += home[i].first;
			link++;
			if (link == N - 2)
				break;
		}
	}
	printf("%d\n", sum);
}
