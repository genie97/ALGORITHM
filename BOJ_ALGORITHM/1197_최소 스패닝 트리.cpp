#include<cstdio>
#include<algorithm>
using namespace std;
typedef pair<int, int> p;
pair <int, p> tree[100002];
int V, E;
int parent[10002];
int find(int v) {
	if (parent[v] == v)
		return v;
	else
		return parent[v] = find(parent[v]);
}
void joint(int i) {
	int a = find(tree[i].second.first);
	int b = find(tree[i].second.second);
	if (a > b)
		swap(a, b);
	parent[b] = a;
}
int main() {
	scanf("%d%d", &V, &E);
	for (int i = 0; i < V; i++)
		parent[i] = i;
	for (int i = 0; i < E; i++) {
		int a, b, c;
		scanf("%d%d%d", &a, &b, &c);
		tree[i] = make_pair(c, make_pair(a, b));
	}
	sort(tree, tree + E);
	long long sum = 0;
	int link = 0;
	for (int i = 0; i < E; i++) {
		if (find(tree[i].second.first) != find(tree[i].second.second)) {
			joint(i);
			link++;
			sum += tree[i].first;
		}
		if (link == V - 1)
			break;
	}
	printf("%lld\n", sum);
}