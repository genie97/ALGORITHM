#include<cstdio>
#include<cstring>
#include<vector>
#include<climits>
using namespace std;
int map[101][101];
int N, cnt, minV, res;
int check;
vector<int>startY;
void moving(int x, int y) {
	if (map[x][y] == 2)
		check = 1;
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
		scanf("%d", &N);
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				scanf("%1d", &map[i][j]);
				if (i == 0 && map[i][j] == 1)
					startY.push_back(j);
			}
		}
		for (int i = 0; i < startY.size(); i++) {
			check = 0;
			moving(0, startY[i]);
			if (check)
				res = startY[i];
		}
		printf("#%d %d\n", N, res);
	}
}