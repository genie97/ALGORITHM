#include<cstdio>
#include<cmath>
#include<algorithm>
#include<tuple>
using namespace std;
int n;
typedef pair<double, double> p;
typedef pair<double, p> pr;
int parent[102];
p map[102];
pr edge[100002];

double cost(int i, int j) {
	return sqrt(pow(map[i].first - map[j].first, 2) + pow(map[i].second - map[j].second, 2));
}

int find(int v) {
	if (v == parent[v])
		return v;
	else
		return parent[v] = find(parent[v]);
}
void joint(int i) {
	int pu = find(edge[i].second.first);
	int pv = find(edge[i].second.second);
	if (pu > pv)
		swap(pu, pv);
	parent[pv] = pu;
}

int main() {
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%lf%lf", &map[i].first, &map[i].second);
		parent[i] = i;
	}
	int cnt = 0;
	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j < n; j++) {
			edge[cnt].second.first = i; //v1
			edge[cnt].second.second = j; //v2
			edge[cnt].first = cost(i, j); //dis
			cnt++;

		}
	}
	sort(edge, edge + cnt);
	double sum = 0;
	int link = 0;
	for (int i = 0; i < cnt; i++) {
		if (find(edge[i].second.first) != find(edge[i].second.second)) {
			sum += edge[i].first;
			joint(i);
			link++;
			if (link == n-1)
				break;
		}
	}
	printf("%.2lf\n", sum);
}