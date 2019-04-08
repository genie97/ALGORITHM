#include<iostream>
#include<algorithm>
#include<stack>
#include<vector>
using namespace std;
int k, res[6];
vector<int> S;
void dfs(int cnt, int v) {
	if (cnt == 6) {
		for (int i = 0; i < 6; i++) {
			printf("%d ", res[i]);
		}
		printf("\n");
		return;
	}
	for (int i = v; i < k; i++) {
		res[cnt] = S[i];
		dfs(cnt + 1, i + 1);
	}
}
int main() {
	while (1) {
		scanf("%d", &k);
		if (k == 0)
			break;
		for (int i = 0; i < k; i++) {
			int a;
			scanf("%d", &a);
			S.push_back(a);
		}
		dfs(0, 0);
		printf("\n");
		fill(res, res + 6, 0);
		S.clear();
	}
}