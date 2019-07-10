#include<cstdio>
#include<cstring>
#include<cmath>
#include<algorithm>
#include<vector>
using namespace std;
int dx[4] = { 1,-1,0,0 }, dy[4] = { 0,0,1,-1 };
int root[12];
int N, ans=0;
int isPossible(int col, int root[12]) {
	for (int i = 1; i < col; i++) {
		if (root[i] == root[col])
			return false;
		if(abs(root[i]-root[col])==abs(i-col))
			return false;
	}
}
void dfs(int row, int root[12]) {
	if (row == N)
		ans++;
	else {
		for (int i = 1; i <= N; i++) {
			root[row + 1] = i;
			if (isPossible(row + 1, root))
				dfs(row + 1, root);
			else
				root[row + 1] = 0;
		}
	}
	root[row] = 0;
}

int main() {
	int T, cnt = 0;
	scanf("%d", &T);
	while (T--) {
		cnt++;
		scanf("%d", &N);
		for (int i = 1; i <= N; i++) {
			root[1] = i;
			dfs(1, root);
		}
		printf("#%d %d\n", cnt, ans);		
		ans = 0;
	}
}