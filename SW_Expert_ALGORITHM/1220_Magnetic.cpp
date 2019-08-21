#include<cstdio>
#include<vector>
#include<algorithm>
using namespace std;
int N;
vector<pair<int, int>> S;
int main() {
	for (int tc = 1; tc <= 10; tc++) {
		int table[100][100], ans = 0;
		S.clear();
		scanf("%d", &N);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				scanf("%d", &table[i][j]);
				if (table[i][j] == 1) S.push_back({ i,j });
			}
		}
		for (int i = 0; i < S.size(); i++) {
			int x = S[i].first;
			int y = S[i].second;
			for (int j = 1; x + j < N; j++) {
				if (table[x + j][y] == 0) continue;
				else if (table[x + j][y] == 1) break;
				else if (table[x + j][y] == 2) { ans++; break; }
			}
		}
		printf("#%d %d\n", tc, ans);
	}
}