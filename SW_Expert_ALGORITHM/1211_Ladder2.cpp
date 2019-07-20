#include<cstdio>
#include<cstring>
#include<vector>
#include<climits>
using namespace std;
int map[101][101];
int N, cnt, minV;
vector<int>startY, res;
void moving(int x, int y) {
	cnt++;
	if (x == 99)
		return;
	map[x][y] = 0;
	if (y != 0 && map[x][y - 1])
		moving(x, y - 1);
	else if (y != 99 && map[x][y + 1])
		moving(x, y + 1);
	else
		moving(x + 1, y);
	map[x][y] = 1;
}
int main() {
	for (int tc = 0; tc < 10; tc++) {
		minV = INT_MAX;
		memset(map, 0, sizeof(map));
		startY.clear();
		res.clear();
		scanf("%d", &N);
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				scanf("%1d", &map[i][j]);
				if (i == 0 && map[i][j] == 1)
					startY.push_back(j);
			}
		}
		for (int i = 0; i < startY.size(); i++) {
			cnt = 0;
			moving(0, startY[i]);
			if (cnt > minV)
				continue;
			else if (cnt <= minV) {
				res.clear();
				minV = cnt;
				res.push_back(startY[i]);
			}
		}
		int size = res.size();
		printf("#%d %d\n", N, res[size - 1]);
	}
}