#include<cstdio>
#include<cstring>
#include<algorithm>
#include<vector>
using namespace std;
typedef pair<long long, long long> pl;
typedef pair<int, int> pi;
int T, N, parent[1001];
double E;
pl loc[1001];
vector<pair<long long,pi>> island;
void cal() {
	int k = 0;
	for (int i = 0; i < N; i++) {
		for (int j = i+1; j < N; j++) {
			long long dis = (long long)pow(loc[i].first - loc[j].first, 2) + (long long)pow(loc[i].second - loc[j].second, 2);
			island.push_back({ dis, { i,j } });
		}
	}
	sort(island.begin(), island.end());
}
int find(int v) {
	if (parent[v] == v) return v;
	else return parent[v] = find(parent[v]);
}

void join(int index) {
	int a = find(island[index].second.first);
	int b = find(island[index].second.second);
	if (a > b)
		swap(a, b);
	parent[b] = a;
}

int main() {
	scanf("%d", &T);
	for (int tc = 1; tc <= T; tc++) {
		scanf("%d", &N);
		memset(loc, sizeof(loc), 0);
		island.clear();
		for (int i = 0; i < N; i++) {
			scanf("%lld", &loc[i].first);
			parent[i] = i;
		}
		for (int i = 0; i < N; i++)
			scanf("%lld", &loc[i].second);
		scanf("%lf", &E);
		cal(); //각 노드간의 거리
		long long res = 0;
		int link = 0;
		for (int i = 0; i < island.size(); i++) {
			if (find(island[i].second.first) != find(island[i].second.second)) {
				join(i);
				res += island[i].first;
			}
		}
		res = round(res*E);
		printf("#%d %lld\n", tc, res);
	}
}