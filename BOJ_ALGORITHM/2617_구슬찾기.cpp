/*#include<cstdio>
#include<vector>
#include<cstring>
using namespace std;
vector<int> c_node[100], p_node[100], inDegree[100], outDegree[100];
int c_visit[100],p_visit[100], c_numA[100], p_numA[100];
int N, M, cnt = 0, c_num = 0, p_num = 0;
int c_dfs(int v) {
	c_visit[v] = 1;
	for (int i = 0; i < c_node[v].size(); i++) {
		if (!c_visit[c_node[v][i]]) {
			c_visit[c_node[v][i]] = 1;
			c_num++;
			c_dfs(c_node[v][i]);
		}
	}
	return c_num;
}
int p_dfs(int v) {
	p_visit[v] = 1;
	for (int i = 0; i < p_node[v].size(); i++) {
		if (!p_visit[p_node[v][i]]) {
			p_visit[p_node[v][i]] = 1;
			p_num++;
			p_dfs(p_node[v][i]);
		}
	}
	return p_num;
}
int main() {
	scanf("%d%d", &N, &M);
	for (int i = 0; i < M; i++) {
		int a, b;
		scanf("%d%d", &a, &b);
		c_node[a].push_back(b);
		p_node[b].push_back(a);
	}
	for (int i = 1; i <= N; i++) {
		memset(c_visit, 0, sizeof(c_visit));
		c_num = 0;
		c_numA[i]=c_dfs(i);
		memset(p_visit, 0, sizeof(p_visit));
		p_num = 0;
		p_numA[i]=p_dfs(i);
	}
	for (int i = 1; i <= N; i++) {
		if (p_numA[i] >= (int)(N+1)/2 || c_numA[i] >= (int)(N+1)/2) {
			cnt++;
		}
	}
	printf("%d\n", cnt);
}*/
#include<cstdio>
#include<cmath>
using namespace std;
int inDegree[100], outDegree[100];
int N, M, cnt = 0;
int main() {
	scanf("%d%d", &N, &M);
	for (int i = 0; i < M; i++) {
		int a, b;
		scanf("%d%d", &a, &b);
		outDegree[a]++;
		inDegree[b]++;
	}
	for (int i = 1; i <= N; i++) {
		if (inDegree[i] >= (N+1) / 2 || outDegree[i] >= (N + 1) / 2)
			cnt++;
	}
	printf("%d\n", cnt);
}