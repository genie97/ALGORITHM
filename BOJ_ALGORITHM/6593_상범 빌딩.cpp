#include<cstdio>
#include<algorithm>
#include<string>
#include<cstring>
#include<iostream>
#include<queue>
using namespace std;
int L, R, C, dx[6] = { 1,-1,0,0,0,0 }, dy[6] = { 0,0,1,-1,0,0 }, dz[6] = { 0,0,0,0,1,-1 };
string str;
bool visit[32][32][32];
int map[32][32][32] = {0,};
pair<int, pair<int, int>> start, fin;
int bfs() {
	queue<pair<pair<int, int>, pair<int, int>>>q;
	q.push({ {start.first, start.second.first},{start.second.second,0} });
	memset(visit, false, sizeof(visit));
	visit[start.first][start.second.first][start.second.second] = true;
	
	while (!q.empty()) {
		int z = q.front().first.first;
		int y = q.front().first.second;
		int x = q.front().second.first;
		int cnt = q.front().second.second;
		q.pop();
		if (z == fin.first&&y == fin.second.first&&x == fin.second.second)
			return cnt;
		for (int i = 0; i < 6; i++) {
			int nz = z + dz[i];
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (nz < 0 || nz >= L || ny < 0 || ny >= R || nx < 0 || nx >= C)
				continue;
			if (map[nz][ny][nx]==0 && !visit[nz][ny][nx]) {
				visit[nz][ny][nx] = true;
				q.push({ { nz, ny }, { nx, cnt + 1} });
			}
		}
	}
	return -1;
}
int main() {
	while (1) {
		scanf("%d%d%d", &L, &R, &C);
		if (!L && !R && !C)
			break;
		for (int f = 0; f < L; f++) {
			for (int r = 0; r < R; r++) {
				cin >> str;
				for (int i = 0; i < str.length(); i++) {
					if (str[i] == 'S') {
						start.first = f;
						start.second.first = r;
						start.second.second = i;
					}
					else if (str[i] == '#')
						map[f][r][i] = 1;
					else if (str[i] == 'E') {
						fin.first = f;
						fin.second.first = r;
						fin.second.second = i;
					}
				}
			}
		}
		int res = bfs();
		if (res == -1)
			printf("Trapped!\n");
		else
			printf("Escaped in %d minute(s).\n", res);
		memset(map, 0, sizeof(map));
	}
}