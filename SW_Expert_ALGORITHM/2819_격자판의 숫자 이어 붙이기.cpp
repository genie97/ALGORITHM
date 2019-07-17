#include<cstdio>
#include<cstring>
#include<algorithm>
#include<set>
using namespace std;
int T;
int num[7];
int map[4][4] = { 0, };
int dx[4] = { 1,-1,0,0 }, dy[4] = { 0,0,1,-1 };
set<int> st;
void dfs(int x, int y, int count) {
	if (count == 7) {
		int ans = 0;
		for (int i = 0; i < 7; i++) {
			ans = ans * 10 + num[i];
		}
		st.insert(ans);
		return;
	}
	num[count] = map[x][y];
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4)
			continue;
		dfs(nx, ny, count+1);
	}
}
int main() {
	scanf("%d", &T);
	for (int tc = 0; tc < T; tc++) {
		st.clear();
		memset(map, 0, sizeof(map));
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				scanf("%1d", &map[i][j]);
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				dfs(i, j, 0);
			}
		}
		printf("#%d %d\n", tc+1, st.size());
	}
}
