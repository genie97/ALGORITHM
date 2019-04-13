#include<cstdio>
#include<cstring>
#include<algorithm>
#include<queue>
#include<vector>
using namespace std;
struct shark {
	int x;
	int y; //좌표
	int size; //크기
	int fish; //먹은 물고기
	int time; //시간
};
int N, map[21][21];
int dx[4] = {1,-1,0,0 }, dy[4] = { 0, 0,1,-1 };
bool visit[21][21];

queue<shark> q;
vector<shark> vt;

bool comp(shark a, shark b) {
	if (a.time <= b.time) {
		if (a.time == b.time) {
			if (a.x <= b.x) {
				if (a.x == b.x) {
					if (a.y < b.y) {
						return true;
					}
					return false;
				}
				return true;
			}
			return false;
		}
		return true;
	}
	return false;
}
int main() {
	scanf("%d", &N);
	shark s;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &map[i][j]);
			if (map[i][j] == 9) {
				map[i][j] = 0;
				s = { i,j,2,0,0 };
			}
		}
	}
	int ans = 0;
	while (1) {
		vt.clear();
		memset(visit, 0, sizeof(visit));
		visit[s.x][s.y] = true;
		q.push(s);
		while (!q.empty()) {
			int x = q.front().x;
			int y = q.front().y;
			int size = q.front().size;
			int fish = q.front().fish;
			int time = q.front().time;
			q.pop();
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;
				if (!visit[nx][ny]) {
					if (map[nx][ny] == 0 || map[nx][ny] == size) {
						visit[nx][ny] = true;
						q.push({ nx, ny, size, fish, time + 1 });
					}
					else if (map[nx][ny] < size) {
						visit[nx][ny] = true;
						vt.push_back({ nx, ny, size, fish + 1, time + 1 });
					}
				}
			}
		}
		if (vt.size() == 0)
			break;
		sort(vt.begin(), vt.end(),comp);
		if (vt[0].size == vt[0].fish) {
			vt[0].size++;
			vt[0].fish = 0;
		}
		map[vt[0].x][vt[0].y] = 0;
		ans += vt[0].time;
		s = { vt[0].x, vt[0].y, vt[0].size, vt[0].fish, 0};
	}
	printf("%d\n", ans);
}